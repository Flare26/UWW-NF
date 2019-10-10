#Nathan Frazier Week 4 Practice

print ( "Enter an integer: ", end="")
x = eval( input() )
result = ( x % 2 == 0 ) #result is bool
print ("Is",x,"an even?", result)
print ("Enter two numbers: ", end="")
first, second = eval ( input() )
# find larger input
# must have colon before block
if first > second :
    print ( first,"is larger." )
elif second > first :
    print ( second,"is larger.")
elif first == second :
    print ( "Both inputs are equal.")
