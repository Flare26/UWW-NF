using System;
using System.Collections.Generic;

namespace _PA4
{
	public class DJP : Graph
	{
		public DJP(String filePath, GraphType type) : base(filePath, type)
		{

		}

        public List<Edge> execute()
        {
            // Initialize labels, closed set and parent edges
            int[] labels = new int[numVertices];
            bool[] closed = new bool[numVertices];
            Edge[] parentEdges = new Edge[numVertices];
            List<Edge> MST = new List<Edge>();

            for (int i = 0; i < numVertices; i++)
            {
                closed[i] = false;
                labels[i] = Int32.MaxValue;
                parentEdges[i] = null;
            }

            int source = 0; // can be any vertex
            labels[source] = 0;
            PriorityQueue<PriorityQueueElement> open = new PriorityQueue<PriorityQueueElement>(new PriorityQueueElementComparator());
            open.add(new PriorityQueueElement(source, 0));

            // Loop until the open set is empty
            while (open.size() > 0)
            {
                int minVertex = open.poll().item;
                closed[minVertex] = true;

                // For each edge adjacent to minVertex
                for (int i = 0; i < adjList[minVertex].Count; i++)
                {
                    Edge adjEdge = adjList[minVertex][i];
                    int adjVertex = adjEdge.dest;
                    if (!closed[adjVertex])
                    {
                        int currentLabel = adjEdge.weight;
                        if (currentLabel < labels[adjVertex])
                        {
                            labels[adjVertex] = currentLabel;
                            parentEdges[adjVertex] = adjEdge; // assign the parent edge
                            open.add(new PriorityQueueElement(adjVertex, currentLabel));
                        }
                    }
                }
            }

            // Build the MST using the parentEdges array
            for (int i = 1; i < numVertices; i++)
            {
                if (parentEdges[i] != null)
                {
                    MST.Add(parentEdges[i]); // Add parent edge to MST
                }
            }

            return MST;
        }

    }
}

