
public class LCS {

	public static String longestCommonSubsequence(final String x, final String y) { // complete this method
	}
    
    private static String reverse(String str) {
        StringBuilder rev = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            rev.append(str.charAt(i));
        }
        return rev.toString();
    }
}
