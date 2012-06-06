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
package org.exoplatform.entity;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieu.lai@exoplatform.com
 * 7 Jun 2011  
 */
public class Book {
  public enum CATEGORY {
    NOVEL, MANGA, COMICS, TECHNICAL, MATHS, HISTORY
  }
  
  private String id;
  
  private String name;

  private CATEGORY category;

  private String content;
  
  public Book() {
  }
  
  public Book(String name, CATEGORY cat, String content) {
    this.name = name;
    this.category = cat;
    this.content = content;
  }
  
  public static String[] getBookCategoryStringArray() {
    return new String[] { "Comics", "History", "Manga", "Maths", "Novel", "Technical" };
  }
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CATEGORY getCategory() {
    return category;
  }

  public void setCategory(CATEGORY category) {
    this.category = category;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
