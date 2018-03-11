//defines Comparable class WordOccurrence 

public class WordOccurence implements Comparable<WordOccurence> {
	private String word;
	private int count;

	public WordOccurence(String word, int count) {
		this.word = word;
		this.count = count;
	}

	public WordOccurence(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return word + "\t\t" + count;
	}

	@Override
	public int compareTo(WordOccurence o) {
		if (count < o.count)
			return -1;
		else if (count > o.count)
			return 1;
		else
			return 0;
	}

}//end class
