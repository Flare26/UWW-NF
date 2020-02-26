package PA2.JAVA;

// Nathan Frazier

public class BinarySearchApplications {

	
	// assume the list is sorted
	private static int minIndexBinarySearch(int array[], int arrayLength, int key) { // Complete this function.
		int lowIdx = 0;
		int highIdx = arrayLength;
		int midIdx = (lowIdx + highIdx)/2;
		int minKeyIdx = -1;
		     
		   while( lowIdx <= highIdx ){  
		      if ( array[midIdx] < key ){  
		        lowIdx = midIdx + 1;     
		      }else if ( array[midIdx] == key ){  
		        //System.out.println("Key found at index: " + midIdx);  
		        minKeyIdx = midIdx;
		        highIdx = midIdx - 1;
		        
		      }else{  
		         highIdx = midIdx - 1;  // set the new high to the left of the last mid, since key will be lower than that val
		      }  
		      midIdx = (lowIdx + highIdx)/2;  
		      
		      if ( lowIdx == highIdx )
		    	  break;
		   }  
		   
		   
			   return minKeyIdx;
		   
	}

	private static int maxIndexBinarySearch(int array[], int arrayLength, int key) { // Complete this function.
		int lowIdx = 0;
		int highIdx = arrayLength;
		
		int midIdx = (lowIdx + highIdx)/2;
		
		int maxKeyIdx = -1;
		int count = 0; // debugging
		 
		   while( lowIdx <= highIdx ){  
			   count++;
			  // System.out.println("Top of while run #" + count);
		      if ( array[midIdx] < key ){  
		        lowIdx = midIdx + 1; 
		      }else if ( array[midIdx] == key ){  
		      //  System.out.println("line 48 key idx " + midIdx);  
		        maxKeyIdx = midIdx;
		        lowIdx = midIdx + 1;
		        
		      }else{  
		         lowIdx = midIdx + 1;  // set the new high to the left of the last mid, since key will be lower than that val
		      }  
		      midIdx = (lowIdx + highIdx)/2; 
		    //  System.out.println("highIdx is now " + highIdx);
		     // System.out.println("lowIdx is now " + lowIdx);
		     // System.out.println("Set midIdx to " + midIdx);
		      
		      if ( lowIdx == highIdx )
		    	  break;
			       
		   } 
		    
		   System.out.println("Broke while loop");
		  
		   return maxKeyIdx;
		
	}

	private static int countNumberOfKeys(int array[], int arrayLength, int key) { // Complete this function.
		// run both, find high and low, just add in 1 line to both that increments the key count
		int minKeyIdx = minIndexBinarySearch( array, arrayLength, key);
		int maxKeyIdx = maxIndexBinarySearch( array, arrayLength, key);
		
		int occurances = ( maxKeyIdx - minKeyIdx + 1 );
		   System.out.println("Returning occurances");
		   return occurances;
	}

	private static int predecessor(int array[], int arrayLen, int key) { // Complete this function.
		
		int leftIdx = 0;
		int rightIdx = arrayLen;
		int midIdx = (leftIdx + rightIdx) / 2;
		int keyIdx = -1;
		int predIndex = -1;
		
		 while( leftIdx <= rightIdx ){  
			      if ( array[midIdx] < key ){ 
			    	predIndex = midIdx;
			        leftIdx = midIdx + 1;     
			      }else if ( array[midIdx] == key ){   
			        break;  
			      }else{  
			         rightIdx = midIdx - 1;  
			      }  
			      midIdx = (leftIdx + rightIdx)/2;  
			   }    
		   
		// System.out.println("Broke while loop, lowIdx > highIdx"); 
		  return predIndex;
		
	}

	public static void main(String[] args) throws Exception {

		int A[] = { 1, 1, 3, 7, 9, 14, 14, 14, 14, 14, 14, 18, 27, 39, 39, 39 };
		System.out.println("*** Counting the number of occurrences of key ***\n");
		System.out.println("Number of occurrences of 1 is " + countNumberOfKeys(A, A.length, 1));
		System.out.println("Number of occurrences of 14 is " + countNumberOfKeys(A, A.length, 14));
		System.out.println("Number of occurrences of 39 is " + countNumberOfKeys(A, A.length, 39));
		System.out.println("Number of occurrences of 7 is " + countNumberOfKeys(A, A.length, 7));
		System.out.println("Number of occurrences of 100 is " + countNumberOfKeys(A, A.length, 100));
		System.out.println("Number of occurrences of -88 is " + countNumberOfKeys(A, A.length, -88));
		System.out.println("Number of occurrences of 16 is " + countNumberOfKeys(A, A.length, 16));

		System.out.println("\n*** Finding Predecessor ***\n");
		int B[] = { 1, 0, 39, 47, 36, 12, 6 };
		for (int i = 0; i < B.length; i++) {
			int key = B[i];
			int x = predecessor(A, A.length, key);
			if (x >= 0)
				System.out.println("Predecessor of " + B[i] + " is " + A[x]);
			else
				System.out.println("Predecessor of " + B[i] + " is not defined.");
		}
	}
}
