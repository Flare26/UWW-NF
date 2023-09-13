using System;
using System.Collections.Generic;

namespace _PA4
{
	public class Johnson : Graph
	{

		public Johnson(Graph graph) : base(graph) { }

        public int[][] execute()
        {
         //complete this method
            // Add a new dummy vertex not already in the list.
            adjList.Add(new List<Edge>());

            for (int i = 0; i < numVertices; i++)
            {
                Edge e = new Edge(numVertices, i, 0);
                adjList[numVertices].Add(e);
            }
            numEdges += numVertices;
            numVertices++;

            // Run BellmanFord on the graph. Polymorphism since this Johnson is also a Graph.
            BellmanFord bf = new BellmanFord(this);
            int[] phis = bf.execute(numVertices - 1);

            // Remove dummy node.
            numVertices--;
            numEdges -= numVertices;
            adjList.RemoveAt(numVertices);

            if (phis == null || phis.Length == 0)
                return null;

            // Modify edge weights using the phi array.
            for (int v = 0; v < adjList.Count; v++)
            {
                for (int e = 0; e < adjList[v].Count; e++)
                {
                    Edge edge = adjList[v][e];
                    edge.weight = edge.weight + phis[edge.src] - phis[edge.dest];
                }
            }

            int[][] shortestPathMatrix = new int[numVertices][];
            Dijkstra dijkstra = new Dijkstra(this);

            // Run Dijkstra on each vertex.
            for (int i = 0; i < numVertices; i++)
            {
                shortestPathMatrix[i] = dijkstra.execute(i);

                // Adjust the distances from Dijkstra's algorithm to reflect the actual shortest path lengths.
                for (int j = 0; j < numVertices; j++)
                {
                    if (shortestPathMatrix[i][j] != int.MaxValue)
                        shortestPathMatrix[i][j] += phis[j] - phis[i];
                }
            }

            // Revert the edge weights back to their original values using the phi array.
            for (int v = 0; v < adjList.Count; v++)
            {
                for (int e = 0; e < adjList[v].Count; e++)
                {
                    Edge edge = adjList[v][e];
                    edge.weight = edge.weight - phis[edge.src] + phis[edge.dest];
                }
            }

            // Return shortest path matrix.
            return shortestPathMatrix;
        }


    }
}
