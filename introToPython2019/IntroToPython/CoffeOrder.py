# Nathan Frazier Week3 problem 1
print("Enter how much coffee to order in lbs : ", end="")
user_input = eval(input())
# declare constants
COFFEE_RATE, SHIPPING_RATE, OVERHEAD = 9.50, 0.95, 2.75  # $ ship + overhead = total shipping where shipping rate is per lb
shipping_fee = float((SHIPPING_RATE * user_input) + OVERHEAD)
cost_of_coffee = float(user_input * COFFEE_RATE)
total_cost = cost_of_coffee + shipping_fee
# SELF NOTES :
# Using string modulo for built-in 'printf()' function in python
# String modulo syntax (%[flags] [width] [.precision] type)
# first number is total number of digits, .precision indicates where to place decimal

print("Coffee rate", COFFEE_RATE)
print("Shipping rate", SHIPPING_RATE)
print("Shipping overhead", OVERHEAD)

"""
IF [width] is too small, format will return the string version of the number.
So, since I put %0.2f, it rounds a float @ #.## and the resulting formatted number is a string.
"""

print("\n| COST BREAKDOWN |")
print(format("%s :\t$ %0.2f") % ("Cost of Coffee", cost_of_coffee))
print(format("%s :\t$ %0.2f") % ("Cost of Shipping", shipping_fee))
print(format("%s :\t$ %0.2f") % ("Overhead Included", OVERHEAD))
print(format("\n\tTotal Due : $ %0.2f" % (total_cost)))
