/* -*- java -*- */
/*
 * Filename:          de.harper_hall.util.collections/EnumByStringMap.java
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

package de.harper_hall.util.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Map using a string representing a enum value to look up any type of value.
 * @author sage
 *
 * @param E Enum whose values as strings  are the keys of the map
 * @param V values
 */
public class EnumByStringMap<E extends Enum<E>, V> implements Map<String, V> {

  @SuppressWarnings("hiding")
  private class Entry<V> implements Map.Entry<String, V> {

    String key = null;
    V value = null;
    
    /**
     * 
     * @param key
     * @param val
     */
    public Entry(String key, V val) {
      this.key = key;
      this.value = val;          
    }
    
    /**
     * @see java.util.Map.Entry#getKey()
     */
    public String getKey() {
      return key;
    }

    /**
     * @see java.util.Map.Entry#getValue()
     */
    public V getValue() {
      return value;
    }

    /**
     * @see java.util.Map.Entry#setValue(java.lang.Object)
     */
    public V setValue(V value) {
      V oldval = value;
      this.value = value;
      return oldval;
    }
    
  }
  
  Map<E, V> enumMap = null;

  Class<E> enumClass = null;

  boolean doUpper = true;

  public EnumByStringMap(Map<E, V> realMap, Class<E> enumClass) {
    enumMap = realMap;
    this.enumClass = enumClass;
  }

  /**
   * clears the backing map
   * 
   * @see java.util.Map#clear()
   */
  public void clear() {
    enumMap.clear();
  }

  /**
   * @see java.util.Map#containsKey(java.lang.Object)
   */
  public boolean containsKey(String key) {

    if (doUpper) {
      return enumMap.containsKey(Enum.valueOf(enumClass, key.toUpperCase()));
    } else {
      return enumMap.containsKey(Enum.valueOf(enumClass, key));
    }
  }

  /**
   * @see java.util.Map#containsValue(java.lang.Object)
   */
  public boolean containsValue(Object value) {
    return enumMap.containsValue(value);
  }

  /**
   * changing the map will invalidate the set returned by this method. The entry set is
   * copied by this method.
   * 
   * @see java.util.Map#entrySet()
   */
  public Set<Map.Entry<String, V>> entrySet() {
    Set<Map.Entry<String, V>> retval = new HashSet<Map.Entry<String, V>>();
    for (Map.Entry<E, V> entry : enumMap.entrySet()) {
      Map.Entry<String, V> newEntry = 
        new Entry<V>(entry.getKey().toString(), entry.getValue()); 
      retval.add(newEntry);
    }
    return retval; 
  }

  /**
   * changing the map will invalidate the set returned by this method. The entry set is
   * copied by this method.
   *
   * @see java.util.Map#keySet()
   */
  public Set<String> keySet() {
    Set<String> retval = new HashSet<String>();
    for (E key : enumMap.keySet()) {
     String newEntry = key.toString();
     retval.add(newEntry);
    }
    return retval;
  }

  /**
   * @see java.util.Map#get(java.lang.Object)
   */
  public V get(Object key) {
    if (!(key instanceof String)) { return null; }
    String s = (String) key;

    if (doUpper) {
      return enumMap.get(Enum.valueOf(enumClass, s.toUpperCase()));
    } else {
      return enumMap.get(Enum.valueOf(enumClass, s));
    }
  }

  /**
   * @see java.util.Map#isEmpty()
   */
  public boolean isEmpty() {
    return enumMap.isEmpty();
  }

  /**
   * @see java.util.Map#put(java.lang.Object, java.lang.Object)
   */
  public V put(String key, V value) {
    if (doUpper) {
      return enumMap.put(Enum.valueOf(enumClass, key.toUpperCase()), value);
    } else {
      return enumMap.put(Enum.valueOf(enumClass, key), value);
    }
  }

  /**
   * not implemented!
   * 
   * @see java.util.Map#putAll(java.util.Map)
   */
  public void putAll(Map<? extends String, ? extends V> m) {
    // Das hier erst wieder einkommentieren, wenn du das mit dem Update der
    // Werte aus einer
    // h:inputText wirklich verstanden hast.
    /*
     * for(Entry e : (Set<Entry>)t.entrySet()){ if(e.getKey() instanceof
     * String){ String s = (String)e.getKey();
     * 
     * if(doUpper){ enumMap.put(Enum.valueOf(enumClass,
     * s.toUpperCase()),e.getValue()); }else
     * enumMap.put(Enum.valueOf(enumClass, s),e.getValue()); } }
     */
  }

  /**
   * @see java.util.Map#remove(java.lang.Object)
   */
  public V remove(String key) {
    if (doUpper) {
      return enumMap.remove(Enum.valueOf(enumClass, key.toUpperCase()));
    } else {
      return enumMap.remove(Enum.valueOf(enumClass, key));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#remove(java.lang.Object)
   */
  public V remove(Object key) {
    if (!(key instanceof String)) {
      return null;
    }

    String s = (String) key;

    if (doUpper) {
      return enumMap.remove(Enum.valueOf(enumClass, s.toUpperCase()));
    } else {
      return enumMap.remove(Enum.valueOf(enumClass, s));
    }
  }

  /**
   * @see java.util.Map#size()
   */
  public int size() {
    return enumMap.size();
  }

  /**
   * @see java.util.Map#values()
   */
  public Collection<V> values() {
    return enumMap.values();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map#containsKey(java.lang.Object)
   */
  public boolean containsKey(Object key) {
    // TODO Auto-generated method stub
    return false;
  }

}

