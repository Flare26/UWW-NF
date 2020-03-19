package PA2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Nathan Frazier
public class AI_PA2_ASTAR {
// Game plan: adapt code from previous assignment to create a method that take in a file name
// as a parameter and returns a 2D MATRIX
	
	
	static int gScore; // current optimal path cost from source to given vertex NUMBER MOVES IT TOOK TO GET HERE
	static int hScore; // heuristic estimate distance from node to goal 
	static int fScore; // gScore + hScore ( notes get dequeued from open set if they have the lowest fScore)
	
	static String [] verts1= null;
	static String [] verts2= null;
	static double [] heurs1= null;
	static double [] heurs2= null;
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
    
    private static ArrayList<Integer> uniformCostSearch (int source, int target, String [] verts, int [] [] matrix){
		
    	Queue<Integer> openSet = new LinkedList<Integer>();
    	Queue<Integer> closedSet = new LinkedList<Integer>();
    	double [] distances = new double [verts.length]; // also known as fScore
    	int [] prev = new int [verts.length];
    	
    	for (int node = 0 ; node < verts.length ; node++) {
    		distances[node] = Double.MAX_VALUE;
    		prev[node] = -1;
    	}
    	
    	openSet.add(source);
    	
    	distances[source] = 0;
    	
    	while ( openSet.isEmpty() == false ) {
    		//on first execution source is only node on here so use that to kick it off
    		//System.out.println("-----------");
    		//System.out.println("openSet.toString() =  " + openSet.toString());
    		// upon first execution should find source
    		int current = -1;
    		double ceil = Double.MAX_VALUE;
    		
    		for ( int idx : openSet ) {
    			if ( distances [ idx ] < ceil ) {
    				ceil = distances [ idx ];
    				current = idx;
    			}	
    		} // This should garuntee we find lowest
    		
    		//System.out.println("CURRENT now = " + current );
    		openSet.remove((Object) current); // remove via index will not work for a dynamic array like this, force object method
    		closedSet.add(current);
    		
    		if ( current == target ) {
    			//We know where each node came from, but not where it goes to
    			//So if we want a path from the source to the target...
    			//...We have to go from the target to the source then reverse the path
    			//System.out.println("--- TARGET FOUND, RECONSTRUCTING PATH ---");
    			double cost = 0;
    			ArrayList<Integer> path = new ArrayList<Integer> ();
    			while ( current != -1 ) {
    				cost += distances [ current ];
    				path.add(current);
    				current = prev[current];
    			}
    			
    			Collections.reverse(path);
    			System.out.println("\nUCS Stats:\ncost = " + cost + "\nfringe size = " + openSet.size() + "\nexplored count = " + closedSet.size() + "\nreturning path...");
    			return path;
    		} // If the end has NOT been found....
    		
    		// discover all neighbors
    		
    		// add neighbors to open set
    		//System.out.println("Finding neighbors of " + current + "...");	
    		ArrayList<Integer> neighbors = getCurrentNeighbors(openSet, current, verts.length, matrix);
    		//System.out.println("neighbors<Integer>.toString() = " + neighbors);
    		// after we have the neighbors, start to logically separate them
    		for ( int neighbor : neighbors ) {
    			
    				if ( closedSet.contains(neighbor) == false ) {
    					if ( openSet.contains(neighbor) == false )
    						openSet.add(neighbor);
    					
    					
						double edgeWeight = matrix[current][neighbor];
						
						//This is the current minimum path cost to get from source to current (distances[ current ])...
						//System.out.println("Edge for Matrix[" + current + "] [" + neighbor + "] = " + edgeWeight);
						//System.out.println("tentativeDistance = " + distances[current] + " + " + edgeWeight);
						double tentativeDistance = distances[current] + edgeWeight;
						if (tentativeDistance < distances[neighbor]) {
							//Update the distance to the new minimum path cost
							distances[neighbor] = tentativeDistance;
							prev[neighbor] = current;
						} // END IF TENTATIVE
						} // END IF closedSet check
					} // END FOR NEIGHBOR
    				
    				//System.out.println("Explored all neighbors of current!");
    			}// END MAIN WHILE
    		//If we get to this line then we exhausted all nodes in the open set but never found the target
    		//No path exists
    	System.out.println("Hit outer return null");
    		return null;
    	}

	private static LinkedList<Integer> AStar(double [] hScore, int [] [] matrix, int sourceIdx, int targetIdx) {
		int nodeCount = hScore.length;
		int [] prev = new int[nodeCount];
		Queue<Integer> openSet = new LinkedList<Integer> ();
		Queue<Integer> closedSet = new LinkedList<Integer> ();
		
		double [] gScore = new double [nodeCount]; // Basically distances array
		
		//The 'f' score of each node is g(n) + h(n)
		//This is the evaluation function unique to A*
		//Rather than explore the node on the open set with the lowest gScore (Dijkstra's & UCS)...
		//..A* will explore the node on the open set with the lowest fScore
		//hScore never changes, but whenever gScore[n] is changed, so must fScore[n]
		
		double [] fScore = new double [nodeCount];
		
		for ( int nodeIdx = 0 ; nodeIdx < nodeCount ; nodeIdx ++ ) {
			gScore[nodeIdx] = Double.MAX_VALUE;
			fScore[nodeIdx] = Double.MAX_VALUE;
			prev[nodeIdx] = -1;
		}
		
		gScore[ sourceIdx ] = 0 ; // source to source dist is 0, fScore updates too
		fScore[ sourceIdx ] = gScore[ sourceIdx ] + hScore[ sourceIdx ];
		
		// add source to open to kick algorithm off
		
		openSet.add(sourceIdx);
		
		while ( openSet.isEmpty() == false ) {
			
			int currNodeIdx = openSet.remove();
			// This for loop ensures we have the node with lowest fScore from the set
			// Exception in thread "main" java.util.ConcurrentModificationException | LINKED LIST MODIFICATION WITHIN LOOP
			ArrayList<Integer> tempArrayList = new ArrayList<Integer> ();
			for ( int nodeIdx : openSet ) {
				tempArrayList.add(nodeIdx);
			}
			for ( int nodeIdx : tempArrayList ) {
				if ( fScore[ nodeIdx ] < fScore[ currNodeIdx ] ) {
					openSet.add(currNodeIdx);
					currNodeIdx = nodeIdx;
				}
			}
			// By now we've gotten the node with lowest fScore from open. So, move it to closedSet
			
			openSet.remove(currNodeIdx);
			closedSet.add(currNodeIdx);
			
			// once we eventually find the target....
			
			if ( currNodeIdx == targetIdx ) {
				double cost = 0;
				LinkedList<Integer> path = new LinkedList<Integer>();
				 // utilize prev pointers array
				while ( currNodeIdx != -1 ) {
					cost += fScore[currNodeIdx];
					path.add(currNodeIdx);
					currNodeIdx = prev[ currNodeIdx ];
				}
				
				Collections.reverse(( path ));
				System.out.println("\nASTAR Stats:\nfCost = " + cost + "\nfringe size = " + openSet.size() + "\nexplored count = " + closedSet.size() + "\nreturning path...");
				return path;
			} // if we have not found target....
			else {
			
			// Get all current neighbors
			ArrayList<Integer> neighbors = getCurrentNeighbors(openSet, currNodeIdx, hScore.length, matrix);
			//En
			for ( int n : neighbors ) {
				double edgeWeight = matrix [ currNodeIdx ] [ n ];
				
				// Checks the gscore cost to this particular neighbor
				
				double tempGScore = gScore [ currNodeIdx ] + edgeWeight;
				
				if ( tempGScore < gScore [ n ] ) {
					gScore [ n ] = tempGScore;
					// If gScore is changed, so if fScore
					fScore [ n ] = gScore [ n ] + hScore [ n ];
					// update parent node to new shorter path
					prev[ n ] = currNodeIdx;
					
					//If we have found a better path to neighbor, then it must be explored again
					if( closedSet.contains( n ) ){
						closedSet.remove( n );
						openSet.add( n );
					}
					//If we are seeing this node for the first time, then ensure that it's on the open set
					else if( openSet.contains( n ) == false )
						openSet.add( n );
				}// END IF GSCORE CHECK
				
				
				}	// End FOR NEIGHBOR 
			
			} // END ELSE
		}
		return null;
	}
	
	private static ArrayList<Integer> getCurrentNeighbors(Queue<Integer> openSet, int currentIndex, int vertexCount, int [] [] adj_matrix) {
		// Plan: take current index and set it as the row. advance through the columns checking for values > 0.
		// If a neighbor is found, make note of the index 
		ArrayList<Integer> neighbors = new ArrayList<Integer> ();
		for ( int i = 0; i < vertexCount - 1; i++ ) {
			
			//System.out.println("Row"+currentIndex+"Col"+i);
			 if ( adj_matrix [currentIndex] [i] > 0 ) {
				//System.out.println("Matrix["+currentIndex+"] ["+i+"] is a neighbor!");
			 neighbors.add(i); // If a neighbor is found by , make note of it's index
			 
			 }
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
		// double [] hScore, int [] [] matrix, int sourceIdx, int targetIdx
		System.out.println("UCS 1 Results: \n" + uniformCostSearch(0,6,verts1,adjMatrix1));
		System.out.println("UCS 2 Results: \n" + uniformCostSearch(0,6,verts2,adjMatrix1));
		System.out.println("ASTAR 1 Results: \n" + AStar(heurs1, adjMatrix1, 0,6) );
		System.out.println("ASTAR 2 Results: \n" + AStar(heurs2, adjMatrix2, 0,6) );
		System.out.println("Pathfinding completed");
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
	
	public double [] readHeurs(String heuristicFilename, int vertexCount) throws FileNotFoundException {
		double [] heuristics = new double [vertexCount]; 
		Scanner scan = new Scanner(new File(heuristicFilename));
		String line = scan.nextLine();
		String [] vals = line.split(",");
		int i = 0;
		for ( String val : vals ) {
			heuristics [i] = Integer.valueOf(val);
			i++;
		}
		scan.close();
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