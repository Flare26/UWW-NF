using System;
namespace _433_PA1
{
    public class Selection : Partition
    {
        public Selection(int[] array, int n) : base(array, n)
        {
        }

        public int select(int k)
        {
            return select(0, n - 1, k);
        }

        private int select(int left, int right, int k)
        { // complete this function
            if (left == right)
                return array[left];

                int ptIdx = left - 1;
                int piv = generateRandomPivot(left, right);
                ptIdx = partition(left, right, piv);

                if (k == ptIdx - left + 1)
                    return piv;

                else if (k < ptIdx - left + 1)
                    return select(left, ptIdx - 1, k);
                else
                    return select(ptIdx+1, right, k - ( ptIdx - left + 1));
            
        }
    }
}
