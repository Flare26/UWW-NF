#Nathan Frazier Week 5_2 Shipping Charges
import math
RATE = 1.50 #per 200 miles shipped
KILOGRAMS = int(input("Enter package weight in Kg (max 20Kg): "))
DISTANCE = int(input("Enter shipping distance (min 10 Mi, max 3000 Mi): "))

#RATE EXPRESSIONS
if ( KILOGRAMS > 2 and KILOGRAMS <= 6 ) :
    RATE = 2.20
elif ( KILOGRAMS > 6 and KILOGRAMS <= 10 ) :
    RATE = 4.00
elif ( KILOGRAMS > 10 and KILOGRAMS <= 20) :
    RATE = 5.20

#Calculations
BLOCKS = math.ceil(DISTANCE / 200)

instances = "There is %d instance of a 200-mile block."

if BLOCKS > 1 :
    instances = "There are %d instances of 200-miles blocks."

cost = (BLOCKS * RATE)
print(instances % (BLOCKS))
print("The shipping rate is $",RATE,"per 200-miles shipped.")
print("Your shipping charge is... ( %d x $%0.2f ) = $%0.2f" % ( BLOCKS, RATE, cost ))
