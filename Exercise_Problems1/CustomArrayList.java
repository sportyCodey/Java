import java.util.ArrayList;

public class CustomArrayList<E> {
	private ArrayList<E> list = new ArrayList<>();

	public void addThis(E o) {
		list.add(o);
	}

	public E setThis(int index, E o) {
		E temp = list.get(index);
		list.set(index, o);
		return temp;
	}

	public int getSize() {
		return list.size();
	}

	public E getThis(int index) {
		E o = list.get(index);
		return o;
	}

	public void shuffle(CustomArrayList<E> list) {
		for (int i = 0; i < list.getSize(); i++) {
			int random = getRandom(list);
			E o = list.setThis(random, list.getThis(i));
			list.setThis(i, o);
		}
	}

	/* just to give an example of this. Also, we could have put <E extends Number>
	* in the shuffle and getRandom methods if we only wanted Numbers to be passed in */
	public <E extends Comparable> void sort() {

	}

	public String toString() {
		return list.toString();
	}

	private int getRandom(CustomArrayList<E> list) {
		return (int)(Math.random() * list.getSize());
	}


	public static void main(String[] args) {
		CustomArrayList<Integer> list = new CustomArrayList<>();

		list.addThis(30);
		list.addThis(3000);
		list.addThis(20);
		list.addThis(1);
		list.addThis(3444444);
		list.addThis(1000);
		list.addThis(-90);
		list.addThis(3);

		list.shuffle(list);

		System.out.println(list);
	}

}//end class