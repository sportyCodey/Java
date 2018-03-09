class Course {

	private String[] info = new String[4];
	private String input;

	public Course(String input) {
		this.input = input;
	}

	public String toString() {
		int k = input.indexOf(',');
		String courseName = input.substring(0,k);
		info[0] = courseName;
		int m = courseName.length() + 1;
		int l = input.indexOf(',', m);
		String enrollment = input.substring(k + 1,l);
		info[1] = enrollment;
	    String TA = input.substring(l + 1);
	    info[2] = TA;
	    if (Integer.valueOf(info[2]) * 30 >= Integer.valueOf(info[1])) {
			info[3] = "Well Covered";
		}
		else {
			info[3] = "Poorly Covered";
		}
		return "Course:  " + info[0].toUpperCase() + ", enrollment  " + info[1] +
		", teaching assistants:  " + info[2] + ", " + info[3];
	}

}