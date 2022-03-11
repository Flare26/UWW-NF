using System;
namespace _PA2
{
    public class BinaryTreeNode
    {
        public char character;
        public int value;
        public BinaryTreeNode left, right;

        public BinaryTreeNode(char c, int val)
        {
            this.character = c;
            value = val;
            left = null;
            right = null;
        }

        override
        public String ToString()
        {
            return "(" + character + ", " + value + ")";
        }
    }
}
