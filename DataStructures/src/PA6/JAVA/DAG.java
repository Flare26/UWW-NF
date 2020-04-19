

import java.io.FileNotFoundException;

public class DAG extends Graph {

	public int topoOrder[];
	public int distance[];
	private int inDegree[];

	public DAG(String filePath) throws FileNotFoundException {
		readWeightedGraph(filePath);
		topoOrder = new int[numVertices];
		distance = new int[numVertices];
		inDegree = new int[numVertices];
	}

	public void longestPaths(int s) throws Exception { // complete this method
	}
}
