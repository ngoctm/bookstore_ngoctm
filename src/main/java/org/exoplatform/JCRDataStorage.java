/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.entity.Book;
import org.exoplatform.exception.BookNotFoundException;
import org.exoplatform.exception.DuplicateBookException;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.jcr.util.IdGenerator;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.utils.PropertyReader;
import org.exoplatform.utils.Utils;

/**
 * Created by The eXo Platform SAS
 * Author : phong tran
 *          phongth@exoplatform.com
 * Jun 20, 2011  
 */
public class JCRDataStorage {
  private static final Log log = ExoLogger.getLogger(JCRDataStorage.class);
  public static final String DEFAULT_PARENT_PATH = "/bookStore"; 
  
  private RepositoryService repoService;
  
  public JCRDataStorage() {
  }
  
  void setRepositoryService(RepositoryService repoService) {
    this.repoService = repoService;
  }
  
  public void init() {
    SessionProvider sProvider = SessionProvider.createSystemProvider();
    Node node = null;
    try {
      node = getNodeByPath(DEFAULT_PARENT_PATH, sProvider);
    } catch (PathNotFoundException e) {
      // If the path not exist then create new path
      try {
        node = getNodeByPath("/", sProvider);
        node.addNode("bookStore", BookNodeTypes.EXO_BOOK_STORE);
        node.getSession().save();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } catch (Exception e) {
      log.error("Failed to init BookStore jcr node's path", e);
    }  finally {
      sProvider.close();
    }
  }
  
  private Node getNodeByPath(String nodePath, SessionProvider sessionProvider) throws Exception {
    return (Node) getSession(sessionProvider).getItem(nodePath);
  }
  
  private Session getSession(SessionProvider sprovider) throws Exception {
    ManageableRepository currentRepo = repoService.getCurrentRepository();
    return sprovider.getSession(currentRepo.getConfiguration().getDefaultWorkspaceName(), currentRepo);
  }

  private Book createBookByNode(Node bookNode) throws Exception {
    if (bookNode == null) {
      return null;
    }
      
    Book bookNew = new Book();
    bookNew.setId(bookNode.getName());
    
    PropertyReader reader = new PropertyReader(bookNode);
    bookNew.setName(reader.string(BookNodeTypes.EXP_BOOK_NAME));
    bookNew.setCategory(Utils.bookCategoryStringToEnum(reader.string(BookNodeTypes.EXP_BOOK_CATEGORY)));
    bookNew.setContent(reader.string(BookNodeTypes.EXP_BOOK_CONTENT));
    return bookNew;
  }
  
  public Book getBook(String id) {
    SessionProvider sProvider = SessionProvider.createSystemProvider();
    try {
      Node node = getNodeByPath(DEFAULT_PARENT_PATH + "/" + id, sProvider);
      return createBookByNode(node);
    } catch (PathNotFoundException e) {
      return null;
    } catch (Exception e) {
      log.error("Failed to get book by id", e);
      return null;
    } finally {
      sProvider.close();
    }
  }
  
  public Book addBook(Book book) throws DuplicateBookException {
    SessionProvider sProvider = SessionProvider.createSystemProvider();    
    
    String nodeId = IdGenerator.generate();
    book.setId(nodeId);
    
    try {
      Node parentNode = getNodeByPath(DEFAULT_PARENT_PATH, sProvider);
      Node bookNode = parentNode.addNode(nodeId, BookNodeTypes.EXO_BOOK);
      bookNode.setProperty(BookNodeTypes.EXP_BOOK_NAME, book.getName());
      bookNode.setProperty(BookNodeTypes.EXP_BOOK_CATEGORY, Utils.bookCategoryEnumToString(book.getCategory()));
      bookNode.setProperty(BookNodeTypes.EXP_BOOK_CONTENT, book.getContent());
      
      parentNode.getSession().save();
      return book;
    } catch (PathNotFoundException e) {
      return null;
    } catch (Exception e) {
      log.error("Failed to add book", e);
      return null;
    } finally {
      sProvider.close();
    }
  }  
}
