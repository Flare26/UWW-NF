# Nathan Frazier Week 10_2 quadratic equation
import math
def getDiscriminant(a,b,c) :
    d = ( math.pow(b,2) ) - ( 4 * a * c )
    return d

def getRoots(a,b,c) :
    d = getDiscriminant(a,b,c)

    if d < 0 :
     return "NO real roots!"

    if ( d >= 0 ) :
        x1 = ( -b + math.sqrt(getDiscriminant(a,b,c))) / ( 2 * a )
        x2 = (-b - math.sqrt(getDiscriminant(a, b, c))) / (2 * a)
        # remember format rounds and returns an 'str' type
        x1 = format(x1, "0.3f")
        x2 = format(x2, "0.3f")

        if x1 == x2 :
            return "The root is %s" % x1
        elif x1 != x2 :
            return"The roots are %s & %s " % ( x1, x2 )

#def main
def main() :
    print("Please enter a , b and c : ", end="")
    a,b,c = eval(input())
    print(getRoots(a,b,c))
#call main
main()