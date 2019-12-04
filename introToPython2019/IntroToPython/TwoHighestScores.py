# Nathan Frazier Week 10 1 average of two highest scores
import math
def avgTwoHighest( score1, score2, score3 ) :
    list = [ score1, score2, score3 ]
    minimum = min(list[0],list[1],list[2]) # identify the lowest so it doesn't get set as max2
    max = 0
    max2 = 0
    for i in range(0, len(list)) :
        if list[i] > max :
            max2 = max
            max = list[i]
            #move old maximum to max2 if a new one is found
    avg = (max + max2) / 2
    return max, max2, avg

def main() :
    #print("Testing MAIN")
    print("Enter three scores between 0 - 100 inclusive." , end=" : ")
    s1, s2, s3 = eval(input())

    while ( not s1 in range(0,101) ) or ( not s2 in range(0,101) ) or ( not s3 in range(0,101) ) :
        # while will be TRUE as long as one of the 3 are 'out of bounds'
        # range has the ability to return boolean values
        print("Each score must be between 0 - 100 inclusive. Enter the three scores." , end=" : ")
        s1, s2, s3 = eval(input())
    high1, high2, avg = avgTwoHighest(s1, s2, s3)
    print("First highest : %d\nSecond Highest : %d\n\tThe average of the two highest scores is : %0.2f" % (high1, high2, avg))

#call main
main()