using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace HW2
{
    internal class FindSplitIndex
    {
        public static int FindSplitIndexVal(int[] A)
        {
            int n = A.Length;
            int left = 0;
            int right = n - 1;
            int mid = left + (right - left) / 2;

            while (left < right)
            {
                mid = left + (right - left) / 2;
                if (A[mid] > A[right])
                {
                    left = mid + 1;
                }
                else if (A[mid] <= A[right])
                {
                    right = mid;
                }
            }

                    return A.Length - ( left + 1 );
        }

        public static int FindHIndex(int[] citations)
        {
            // for non-decreasing order
            int n = citations.Length;
            int lo = 0, hi = n - 1;
            int hIndex = 0;
            while (lo <= hi)
            {
                int mid = lo + (hi - lo) / 2;
                int count = n - mid;
                if (citations[mid] >= count)
                {
                    hIndex = count;
                    hi = mid - 1;
                }
                else
                {
                    lo = mid + 1;
                }
            }
            return hIndex;
        }

        public static int FindHIndexUnsorted(int[] citations)
        {
            Array.Sort(citations, (a, b) => b.CompareTo(a));
            int n = citations.Length;
            int lo = 0, hi = n - 1;
            int hIndex = 0;
            while (lo <= hi)
            {
                int mid = lo + (hi - lo) / 2;
                int count = mid + 1;
                if (citations[mid] >= count)
                {
                    hIndex = count;
                    lo = mid + 1;
                }
                else
                {
                    hi = mid - 1;
                }
            }
            return hIndex;
        }

        public static bool HasMajorityElement(int[] arr, int k)
        {
            // Boyer-Moore voting

            int n = arr.Length;
            int count = 0;
            double candidate = 0;

            // Find candidate element
            for (int i = 0; i < n; i++)
            {
                if (count == 0)
                {
                    candidate = arr[i];
                    count = n / k - 1;
                }
                else if (arr[i] == candidate)
                {
                    count++;
                }
                else
                {
                    count--;
                }
            }

            // Count occurrences of candidate element
            count = 0;
            for (int i = 0; i < n; i++)
            {
                if (arr[i] == candidate)
                {
                    count++;
                }
            }

            return count > n / k;
        }
    }
}
