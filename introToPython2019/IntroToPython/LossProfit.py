cost, revenue = 0,0
print ( "Enter the costs and revenue:", end="")
costs, revenue = eval( input () )
if ( costs == revenue ) :
    print ( "Break Even!")
elif ( cost > revenue ) :
    profit = revenue-costs
    print( "Profit = ", profit )