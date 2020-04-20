

import java.io.FileNotFoundException;

public class DAG extends Graph {

	public int topoOrder[];
	public int distance[];
	private int inDegree[];
// Nathan Frazier
	public DAG(String filePath) throws FileNotFoundException {
		readWeightedGraph(filePath);
		topoOrder = new int[numVertices];
		distance = new int[numVertices];
		inDegree = new int[numVertices];
	}

	public void longestPaths(int s) throws Exception { // complete this method
		// Initialize
		for ( int i = 0; i < numVertices ; i++) {
			inDegree[i] = 0; distance[i] = Integer.MIN_VALUE;}
		// begin
		for ( int i = 0; i < numVertices ; i++) {
			for ( int j = 0 ; j < outDegree[i] ; j++) {
				Edge adjEdge = adjList.get(i).get(j);
				inDegree[adjEdge.dest] += 1; 
			}
		}
		
		Queue vertexQ = new Queue(numVertices);		
		
		for ( int i = 0; i < numVertices ; i++) {
			if ( inDegree[i] == 0 ) {
				vertexQ.enqueue(i);
			}
		}
		
		distance[s] = 0;
		int topoLevel = 0;
		
		while (vertexQ.size() > 0) {
			
			int v = vertexQ.dequeue();
			topoOrder[topoLevel] = v;
			topoLevel ++;
			for (int j = 0; j < outDegree[v]; j++) {
				Edge adjEdge = adjList.get(v).get(j);
				int adjVertex = adjEdge.dest;
				inDegree[adjVertex] --;
				if (inDegree[adjVertex] == 0)
					vertexQ.enqueue(adjVertex);
				if (distance[v] != Integer.MIN_VALUE) {
					int len = distance[v] + adjEdge.weight;
					if ( len > distance[adjVertex] )
						distance[adjVertex] = len;
				}
					
				
				
			}
		}
	}
}
