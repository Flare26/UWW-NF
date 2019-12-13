# LargerThan.py program


def main():
	s =input("Enter some integers, separate each number by space in one line, then press enter :\n")
	
	#split the string line into a list
	strList = s.split()
	
	#convert each split string element from string to number 
	numList =[eval(x) for x in strList]
	
	#getting a user input number 
	num = eval(input("Enter an integer: "))
	
	
	#calling the LargerThan function
	LargerThan(numList, num)
	
	print("")

#this function accepts a list and an integer, the function finds and prints all of the numbers 
#in the list that are greater than the given integer	
def LargerThan(xList, x):
	
	print("\nThe input list is: ")
	for i in range(len(xList)):
		print(xList[i], end="  ")
		
	print() #start a new line
	
	#find and print all of the numbers in the list that are greater than x
	print("\nAll of the numbers in the list that are greater than", x, "are: ")
	for i in range(len(xList)):
		if (xList[i] > x):
			print(xList[i], end="  ")
		
main()
	
