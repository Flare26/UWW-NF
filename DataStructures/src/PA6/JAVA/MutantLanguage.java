// Nathan Frazier
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MutantLanguage extends Graph {

	private String words[];
	private int numWords;
	private int inDegree[];

	public MutantLanguage(String filePath) throws FileNotFoundException {
		readLanguage(filePath);
		makeGraph();
	}

	private void readLanguage(String filePath) throws FileNotFoundException { // complete this method
		Scanner reader = new Scanner(new FileInputStream(filePath));
		numVertices = reader.nextInt();
		numWords = reader.nextInt();
		words = new String [numWords];
		for ( int i = 0 ; i < numWords ; i++) {
			words[i] = reader.nextLine(); // read in each word
		}
		reader.close();
	}

	private void makeGraph() { // complete this method
		outDegree = new int [numVertices];
		inDegree = new int [numVertices];
		adjList = new ArrayList<ArrayList<Edge>> (numVertices);
		for ( int i = 0 ; i < numVertices; i++) {
			outDegree[i] = 0;
			adjList.add(new ArrayList<Edge>()); // add blank row to list	
		}
		for ( int i = 0 ; i < numWords - 1; i++) {
			String currentWord = words[i];
			String nextWord = words[i+1];
			int minLength = currentWord.length();
			if ( minLength > nextWord.length())
				minLength = nextWord.length();
			for ( int j = 0 ; j < minLength ; j ++ ) {
				char x = currentWord.charAt(j);
				char y = nextWord.charAt(j);
				if ( x != y ) {
					int src = x - 97; 
					int dest = y - 97;
					Edge e = new Edge(src, dest);
					adjList.get(src).add(e);
					outDegree[src] ++;
					break;
				}
			}
		}
	}

	public char[] getOrder() throws Exception { // complete this method
		for (int i = 0 ; i < numVertices ; i++) {
			inDegree[i] = 0;
		}
		
		char [] topoOrder = new char [ numVertices ];
		for (int i = 0 ; i < numVertices ; i ++ ) {
			
			for (int j = 0 ; j < outDegree[i] ; j ++ ){
				Edge adjEdge = adjList.get(i).get(j);
				inDegree[adjEdge.dest] ++;
			}
		}
		
		Queue vertexQ = new Queue(numVertices);
		int topoLevel=0;
		for (int i = 0 ; i < numVertices ; i ++ ) {
			if (inDegree[i] == 0 ) {
				vertexQ.enqueue(i);
			}
		}// END vertex check
		
		while (vertexQ.size() > 0 ) {
			int v = vertexQ.dequeue();
			topoOrder[topoLevel] = (char) (v+97);
			topoLevel++;
			for ( int j = 0 ; j < outDegree[v]; j++) {
				Edge adjEdge = adjList.get(v).get(j);
				int adjVertex = adjEdge.dest;
				inDegree[adjVertex] --;
				if (inDegree[adjVertex] == 0)
					vertexQ.enqueue(adjVertex);
			}
		} // End while queue process
		
		if ( topoLevel != numVertices )
			return null;
		else
			return topoOrder;
		
	}
}
