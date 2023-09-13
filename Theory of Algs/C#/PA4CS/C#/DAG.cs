using System;
using System.Collections.Generic;
using System.Numerics;

namespace _PA4
{
    public class DAG : Graph
    {

        public DAG(Graph graph) : base(graph)
        {
        }

        public List<int> topoSort()
        {
            List<int> topoOrder = new List<int>();
            int[] inDegree = new int[numVertices];
            for (int i = 0; i < numVertices; i++)
                inDegree[i] = 0;

            for (int i = 0; i < numVertices; i++)
                foreach (Edge e in adjList[i])
                    inDegree[e.dest]++;

            Queue<int> q = new Queue<int>();
            for (int i = 0; i < numVertices; i++)
                if (inDegree[i] == 0)
                    q.Enqueue(i);

            while (q.Count > 0)
            {
                int u = q.Dequeue();
                topoOrder.Add(u);
                foreach (Edge e in adjList[u])
                {
                    inDegree[e.dest]--;
                    if (inDegree[e.dest] == 0)
                        q.Enqueue(e.dest);
                }
            }
            if (topoOrder.Count != numVertices)
                return null;
            return topoOrder;
        }

        public int[] longestPaths(int s)
        { // complete this method
          //Topologically sort vertices
            List<int> topo = topoSort();

            int[] dist = new int[numVertices];

            for (int i = 0; i < numVertices; i++)
                dist[i] = Int32.MinValue;

            dist[s] = 0;

            for (int v = 0; v < topo.Count; v++)
            {
                List<Edge> outEdges = adjList[topo[v]];
                for (int adjEdge = 0; adjEdge < outEdges.Count; adjEdge++)
                {
                    int adjVertex = outEdges[adjEdge].dest;
                    if (dist[topo[v]] != Int32.MinValue)  // Corrected the code to use topo instead of the counter as an index, before the order was not actually topo
                    {
                        int len = dist[topo[v]] + outEdges[adjEdge].weight;
                        if (len > dist[adjVertex])
                            dist[adjVertex] = len;
                    }
                }
            }
            return dist;
        }


        public int[][] countOddEvenHops(int s)
        {
            // Topologically sort the vertices
            List<int> topo = topoSort();

            // Prepare the result array with the first row for even counts and the second row for odd counts
            int[][] results = new int[2][];
            results[0] = new int[numVertices];
            results[1] = new int[numVertices];

            // Initialize base case values
            results[0][s] = 1; // countEven(s) = 1
            results[1][s] = 0; // countOdd(s) = 0

            // Iterate through the vertices in topological order
            for (int v = 0; v < topo.Count; v++)
            {
                int u = topo[v];

                // Iterate through outgoing edges of the current vertex u
                List<Edge> outEdges = adjList[u];

                foreach (Edge edge in outEdges)
                {
                    int adjVertex = edge.dest;

                    // Update countEven and countOdd for the adjacent vertex v using the recursion rules
                    results[0][adjVertex] += results[1][u]; // countEven(v) = countEven(v) + countOdd(u)
                    results[1][adjVertex] += results[0][u]; // countOdd(v) = countOdd(v) + countEven(u)
                }
            }

            return results;
        }

    }
}
