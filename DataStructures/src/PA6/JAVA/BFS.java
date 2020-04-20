

public class BFS extends Graph {

	public int level[];

	public BFS(String filePath) throws Exception {
		readUnweightedGraph(filePath);
		level = new int[numVertices];
	}
// Nathan Frazier
	public void executeBFS(int s) throws Exception { // complete this method
		// initialize
		for (int i = 0 ; i < numVertices ; i++) {
			level[i] = Integer.MAX_VALUE;
		}
		
		Queue vertexQ = new Queue(numVertices); 
		
		vertexQ.enqueue(s);
		level[s] = 0;
		
		while ( vertexQ.size() > 0 ) {
			int v = vertexQ.dequeue();
			for ( int i = 0 ; i < outDegree[v] ; i++) {
				Edge adjEdge = adjList.get(v).get(i);
				int w = adjEdge.dest;
				if ( level[w] == Integer.MAX_VALUE ) {
					level[w] = level[v] + 1;
					vertexQ.enqueue(w);
				}
				
			}
		}
	}
}
