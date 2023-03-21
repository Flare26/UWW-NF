using System;
namespace _PA2
{
    public class Interval
    {
		public char name;
		public int start, end;

		public Interval(char n, int s, int e)
		{
			name = n;
			start = s;
			end = e;
		}

		override
		public String ToString()
		{
			return String.Format("({0}, {1}, {2})", name, start, end);
		}
	}
}
