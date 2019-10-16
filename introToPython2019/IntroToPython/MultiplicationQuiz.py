# Nathan Frazier Week 6_1 Multiplication Quiz
import random
#in java, referecing random would technically be referencing a static method

num1 = random.randint(1,9)
num2 = random.randint(1,9)
print("%d x %d = _ ? : " % ( num1 ,  num2) , end="")
answer = int(input())
dialogue = random.randint(1,4)
if (answer == (num1 * num2)) :
    if ( dialogue == 1 ) :
        response = "Very good!"
        print(response)
    elif ( dialogue == 2 ) :
        response = "Excellent!"
        print(response)
    elif ( dialogue ==  3 ) :
        response = "Nice work!"
        print(response)
    elif ( dialogue == 4 ) :
        response = "Keep up the good work!"
        print(response)

else :
    if ( dialogue == 1 ) :
        response = "No, but thanks for trying."
        print(response)
    elif ( dialogue == 2 ) :
        response = "Close, but not quite right."
        print(response)
    elif ( dialogue ==  3 ) :
        response = "Wrong answer."
        print(response)
    elif ( dialogue == 4 ) :
        response = "No, but it's okay to be wrong."
        print(response)