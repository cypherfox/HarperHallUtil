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
package de.harper_hall.util.teatime;

import de.harper_hall.util.teatime.TObject.State;

class CommitRecord {
  private State state = State.unknown;
  private PseudoTime timeout = null;
  
  void commit(PseudoTime ttime) throws AllreadyAbortedException {
    if (state == State.aborted) { throw new AllreadyAbortedException(); }
    state = State.commited;
  }
  
  public void abort() throws AllreadyCommitedException {
    if (state == State.commited) { throw new AllreadyCommitedException(); }
    state = State.aborted;
  }

  /**
   * 
   * @return current state of 
   */
  public State getState() { 
    return state; 
  }
  
  public PseudoTime getTimeout() {
    return timeout;
  }
  
  public CommitRecord(PseudoTime deadline) {
    timeout = deadline;
  }
}