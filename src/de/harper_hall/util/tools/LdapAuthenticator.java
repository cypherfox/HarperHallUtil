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
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import de.harper_hall.util.ldap.LdapURLStreamHandler;

public class LdapAuthenticator {

  String authenticationMethod = "simple";
  String baseDN =null;
  String host = null;
  String port;
  
  URL provider_url;
  
  public LdapAuthenticator(String host, String baseDN) {
    try {
      provider_url = new URL("ldap",host,389,baseDN,new LdapURLStreamHandler());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  public LdapAuthenticator(String url_spec){
    try {
      provider_url = new URL(null, url_spec, new LdapURLStreamHandler());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }    
  }
  public LdapAuthenticator(URL providerUrl) {
    this.provider_url = providerUrl;
  }
  
  public String findDN(String username){
    return "uid="+username+",ou=Users,"+provider_url.getPath();
  }
  
    public boolean authenticate(String username, String password){
//  Set up the environment for creating the initial context
    // uid=behnke_l,ou=Users,dc=informatik,dc=haw-hamburg,dc=de
    
    Hashtable<String,String> env = new Hashtable<String,String>();
    env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, provider_url.toExternalForm());

//     Authenticate as S. User and password "mysecret"
    env.put(Context.SECURITY_AUTHENTICATION, authenticationMethod);
    env.put(Context.SECURITY_PRINCIPAL, findDN(username));

    env.put(Context.SECURITY_CREDENTIALS, password);

//     Create the initial context
    try {
     new InitialDirContext(env);
    } catch (AuthenticationException e) {
       return false;
    } catch (AuthenticationNotSupportedException e) {
      System.err.println("Authentication method "+authenticationMethod+" not supported by LDAP server "+host);
      System.exit(-1);
    } catch (NamingException e) {
      System.err.println("Could not connect or bind to user object: "+e.toString());
      System.exit(-1);
    }
    return true;
  }
  
    protected void print_help(){
      String help_str =
        "usage: ldapAuthenticator [options] ldap-url username password\n\n" +       
        "--search-bind-dn       An optional DN to bind with during the search phase.\n" +
        "--search-bind-passwd   An optional password to bind with during the search phase.\n" +
        "--compare-dn-no-server Use the LDAP server to compare the DNs (not implemented)\n" +
        "--derefer-aliases     When will the module de-reference aliases (never|searching|finding|always)(not implmented)\n" +
        "--group-attribute      LDAP attributes used to check for group membership (not implemented)\n" +
        "--group-attr-is-dn     Use the DN of the client username when checking for group membership (not implemented)\n" +
        "--help                 print this help and exit";
        
      System.out.println(help_str);
    }
    
  /**
   * @param args
   */
  public static void main(String[] argv) {
    LongOpt[] longopts = new LongOpt[3];
    // 
    StringBuffer sb = new StringBuffer();
    StringBuffer base_url = new StringBuffer();
    longopts[0] = new LongOpt("base-url", LongOpt.REQUIRED_ARGUMENT, base_url, 'h');
    longopts[1] = new LongOpt("", LongOpt.REQUIRED_ARGUMENT, sb, 'o'); 
    longopts[2] = new LongOpt("", LongOpt.OPTIONAL_ARGUMENT, null, 2);
    // 
    Getopt g = new Getopt("LdapAuth", argv, "v", longopts);
    g.setOpterr(false); // We'll do our own error handling


    LdapAuthenticator la = new LdapAuthenticator("ldap.informatik.haw-hamburg.de","dc=informatik,dc=haw-hamburg,dc=de");
    int needed_len = 2;
    int arg_start = 0;
    boolean verbose = false;
    
    needed_len+=g.getOptind();
    arg_start+=g.getOptind();
    
    if(argv.length < needed_len){
      System.err.println("not enough parameters.\n  usage: LdapAuthenticator [-v] <username> <password>\n\n   -v    be verbose");
      System.exit(-1);
    }
    
    if(la.authenticate(argv[arg_start],argv[arg_start+1])){
      if(verbose){System.out.println("authentication for user '"+argv[arg_start]+"' accepted");}
      
      System.exit(0);
    }
    
    if(verbose){System.out.println("authentication for user '"+argv[arg_start]+"' failed");}
    System.exit(1);
    
  }

}
