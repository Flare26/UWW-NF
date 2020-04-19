

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {

	public int numVertices;
	public int outDegree[];
	public List<ArrayList<Edge>> adjList;
	
	public void readUnweightedGraph(String filePath) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(filePath));
		numVertices = fileReader.nextInt();
		adjList = new ArrayList<ArrayList<Edge>>(numVertices);
		outDegree = new int[numVertices];
		for (int i = 0; i < numVertices; i++) {
			int numEdges = fileReader.nextInt();
			outDegree[i] = numEdges;
			ArrayList<Edge> outEdges = new ArrayList<Edge>(numEdges);
			for (int j = 0; j < numEdges; j++) {
				Edge edge = new Edge(i, fileReader.nextInt(), 1);
				outEdges.add(edge);
			}
			adjList.add(outEdges);
		}
		fileReader.close();
	}
	
	public void readWeightedGraph(String filePath) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(filePath));
		numVertices = fileReader.nextInt();
		adjList = new ArrayList<ArrayList<Edge>>(numVertices);
		outDegree = new int[numVertices];
		for (int i = 0; i < numVertices; i++) {
			int numEdges = fileReader.nextInt();
			outDegree[i] = numEdges;
			ArrayList<Edge> outEdges = new ArrayList<Edge>(numEdges);
			for (int j = 0; j < numEdges; j++) {
				Edge edge = new Edge(i, fileReader.nextInt(), fileReader.nextInt());
				outEdges.add(edge);
			}
			adjList.add(outEdges);
		}
		fileReader.close();
	}
}
