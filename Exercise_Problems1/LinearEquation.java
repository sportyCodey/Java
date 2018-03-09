//This program creates the class LinearEquation

public class LinearEquation{
	private double a;
	private double b;
	private double c;
	private double d;
	private double e;
	private double f;

	public LinearEquation(){
		this(0,0,0,0,0,0);
	}

	public LinearEquation(double someA, double someB, double someC, double someD, double someE, double someF){
		this.a = someA;
		this.b = someB;
		this.c = someC;
		this.d = someD;
		this.e = someE;
		this.f = someF;
	}

	public double getA(){
		return a;
	}

	public double getB(){
		return b;
	}

	public double getC(){
		return c;
	}

	public double getD(){
		return d;
	}

	public double getE(){
		return e;
	}

	public double getF(){
		return f;
	}

	public double getX(){
		double xSolution = ((e*d)-(b*f))/((a*d)-(b*c));
		return xSolution;
	}

	public double getY(){
		double ySolution = ((a*f)-(e*c))/((a*d)-(b*c));
		return ySolution;
	}

	public boolean isSolvable(){
		if (a*d - b*c != 0)
			return true;
			else return false;
	}
}//end class LinearEquations



