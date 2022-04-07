#include <iostream>
#include <string>
using namespace std;

#ifndef LCS_H_
#define LCS_H_

class LCS {

public:

	string longestCommonSubsequence(const string &x, const string &y) { // complete this method
	}

private:

	static string reverse(const string &str) {
		string rev = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			rev += str.at(i);
		}
		return rev;
	}
};

#endif /* LCS_H_ */
