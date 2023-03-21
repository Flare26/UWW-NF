using System;
using System.Collections.Generic;

namespace _PA2
{
    public class StringSorter
    {
        public static void radixSort(String[] strings, int n)
        {//complete this method
            int max = getMaxStringLength(strings, n);

            for (int e = max - 1; e >= 0; e--)
            {
                int[] digits = new int[n];
                for (int i = 0; i < n; i++)
                {
                    if (e >= strings[i].Length)
                        digits[i] = 0;
                    else
                        digits[i] = strings[i][e] - 'a' + 1;
                }
                countSortOnLowerCaseCharacters(strings, digits, n);
            }
        }

        private static void countSortOnLowerCaseCharacters(String[] strings, int[] digits, int n)
        {
            //complete this method
            int[] digitCount = new int[27];
            string[] temp = new string[n];

            for (int i = 0; i < n; i++)
                digitCount[digits[i]]++;

            for (int i = 1; i <= 26; i++)
                digitCount[i] += digitCount[i - 1];

            for (int i = n - 1; i >= 0; i--)
            {
                temp[digitCount[digits[i]] - 1] = strings[i];
                digitCount[digits[i]]--;
            }

            for (int i = 0; i < n; i++)
                strings[i] = temp[i];
        }

        private static int getMaxStringLength(String[] strings, int n)
        {
            int max = strings[0].Length;
            for (int i = 1; i < n; i++)
            {
                if (strings[i].Length > max)
                    max = strings[i].Length;
            }
            return max;
        }
    }

}
