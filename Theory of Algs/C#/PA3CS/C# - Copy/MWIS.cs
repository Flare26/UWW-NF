using System;
using System.Collections.Generic;

namespace _PA3
{
	public class MWIS : Tree
	{
		public int[] computedSum;
		public bool[] isIncludedSumLarger;
		public bool[] isInSet;

		public MWIS(String filePath) : base(filePath)
		{
			computedSum = new int[numNodes];
			isIncludedSumLarger = new bool[numNodes];
			isInSet = new bool[numNodes];
			for (int i = 0; i < numNodes; i++)
			{
				computedSum[i] = Int32.MinValue;
				isIncludedSumLarger[i] = false;
				isInSet[i] = false;
			}
		}

		public int computeSum(int node)
		{   // complete this function
			if (computedSum[node] != Int32.MinValue)
				return computedSum[node];

			int excl = 0;
			int incl = weights[node];

			List<int> children = adjList[node];

			foreach(int child in children)
			{
				excl += computeSum(child);
				List<int> grandChildren = adjList[child];
				foreach (int grandChild in grandChildren)
				{
					incl += computeSum(grandChild);
				}
			}

			if (incl > excl)
			{
				computedSum[node] = incl;
				isIncludedSumLarger[node] = true;
			}
			else
				computedSum[node] = excl;

			return computedSum[node];
		}

		public void computeSet(int root)
		{   // complete this function
			if (isIncludedSumLarger[root])
				isInSet[root] = true;

				foreach (int child in adjList[root])
				{
					computeSetHelper(child, root);
				}
			
		}

		private void computeSetHelper(int node, int parent)
		{   // complete this function
			if (isIncludedSumLarger[node] && !isInSet[parent])
				isInSet[node] = true;

			foreach (int child in adjList[node])
				computeSetHelper(child, node);
		}
	}
}
