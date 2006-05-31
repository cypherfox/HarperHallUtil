/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.1  2006/05/31 13:10:28  behnke_l
 * correct implementation an implement full tests for longValue. Some additional little tools
 *
 */
package de.harper_hall.util.tools;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * @author sage
 */
public class RegistryDiscoverer {

  /**
   * @param args
   */
  public static void main(String[] args) {
    String hostname;
    String urltext;
    String[] bound_objs = null;
    int i;
    
    if(args.length != 0){
      hostname=args[0];
    }else{
      hostname="localhost";
    }
    urltext="//"+hostname;
    
    try {
      bound_objs = Naming.list("//"+hostname);
    } catch (RemoteException e) {
      System.err.println("could not contact registry on host '"+hostname+"'");
      System.exit(255);
    } catch (MalformedURLException e) {
      System.err.println("the hostname '"+hostname+"' has produced an invalid URL '"+urltext+"'");
      System.exit(255);
    }

    if((bound_objs == null) || (bound_objs.length==0)){
      System.out.println("No objects registered with registry on "+hostname);
      System.exit(1);
    }
    for(i=0;i<bound_objs.length;i++){
      System.out.println(bound_objs[i]);
    }
  }

}
