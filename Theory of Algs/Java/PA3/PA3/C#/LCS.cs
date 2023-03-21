using System;
namespace _PA3
{
	public class LCS
	{
		public static String longestCommonSubsequence(String x, String y)
		{   // complete this function
		}

		private static String reverse(String str)
		{
			String rev = "";
			for (int i = str.Length - 1; i >= 0; i--)
			{
				rev += str[i];
			}
			return rev;
		}
	}
}
