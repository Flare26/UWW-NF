import java.util.Arrays;

public class TestCorrectness {
	
	private static void testTrie() {
		System.out.println("*** Test Trie (Spell Checker) ***\n");
		Trie trie = new Trie();
		String dictionary[] = { "abc$", "abcd$", "bce$", "abx$", "acfe$", "bfr$", "de$" };
		for (int i = 0; i < 7; i++)
			trie.insert(dictionary[i]);

		String document[] = { "abc", "def", "abcd", "bce", "abx", "acfe", "bfr", "xyz", "de", "tyu", "ab" };
		int numWordsInDoc = 11;
		boolean spellCheck[] = new boolean[numWordsInDoc];

		for (int i = 0; i < numWordsInDoc; i++) {
			spellCheck[i] = trie.match(document[i] + "$");
		}

		System.out.println("Dictionary: " + Arrays.toString(dictionary));
		System.out.println("Document:   " + Arrays.toString(document));
		System.out.print("\nIncorrect spellings: ");
		for (int i = 0; i < numWordsInDoc; i++) {
			if (!spellCheck[i])
				System.out.print(document[i] + " ");
		}
	}

	public static void testHeapSort() throws Exception {
		System.out.println("*** Test Heap Sort ***\n");
		String[] array = { "abc", "def", "abcd", "bce", "abx", "acfe", "bfr", "xyz", "de", "tyu", "ab", "abcd", "xy", "zxy", "abx", "def" };
		System.out.println("Before Sorting: " + Arrays.toString(array));
		HeapApplications.heapSort(array, array.length);
		System.out.println("After Sorting:  " + Arrays.toString(array));
	}

	private static void testTopKElements() throws Exception {
		
		System.out.println("*** Test Top-k ***\n");
		String array[] = { "xyz", "wrtu", "z", "pqr", "b", "abx", "def", "fgh", "fgh" };
		int length = array.length;
		System.out.printf("Original Array:     %s%n", Arrays.toString(array));
		String top3Array[] = HeapApplications.topK(array, length, 3);
		String top6Array[] = HeapApplications.topK(array, length, 6);
		String top9Array[] = HeapApplications.topK(array, length, 9);
		System.out.printf("Highest 3 elements: %s%n", Arrays.toString(top3Array));
		System.out.printf("Highest 6 elements: %s%n", Arrays.toString(top6Array));
		System.out.printf("Highest 9 elements: %s%n", Arrays.toString(top9Array));
	}

	private static void testDijkstra() throws Exception {
		System.out.println();
		String filePaths[] = { "dijkstra1.txt", "dijkstra2.txt" };
		for (int j = 0; j < filePaths.length; j++) {
			System.out.println("*** Test Dijkstra (" + filePaths[j] + ") ***\n");
			Dijkstra dijk = new Dijkstra(filePaths[j]);
			for (int i = 0; i < dijk.numVertices; i++) {
				dijk.executeDijkstra(i);
				System.out.println("Distance array (from v" + i + "): "
						+ Arrays.toString(dijk.distance).replaceAll("" + Integer.MAX_VALUE, "infty"));
				System.out.println("Parent array (from v" + i + "):   "
						+ Arrays.toString(dijk.parent).replaceAll("" + Integer.MAX_VALUE, "infty"));
				System.out.println();
			}
		}
	}

	public static void main(String[] args) throws Exception {

		testTrie();
		System.out.println();
		testDijkstra();
		testHeapSort();
		System.out.println();
		testTopKElements();
	}
}
