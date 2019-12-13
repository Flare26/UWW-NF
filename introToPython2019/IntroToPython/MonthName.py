# MonthNames program

def main():
	print("")
	#declare a string that contains 12 month names, separated by comma
	monthName = "January,February,March,April,May,June,July,August,September,October,November,December"
	
	#split the comma delimited string into a list 
	monthList =monthName.split(",")
	
	#get a user input between 1 and 12
	number = eval(input("Enter a number between 1 and 12: "))
	while (number >12 or number <1):
		number = eval(input("Enter a number between 1 and 12: "))
		
	print("\nYou entered", number, ". The month name is", monthList[number-1], ".")
		
#call main
main()