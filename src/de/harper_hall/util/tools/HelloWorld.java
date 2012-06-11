/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005-2012 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 */
package de.harper_hall.util.tools;

public class HelloWorld {

  public HelloWorld() {
    super();
  }

  public void doHelloWorld() {
    System.out.println("Hello World!");
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    HelloWorld hw = new HelloWorld();
    
    hw.doHelloWorld();
  }

}
