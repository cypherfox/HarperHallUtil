/* -*- java -*- */
/* $Id$ */
/* 
 * Filename:          IntervalMap.java
 *                                                                       *
 * Project:           HarperHallUtil
 * Programm:          
 * Function:          
 * Documentationfile: 
 *
 * This file is distributed under the GNU Public License 2.0
 * See the file Copying for more information
 *
 * copyright (c) 2007 Lutz Behnke <lutz.behnke@gmx.de>
 *
 * THE AUTHOR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHOR SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.  
 */
/* 
 * $Log$
 */

package de.harper_hall.util.collections;

import java.util.Map;

/** A Map that allows keys to act as borders of intervals.
 * 
 * This interface extends the Map interface, so that a keys which are added
 * together with the value define the border of an interval, rather than
 * an specific value.
 * 
 * The method get(K key) will return the val for the smallest key that is 
 * greater than or equeal to key.
 * 
 * the type of must implement the Comparable interface.
 * 
 * lastForBeyond is set to false by default.
 * 
 * @author sage
 *
 */
public interface IntervalMap<K extends Comparable, V> extends Map {
	
	/**
	 * 
	 * @param key
	 * @param val
	 */
	void put(K key, V val);

	/**  
	 * @return the lastForBexon
	 */
	public boolean isLastForBeyond();

	/**Use the last element for any values beyond the last entry.
	 * the method <tt>get(K key)</tt> will return null otherwise.
	 *
	 * @param lastForBexon the lastForBexon to set
	 */
	public void setLastForBeyond(boolean lastForBeyond);

}
