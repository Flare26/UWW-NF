

import java.io.FileNotFoundException;

public class Dijkstra extends Graph {
//NF
	public int distance[], parent[];
	private boolean closed[];

	public Dijkstra(String filePath) throws FileNotFoundException {
		readWeightedGraph(filePath);
		distance = new int[numVertices];
		parent = new int[numVertices];
		closed = new boolean[numVertices];
	}

	public void executeDijkstra(int source) { // complete this function

		for (int i = 0 ; i < numVertices ; i++) {
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
			closed[i] = false;
		}
		
		distance[source] = 0;
		
		int numOpen = 1;
		while (numOpen > 0)
		{
			int minDist = Integer.MAX_VALUE;
			int minVertex = -1;
			// these will be used to expand the open vertex with the minimum distance label
			
			for (int i=0 ; i < numVertices ; i++ )
			{
				if (closed[i] == false && distance[i] < minDist)
				{
					//if node i is STILL OPEN
					minDist = distance[i];
					minVertex = i;
				}
				
			}
			closed[minVertex] = true;
			numOpen --;
			for (int i=0 ; i < outDegree[minVertex] ; i++)
			{
				//for each outward going edge of the minVertex...
				Edge adjEdge = adjList.get(minVertex).get(i);
				int adjVertex = adjEdge.dest;
				if (closed[adjVertex] == false)
				{
					int newDist = distance[minVertex] + adjEdge.weight;
					if (distance[adjVertex] == Integer.MAX_VALUE)
					{
						//this vertex has not actually been visited...
						numOpen++;
						if (newDist<distance[adjVertex])
						{
							distance[adjVertex] = newDist;
							parent[adjVertex] = minVertex;
						}
						
					}

					
				}
			}
		}
	}
}
