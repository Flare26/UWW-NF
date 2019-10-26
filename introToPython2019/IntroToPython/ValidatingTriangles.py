#Nathan Frazier Week 8_1 Validating Triangles Program
import math

print("*this program takes 3 numbers (inches) for triangle edges [ A B C ]")
print("Please enter 3 edge lengths A , B , C : " , end="")
A, B, C = eval(input())

isTriangle = (  ( A + B > C ) and ( B + C > A ) and ( A + C > B) )

while ( not isTriangle ) :
    print("The three sides cannot form a triangle.\nEnter new numbers for A , B , C : " , end="")
    A, B, C = eval(input())
    isTriangle = ((A + B > C) and (B + C > A) and (A + C > B)) #re-check the bool to break the loop

if ( isTriangle ) :
    print("Yes, the sides CAN form a triangle!")
    s = ( A + B + C ) / 2
    # CANNOT use only parentheses to multiply -- must seperate with * operator
    area = math.sqrt(s*(s - A)*(s - B)*(s - C))
    print("The area of the triangle is : %0.2f" % area)