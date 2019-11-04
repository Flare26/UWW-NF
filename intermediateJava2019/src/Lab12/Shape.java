
public abstract class Shape {
	String name;
	double area;
	
	Shape(String name) {
		this.name = name;
	}
	
	public String getName( ) { 
		return name;
	}
	
	public abstract int getSideCount();
	
	public abstract double getLength();
	
	public abstract double getWidth();
	
	public abstract double getPerimeter();
	
	public abstract double getArea();
	
	public String toString() {
		
		return String.format("Shape name : %s\nArea : %f\nPerimeter : %f\n", name, this.getArea(), this.getPerimeter());
		
	}
	
}
