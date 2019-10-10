#Nathan Frazier Speeding Fine Calculator Week 4_2

#init vars
BASE_FINE = 85 #dollars
final_fine = 0.00
over90mph = False
isLegal = "LEGAL"
speedOverLimit = 0

print("Enter the speed limit & clocked speed",end=": ")

#Invalid input will generate exception
SPEED_LIMIT, CLOCKED_SPEED = eval(input())

# if person was speeding do this
if CLOCKED_SPEED > SPEED_LIMIT :

    isLegal = "ILLEGAL"
    final_fine += BASE_FINE #Add base fine to shopping cart
    if CLOCKED_SPEED > 90 :
        over90mph = True # Was offense over 90mph? If so + 200
        final_fine += 200
        print("Over 90mph, + $ 200\n")

    speedOverLimit = CLOCKED_SPEED - SPEED_LIMIT
    final_fine += ( speedOverLimit * 5 )

print( "\nClocked Speed is %s @ %d mph [ %d over limit ]" % ( isLegal , CLOCKED_SPEED , speedOverLimit) )
print( "Over 90 mph: %r" % ( over90mph ) )
print( "\tThe fine is: $ %0.2f" % ( final_fine ) )