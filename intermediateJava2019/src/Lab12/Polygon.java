//Nathan Frazier
public abstract class Polygon extends Shape {
	
	int sidecount;
	double sideL;
	double sideW;
	
	Polygon(String name , int sides) {
		super(name);
		sidecount = sides;
	}
	
}
