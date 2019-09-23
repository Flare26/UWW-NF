#SummingDigits.py
# step 1: declare variables
userIn = 0 # int, for holding input value
sum = 0     # int, to store the output
ones, tens = 0, 0

# step 2: getting the user input
print("Enter an integer between 1 and 99 :", end="")
userIn = eval(input())

# step 3: making calculations
ones = userIn % 10 #getting the rightmost digit from userIn
tens = userIn // 10 #removing the rightmost digit from userIn
sum = ones + tens
# step 4: print the result
print("The sum is", sum)