//Nathan Frazier
public abstract class Quadrilateral extends Polygon {
	double l;
	double w;
	
	Quadrilateral(String name, int sides) {
		super(name , sides);
	}
	
	Quadrilateral(String name , int sides , double length, double width) {
		super(name , sides);
		l = length;
		w = width;
	}
	
	public double getArea() {
		return l * w;
	}

	public double getPerimeter() {
		
		return 2 * ( l + w );
	}
}
