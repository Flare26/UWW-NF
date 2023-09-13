using System;
namespace _PA4
{
    public class BellmanFord : Graph
    {

        public BellmanFord(Graph graph) : base(graph) { }

        public int[] execute(int source)
        {
            bool didDistChange;
            int[] dist = new int[numVertices];
            for (int i = 0; i < dist.Length; i++)
                dist[i] = Int32.MaxValue;

            dist[source] = 0;

            for (int i = 1; i < numVertices; i++)
            {
                didDistChange = false;

                for (int u = 0; u < numVertices; u++)
                {
                    foreach (Edge e in adjList[u])
                    {
                        if (dist[e.src] != Int32.MaxValue)
                        {
                            int newDist = dist[e.src] + e.weight;
                            if (newDist < dist[e.dest])
                            {
                                dist[e.dest] = newDist;
                                didDistChange = true;
                            }
                        }
                    }
                }

                if (!didDistChange)
                {
                    return dist;
                }
            }

            for (int i = 0; i < numVertices; i++)
            {
                foreach (Edge e in adjList[i])
                {
                    if (dist[e.src] != Int32.MaxValue)
                        if (dist[e.src] + e.weight < dist[e.dest])
                            return null;
                }
            }
            return dist;
        }

    }
}
