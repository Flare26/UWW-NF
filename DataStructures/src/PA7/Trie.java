

public class Trie {

	TrieNode root;

	Trie() {
		root = new TrieNode();
	}

	void insert(String str) { // complete this function
		TrieNode t = root;
		for (int i = 0 ; i < str.length(); i ++) {
			char c = str.charAt(i);
			if (t.getChild(c) != null)
				t = t.getChild(c); // move to child if exists
			else while (i < str.length()) {
				t = t.insertChild(str.charAt(i)); // insert children that we know do not exist yet. They will exist as branches after this.
				i++;
			}
		}
	}

	boolean match(String str) { // complete this function
		TrieNode t = root;
		for (int i = 0 ; i < str.length(); i ++) {
			char c = str.charAt(i);
			if ( t.getChild(c) == null )
				return false;
				else
					t = t.getChild(c);
		}
		return true; // if it doesnt terminate before here, must be true!
	}
}
