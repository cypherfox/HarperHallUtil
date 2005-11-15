/*
 *  Copyright 2001-2004 The Apache Software Foundation
 *  Copyright 2005 Lutz Behnke <lutz.behnke@gmx.de> for the conversion to generics
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package de.harper_hall.util.collections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.collections.iterators.EmptyIterator;

/** 
 * <code>MultiHashMap</code> is the default implementation of the 
 * {@link de.harper_hall.util.collections.MultiMap MultiMap} interface.
 * <p>
 * A <code>MultiMap</code> is a Map with slightly different semantics.
 * Putting a value into the map will add the value to a Collection at that key.
 * Getting a value will return a Collection, holding all the values put to that key.
 * <p>
 * This implementation uses an <code>ArrayList</code> as the collection.
 * The internal storage list is made available without cloning via the
 * <code>get(Object)</code> and <code>entrySet()</code> methods.
 * The implementation returns <code>null</code> when there are no values mapped to a key.
 * <p>
 * For example:
 * <pre>
 * MultiMap mhm = new MultiHashMap();
 * mhm.put(key, "A");
 * mhm.put(key, "B");
 * mhm.put(key, "C");
 * List list = (List) mhm.get(key);</pre>
 * <p>
 * <code>list</code> will be a list containing "A", "B", "C".
 *
 * 
 * @author Christopher Berry
 * @author James Strachan
 * @author Steve Downey
 * @author Stephen Colebourne
 * @author Julien Buret
 * @author Serhiy Yevtushenko
 * @author Lutz Behnke
 */
public class MultiHashMap<K,V>  implements MultiMap<K,V> {
    
    private HashMap<K,Collection<V>> data; 
  
    // backed values collection
    private transient Collection values = null;
    
    /**
     * Constructor.
     */
    public MultiHashMap() {
      data = new HashMap<K,Collection<V>>();
    }

    /**
     * Constructor.
     * 
     * @param initialCapacity  the initial map capacity
     */
    public MultiHashMap(int initialCapacity) {
      data = new HashMap<K,Collection<V>>(initialCapacity);
    }

    /**
     * Constructor.
     * 
     * @param initialCapacity  the initial map capacity
     * @param loadFactor  the amount 0.0-1.0 at which to resize the map
     */
    public MultiHashMap(int initialCapacity, float loadFactor) {
      data = new HashMap<K,Collection<V>>(initialCapacity, loadFactor);
    }

    /**
     * Constructor that copies the input map creating an independent copy.
     * <p>
     * This method performs different behaviour depending on whether the map
     * specified is a MultiMap or not. If a MultiMap is specified, each internal
     * collection is also cloned. If the specified map only implements Map, then
     * the values are not cloned.
     * <p>
     * NOTE: From Commons Collections 3.1 this method correctly copies a MultiMap
     * to form a truly independent new map.
     * 
     * @param mapToCopy  a Map to copy
     */
    public MultiHashMap(Map mapToCopy) {
      data =new HashMap<K,Collection<V>>((int) (mapToCopy.size() * 1.4f));
      if (mapToCopy instanceof MultiMap) {
        for (Iterator it = mapToCopy.entrySet().iterator(); it.hasNext();) {
          Map.Entry entry = (Map.Entry) it.next();
          Collection<V> coll = (Collection<V>) entry.getValue();
          Collection<V> newColl = createCollection(coll);
          data.put((K)entry.getKey(), newColl);
        }
      } else {
        putAll(mapToCopy);
      }
    }


    //-----------------------------------------------------------------------
    /**
     * Gets the total size of the map by counting all the values.
     * 
     * @return the total size of the map counting all values
     * @since Commons Collections 3.1
     */
    public int totalSize() {
        int total = 0;
        Collection values = data.values();
        for (Iterator it = values.iterator(); it.hasNext();) {
            Collection coll = (Collection) it.next();
            total += coll.size();
        }
        return total;
    }

    /**
     * Gets the collection mapped to the specified key.
     * This method is a convenience method to typecast the result of <code>get(key)</code>.
     * 
     * @param key  the key to retrieve
     * @return the collection mapped to the key, null if no mapping
     * @since Commons Collections 3.1
     */
    public Collection<V> getCollection(Object key) {
        return (Collection<V>) data.get(key);
    }

    /**
     * Gets the size of the collection mapped to the specified key.
     * 
     * @param key  the key to get size for
     * @return the size of the collection at the key, zero if key not in map
     * @since Commons Collections 3.1
     */
    public int size(Object key) {
        Collection coll = getCollection(key);
        if (coll == null) {
            return 0;
        }
        return coll.size();
    }

    /**
     * Gets an iterator for the collection mapped to the specified key.
     * 
     * @param key  the key to get an iterator for
     * @return the iterator of the collection at the key, empty iterator if key not in map
     * @since Commons Collections 3.1
     */
    public Iterator iterator(Object key) {
        Collection coll = getCollection(key);
        if (coll == null) {
            return EmptyIterator.INSTANCE;
        }
        return coll.iterator();
    }

    /**
     * Adds the value to the collection associated with the specified key.
     * <p>
     * Unlike a normal <code>Map</code> the previous value is not replaced.
     * Instead the new value is added to the collection stored against the key.
     *
     * @param key  the key to store against
     * @param value  the value to add to the collection at the key
     * @return the value added if the map changed and null if the map did not change
     */    
    public V put(K key, V value) {
        // NOTE:: put is called during deserialization in JDK < 1.4 !!!!!!
        //        so we must have a readObject()
        Collection<V> coll = getCollection(key);
        if (coll == null) {
            coll = createCollection(null);
            data.put(key, coll);
        }
        boolean results = coll.add(value);
        return (results ? value : null);
    }

    /**
     * Adds a collection of values to the collection associated with the specified key.
     *
     * @param key  the key to store against
     * @param values  the values to add to the collection at the key, null ignored
     * @return true if this map changed
     * @since Commons Collections 3.1
     */    
    public boolean putAll(K key, Collection<V> values) {
        if (values == null || values.size() == 0) {
            return false;
        }
        Collection<V> coll = getCollection(key);
        if (coll == null) {
            coll = createCollection(values);
            if (coll.size() == 0) {
                return false;
            }
            data.put(key, coll);
            return true;
        } else {
            return coll.addAll(values);
        }
    }

    /**
     * 
     * @param key
     * @return
     */
    public V  get(Object key){
      Collection<V> col = getCollection(key);
      if(col == null) return null;
      return (V)col.iterator().next();
    }

    /**
     * Checks whether the map contains the value specified.
     * <p>
     * This checks all collections against all keys for the value, and thus could be slow.
     * 
     * @param value  the value to search for
     * @return true if the map contains the value
     */
    public boolean containsValue(Object value) {
        Set pairs = data.entrySet();

        if (pairs == null) {
            return false;
        }
        Iterator pairsIterator = pairs.iterator();
        while (pairsIterator.hasNext()) {
            Map.Entry keyValuePair = (Map.Entry) pairsIterator.next();
            Collection coll = (Collection) keyValuePair.getValue();
            if (coll.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the collection at the specified key contains the value.
     * 
     * @param value  the value to search for
     * @return true if the map contains the value
     * @since Commons Collections 3.1
     */
    public boolean containsValue(Object key, Object value) {
        Collection coll = getCollection(key);
        if (coll == null) {
            return false;
        }
        return coll.contains(value);
    }

    /**
     * Removes a specific value from map.
     * <p>
     * The item is removed from the collection mapped to the specified key.
     * Other values attached to that key are unaffected.
     * <p>
     * If the last value for a key is removed, <code>null</code> will be returned
     * from a subsequant <code>get(key)</code>.
     * 
     * @param key  the key to remove from
     * @param item  the value to remove
     * @return the value removed (which was passed in), null if nothing removed
     */
    public V remove(Object key, V item) throws UnsupportedOperationException {
        Collection valuesForKey = getCollection(key);
        if (valuesForKey == null) {
            return null;
        }
        valuesForKey.remove(item);

        // remove the list if it is now empty
        // (saves space, and allows equals to work)
        if (valuesForKey.isEmpty()){
            remove(key);
        }
        return item;
    }

    /**
     * Clear the map.
     * <p>
     * This clears each collection in the map, and so may be slow.
     */
    public void clear() {
        // For gc, clear each list in the map
        Set pairs = data.entrySet();
        Iterator pairsIterator = pairs.iterator();
        while (pairsIterator.hasNext()) {
            Map.Entry keyValuePair = (Map.Entry) pairsIterator.next();
            Collection coll = (Collection) keyValuePair.getValue();
            coll.clear();
        }
        data.clear();
    }

    /**
     * Gets a collection containing all the values in the map.
     * <p>
     * This returns a collection containing the combination of values from all keys.
     *
     * @return a collection view of the values contained in this map
     */
    public Collection values() {
        Collection vs = values;
        return (vs != null ? vs : (values = new Values()));
    }

    //-----------------------------------------------------------------------
    /**
     * Inner class to view the elements.
     */
    private class Values extends AbstractCollection {

        public Iterator iterator() {
            return new ValueIterator();
        }

        public int size() {
            int compt = 0;
            Iterator it = iterator();
            while (it.hasNext()) {
                it.next();
                compt++;
            }
            return compt;
        }

        public void clear() {
            MultiHashMap.this.clear();
        }

    }

    /**
     * Inner iterator to view the elements.
     */
    private class ValueIterator implements Iterator {
        private Iterator backedIterator;
        private Iterator tempIterator;

        private ValueIterator() {
            backedIterator = data.values().iterator();
        }

        private boolean searchNextIterator() {
            while (tempIterator == null || tempIterator.hasNext() == false) {
                if (backedIterator.hasNext() == false) {
                    return false;
                }
                tempIterator = ((Collection) backedIterator.next()).iterator();
            }
            return true;
        }

        public boolean hasNext() {
            return searchNextIterator();
        }

        public Object next() {
            if (searchNextIterator() == false) {
                throw new NoSuchElementException();
            }
            return tempIterator.next();
        }

        public void remove() {
            if (tempIterator == null) {
                throw new IllegalStateException();
            }
            tempIterator.remove();
        }

    }

    //-----------------------------------------------------------------------
    /**
     * Clones the map creating an independent copy.
     * <p>
     * The clone will shallow clone the collections as well as the map.
     * 
     * @return the cloned map
     */
    public Object clone() {
        MultiHashMap<K,V> cloned = null;
        HashMap<K,V> result = null;
        try { 
          cloned = (MultiHashMap<K,V>) super.clone();
        } catch (CloneNotSupportedException e) { 
            // assert false;
        }
        cloned.data = (HashMap<K,Collection<V>>)data.clone();
        
        // clone each Collection container
        for (Iterator it = cloned.entrySet().iterator(); it.hasNext();) {
            Map.Entry<K,Collection<V>> entry = (Map.Entry<K,Collection<V>>) it.next();
            Collection<V> coll = (Collection<V>) entry.getValue();
            Collection<V> newColl = createCollection(coll);
            entry.setValue(newColl);
        }
        return cloned;
    }

    /** 
     * Creates a new instance of the map value Collection container.
     * <p>
     * This method can be overridden to use your own collection type.
     *
     * @param coll  the collection to copy, may be null
     * @return the new collection
     */
    protected Collection<V> createCollection(Collection<V> coll) {
        if (coll == null) {
            return new ArrayList<V>();
        } else {
            return new ArrayList<V>(coll);
        }
    }

    /**
     * @see de.harper_hall.util.collections.MultiMap#mergeAllSet(java.util.Map)
     */
    public void mergeAllSet(Map<? extends K, Set<? extends V>> t) throws UnsupportedOperationException, ClassCastException, IllegalArgumentException, NullPointerException {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException();
    }

    /**
     * @see de.harper_hall.util.collections.MultiMap#mergeAllSingle(java.util.Map)
     */
    public void mergeAllSingle(Map<? extends K, ? extends V> t) throws UnsupportedOperationException, ClassCastException, IllegalArgumentException, NullPointerException {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException();
    }

    /**
     * @see de.harper_hall.util.collections.MultiMap#putAllSet(java.util.Map)
     */
    public void putAllSet(Map<? extends K, Set<? extends V>> t) throws UnsupportedOperationException, ClassCastException, IllegalArgumentException, NullPointerException {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException();
    }

    /**
     * @see de.harper_hall.util.collections.MultiMap#putAllSingle(java.util.Map)
     */
    public void putAllSingle(Map<? extends K, ? extends V> t) throws UnsupportedOperationException, ClassCastException, IllegalArgumentException, NullPointerException {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException();
    }

    /**
     * @see java.util.HashMap#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
      return data.containsKey(key);
    }

    /**
     * Warning: this function is not implemented and will return null!!!
     * @return null
     * @see java.util.HashMap#entrySet()
     */
    public Set<Entry<K, V>> entrySet() {
      return null;
    }


    /**
     * @see java.util.HashMap#remove(java.lang.Object)
     */
    public V remove(Object key) {
      Collection<V> col = data.get(key);
      if(col == null) return null;
      
      Iterator iter = col.iterator();
      if((iter == null)||(!iter.hasNext())) return null;
      
      return (V)iter.next();
    }

    /**
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(Map<? extends K, ? extends V> t) {
      Iterator iter;
      for(iter = t.entrySet().iterator(); iter.hasNext();){
        Map.Entry entry = (Map.Entry)iter.next();
        K key = (K)entry.getKey();
        Collection<V> col = createCollection(data.get(key));
        col.add((V)entry.getValue());
        data.put(key,col);
      }
    }

    
    //###############################################################################################
    //#                                          Delegates                                          #
    //###############################################################################################


    /**
     * @see java.util.AbstractMap#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
      return data.equals(o);
    }

    /**
     * @see java.util.AbstractMap#hashCode()
     */
    public int hashCode() {
      return data.hashCode();
    }

    /**
     * @see java.util.HashMap#isEmpty()
     */
    public boolean isEmpty() {
      return data.isEmpty();
    }

    /**
     * @see java.util.HashMap#keySet()
     */
    public Set<K> keySet() {
      return data.keySet();
    }

    /**
     * @see java.util.HashMap#put(K, V)
     */
    public Collection<V> put(K key, Collection<V> value) {
      return data.put(key, value);
    }

    /**
     * @see java.util.HashMap#size()
     */
    public int size() {
      return data.size();
    }

    /**
     * @see java.util.AbstractMap#toString()
     */
    public String toString() {
      return data.toString();
    }
    
}
