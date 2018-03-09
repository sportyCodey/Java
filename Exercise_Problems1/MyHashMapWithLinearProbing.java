public class MyHashMapWithLinearProbing<K, V> implements MyMap<K, V> {
  // Define the default hash table size. Must be a power of 2
  private static int DEFAULT_INITIAL_CAPACITY = 4;

  // Define the maximum hash table size. 1 << 30 is same as 2^30
  private static int MAXIMUM_CAPACITY = 1 << 30;

  // Current hash table capacity. Capacity is a power of 2
  private int capacity;

  // Define default load factor
  private static float DEFAULT_MAX_LOAD_FACTOR = 0.4f;

  // Specify a load factor used in the hash table
  private float loadFactorThreshold;

  // The number of entries in the map
  private int size = 0;

  // Hash table is an array with each cell that is a linked list
  MyMap.Entry<K,V>[] table;

  /** Construct a map with the default capacity and load factor */
  public MyHashMapWithLinearProbing() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
  }

  /** Construct a map with the specified initial capacity and
   * default load factor */
  public MyHashMapWithLinearProbing(int initialCapacity) {
    this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
  }

  /** Construct a map with the specified initial capacity
   * and load factor */
  public MyHashMapWithLinearProbing(int initialCapacity, float loadFactorThreshold) {
    this.capacity = initialCapacity;
    this.loadFactorThreshold = loadFactorThreshold;
    table = new MyMap.Entry[capacity];
  }

  @Override /** Remove all of the entries from this map */
  public void clear() {
    size = 0;
    removeEntries();
  }

  @Override /** Return true if the specified key is in the map */
  public boolean containsKey(K key) {
    if (get(key) != null)
      return true;
    else
      return false;
  }

  @Override /** Return true if this map contains the value */
  public boolean containsValue(V value) {
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null && table[i].value.equals(value))
        	return true;
    }

    return false;
  }

  @Override /** Return a set of entries in the map */
  public java.util.Set<MyMap.Entry<K,V>> entrySet() {
    java.util.Set<MyMap.Entry<K, V>> set =
      new java.util.HashSet<MyMap.Entry<K, V>>();

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
          set.add(table[i]);
      }
    }

    return set;
  }

  @Override /** Return the value that matches the specified key */
  public V get(K key) {
    int hashIndex = hash(key.hashCode());
    int j = 1;
    int test = hashIndex;

	while (table[test] != null) {
      if (table[test].key.equals(key))
	    return table[test].value;
	  test = (hashIndex + j) % capacity;
	  j++;
	}
    return null;
  }

  @Override /** Return true if this map contains no entries */
  public boolean isEmpty() {
    return size == 0;
  }

  @Override /** Return a set consisting of the keys in this map */
  public java.util.Set<K> keySet() {
    java.util.Set<K> set = new java.util.HashSet<K>();

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
          set.add(table[i].key);
      }
    }

    return set;
  }

  public java.util.ArrayList<Object> getNullList() {
	  java.util.ArrayList<Object> list = new java.util.ArrayList<>();

	  for (int i = 0; i < capacity; i++) {
		  if (table[i] == null)
		  	list.add(-1);
		  else
		  	list.add(table[i].key);
	  }

	  return list;
  }

  @Override /** Add an entry (key, value) into the map */
  public V put(K key, V value) {
    int hashIndex = hash(key.hashCode());
    int increment = 1;
    int test = hashIndex;

    while (table[test] != null) {
	  if (table[test].key.equals(key)) {
		   V oldValue = table[test].value;
		   table[test].value = value;

           return oldValue;
	  }
	  test = (hashIndex + increment) % capacity;
	  increment++;
	}

	table[test] = new MyMap.Entry<K, V>(key, value);
	size++;

	double loadFactor = (double)size / capacity;
	if (loadFactor > 0.5) {
	  if (capacity == MAXIMUM_CAPACITY)
	    throw new RuntimeException("Exceeding maximum capacity");

      rehash();
    }

	return value;
  }

  public int putAndGetHashCode(K key, V value) {
      int hashIndex = hash(key.hashCode());
      int increment = 1;
      int test = hashIndex;

      while (table[test] != null) {
  	  if (table[test].key.equals(key)) {
  		   V oldValue = table[test].value;
  		   table[test].value = value;

             return -1;
  	  }
  	  test = (hashIndex + increment) % capacity;
  	  increment++;
  	}

  	table[test] = new MyMap.Entry<K, V>(key, value);
  	size++;

  	double loadFactor = (double)size / capacity;
  	if (loadFactor > 0.5) {
  	  if (capacity == MAXIMUM_CAPACITY)
  	    throw new RuntimeException("Exceeding maximum capacity");

        rehash();
      }

  	return test;
  }

  @Override /** Remove the entries for the specified key */
  public void remove(K key) {
    int hashIndex = hash(key.hashCode());
    int test = hashIndex;
    int j = 1;

    while (table[test] != null) {
		if (table[test].key.equals(key)) {
			table[test] = null;
			size--; // Decrease size
			break;
		}
	    test = (test + j) % capacity;
	    j++;
    }
  }

  public int removeAndGetHashCode(K key) {
      int hashIndex = hash(key.hashCode());
      int test = hashIndex;
      int j = 1;

      while (table[test] != null) {
  		if (table[test].key.equals(key)) {
  			table[test] = null;
  			size--; // Decrease size
  			break;
  		}
  	    test = (test + j) % capacity;
  	    j++;
      }

      return test;
  }

  @Override /** Return the number of entries in this map */
  public int size() {
    return size;
  }

  @Override /** Return a set consisting of the values in this map */
  public java.util.Set<V> values() {
    java.util.Set<V> set = new java.util.HashSet<V>();

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
          set.add(table[i].value);
      }
    }

    return set;
  }

  /** Hash function */
  private int hash(int hashCode) {
    return hashCode % capacity;
  }

  /** Remove all entries from each bucket */
  private void removeEntries() {
    for (int i = 0; i < capacity; i++) {
        table[i] = null;
    }
  }

  /** Rehash the map */
  private void rehash() {
    java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
    capacity <<= 1; // Double capacity
    table = new MyMap.Entry[capacity]; // Create a new hash table
    size = 0; // Reset size to 0

    for (Entry<K, V> entry: set) {
      put(entry.getKey(), entry.getValue()); // Store to new table
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");

    for (int i = 0; i < capacity; i++) {
      if (table[i] != null)
          builder.append(table[i].toString());
    }

    builder.append("]");
    return builder.toString();
  }
}//end class MyHashMapWithLinearProbing

