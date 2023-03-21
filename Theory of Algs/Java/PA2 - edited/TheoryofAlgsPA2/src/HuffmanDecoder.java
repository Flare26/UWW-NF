

import java.util.Hashtable;

public class HuffmanDecoder {

	public static String decode(String encodedMsg, Hashtable<String, Character> encodingToCharMapping) {
		String code = new String();
		String decoded = new String();
		int n = encodedMsg.length();
		for (int i = 0 ; i <= n-1 ; i++)
		{
			code += encodedMsg.charAt(i);
			if (encodingToCharMapping.containsKey(code))
			{
				char c = encodingToCharMapping.get(code);
				decoded += c;
				code = new String(); // wipe the string
			}
		}
		return encodedMsg; // complete this method
	}
}
