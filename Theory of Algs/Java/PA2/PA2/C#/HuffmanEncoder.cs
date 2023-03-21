using System;
using System.Collections;
using System.Collections.Generic;

namespace _PA2
{
    class BinaryTreeNodeComparator : IComparer<BinaryTreeNode>
    {
        public int Compare(BinaryTreeNode arg1, BinaryTreeNode arg2)
        {
            return arg1.value - arg2.value;
        }
    }

    public class HuffmanEncoder
    {

        private char[] alphabet;
        private int[] frequencies;
        private int sigma;
        private int encodingLength, tableSize;
        private Hashtable charToEncodingMapping;

        public HuffmanEncoder(char[] alphabet, int[] frequencies, int sigma)
        {
            this.alphabet = alphabet;
            this.sigma = sigma;
            this.frequencies = frequencies;
            encodingLength = tableSize = 0;
            charToEncodingMapping = new Hashtable();
            encode();
        }

        private void encode()
        {   // complete this method
        }

        private BinaryTreeNode buildTree()
        {   // complete this method
        }

        private void createTable(BinaryTreeNode node, String encoding)
        {   // complete this method
        }

        public string getEncoding(char c)
        {
            return charToEncodingMapping[c].ToString();
        }

        public int getSigma()
        {
            return sigma;
        }

        public int[] getFrequencies()
        {
            return frequencies;
        }

        public char[] getAlphabet()
        {
            return alphabet;
        }

        public int getTableSize()
        {
            return tableSize;
        }

        public int getEncodingLength()
        {
            return encodingLength;
        }
    }
}