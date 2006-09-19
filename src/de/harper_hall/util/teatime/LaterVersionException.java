/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.2  2006/09/19 13:42:08  behnke_l
 * fix warnings
 *
 * Revision 1.1  2006/05/03 17:19:45  behnke_l
 * junit-target in build.xml
 *
 */
package de.harper_hall.util.teatime;

public class LaterVersionException extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = -1426452844875637347L;

public LaterVersionException() {
    super();
  }

  public LaterVersionException(String message) {
    super(message);
  }

  public LaterVersionException(String message, Throwable cause) {
    super(message, cause);
  }

  public LaterVersionException(Throwable cause) {
    super(cause);
  }

}
