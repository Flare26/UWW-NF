

import java.util.Comparator;
import java.util.Hashtable;

class BinaryTreeNodeComparator implements Comparator<BinaryTreeNode> {

	@Override
	public int compare(BinaryTreeNode o1, BinaryTreeNode o2) {
		return o1.value - o2.value;
	}
}

public class HuffmanEncoder {

	private char alphabet[];
	private int frequencies[];
	private int sigma;

	private int encodingLength;
	private int tableSize;
	private Hashtable<Character, String> charToEncodingMapping; // hash table is instantiated

	public HuffmanEncoder(char alphabet[], int frequencies[], int sigma) {
		this.alphabet = alphabet;
		this.sigma = sigma;
		this.frequencies = frequencies;
		encodingLength = tableSize = 0;
		charToEncodingMapping = new Hashtable<>();
		encode();
	}

	private void encode() throws IndexOutOfBoundsException { // complete this method
		
		
	}

	private void buildTree() throws IndexOutOfBoundsException { // complete this method
		
	}

	private void createTable(BinaryTreeNode node, String encoding) { // complete this method
		charToEncodingMapping.put(node.character, encoding);
	}

	public String getEncoding(char c) {
		return charToEncodingMapping.get(c);
	}

	public int getSigma() {
		return sigma;
	}

	public int[] getFrequencies() {
		return frequencies;
	}

	public char[] getAlphabet() {
		return alphabet;
	}

	public int getTableSize() {
		return tableSize;
	}

	public int getEncodingLength() {
		return encodingLength;
	}
}
