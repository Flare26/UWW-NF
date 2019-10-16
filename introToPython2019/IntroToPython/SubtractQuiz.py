# Program #2 – SubtractionQuiz.py
import random
#1: generate two random integers between 0 and 9
x1 = random.randint(0,9)
x2 = random.randint(0,9)

#make sure that x1 >= x2
if(x1 <x2):
    #swapping x1 and x2
    x1, x2 = x2, x1

#2: prompt the student to answer "What is x - y = ?"
print("What is", x1 , " - ", x2 , ' = ', end="" )
studentAns = eval(input())

#3: use an if ... else statement to verify the answer
if (studentAns == (x1-x2)):
    print("Correct!!")
else:
    print("Incorrect!")
    print(x1, " - ", x2, " = ", x1-x2)
