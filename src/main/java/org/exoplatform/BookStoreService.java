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

import java.io.Serializable;
import java.util.List;

import org.exoplatform.entity.Book;
import org.exoplatform.entity.Book.CATEGORY;
import org.exoplatform.exception.BookNotFoundException;
import org.exoplatform.exception.DuplicateBookException;
import org.exoplatform.services.cache.CacheService;
import org.exoplatform.services.cache.ExoCache;
import org.exoplatform.services.jcr.RepositoryService;
import org.picocontainer.Startable;

/**
 * Created by The eXo Platform SAS 
 * Author : Lai Trung Hieu
 *          hieu.lai@exoplatform.com 
 * 7 Jun 2011
 */
public class BookStoreService implements Startable {
  private ExoCache<Serializable, Book> cache;
  private JCRDataStorage jcrDataStorage = new JCRDataStorage();

  public BookStoreService(RepositoryService rservice, CacheService cacheService) {
    this.cache = cacheService.getCacheInstance(getClass().getName());
    jcrDataStorage.setRepositoryService(rservice);
  }

  public void start() {
    jcrDataStorage.init();    
  }

  public void stop() {
  }
  

  public Book getBook(String id) {
    Book book = cache.get(id);
    if (book != null) {
      return book;
    }
    return jcrDataStorage.getBook(id);
  }

  public Book addBook(String bookName, CATEGORY category, String content) throws DuplicateBookException {
    Book book = new Book(bookName, category, content);
    return jcrDataStorage.addBook(book);
  }  
  
}
