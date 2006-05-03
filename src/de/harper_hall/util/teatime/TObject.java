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

/** Class implements the TeaTime protocol, using timeout-base two phase commit 
 * protocol to provide synchonisation
 * 
 * @author sage
 */
public class TObject {

  class Version{
    public Version next    = null;
    public CommitRecord cr = null;
    public PseudoTime ptw  = null; // write time of this version
    public PseudoTime ptr  = null; // latest read on this version;
    public Object val      = null;
  }

  public static enum State {unknown, commited, aborted};
  
  private Version history =null;
  
  public TObject() {
    super();
  }

  /* Read value for given TeaTime
   * 
   */
  Object read(PseudoTime ttime){
    Version cur = history, last = null;

    while(ttime.lessThen(cur.ptw) && cur.cr.getState() != State.commited){
      // clean up old aborted versions while we're at it
      if(cur.cr.getState() == State.aborted){
        if(last == null)
          history = cur.next;
        else
          last.next = cur.next;
        cur = cur.next;
      } else //simply walk the list
        last = cur;  cur = cur.next;      
    }
    
    cur.ptr = ttime;
    
    return cur.val;

  }
  
  /* Write value to object on given TeaTime
   * 
   * @throws LateReadException if the last version in history has been read after ttime
   * @throws LateVersionException if a commited version exists for a time later than ttime
   * @throws Error if the atomic operation to which q is the Possibility has 
   *               been commited bevore all Write Operations (like this one) 
   *               have been completed
   */
  void write(Object val, PseudoTime ttime, CommitRecord q) throws LaterVersionException, LateReadException{
    Version token = new Version();
    
    Version cur =history, last = null;
    
    // sort token into history 
    while(ttime.lessThen(cur.ptw)){
      // check for commited versions 
      if(cur.cr.getState() == State.commited){
        try {
          q.abort();
        } catch (AllreadyCommitedException e) {
          // OK, serious! somebody commited this action before all ops where done
          throw new Error("Atomic operation commited before completing all write operations");
        }
        throw new LaterVersionException("there allready exists a commited version after ttime");        
      }

      //walk the list
      last = cur;  cur = cur.next;      
   }
    
    // check for late read
    if(ttime.lessThen(cur.ptr)){
      //invalidate this versions atomic action
      try {
        q.abort();
      } catch (AllreadyCommitedException e) {
        // OK, serious! somebody commited this action before all ops where done
        throw new Error("Atomic operation commited before completing all write operations");
      }
      throw new LateReadException();
    }

    // insert new version
    token.cr = q;
    token.next = cur;
    token.ptw = ttime;
    token.val = val;
    
    if(last == null)
      history = token;
    else
      last.next = token;
    
  }
}
