#Program #1: RandomMonth.py
# This program generates a random number between 1 and 12
# the program then displays the month name based on the random number
import random
#1: declare variables
x =0
monthName =""

#2: generate a random number between 1 and 12
x = random.randint(1, 12)

if (x==1);
    monthName ="Jan"
elif (x==2):
    monthName ="Feb"

#3: print out the results
print("The random number is", x, ".The month name is", monthName)

