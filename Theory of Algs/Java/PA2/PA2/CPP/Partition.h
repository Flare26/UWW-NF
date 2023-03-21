#ifndef PARTITION_H_
#define PARTITION_H_

class Partition {

private:

	static void swap(int *array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

public:

	static int* partition(int *array, int left, int right, int pivot) { // complete this method
	}
};

#endif /* PARTITION_H_ */
