public class MyHashMapWithDoubleHashing<K, V> implements MyMap<K, V> {
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
  public MyHashMapWithDoubleHashing() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
  }

  /** Construct a map with the specified initial capacity and
   * default load factor */
  public MyHashMapWithDoubleHashing(int initialCapacity) {
    this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
  }

  /** Construct a map with the specified initial capacity
   * and load factor */
  public MyHashMapWithDoubleHashing(int initialCapacity, float loadFactorThreshold) {
    if (initialCapacity > MAXIMUM_CAPACITY)
      this.capacity = MAXIMUM_CAPACITY;
    else
      this.capacity = trimToPowerOf2(initialCapacity);

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
    int test = hashIndex;
    int j = 1;

	while (table[hashIndex] != null) {
      if (table[hashIndex].key.equals(key))
	    return table[hashIndex].value;
	  test = (hashIndex + j * hash2(hashIndex)) % capacity;
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

  @Override /** Add an entry (key, value) into the map */
  public V put(K key, V value) {
    double doubleSize = (double)size / capacity;
	if (doubleSize > 0.5) {
	  if (capacity == MAXIMUM_CAPACITY)
	    throw new RuntimeException("Exceeding maximum capacity");

	    rehash();
    }
    int hashIndex = Math.abs(hash(key.hashCode()));
    int increment = 1;
    int test = hashIndex;

    while (table[test] != null) {
		 if (table[test].key.equals(key)) {
		   V oldValue = table[test].value;
		   table[test].value = value;

		   return oldValue;
	  }
	  test = (hashIndex + increment * hash2(hashIndex)) % capacity;
	  increment++;
	}

	table[test] = new MyMap.Entry<K, V>(key, value);
	size++;

	return value;
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
	    test = (hashIndex + j * hash2(hashIndex)) % capacity;
	    j++;
    }
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

  private int hash2(int hashCode) {
	  return 7 - hashCode % 7;
  }

  /** Hash function */
  private int hash(int hashCode) {
    return supplementalHash(hashCode) & (capacity - 1);
  }

  /** Ensure the hashing is evenly distributed */
  private static int supplementalHash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }

  /** Return a power of 2 for initialCapacity */
  private int trimToPowerOf2(int initialCapacity) {
    int capacity = 1;
    while (capacity < initialCapacity) {
      capacity <<= 1;
    }

    return capacity;
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
}//end class MyHashMapWithDoubleHashing

