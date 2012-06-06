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
package org.exoplatform.exception;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieu.lai@exoplatform.com
 * 7 Jun 2011  
 */
public class DuplicateBookException extends Exception {

  private static final long serialVersionUID = 2L;

  public DuplicateBookException() {
    super();
  }

  public DuplicateBookException(String message, Throwable cause) {
    super(message, cause);
  }

  public DuplicateBookException(String message) {
    super(message);
  }

  public DuplicateBookException(Throwable cause) {
    super(cause);
  }
}
