# Program #2 -- TestBoolean program
print("Enter an integer: ", end="")
userInput = eval(input())

check1 = (userInput % 2 == 0 and userInput % 3 ==0)
check2 = (userInput % 2 == 0 or userInput % 3 ==0)
print("Is", userInput )
print("divisible by 2 and 3 ?",check1)
print("divisible by 2 or 3? ", check2)
