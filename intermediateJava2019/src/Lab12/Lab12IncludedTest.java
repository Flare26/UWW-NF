public final class Lab12IncludedTest{
	
	//Nathan Frazier
    public static void main( String[] args ){
        Shape[] shapes = {
                new Rectangle( 10d , 5d ),
                new Square( 4d ),
                new Triangle( 8d , 8d , 8d ),
                new Triangle( 10d , 4d , 10.77d ),
                new Circle( 3.5d ),
                new Parallelogram( 2d , 2.5d , 1.8d ) ,
                new Rectangle( 7d , 7d )
            };

        System.out.println( "\f" );
        System.out.println( "Lab 12\n" );

        //TODO:	For each Shape in the array, print out its name, area, and perimeter
        int index = 0;
        System.out.println("---INSTANCES OF SHAPE---");
        for (Shape s : shapes) {
        	System.out.printf("Index [%d] :\n" , index);
        	System.out.println(s.toString());
        	index++;
        }
        
        //TODO:	For each Polygon in the array, print out its name and number of sides
        index = 0;
        System.out.println("---INSTANCES OF POLYGON---");
        for (Shape s : shapes) {
        	if ( s instanceof Polygon ) {
	        	System.out.printf("Index [%d] :\n" , index);
	        	System.out.println("Name : " + s.getName());
	        	System.out.println("Sides : " + s.getSideCount());
	        	System.out.println();
        	}
        	index++;
        }
        //TODO:	For each Rectangle in the array, print out its name, length, width, area, and perimeter
        index = 0;
        System.out.println("---INSTANCES OF RECTANGLE---");
        for (Shape s : shapes) {
        	if ( s instanceof Rectangle) {
        		System.out.printf("Index [%d] :\n" , index);
        		System.out.println("Name : " + s.getName());
        		System.out.println("Length : " + s.getLength() );
        		System.out.println("Width : " + s.getWidth());
        		System.out.println("Area : " + s.getArea());
        		System.out.println("Perimeter : " + s.getPerimeter());
        		System.out.println();
        	}
        	index++;
        }
        //TODO:	For each Square in the array, print out its name, length, area, and perimeter
        index = 0;
        System.out.println("---INSTANCES OF SQUARE---");
        for (Shape s : shapes) {
        	if ( s instanceof Square) {
        		System.out.printf("Index [%d] :\n" , index);
        		System.out.println("Name : " + s.getName());
        		System.out.println("Length : " + s.getLength() );
        		System.out.println("Area : " + s.getArea());
        		System.out.println("Perimeter : " + s.getPerimeter());
        		System.out.println();
        	}
        	index++;
        }
        //TODO:	For each Circle in the array, print out its name, radius, area, and circumference (perimeter)
        
        index = 0;
        System.out.println("---INSTANCES OF CIRCLE---");
        for (Shape s : shapes) {
        	if ( s instanceof Circle) {
        		System.out.printf("Index [%d] :\n" , index);
        		System.out.println("Name : " + s.getName());
        		System.out.println("Radius : " +  s.getLength() );
        		System.out.println("Area : " + s.getArea());
        		System.out.println("Circumference : " + s.getPerimeter());
        		System.out.println();
        	}
        	index++;
        }
    }
}