using System;
namespace _PA2
{
	public class SelectionRandom
	{
		private static Random rand = new Random((int)(DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond));

		private static int generateRandomPivot(int[] array, int left, int right)
		{
			int pivotIndex = left + rand.Next(right - left + 1);
			return array[pivotIndex];
		}

		public static int select(int[] arr, int left, int right, int k)
		{	// complete this method
		}
	}
}
