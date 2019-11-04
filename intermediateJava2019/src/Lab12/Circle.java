//Nathan Frazier
public class Circle extends Shape {

	double radius;
	
	Circle(double radius) {
	super("Circle");
	this.radius = radius;
	
	}

	public double getRadius() {
		return radius;
	}
	
	public double getArea() {
		//Area = PI * area of radius
		//Calculates area on the fly
		return (Math.PI) * Math.pow(radius, 2);
	}
	
	public double getPerimeter() {
		// Circumference = 2 * ( PI * radius )
		return 2 * ( (Math.PI) * radius );
	}
	
	public int getSideCount() {
		return 0;
	}



	@Override
	public double getLength() {
		
		return radius;
	}



	@Override
	public double getWidth() {
		
		return radius;
	}
	
	
}
