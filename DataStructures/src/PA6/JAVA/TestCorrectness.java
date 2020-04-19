

import java.util.Arrays;



public class TestCorrectness {
	
	static final String UNWEIGHTED_GRAPH1_PATH = "unweighted1.txt";
	static final String UNWEIGHTED_GRAPH2_PATH = "unweighted2.txt";
	static final String DAG1_PATH = "dag1.txt";
	static final String DAG2_PATH = "dag2.txt";
	static final String MUTANT1_PATH = "mutant1.txt";
	static final String MUTANT2_PATH = "mutant2.txt";
	static final String MUTANT3_PATH = "mutant3.txt";

	private static void testBFS() throws Exception {
		String filePaths[] = {UNWEIGHTED_GRAPH1_PATH, UNWEIGHTED_GRAPH2_PATH};
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Test BFS on UnweightedGraph %d ***\n\n", i + 1);
			BFS bfs = new BFS(filePaths[i]);
			for (int j = 0; j < bfs.numVertices; j++) {
				bfs.executeBFS(j);
				System.out.println("Level array (from v" + j + "):   "
						+ Arrays.toString(bfs.level).replaceAll("" + Integer.MAX_VALUE, "infty"));
			}
			System.out.println();
		}
	}

	private static void testDFS() throws Exception {
		String filePaths[] = {UNWEIGHTED_GRAPH1_PATH, UNWEIGHTED_GRAPH2_PATH};
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Test DFS on UnweightedGraph %d ***\n\n", i + 1);
			DFS dfs = new DFS(filePaths[i]);
			for (int j = 0; j < dfs.numVertices; j++) {
				dfs.executeDFS(j);
				System.out.println("Level array (from v" + j + "):   "
						+ Arrays.toString(dfs.level).replaceAll("" + Integer.MAX_VALUE, "infty"));
			}
			System.out.println();
		}
	}

	private static void testDAG() throws Exception {
		String filePaths[] = {DAG1_PATH, DAG2_PATH};
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Test Longest Path on DAG %d ***\n\n", i + 1);
			DAG ag = new DAG(filePaths[i]);
			ag.longestPaths(0);
			System.out.println("Topological Order: " + Arrays.toString(ag.topoOrder));
			for (int j = 0; j < ag.numVertices; j++) {
				ag.longestPaths(j);
				System.out.printf("Longest Path Array (from v%d): %s\n", j,
						Arrays.toString(ag.distance).replaceAll("" + Integer.MIN_VALUE, "-infty"));
			}
			System.out.println();
		}
	}

	private static void testMutantLanguage() throws Exception {

		String filePaths[] = {MUTANT1_PATH, MUTANT2_PATH, MUTANT3_PATH};
		for (int i = 0; i < filePaths.length; i++) {
			System.out.printf("*** Test Mutant Language %d ***\n\n", i + 1);
			MutantLanguage mutant = new MutantLanguage(filePaths[i]);
			char topoOrder[] = mutant.getOrder();
			if (topoOrder != null)
				System.out.println("Alphabet order: " + Arrays.toString(topoOrder));
			else
				System.out.println("Unfortunately, this language has circular dependency.");
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		testBFS();
		testDFS();
		testDAG();
		testMutantLanguage();
	}

}
