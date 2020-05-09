

import java.util.Hashtable;

public class TrieNode {

	Hashtable<Character, TrieNode> edges;

	TrieNode() {
		edges = new Hashtable<Character, TrieNode>();
	}

	TrieNode insertChild(char c) {
		TrieNode child = new TrieNode();
		edges.put(c, child);
		return child;
	}

	TrieNode getChild(char c) {
		return edges.get(c);
	}
}