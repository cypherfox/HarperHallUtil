/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.1  2006/05/03 17:19:45  behnke_l
 * junit-target in build.xml
 *
 */
package de.harper_hall.util.ldap;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class LdapURLStreamHandler extends URLStreamHandler {

  public LdapURLStreamHandler() {
    super();
    // TODO Auto-generated constructor stub
  }

  @Override
  protected URLConnection openConnection(URL u) throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected int getDefaultPort() {
    return 389;
  }

  @Override
  protected String toExternalForm(URL u) {
    // pre-compute length of StringBuffer
    int len = u.getProtocol().length() + 1;
    if (u.getAuthority() != null && u.getAuthority().length() > 0)
      len += 2 + u.getAuthority().length();
    if (u.getPath() != null) {
      len += 1 + u.getPath().length();
    }
    
    StringBuffer result = new StringBuffer(len);
    result.append(u.getProtocol());
    result.append(":");
    if (u.getAuthority() != null && u.getAuthority().length() > 0) {
      result.append("//");
      result.append(u.getAuthority());
    }
    if (u.getPath() != null) {
      result.append("/");
      result.append(u.getPath());
    }
    return result.toString();
  }

}
