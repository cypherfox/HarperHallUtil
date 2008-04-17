/**
 * 
 */
package de.harper_hall.util.awt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/** distribute action events to multiple listeners
 * 
 * If a listener is added multiple times, it will be called multiple times and
 * will need to be removed multiple times if so desired.
 * 
 * @author sage
 *
 */
public class ActionMulticaster implements ActionListener {

  List<ActionListener> listeners = new ArrayList<ActionListener>();
  
  /**
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e) {
    for(ActionListener al : listeners ){
      al.actionPerformed(e);
    }
  }

  /**
   * 
   * @param l
   */
  public void addActionListener(ActionListener l) {
    listeners.add(l);
  }
    
  /** remove listener from list
   * 
   * @param l listener to be removed
   */
  public void removeActionListener(ActionListener l){
    listeners.remove(l);
  }
  
}
