/* This file is distributed under the GNU Public License 2.0 or a later version
 * at your discretion. See the file Copying for more information
 * 
 * copyright (c) 2005 Lutz Behnke <lutz.behnke@gmx.de> 
 * 
 * $Id$
 * $Log$
 * Revision 1.1  2005/11/15 17:52:52  behnke_l
 * files added
 *
 */
package de.harper_hall.util.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/** 
 * An object that maps keys to one or more objects. It tries to emulate the Map<K,V> interface where possible, 
 * but is not completely compatible.
 * 
 * This container can be though of as a collection of Sets, allowing for each key to exist multiple value objects.
 * The objects per key themself are handled by a set, according to the contract of Set
 * 
 * @author sage
 */
public interface MultiMap<K,V> extends Map<K,V>{
  
  /* TODO: das gesamte entrySet()/Entry Interface müßte noch mal auf MultiMap angepasst werden. Dann muß aber die Abhängigkeit
   * zu Map gelößt werden
   * 
   */
  
  /**
   * 
   * @param key
   * @return a set of values to which this map maps the specified key.
   */
  Collection<V>  getCollection(Object key);
  
  /**
   * 
   * @return a set view of the keys contained in this map.
   */
  Set<K> keySet();
  
  /** Associates the specified value with the specified key in this map (optional operation).
   * 
   * @param key
   * @param value
   * @return typically the value added if the map changed and null if the map did not change.
   * @throws UnsupportedOperationException if the operation is not supported by a given implementation
   */
  V put(K key, V value) throws UnsupportedOperationException;

  /**
   * If any of the key-value mappings allready exsist, they are merged with the data from the parameter
   * @param t
   * @throws UnsupportedOperationException
   * @throws ClassCastException
   * @throws IllegalArgumentException
   * @throws NullPointerException
   */
  void    mergeAllSingle(Map<? extends K,? extends V> t) throws UnsupportedOperationException,  ClassCastException,
  IllegalArgumentException, NullPointerException;

  /**
   * If any of the key-value mappings allready exsist, they are merged with the data from the parameter
   * @param t
   * @throws UnsupportedOperationException
   * @throws ClassCastException
   * @throws IllegalArgumentException
   * @throws NullPointerException
   */
  void    mergeAllSet(Map<? extends K,Set<? extends V>> t) throws UnsupportedOperationException,  ClassCastException,
  IllegalArgumentException, NullPointerException;
  
  /** Copies all of the mappings from the specified map to this map (optional operation).
   * If any the key mappings allready exist they are overwritten! 
   * @param t
   * @throws UnsupportedOperationException
   * @throws ClassCastException
   * @throws IllegalArgumentException
   * @throws NullPointerException
   */
  void    putAllSingle(Map<? extends K,? extends V> t) throws UnsupportedOperationException,  ClassCastException,
  IllegalArgumentException, NullPointerException;

  /** Copies all of the mappings from the specified map to this map (optional operation).
   * If any the key mappings allready exist they are overwritten! 
   * @param t
   * @throws UnsupportedOperationException
   * @throws ClassCastException
   * @throws IllegalArgumentException
   * @throws NullPointerException
   */
  void    putAllSet(Map<? extends K,Set<? extends V>> t) throws UnsupportedOperationException,  ClassCastException,
  IllegalArgumentException, NullPointerException;

  /**
   * remove a certain value from the set.
   * 
   * @param key
   * @return
   * @throws UnsupportedOperationException
   */
  V remove(Object key, V value) throws UnsupportedOperationException;
  
  /** Removes the mapping for this key from this map if it is present (optional operation).
   * 
   * @param key
   * @return the first object of the collection that was found for this key
   * @throws UnsupportedOperationException
   */
  V remove(Object key)throws UnsupportedOperationException;
 
  /**
   * 
   * @return a collection view of the values contained in this mulit-map.
   */
  Collection<V> values();
  
 }
