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
package org.exoplatform.utils;

import org.exoplatform.entity.Book;

/**
 * Created by The eXo Platform SAS
 * Author : phong tran
 *          phongth@exoplatform.com
 * Jun 7, 2011  
 */
public class Utils {
  public static String bookCategoryEnumToString(Book.CATEGORY category) {
    if (category != null) {
      return category.toString().toLowerCase();
    }
    return null;
  }

  public static Book.CATEGORY bookCategoryStringToEnum(String categoryStr) {
    return Book.CATEGORY.valueOf(categoryStr.toUpperCase());
  }
}
