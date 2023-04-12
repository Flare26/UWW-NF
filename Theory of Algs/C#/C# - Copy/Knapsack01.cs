using System;
using System.Collections.Generic;
namespace _PA3
{
    public class Knapsack01
    {
        public static int findOptimalProfit(int[] profits, int[] weights, int numElements, int capacity)
        {
            //complete this function
            Dictionary<int, int> weightsToProfitsPrev = new Dictionary<int, int> { { 0, 0 } };
            int max = Int32.MinValue;

            for (int i = 0; i < numElements; i++)
            {
                Dictionary<int, int> weightsToProfitsCurr = new Dictionary<int, int>();

                foreach (KeyValuePair<int, int> entry in weightsToProfitsPrev)
                {
                    int w = entry.Key;
                    int p = entry.Value;
                    weightsToProfitsCurr[w] = p;
                }

                foreach (KeyValuePair<int, int> entry in weightsToProfitsPrev)
                {
                    int w = entry.Key;
                    int p = entry.Value;
                    int weightNew = w + weights[i];

                    if (weightNew > capacity) continue;

                    int profitNew = p + profits[i];

                    if (weightsToProfitsCurr.ContainsKey(weightNew))
                    {
                        int profitExisting = weightsToProfitsCurr[weightNew];
                        if (profitExisting < profitNew)
                        {
                            weightsToProfitsCurr[weightNew] = profitNew;
                        }
                    }
                    else
                    {
                        weightsToProfitsCurr[weightNew] = profitNew;
                    }

                    if (max < profitNew)
                    {
                        max = profitNew;
                    }
                }

                weightsToProfitsPrev = weightsToProfitsCurr;
            }

            return max;
        }
    }
}
