#include <climits>
#include <vector>
using namespace std;

#ifndef LIS_H_
#define LIS_H_

class LIS {

public:

	static vector<int> longestIncreasingSubsequence(int *arr, int len) { // complete this method
	}

	static void reverse(vector<int> &list) {
		for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
			int temp = list.at(j);
			list[j] = list.at(i);
			list[i] = temp;
		}
	}
};

#endif /* LIS_H_ */
