#Nathan Frazier Copy Charge Calculator Week 4_1
#Declare vars and get user input
copies = 0
charge = 0

print( "Enter the number of desired copies: ", end="")
copies = eval ( input () )
if copies <= 20 :
    unitPrice = 0.10 #cents per copy
    charge = copies*unitPrice
    print("You made", copies, "copies.")
    print(copies," x $ 0.10")
    print("Your order will cost $", format(charge, "0.2f"))
else :
    additional = copies - 20
    unitPrice = 0.08
    charge = 2 + ( additional*unitPrice )
    print("You made", copies, "copies.")
    print("( 20 x 0.10 ) + (",additional,"x 0.08 )")
    print("Your order will cost $", format(charge, "0.2f"))


