#include <iostream>
#include <vector>
#include <string>
using namespace std;

#ifndef PRIORITYQUEUE_H_
#define PRIORITYQUEUE_H_

template<class T, typename F>
class PriorityQueue {

private:
	vector<T> contents;
	int currentSize;
	F comparator;

public:

	PriorityQueue() {
		currentSize = 0;
	}

	T peek() {
		if (currentSize == 0)
			throw "Out of bounds access";
		return contents[0];
	}

	int size() {
		return currentSize;
	}

	T poll() {

		if (currentSize == 0)
			throw "Out of bounds access";
		if (currentSize == 1) {
			T item = contents[--currentSize];
			contents.pop_back();
			return item;
		} else {
			T item = contents[0];
			contents[0] = contents[--currentSize];
			contents.pop_back();
			siftDown(0);
			return item;
		}
	}

	void add(T item) {
		int index = currentSize++;
		contents.push_back(item);
		siftUp(index);
	}

private:

	void siftDown(int index) {
		int leftIndex = index * 2 + 1, rightIndex = leftIndex + 1;
		while (leftIndex < currentSize) {
			T minValue = contents[leftIndex];
			int minIndex = leftIndex;
			if (rightIndex < currentSize) {
				T rightValue = contents[rightIndex];
				if (comparator(minValue, rightValue) > 0) {
					minValue = rightValue;
					minIndex = rightIndex;
				}
			}
			if (comparator(minValue, contents[index]) < 0) {
				swap(index, minIndex);
				index = minIndex;
			} else
				break;
			leftIndex = index * 2 + 1;
			rightIndex = leftIndex + 1;
		}
	}

	void siftUp(int index) {
		while (index > 0 && comparator(contents[parent(index)], contents[index]) > 0) {
			swap(index, parent(index));
			index = parent(index);
		}
	}

	void swap(int index1, int index2) {
		T temp = contents[index1];
		contents[index1] = contents[index2];
		contents[index2] = temp;
	}

	int parent(int index) {
		return (index - 1) / 2;
	}
};

#endif /* PRIORITYQUEUE_H_ */
