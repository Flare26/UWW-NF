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
            BinaryTreeNode root = buildTree();
            
            createTable(root, "");
            for (int i = 0; i < alphabet.Length; i++)
            {
                char c = alphabet[i];
                string str = getEncoding(c);
                encodingLength += frequencies[i] * str.Length;
                tableSize += str.Length + 8; // 8 bits for the character code
            }
        }

        private BinaryTreeNode buildTree()
        {   // complete this method
            PriorityQueue<BinaryTreeNode> PQ = new PriorityQueue<BinaryTreeNode>(new BinaryTreeNodeComparator());
            for (int i = 0; i < alphabet.Length; i++)
            {
                BinaryTreeNode x = new BinaryTreeNode(alphabet[i], frequencies[i]);
                PQ.add(x);
            }

            while (PQ.size() > 1)
            {
                BinaryTreeNode min = PQ.poll();
                BinaryTreeNode secMin = PQ.poll();
                BinaryTreeNode y = new BinaryTreeNode('\0', min.value + secMin.value);
                y.left = min;
                y.right = secMin;
                PQ.add(y);
                
            }
            return PQ.poll();
        }

        private void createTable(BinaryTreeNode node, String encoding)
        {   // complete this method
            if (node.left is null && node.right is null)
            {
                charToEncodingMapping.Add(node.character, encoding);
            }else
            {
                if (node.left is not null)
                    createTable(node.left, encoding + "0");
                if (node.right is not null)
                    createTable(node.right, encoding + "1");
            }
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