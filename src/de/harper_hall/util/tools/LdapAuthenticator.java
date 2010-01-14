/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.2  2006/05/31 13:10:28  behnke_l
 * correct implementation an implement full tests for longValue. Some additional little tools
 *
 * Revision 1.1  2006/05/03 17:19:45  behnke_l
 * junit-target in build.xml
 *
 */
package de.harper_hall.util.tools;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import de.harper_hall.util.ldap.LdapURLStreamHandler;

public class LdapAuthenticator {

  String authenticationMethod = "simple";

  String baseDN = null;

  String host = null;

  String port;

  protected static final int DEFAULT_PORT = 389;

  URL providerUrl;

  public LdapAuthenticator(String host, String baseDN) {
    try {
      providerUrl = new URL("ldap", host, DEFAULT_PORT, baseDN, new LdapURLStreamHandler());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  public LdapAuthenticator(String urlSpec) {
    try {
      providerUrl = new URL(null, urlSpec, new LdapURLStreamHandler());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  public LdapAuthenticator(URL providerUrl) {
    this.providerUrl = providerUrl;
  }

  public String findDN(String username) {
    return "uid=" + username + ",ou=Users," + providerUrl.getPath();
  }

  public boolean authenticate(String username, String password) {
    // Set up the environment for creating the initial context
    // uid=behnke_l,ou=Users,dc=informatik,dc=haw-hamburg,dc=de

    Hashtable<String, String> env = new Hashtable<String, String>();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, providerUrl.toExternalForm());

    // Authenticate as S. User and password "mysecret"
    env.put(Context.SECURITY_AUTHENTICATION, authenticationMethod);
    env.put(Context.SECURITY_PRINCIPAL, findDN(username));

    env.put(Context.SECURITY_CREDENTIALS, password);

    // Create the initial context
    try {
      new InitialDirContext(env);
    } catch (AuthenticationException e) {
      return false;
    } catch (AuthenticationNotSupportedException e) {
      System.err.println("Authentication method " + authenticationMethod + " not supported by LDAP server " + host);
      System.exit(-1);
    } catch (NamingException e) {
      System.err.println("Could not connect or bind to user object: " + e.toString());
      System.exit(-1);
    }
    return true;
  }

  protected void print_help() {
    String helpStr = "usage: ldapAuthenticator [options] ldap-url username password\n\n"
        + "--search-bind-dn       An optional DN to bind with during the search phase.\n"
        + "--search-bind-passwd   An optional password to bind with during the search phase.\n"
        + "--compare-dn-no-server Use the LDAP server to compare the DNs (not implemented)\n"
        + "--derefer-aliases     When will the module de-reference aliases (never|searching|"
        + "finding|always)(not implmented)\n"
        + "--group-attribute      LDAP attributes used to check for group membership (not implemented)\n"
        + "--group-attr-is-dn     Use the DN of the client username when checking for group "
        + "membership (not implemented)\n" + "--help                 print this help and exit";

    System.out.println(helpStr);
  }

  /**
   * @param args
   */
  public static void main(String[] argv) {

    // 
    StringBuffer sb = new StringBuffer();
    StringBuffer baseURL = new StringBuffer();

    LongOpt[] longopts = new LongOpt[] { new LongOpt("base-url", LongOpt.REQUIRED_ARGUMENT, baseURL, 'h'),
                                         new LongOpt("", LongOpt.REQUIRED_ARGUMENT, sb, 'o'),
                                         new LongOpt("", LongOpt.OPTIONAL_ARGUMENT, null, 2) };
    // 
    Getopt g = new Getopt("LdapAuth", argv, "v", longopts);
    g.setOpterr(false); // We'll do our own error handling

    LdapAuthenticator la = new LdapAuthenticator("ldap.informatik.haw-hamburg.de", 
                                                 "dc=informatik,dc=haw-hamburg,dc=de");
    int neededLen = 2;
    int argStart = 0;
    boolean verbose = false;

    neededLen += g.getOptind();
    argStart += g.getOptind();

    if (argv.length < neededLen) {
      System.err
          .println("not enough parameters.\n  usage: LdapAuthenticator [-v]"
                   + " <username> <password>\n\n   -v    be verbose");
      System.exit(-1);
    }

    if (la.authenticate(argv[argStart], argv[argStart + 1])) {
      if (verbose) {
        System.out.println("authentication for user '" + argv[argStart] + "' accepted");
      }

      System.exit(0);
    }

    if (verbose) {
      System.out.println("authentication for user '" + argv[argStart] + "' failed");
    }
    System.exit(1);

  }

}
