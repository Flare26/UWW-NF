

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lab20 {

	public static void main(String[] args) {
		List<String> words = new ArrayList<String>( 5 );
		words.add("Hello");
		words.add("World");
		words.add("How");
		words.add("Are");
		words.add("You");
		
		Collections.shuffle(words);
		
		Collections.sort( words , new Comparator<String>() {
			@Override
			public int compare( String s1 , String s2 ) {
				return s1.length() - s2.length();
			}
		}); // Anonymous class is built in the constructor, Overrides compare method
		
		System.out.println(words);
	}

}
