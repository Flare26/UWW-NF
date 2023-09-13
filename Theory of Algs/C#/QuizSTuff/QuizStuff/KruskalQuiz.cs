using System;
using System.Collections.Generic;
using System.Linq;

class Edge
{
    public int Source, Destination, Weight;
    public Edge(int source, int destination, int weight)
    {
        Source = source;
        Destination = destination;
        Weight = weight;
    }
}

class UnionFind
{
    private int[] parent;
    private int[] rank;

    public UnionFind(int size)
    {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++)
        {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int Find(int x)
    {
        if (parent[x] != x)
        {
            parent[x] = Find(parent[x]);
        }

        return parent[x];
    }

    public bool Union(int x, int y)
    {
        int rootX = Find(x);
        int rootY = Find(y);

        if (rootX == rootY)
        {
            return false;
        }

        if (rank[rootX] > rank[rootY])
        {
            parent[rootY] = rootX;
        }
        else if (rank[rootX] < rank[rootY])
        {
            parent[rootX] = rootY;
        }
        else
        {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }
}

class KruskalAlgorithm
{
    public static List<Edge> Kruskal(List<Edge> edges, int numVertices, bool maxSpanningTree)
    {
        edges = edges.OrderBy(x => maxSpanningTree ? -x.Weight : x.Weight).ToList();
        UnionFind uf = new UnionFind(numVertices);
        List<Edge> result = new List<Edge>();

        foreach (Edge edge in edges)
        {
            if (uf.Union(edge.Source, edge.Destination))
            {
                result.Add(edge);
                if (result.Count == numVertices - 1)
                {
                    break;
                }
            }
        }

        return result;
    }
}

class KruskalQuiz
{
    static void Main(string[] args)
    {
        List<Edge> edges = new List<Edge>
        {
            new Edge(0, 4, 8),
            new Edge(0, 1, 10),
            new Edge(0, 3, -10),
            new Edge(2, 1, 0),
            new Edge(2, 4, 0),
            new Edge(3, 2, -7),
            new Edge(3, 1, 12),
            new Edge(3, 4, 20),
            new Edge(3, 5, 25),
            new Edge(4, 5, 15)
        };

        int numVertices = 6;

        List<Edge> minSpanningTree = KruskalAlgorithm.Kruskal(edges, numVertices, false);
        int minWeight = minSpanningTree.Sum(e => e.Weight);
        Console.WriteLine($"Minimum spanning tree weight: {minWeight}");
        Console.WriteLine($"Edges processed: {minSpanningTree.Count}");

        List<Edge> maxSpanningTree = KruskalAlgorithm.Kruskal(edges, numVertices, true);
        int maxWeight = maxSpanningTree.Sum(e => e.Weight);
        Console.WriteLine($"Maximum spanning tree weight: {maxWeight}");
        Console.WriteLine($"Edges processed: {maxSpanningTree.Count}");
    }
}
