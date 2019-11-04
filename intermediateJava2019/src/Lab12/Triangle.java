//Nathan Frazier
public class Triangle extends Polygon {
	
	double a;
	double b;
	double c;
	
	Triangle(double sideA , double sideB , double sideC) {
		super("Triangle", 3);
		a = sideA;
		b = sideB;
		c = sideC;
	}

	public int getSideCount() {
		return this.sidecount;
	}
	
	public double getArea() {
		double s = getPerimeter() / 2;
		return Math.sqrt(s * (s - a ) * ( s - b ) * ( s - c ) );
	}
	
	
	public double getPerimeter() {
		return a + b + c;
	}

	@Override
	public double getLength() {
		
		return 0;
	}

	@Override
	public double getWidth() {
		return 0;
	}
}
