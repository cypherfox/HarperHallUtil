/* -*- java -*- */
/* $Id$ */
/* 
 * Filename:          IntervalArrayHashMap.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This implementation O(log(n)*n) for time and O(n) for space
 * 
 * @author sage
 *
 */
public class IntervalArrayHashMap<K extends Comparable, V> implements IntervalMap<K,V> {

	private List<Comparable> intervals = new ArrayList<Comparable>();
	private Comparable[] sorted = null;
	private Map<Comparable,Object> map = new HashMap<Comparable,Object>();

	private boolean lastForBeyond = false;
	
	/**
	 * @see de.harper_hall.util.collections.IntervalMap#put(java.lang.Comparable, java.lang.Object)
	 */

	public void put(Comparable key, Object val) {
		  map.put(key, val);
		  sorted = null;
		  intervals.add(key);		  
	}

	/**
	 * TODO: synchronize
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		intervals.clear();
		sorted = null;
		map.clear();
	}

	/**
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		Comparable ckey = (Comparable)key;
		if(ckey.compareTo(intervals.get(intervals.size()-1)) < 1)
			return true;
		else
			return false;
	}

	/**
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	/**
	 * @see java.util.Map#entrySet()
	 */
	public Set entrySet() {
		return map.entrySet();
	}

	/**
	 * implementation may take up to O(n) to find value
	 * 
	 * TODO: synchronization
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Object get(Object key) {
		int pos;
		
		if(sorted == null){
		  sorted = intervals.toArray(new Comparable[0]);
		  Arrays.sort(sorted);
		}
		pos = Arrays.binarySearch(sorted, key);
		if(pos < 0) {
			pos++;
			pos *= -1;
		}
		
		// value beyond the last element
		if(pos >= intervals.size()){
			if(lastForBeyond){
				return map.get(intervals.get(pos-1));
			}else{
				return null;
			}
		}
		
		return map.get(intervals.get(pos));
	}

	/**
	 * @return the lastForBexon
	 */
	public boolean isLastForBeyond() {
		return lastForBeyond;
	}

	/**
	 * @param lastForBexon the lastForBexon to set
	 */
	public void setLastForBeyond(boolean lastForBeyond) {
		this.lastForBeyond = lastForBeyond;
	}

	/**
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * @see java.util.Map#keySet()
	 */
	public Set keySet() {
		return map.keySet();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(Object key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see java.util.Map#size()
	 */
	public int size() {
		return map.size();
	}

	/**
	 * @see java.util.Map#values()
	 */
	public Collection values() {
		return map.values();
	}

}