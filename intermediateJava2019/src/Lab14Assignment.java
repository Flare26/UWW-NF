// Nathan Frazier Lab 14 enum assignment

enum Direction {
		NORTH, EAST, SOUTH, WEST;
	}

public class Lab14Assignment {

	// Create 5 different treasure maps using different # of instructions
	
	public static void main(String [] args) {
		
		TreasureMap m1 = new TreasureMap(Direction.EAST, Direction.NORTH, 8 , 4);
		TreasureMap m2 = new TreasureMap(Direction.EAST, Direction.SOUTH, Direction.SOUTH, 9, 5, 9);
		TreasureMap m3 = new TreasureMap(Direction.WEST, Direction.NORTH, Direction.EAST, Direction.NORTH, 2,7,4,5);
		TreasureMap m4 = new TreasureMap(Direction.SOUTH, Direction.EAST, Direction.SOUTH, Direction.EAST, Direction.NORTH, 8,3,8,3,5);
		TreasureMap m5 = new TreasureMap();
		
		
		System.out.printf("--- MAP %d --- \n" , 1);
		System.out.println(m1.toString());
		System.out.printf("--- MAP %d --- \n" , 2);
		System.out.println(m2.toString());		
		System.out.printf("--- MAP %d --- \n" , 3);
		System.out.println(m3.toString());		
		System.out.printf("--- MAP %d --- \n" , 4);
		System.out.println(m4.toString());		
		System.out.printf("--- MAP %d --- \n" , 5);
		System.out.println(m5.toString());
		
		System.out.println("! CLONE & INVERT MAP 4 !");
		TreasureMap invert = m4.invertMap(); // return TreasureMap Object but it's cardinals invertned
		System.out.println(invert.toString());
		
	}
	
	
}
