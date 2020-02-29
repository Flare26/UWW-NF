package PA2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Nathan Frazier
public class AI_PA2_ASTAR {
// Game plan: adapt code from previous assignment to create a method that take in a file name
// as a parameter and returns a 2D MATRIX
	
	private static final int INFINITY = 999999999;
	static int gScore; // current optimal path cost from source to given vertex NUMBER MOVES IT TOOK TO GET HERE
	static int hScore; // heuristic estimate distance from node to goal 
	static int fScore; // gScore + hScore ( notes get dequeued from open set if they have the lowest fScore)
	
	static String [] verts1= null;
	static String [] verts2= null;
	static int [] heurs1= null;
	static int [] heurs2= null;
	static int [] [] adjMatrix1= null;
	static int [] [] adjMatrix2= null;
	
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

    private ArrayList<Integer> uniformCostSearch (int source, int target, String [] verts, int [] [] matrix){
		
    	ArrayList<Integer> openSet = new ArrayList<Integer>();
    	ArrayList<Integer> closedSet = new ArrayList<Integer>();
    	double [] distances = new double [verts.length];
    	int [] prev = new int [verts.length];
    	
    	for (int node = 0 ; node < verts.length ; node++) {
    		distances[node] = Double.MAX_VALUE;
    		prev[node] = -1;
    	}
    	
    	openSet.add(source);
    	
    	distances[source] = 0;
    	
    	while ( openSet.isEmpty() == false ) {
    		// use distances array maybe?
    		
    	}
    	
    	
    	return null;
    }
	
	private void AStar() {
		
		
	}
	
	private static ArrayList<Integer> getCurrentNeighbors(int currentIndex, int vertexCount, double [] distances, int [] [] adj_matrix) {
		// Plan: take current index and set it as the row. advance through the columns checking for values > 0.
		// If a neighbor is found, make note of the index 
		ArrayList<Integer> neighbors = new ArrayList<Integer> ();
		for ( int i = 0; i < vertexCount; i++ ) {
			 if ( adj_matrix [currentIndex] [i] > 0 )
			 neighbors.add(i); // If a neighbor is found by , make note of it's index
			 distances[i] = Double.valueOf(adj_matrix[currentIndex].toString());
		}
		
		return neighbors;
	}
	
	
	public static void main(String[] args) {
		// Initialize
		Populator populator = new Populator();
		int count1 = -1;
		int count2 = -1;
		
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
	
	// Node class pleas and thank you
	class Node {
		int idx;
		String label;
		
		
	}
	
}