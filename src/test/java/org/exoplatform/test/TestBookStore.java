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
package org.exoplatform.test;

import java.util.List;

import junit.framework.TestCase;

import org.exoplatform.BookStoreService;
import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.entity.Book;
import org.exoplatform.entity.Book.CATEGORY;
import org.exoplatform.exception.BookNotFoundException;
import org.exoplatform.exception.DuplicateBookException;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieu.lai@exoplatform.com
 * 7 Jun 2011  
 */
public class TestBookStore extends TestCase {
  protected static BookStoreService service;

  protected static StandaloneContainer container;
  
  static {
    initContainer();
  }

  private static void initContainer() {
    try {
      String jcrConf = TestBookStore.class.getResource("/conf/portal/test-configuration.xml").toString();
      StandaloneContainer.addConfigurationURL(jcrConf);
      
      container = StandaloneContainer.getInstance();
      service = (BookStoreService) container.getComponentInstanceOfType(BookStoreService.class);
    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize standalone container: " + e.getMessage(), e);
    }
  }
  
  @Override
  protected void setUp() throws Exception {
    //service.deleteAll();
  }

  public void testAddAndGetBook() throws DuplicateBookException {
    String name = "Doraemon";
    CATEGORY category = CATEGORY.MANGA;
    String content = "Nobita & Doreamon";
    
    Book book = service.addBook(name, category, content);
    Book addedBook = service.getBook(book.getId());
    
    assertNotNull(addedBook);
    assertEquals(name, addedBook.getName());
    assertEquals(category, addedBook.getCategory());
    assertEquals(content, addedBook.getContent());
  }
}
