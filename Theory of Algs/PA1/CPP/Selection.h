#ifndef SELECTION_H_
#define SELECTION_H_

#include "Partition.h"

class Selection: public Partition {

public:

	Selection(int *array, int n) :
			Partition(array, n) {
	}

	int select(int k) {
		return select(0, n - 1, k);
	}

private:

	int select(int left, int right, int k) { // complete this function
	}
};

#endif /* SELECTION_H_ */
