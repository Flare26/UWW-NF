//Nathan Frazier
public class Rectangle extends Quadrilateral {

	Rectangle(double length, double width) {
		super("Rectangle" , 4 , length, width);
	}
	
	Rectangle (String name, int side_count , double side_length) {
		super(name , side_count , side_length , side_length);
	}
	
	public int getSideCount() {
		return this.sidecount;
	}
	
	public double getLength() {
		return l;
	}
	
	public double getWidth() {
		return w;
	}
}
