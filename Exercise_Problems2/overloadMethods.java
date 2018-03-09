public class overloadMethods{
    public static void printMe(char c, int n){
	for(int i = 0; i < n; i++){
		System.out.print(c);
    }
	System.out.println("");
    }

    public static void printMe(int someInt, int n){
	for(int i = 0; i < n; i++){
		System.out.print(someInt);
 	}
	System.out.println("");
    }

    public static void printMe(double someDouble, char c, int n){
	for(int i = 0; i < n; i++){
		System.out.print(someDouble);
		System.out.print(c);
	}
	System.out.println("");
    }

    public static void printMe(String s, int n){
	for(int i = 0; i < n; i++){
		System.out.print(s);
    }
	System.out.println("");
	}
    public static void main(String[] args){
	printMe('g', 4);
	printMe(3, 4);
	printMe(45.6,'b', 4);
    }
}