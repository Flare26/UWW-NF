using System;
using System.Collections.Generic;

namespace _PA3
{
	public class Tree
	{
		public List<List<int>> adjList;
		public int[] weights;
		public int numNodes;

		public Tree(String filePath)
		{
			string line;
			System.IO.StreamReader fileReader =
				new System.IO.StreamReader(filePath);
			line = fileReader.ReadLine().Trim();
			numNodes = Int32.Parse(line);
			weights = new int[numNodes];
			adjList = new List<List<int>>(numNodes);

			for (int i = 0; i < numNodes; i++)
			{
				line = fileReader.ReadLine().Trim();
				weights[i] = Int32.Parse(line);
				adjList.Add(new List<int>());
			}

			for (int i = 0; i < numNodes - 1; i++)
			{
				line = fileReader.ReadLine().Trim();
				String[] tokens = line.Split(' ');
				int src = Int32.Parse(tokens[0]);
				int dest = Int32.Parse(tokens[1]);
				adjList[src].Add(dest);
			}
			fileReader.Close();
		}
	}
}
