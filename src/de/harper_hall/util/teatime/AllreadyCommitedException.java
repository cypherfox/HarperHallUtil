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

public class AllreadyCommitedException extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = 6890860757609616760L;

public AllreadyCommitedException() {
    super();
    // TODO Auto-generated constructor stub
  }

  public AllreadyCommitedException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  public AllreadyCommitedException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public AllreadyCommitedException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }

}
