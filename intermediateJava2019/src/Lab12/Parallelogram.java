//Nathan Frazier
public class Parallelogram extends Quadrilateral {
	
	double h;
	double a; //diagonal
	double b;

	Parallelogram(double height , double aDiag , double bSide) {
		super("Parallelogram" , 4);
		h = height;
		a = aDiag;
		b = bSide;
	}
	
	
	public double getLength() {
		return a;
	}
	
	public double getWidth() {
		return b;
	}
	
	public int getSideCount() {
		return this.sidecount;
	}
	
	public double getPerimeter() {
		return h + a + b;
	}
	
	public double getArea() {
		return b * h;
	}
	
	public String toString() {
		return String.format("Shape name : %s\nArea : %f\nPerimeter : %f\n", name, this.getArea(), this.getPerimeter());
	}
}
