

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//NF
public class Graph {

	public int numVertices;
	public int numEdges;
	public int outDegree[];
	public List<ArrayList<Edge>> adjList;

	public void readWeightedGraph(String filePath) throws FileNotFoundException { // complete this function
		//System.out.println("file path = " + filePath);
		Scanner scanbot = new Scanner(new FileInputStream(filePath));
		numVertices = scanbot.nextInt();
		numEdges = scanbot.nextInt();
		outDegree = new int [numVertices];
		adjList = new ArrayList<ArrayList<Edge>>(numEdges);
		for (int i = 0; i < numVertices ; i++)
		{
			outDegree[i]=0;
			adjList.add(new ArrayList<Edge>());
		}
		for (int i = 0 ; i < numEdges; i++)
		{
			int src, dest, weight;
			src = scanbot.nextInt();
			dest = scanbot.nextInt();
			weight = scanbot.nextInt();
			Edge e = new Edge(src, dest, weight);
			adjList.get(src).add(e);
		}
		scanbot.close();
	}
}
