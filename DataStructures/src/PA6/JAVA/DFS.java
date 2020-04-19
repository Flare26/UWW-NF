

public class DFS extends Graph {

	public int level[];
	private boolean closed[];

	public DFS(String filePath) throws Exception {
		readUnweightedGraph(filePath);
		level = new int[numVertices];
		closed = new boolean[numVertices];
	}

	public void executeDFS(int s) { // complete this method; this is the main DFS function
	}

	private void helpDFS(int v) { // complete this method; this is the DFS helper function
	}
}
