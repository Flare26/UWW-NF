#Declare vars and get user input
#use if else
bagels = 0
charge = 0
print( "Enter the number of bagels you need: ", end="")
bagels = eval ( input () )
if bagels < 6 :
    unitPrice = 0.75
    charge = bagels*0.75
else :
    unitPrice = 0.6
    charge = bagels*0.6
print ( "Each bagel costs: $",unitPrice)
print ( "Each bagel costs: $",format(unitPrice, "0.2f"))
print ("Your order of",bagels,"bagels will cost $", format(charge,"0.2f"))
