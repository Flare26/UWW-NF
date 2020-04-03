import java.util.ArrayList;
import java.util.Arrays;
//Nathan Frazier
public class MergeSortAndFriends {

	private static int[] binaryMerge(int A[], int B[], int lenA, int lenB) { // complete this function
		int lenC = lenA + lenB;
		int [] C = new int [lenC];
		
		// The following are index placeholders
		int a = 0;
		int b = 0;
		int c = 0;
		
		// While we still have more indexes in A && B
		// if element at A idx < element at B idx insert that one into C and increment
		// Else that means B is equal or bigger, so just copy element B idx into C and increment
		while ( a < lenA && b < lenB ) {
			if ( A[a] < B[b] ) {
				C[c] = A[a];
				a++;
			} else {
				C[c] = B[b];
				b++;
			}
			c++; // Move to the next idx in C
		}
		// after finding which val is lesser, insert the greater after it!
		while ( a < lenA ) {
			C[c] = A[a];
			a++;
			c++;
		}
		while ( b < lenB ) {
			C[c] = B[b];
			b++;
			c++;
		}
		return C;
	}

	public static ArrayList<Integer> commonElements(int A[], int B[], int lenA, int lenB) { // complete this function
		ArrayList<Integer> list = new ArrayList<Integer> ();
		int a = 0;
		int b = 0;
		// same concept except add common elements to the ArrayList
		while ( a < lenA && b < lenB ) {
			if ( A[a] < B[b] )
				a ++;
			else if ( A[a] > B[b] )
				b++;
			else {
				list.add(A[a]);
				a++;
				// Skip past repeated vals
				while ( a < lenA && A[a] == B[b] )
					a++;
			}
		}
		return list;
	}

	public static int[] kWayMerge(int lists[][], int listLengths[], int k) { // complete this function
		int newK = (k+1)/2; // New num of sorted lists after merging
		//ArrayList<int []>  mergedList = new ArrayList<int[]> (newK);
		int [] [] merged = new int [newK] [] ;
		int [] mergedListLengths = new int [newK];
		
		// if a single row was passed into this function via recursion...
		if ( k == 1 )
			return lists[0];
		// if we are left with exactly 2 left...
		if ( k == 2 )
			return binaryMerge(lists[0] , lists[1] , listLengths[0] , listLengths[1] );

	
		for ( int i = 0 ; i <= (k - 1) /2 - 1 ; i++) {
			mergedListLengths[i] = listLengths[2*i] + listLengths[2*i+1];
			merged[i] = binaryMerge(lists[2*i] , lists[2*i + 1], listLengths[2*i], listLengths[2*i + 1] ); // create new array relative to the length of new merged list
		}
		
		 if ( k % 2 != 0 ) {
			 //mergedList.set(newK - 1, lists[ k - 1 ]);
			 merged[newK - 1] = lists[k-1];
			 mergedListLengths[newK - 1] = listLengths[k - 1];
			 // If k is odd then last row does not have a pair to be merged so it gets copied to mergedLists[newK - 1]
		 } 
		
		 return kWayMerge(merged, mergedListLengths, newK);
	}

	public static void mergesort(int[] array, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergesort(array, left, mid);
			mergesort(array, mid + 1, right);
			int A[] = Arrays.copyOfRange(array, left, mid + 1);
			int B[] = Arrays.copyOfRange(array, mid + 1, right + 1);
			int mergedArray[] = binaryMerge(A, B, A.length, B.length);
			int i = left;
			int j = 0;
			while (j <= right - left)
				array[i++] = mergedArray[j++];
		}
	}
}