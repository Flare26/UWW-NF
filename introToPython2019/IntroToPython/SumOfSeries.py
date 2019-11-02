# Nathan Frazier Week 9_1 Sum of Series

def main() :
    print("Please enter a non-negative integer" , end=" : ")
    i = eval(input())
    while ( i < 0 ) :
      print("Please try again, input must be a positive integer" , end=" : ")
      i = eval(input())
    print("When i = %d, sum ( %d ) = %0.4f" % (i, i, sum(i)))

def sum(i) :
    result = 0
    for x in range(1, i + 1) :
        result += ( ( 2 * x ) - 1)  / ( ( 2 * x ) + 1 )
    return result

# MUST call main, python will NOT run the functions without calling them
main()