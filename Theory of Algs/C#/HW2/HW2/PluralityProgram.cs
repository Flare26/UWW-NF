// See https://aka.ms/new-console-template for more information
using HW2;

class PluralityProgram
{
    
    public static void Main(string[]args)
    {
        int[] A = { 4, 5, 6, 7, 4, 5, 4 };
        int [] B ={ 3,5,6,7,3,5 };
        int[] C ={ 78, 83, 96, 5, 9, 17, 29, 32, 46 };
        int[] D = { 58, 74, 77, 81, 6, 7, 8, 9, 11, 12, 23, 25, 32, 33, 34, 38, 40, 41, 44, 52 };
        int[] E = { 11, 12, 23, 25, 32, 33, 34, 38, 40, 41, 44, 52,55 };
        int[] F = { 4, 2, 8, 9, 1, 5, 7, 1 };
        int[] G = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Console.WriteLine(Plurality(A));
        Console.WriteLine(Plurality(B));
        Console.WriteLine("FindSplitIndexVal() = " + FindSplitIndex.FindSplitIndexVal(C));
        Console.WriteLine("FindSplitIndexVal() = " + FindSplitIndex.FindSplitIndexVal(D));
        Console.WriteLine("FindHIndex() = " + FindSplitIndex.FindHIndex(E));
        Console.WriteLine("FindHIndex() = " + FindSplitIndex.FindHIndexUnsorted(F));
        Console.WriteLine("FindMajorityElement() = " + FindSplitIndex.HasMajorityElement(G,10));
    }

    public static int Plurality(int[] arr)
    {
        // Create a dictionary to store the occurrences of each element in the array
        Dictionary<int, int> counts = new Dictionary<int, int>();

        // Loop through the array and count the occurrences of each element
        foreach (int num in arr)
        {
            if (counts.ContainsKey(num))
            {
                counts[num]++;
            }
            else
            {
                counts[num] = 1;
            }
        }

        // Find the element(s) with the highest count
        int maxCount = 0;
        List<int> maxNums = new List<int>();
        foreach (KeyValuePair<int, int> entry in counts)
        {
            if (entry.Value > maxCount)
            {
                maxCount = entry.Value;
                maxNums.Clear();
                maxNums.Add(entry.Key);
            }
            else if (entry.Value == maxCount)
            {
                maxNums.Add(entry.Key);
            }
        }

        // If there is a single element with the highest count, return it
        if (maxNums.Count == 1)
        {
            return maxNums[0];
        }
        // Otherwise, there is no plurality
        else
        {
            return -1;
        }
    }
}
