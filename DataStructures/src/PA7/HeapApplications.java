

public class HeapApplications {
// NF
	public static void heapSort(String array[], int arrayLen) throws Exception { // complete this function
		Heap h = new Heap();
		for (String s : array)
		{
			h.insert(s);
		}
		
		for (int i = 0 ; i < array.length; i++)
		{
			array[i] = h.getMinimum();
			h.deleteMinimum();
		}
	}

	public static String[] topK(String array[], int arrayLen, int k) { // complete this function
		if (k > arrayLen)
			arrayLen = k;
		Heap h = new Heap();
		for (int i = 0 ; i < k; i++) {
			h.insert(array[i]);
		}
		for (int i = k; i < arrayLen; i++)
		{
			String minString = h.getMinimum();
			if (minString.compareTo(array[i]) < 0)
			{
				h.deleteMinimum();
				h.insert(array[i]);
			}
		}
		
		String [] topK = new String [k];
		int pos = 0;
		while (h.size() > 0)
		{
			//System.out.println("H size = " + h.size());
			topK[pos] = h.getMinimum();
			pos++;
			h.deleteMinimum();
		}
		return topK;
	}
}
