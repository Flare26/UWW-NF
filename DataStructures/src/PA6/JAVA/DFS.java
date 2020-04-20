

public class DFS extends Graph {

	public int level[];
	private boolean closed[];

	public DFS(String filePath) throws Exception {
		readUnweightedGraph(filePath);
		level = new int[numVertices];
		closed = new boolean[numVertices];
	}
// Nathan Frazier
	public void executeDFS(int s) { // complete this method; this is the main DFS function
		for ( int i = 0 ; i < numVertices ; i++ ) {
			closed[i] = false;
			level[i] = Integer.MAX_VALUE;
		}
		
		level[s] = 0;
		helpDFS(s);
		
	}

	private void helpDFS(int v) { // complete this method; this is the DFS helper function
		closed[v] = true; // We explored the current node
		for ( int w = 0 ; w < outDegree[v] ; w++) {
			if (closed[w] == false) {
				level[w] = level[v] + 1;
				helpDFS(w);
			}
		}
		
	}
}
