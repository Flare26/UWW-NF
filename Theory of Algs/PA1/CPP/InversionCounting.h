#ifndef INVERSIONCOUNTING_H_
#define INVERSIONCOUNTING_H_

class InversionCounting {

	int *array;
	int *mergedArray;
	int n;

public:

	InversionCounting(int *array, int n) {
		this->array = array;
		this->n = n;
		mergedArray = new int[n];
	}

	~InversionCounting() {
		delete[] mergedArray;
	}

	int countInversions() {
		mergedArray = new int[n];
		return countInversions(0, n - 1);
	}

	int bruteForce() {
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (array[i] > array[j])
					count++;
		return count;
	}

private:

	int countInversions(int left, int right) { // complete this function
	}
};

#endif /* INVERSIONCOUNTING_H_ */
