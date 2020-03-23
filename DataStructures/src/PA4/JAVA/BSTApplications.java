
// Nathan Frazier
public class BSTApplications extends BST {

	public BSTApplications(int A[]) {
		root = null;
		size = 0;
		for (int a : A)
			insertAndSet(a);
	}

	public BSTNode getPredecessor(int key) { // complete this method
		
		BSTNode predecessor = null;
		var temp = root;
		while ( temp != null ) {
			if ( temp.value == key )
				return temp;
			if ( temp.value < key ) {
				predecessor = temp; // these are transferring the memory adresses
				temp = temp.right;
			} else {
				temp = temp.left;
			}
		}
		return predecessor;												
	}

	public BSTNode getSuccessor(int key) { // complete this method
		BSTNode sucessor = null;
		var temp = root;
		while ( temp != null ) {
			if ( temp.value == key )
				return temp;
			if ( temp.value > key ) {
				sucessor = temp; // these are transferring the memory adresses
				temp = temp.left;
			} else {
				temp = temp.right;
			}
		}
		return sucessor;			
		
	}

	public int nearestNeighbour(int key) throws Exception { // complete this method
		BSTNode p = getPredecessor(key);
		BSTNode s = getSuccessor(key);
		int neighbor = 0;
		
		if ( p == null ) {
			return s.value;
		} else if ( s == null ) {
			return p.value;
		} else if (p != null && s != null) {
			if (Math.abs(key-p.value) < Math.abs(key-s.value))
				 neighbor = p.value;
			else
				neighbor = s.value;
		}
		return neighbor;
	}

	public BSTNode getLCA(int x, int y) { // complete this method
//Define the lowest common ancestor (LCA) of x and y as the lowest node (i.e., the node with minimum height) that contains both x and y in its subtree
		BSTNode tmp = root;
		
		if ( x > y ) {
			int temp = x;
			x = y;
			y = temp;
		}
		
		while (tmp != null) {
			if ( tmp.value < x )
				tmp = tmp.right;
			else if ( tmp.value > y )
				tmp = tmp.left;
			else
				break;
		}
		return tmp;
	}

	public int rangeCount(int L, int R) { // complete this method
		int counter = 0;
		if ( L > R ) {
			return 0;
		} else {
			BSTNode LCA = getLCA(L , R);
			if (LCA == null)
				return 0;
			
			if ( LCA.value == L && LCA.value == R )
				return 1;
			
			
			//• If LCA’s value > L and < R, then there is at least one value, so set counter to 1
			//• Else If LCA’s value equals L or R, then there is at least one value, so set counter to 1
			if ( LCA.value > L && LCA.value < R )
				counter = 1;
			else if ( LCA.value == L || LCA.value == R )
				counter = 1;
			
			if (LCA.value > L) {
				BSTNode tmp = LCA.left;
				
				while ( tmp != null ) {
					
					if ( tmp.value == L ) {
						try {
						counter += (1 + tmp.right.subtreeSize);
						} catch (NullPointerException e) {
							counter += 1;
						}
						break;
					}
					
					else if ( L < tmp.value ) {
						try {
						counter += (1 + tmp.right.subtreeSize);
						} catch (NullPointerException e) {
						counter += 1;
						}
						tmp = tmp.left;
					} else {
						tmp = tmp.right;
					}
				}
			}
			
			if ( LCA.value < R ) {
				BSTNode temp = LCA.right;
				
				while (temp != null) {
					if (R == temp.value) {
						try {
						counter += (1 + temp.left.subtreeSize);
						} catch (NullPointerException e) {
						counter += 1;
						}
						break;
					} else if ( R > temp.value ) {
						try {
						counter += (1 + temp.left.subtreeSize);
						} catch (NullPointerException e) {
						counter += 1;
						}
						temp = temp.right;
					} else {
						temp = temp.left;
					}
				}
			}
			
		}
		return counter;
	}
}
