#Nathan Frazier week 7_2 Counting Numbers Positive / Negative
#Python does not know ++ or --, must increment by += / -=
#EDIT 10/26
count = 0
pos = 0
neg = 0
sum = 0

print("Enter an integer, 0 to exit : " , end="")
userin = eval(input())

while( userin != 0 ) :
    #Increment tally and calc new average
    sum += userin
    count += 1
    avg = sum / count
    #Check to increment positive / negative counters
    if userin > 0 :
        pos += 1
    elif userin < 0 :
        neg += 1
    userin = eval(input("Enter another input, 0 to exit : "))
#END while loop
if userin == 0 :
    #If user enters 0 at any point...
    print("No numbers are entered except 0.")
    if sum == 0 :
        #if user enters 0 without any non-zero numbers first...
        exit(0)
print("----------")
print("Sum = " , sum)
print("Positive integers = ", pos)
print("Negative integers = " , neg)
print("AVG = " , format(avg , "4.2f"))
