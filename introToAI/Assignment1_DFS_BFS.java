import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//Nathan Frazier
import java.util.Stack;

//read in city names, this is the number of cities, and square the value 


public class Assignment1_DFS_BFS {
	private static ArrayList<String> vertexLabels = new ArrayList<String> (  );
	private static double [] [] adj_matrix;
	static int idx1 = 0;
	static int idx2 = 0;
	
    public static void print2D(double mat[][]) 
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
	
	private static void populateCity_Strs(Scanner seebot) {
		while (seebot.hasNext()) {
			vertexLabels.add(seebot.next());
		}
		
	}
	
	private static void populateMatrix(Scanner matrixReader, double [] [] adj_matrix) {
		for (int y = 0; y < vertexLabels.size(); y++) {
			String currentRow = matrixReader.nextLine(); // once every column has been visited, bump next row to scanner
			String [] rowVals = currentRow.split(",");
			for (int x = 0; x < vertexLabels.size(); x++) {
				// matrix syntax [ row ] [ col ]
				//System.out.println(matrixReader.next());
				adj_matrix [y] [x] = Double.valueOf(rowVals[x]);
			}
			
		}
		
	}
	
	
	private static String checkEdge(String c1, String c2) {
		// cities come in all uppercase
		// This is where indexes are set
		String response;
		double edge = -1;
		
		for ( int i = 0; i < vertexLabels.size(); i++) {
			String city = vertexLabels.get(i);
			if ( city.toUpperCase().contains(c1) )
					idx1 = i;
			if ( city.toUpperCase().contains(c2) )
					idx2 = i;
		}
		
		System.out.println("Index 1 = " + idx1);
		System.out.println("Index 2 = " + idx2);

		
		edge = adj_matrix [idx1] [idx2];
		
		if ( (edge < 0) || (idx1 == idx2) ) {
			response = "!! Edge either does not exist, or you entered the same city twice !!";
		} else {
			response = "Found edge: " + edge + "mi";
		}
		
		return response;
	}
	
	private static ArrayList<Integer> getCurrentNeighbors(int currentIndex) {
		// Plan: take current index and set it as the row. advance through the columns checking for values > 0.
		// If a neighbor is found, make note of the index 
		ArrayList<Integer> neighbors = new ArrayList<Integer> ();
		for ( int i = 0; i < vertexLabels.size(); i++ ) {
			 if ( adj_matrix [currentIndex] [i] > 0 )
				 neighbors.add(i); // If a neighbor is found by , make note of it's index
		}
		
		return neighbors;
	}
	
	public static boolean BFS(int source, int target) {
		boolean [] discovered = new boolean [vertexLabels.size()]; // these booleans correspond to the proper indexes of the vertexes
		Arrays.fill(discovered , false); // nifty fill method for arrays thanks google
		Queue<Integer> queue = new LinkedList<Integer> () ; // Queue is an interface, must import a class that uses it
		
		queue.add(source);
		discovered[source] = true;
		
		while ( queue.isEmpty() == false ) {
			int current = queue.remove(); // retrieved the head of the queue, which is ints in this case
			
			if ( current == target )
				return true;
			
			for ( int neighbor : getCurrentNeighbors(current) ) {
				discovered[neighbor] = true;
				queue.add(neighbor);
			}
			
			
		}
		
		return false;
		
	};
	
	private static boolean DFS(int source , int target) {
		boolean [] discovered = new boolean [vertexLabels.size()]; // these booleans correspond to the proper indexes of the vertexes
		Arrays.fill(discovered , false); // nifty fill method for arrays thanks google
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.push(source);
		
		while( stack.isEmpty() == false ){
			int current = stack.pop();
			
			if ( discovered[current] == false ) {
				discovered[current] = true; // we are discovering this vertex index
				
				if ( current == target ) // if the current location index equals the index of our target, terminate
					return true;
				
				for ( int neighbor : getCurrentNeighbors(current)) 
				{
					stack.push(neighbor);
				}
				
			}
			
		}

		return false;
		
		
	};
	
	public static void main(String[] args) {
		try {
		Scanner nameScan = new Scanner(new File("CityNames.csv"));
		Scanner matrixScan = new Scanner(new File("CityDistances.csv"));
		populateCity_Strs(nameScan.useDelimiter(",")); // add all cities to array list
		System.out.printf("\nTotal cities detected = %d\n", vertexLabels.size());
		System.out.println(vertexLabels);
		// Generates blank 2D matrix of appropriate size
		adj_matrix = new double [vertexLabels.size()] [vertexLabels.size()];
		populateMatrix(matrixScan.useDelimiter(","), adj_matrix);
		//print2D(adj_matrix);
		
		/*     Prompt the user for two city names (NOT indices) and print out the following:
        The index of each vertex (as integers)
        Whether or not an edge exists between these vertices (yes/no)
        If an edge exists, print out the edge weight (otherwise skip this step)
        Run Depth First Search and print out whether a path that connects these vertices was found (yes/no)
        Run Breadth First Search and print out whether a path that connects these vertices was found (yes/no)

		Note: you may assume that the user will input correct vertex names. You program does not need to loop. */
		
		nameScan.close();
		matrixScan.close();
		
		nameScan = new Scanner(System.in);
		
		System.out.println("Please enter any two valid city names (1/2): ");
		String city1 = nameScan.nextLine().trim().toUpperCase();
		System.out.println("Please enter any two valid city names (2/2): ");
		String city2 = nameScan.nextLine().trim().toUpperCase();
		System.out.println("Thank you!");
		nameScan.close();
		
		System.out.println(checkEdge(city1, city2));
		
		//at this point the indexes are set properly
		
		System.out.println( "DFS path = " + DFS(idx1, idx2) );
		System.out.println( "BFS path = " + BFS(idx1, idx2) );
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
