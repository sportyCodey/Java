//this program tests class Course

import java.util.Scanner;

public class TestCourse {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter relevant info separated by commas: Course: __, Enrollment: __, numOfTA's: __");
		String user = input.nextLine();

	   	Course course = new Course(user);

		System.out.println(course.toString());
	}

}
