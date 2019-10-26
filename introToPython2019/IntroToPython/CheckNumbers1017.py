#CheckNumbers 10/17 in class

inX, evenNum, oddNum, sum = 0, 0, 0, 0,
print("Enter an int, enter 0 to exit", end=": ")
inX = eval(input())
#get userinput
while ( inX != 0 ) :

    #check if inX is even
    check = (inX % 2 == 0)
    if ( check ) :
        evenNum += 1
    else:
        oddNum += 1
    sum += inX
    #only get to second input after we check first input!!
    print("Enter an int, enter 0 to exit", end=": ")
    inX = eval(input())
#print results
print("Number of even valuies ( %d ) / Numeber of odd values ( %d ) | SUM = ( %d )" % (evenNum, oddNum, sum))