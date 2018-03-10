//counts the occurrence of words

import java.util.*;

public class CountOccurrenceOfWords {
	public static void main(String[] args) {
    	// Set text in a string
    	String text = "Sleeping in is not good. Sleeping in causes lazyness. Sleeping in sucks! " +
    	"So I recommend you to stop! If you don't stop sleeping in, then you might turn into a potato. Yes, a potato! " +
    	"It is what I recommend";

    	// Create a ArrayList to hold words as key and count as value
    	List<WordOccurence> list = new ArrayList<>();
    	/* Why can't you use a tree set? You can't use index with set! */

    	String[] words = text.split("[ \n\t\r.,;:!?(){}]");
    	for (int i = 0; i < words.length; i++) {
      		String key = words[i].toLowerCase();

    	  	if (key.length() > 0) {
    	  		if (!isDuplicate(list, key)) {
    	  			list.add(new WordOccurence(key, 1));
    	  		}
    	  		else {
					//int value = getValue(list, key);
    	  		   	//value++;
    	  		   	int index = getIndex(list, key);
    	  		   	list.get(index).setCount(list.get(index).getCount() + 1);
    	  		}
    	  	}
		}

		Collections.sort(list);

   		for (WordOccurence entry: list) {
			System.out.println(entry);
		}
 	}//end main

 	public static boolean isDuplicate(List<WordOccurence> list, String key) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getWord().equals(key))
				return true;
		}
		return false;
	}
/*
	public static int getValue(List<WordOccurence> list, String key) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getWord().equals(key))
				return list.get(i).getCount();
		}
		return -1;
	}
*/
	public static int getIndex(List<WordOccurence> list, String key) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getWord().equals(key))
				return i;
		}
		return -1;
	}

}//end class

