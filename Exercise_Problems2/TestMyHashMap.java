public class TestMyHashMap {
  public static void main(String[] args) {

	MyMap<String, Integer> map3 = new MyHashMapWithDoubleHashing<>();

	map3.put("Smith", 30);
	map3.put("Anderson", 31);
	map3.put("Lewis", 29);
	map3.put("Cook", 29);
	System.out.println(map3.put("Smith", 65));

	System.out.println("Entries in map3: " + map3);

	System.out.println("The age for " + "Lewis is " +
	map3.get("Lewis"));

	System.out.println("Is Smith in the map3? " +
	map3.containsKey("Smith"));
	System.out.println("Is age 33 in the map3? " +
	map3.containsValue(33));

	System.out.println("LOOP THROUGH ENTRIES: ");
	java.util.Set<MyMap.Entry<String, Integer>> set3 = map3.entrySet();
	for (MyMap.Entry<String, Integer> entry: set3) {
	  System.out.println(entry);
    }

	map3.remove("Smith");
	System.out.println("Entries in map3 after removing Smith: " + map3);

	map3.clear();
	System.out.println("Entries in map3: " + map3);

	System.out.println("*******************************************************************************************************************");

	// Create a map
	MyMap<String, Integer> map1 = new MyHashMapWithLinearProbing<>();

    map1.put("Smith", 30);
	map1.put("Anderson", 31);
	map1.put("Lewis", 29);
	map1.put("Cook", 29);
    System.out.println(map1.put("Smith", 65));

	System.out.println("Entries in map1: " + map1);

	System.out.println("The age for " + "Lewis is " +
	map1.get("Lewis"));

	System.out.println("Is Smith in the map1? " +
	map1.containsKey("Smith"));
	System.out.println("Is age 33 in the map1? " +
	map1.containsValue(33));

	System.out.println("LOOP THROUGH ENTRIES: ");
	java.util.Set<MyMap.Entry<String, Integer>> set1 = map1.entrySet();
	for (MyMap.Entry<String, Integer> entry: set1) {
	  System.out.println(entry);
    }

	map1.remove("Smith");
	System.out.println("Entries in map1 after removing Smith: " + map1);

	map1.clear();
	System.out.println("Entries in map1: " + map1);

	System.out.println("***************************************************************************************************************");

    // Create a map
    MyMap<String, Integer> map = new MyHashMap<String, Integer>();
    map.put("Smith", 30);
    map.put("Anderson", 31);
    map.put("Lewis", 29);
    map.put("Cook", 29);
    System.out.println(map.put("Smith", 65));

    System.out.println("Entries in map: " + map);

    System.out.println("The age for " + "Lewis is " +
      map.get("Lewis"));

    System.out.println("Is Smith in the map? " +
      map.containsKey("Smith"));
    System.out.println("Is age 33 in the map? " +
      map.containsValue(33));

    System.out.println("LOOP THROUGH ENTRIES: ");
    java.util.Set<MyMap.Entry<String, Integer>> set = map.entrySet();
	for (MyMap.Entry<String, Integer> entry: set) {
	  System.out.println(entry);
    }

    map.remove("Smith");
    System.out.println("Entries in map: " + map);

    map.clear();
    System.out.println("Entries in map: " + map);

  }
}

