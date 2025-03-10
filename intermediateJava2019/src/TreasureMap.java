// Nathan Fraier TreasureMap for Lab14
// Create class with no intention of changing vars once set 
public class TreasureMap {
	
	public Instruction [] instruction_List; //Contains up to 5 instruction objects
	
	TreasureMap() {
		this.instruction_List = new Instruction [2];
		instruction_List[0] = new Instruction();
		instruction_List[1] = new Instruction(Direction.SOUTH , 10);
		System.out.println("This treasure map doesn't seem very useful...");
	}
	
	TreasureMap(Instruction [] list) {
		// If only instruction [] is being passed in, it MUST be a clone
		this.instruction_List = list;
		
	}
	
	// 2 instuctions
	TreasureMap(Direction d, Direction e, int dp , int ep) {
		instruction_List = new Instruction [] { new Instruction(d , dp) , new Instruction(e , ep) };
	}
	// 3 inst
	TreasureMap(Direction d, Direction e, Direction f, int dp, int ep, int fp) {
		instruction_List = new Instruction [] { new Instruction(d , dp) , new Instruction(e , ep) , new Instruction( f , fp ) };
	}
		
	// 4 inst
	TreasureMap(Direction d, Direction e, Direction f, Direction g , int dp, int ep, int fp, int gp) {
		instruction_List = new Instruction [] { new Instruction(d , dp), new Instruction(e, ep), new Instruction(f, fp), new Instruction(g, gp) };
	}
	// 5 inst
	TreasureMap(Direction d, Direction e, Direction f, Direction g, Direction h, int dp, int ep, int fp, int gp, int hp) {
		instruction_List = new Instruction [] { new Instruction(d , dp), new Instruction(e, ep), new Instruction(f, fp), new Instruction(g, gp), new Instruction(h , hp) };
		
	}
	
	public TreasureMap invertMap() {
		Instruction [] list_copy = instruction_List; // Create copy of this map's directions to reverse
		for ( Instruction x : list_copy) {
			// Switch checks the Instruction object at x and inverts the D direction
			
			switch (x.getDir()) {
			
				case NORTH :
					x.D = Direction.SOUTH;
					break;
				case SOUTH :
					x.D = Direction.NORTH;
					break;
				case EAST : 
					x.D = Direction.WEST;
					break;
				case WEST:
					x.D = Direction.EAST;
					break;
			}	// OUT OF SWITCH STATEMENT	
			
		}
		
		TreasureMap clone = new TreasureMap(list_copy);
		return clone;
		// reversed the directions of current map and created a clone to return
	}

	public String getInstruction(int index) {
		return instruction_List[index].toString();
	}
	
	public String toString() {
		String response = "";
		
		for ( Instruction x : instruction_List ) {
			response += x.toString();
		}
		
		return response;
	}
	
	
	private class Instruction {
		// An instruction takes a DIRECTION and # PACES.
		Direction D;
		int PACES;
		
		Instruction() {
			this.D = Direction.NORTH;
			this.PACES = 10;
		}
		
		Instruction(Direction d , int paces) {
			D = d;
			PACES = paces;
		}
		
		public int getPaces() {
			return PACES;
		}
		
		public Direction getDir( ) {
			return D;
		}
		
		public String toString() {
			return String.format("- %d paces to the %s\n", PACES, D );
		}
	}
}
