package PA2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// Nathan Frazier
public class AI_PA2_ASTAR {
// Game plan: adapt code from previous assignment to create a method that take in a file name
// as a parameter and returns a 2D MATRIX
	
    public static void print2D(int mat[][]) 
    { 
        // Loop through all rows 
        for (int i = 0; i < mat.length; i++) {
  
            // Loop through all elements of current row 
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " "); 
            }
            System.out.println();
        }
    }

	private void uniformAStar() {
		// Asar = USC if heuristics = 0
		
		
	}
	
	private void AStar() {
		
		
	}
	
	
	public static void main(String[] args) {
		// Initialize
		Populator populator = new Populator();
		int count1 = -1;
		int count2 = -1;
		String [] verts1= null;
		String [] verts2= null;
		int [] heurs1= null;
		int [] heurs2= null;
		int [] [] adjMatrix1= null;
		int [] [] adjMatrix2= null;
		
		try {
			verts1 = populator.readVerts("PA2/PA2 Part 1 Names.csv");
			verts2 = populator.readVerts("PA2/PA2 Part 2 Names.csv");
			
			count1 = verts1.length;
			count2 = verts2.length;
			
			heurs1 = populator.readHeurs("PA2/PA2 Part 1 Heuristics.csv", count1);
			heurs2 = populator.readHeurs("PA2/PA2 Part 2 Heuristics.csv", count2);
			
			adjMatrix1 = populator.createAdjMatrix("PA2/PA2 Part 1 Distances.csv", count1 );
			adjMatrix2 = populator.createAdjMatrix("PA2/PA2 Part 2 Distances.csv", count2 );
			
		} catch (Exception e) {
			System.err.println("INIT. ERROR");
			e.printStackTrace();
		}
		
		System.out.println("count1 = " + count1);
		System.out.println("Names1 = " + Arrays.toString(verts1));
		System.out.println("Heurs1 = " + Arrays.toString(heurs1) + " | len match = " + ( heurs1.length == verts1.length) );
		System.out.println("count2 = " + count2);
		System.out.println("Names2 = " + Arrays.toString(verts2));
		System.out.println("Heurs2 = " + Arrays.toString(heurs2) + " | len match = " + ( heurs2.length == verts2.length));
		System.out.println("---ADJ1---");
		print2D(adjMatrix1);
		System.out.println("---ADJ2---");
		print2D(adjMatrix2);
		// END Initialize
		
		
		
	}

	
	
	
}

//Segment everything that has to do with input and parsing csv into populator class...
class Populator {
	public String [] readVerts(String vertFileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(vertFileName));
		String str = scan.next();
		scan.close();
		return str.split(",");
	}
	
	public int [] readHeurs(String heuristicFilename, int vertexCount) throws FileNotFoundException {
		int [] heuristics = new int [vertexCount]; 
		Scanner scan = new Scanner(new File(heuristicFilename));
		String line = scan.nextLine();
		String [] vals = line.split(",");
		int i = 0;
		for ( String val : vals ) {
			heuristics [i] = Integer.valueOf(val);
			i++;
		}
		return heuristics;
	}
	
	public int [] [] createAdjMatrix(String csvName, int vertexCount) throws FileNotFoundException {
		Scanner matrixReader = new Scanner(new File(csvName));
		int [] [] adj_matrix = new int [vertexCount] [vertexCount]; //adj matrix are always squared
		
		for (int y = 0; y < vertexCount; y++) {
			String currentRow = matrixReader.nextLine(); // once every column has been visited, bump next row to scanner
			String [] rowVals = currentRow.split(",");
			for (int x = 0; x < vertexCount; x++) {
				// matrix syntax [ row ] [ col ]
				//System.out.println(matrixReader.next());
				adj_matrix [y] [x] = Integer.valueOf(rowVals[x]);
			}
			
		}
		matrixReader.close();
		return adj_matrix;
	}
	
}