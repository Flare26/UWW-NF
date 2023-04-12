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
		}

		public void computeSet(int root)
		{   // complete this function
		}

		private void computeSetHelper(int node, int parent)
		{   // complete this function
		}
	}
}
