#RestaurantBill.py
# step 1: declare variables
mealCost, tip, tax, total = 0, 0, 0, 0  #float
tipRate = 0.18  # constant, tipRate = 18%
taxRate = 0.07  # constant, taxRate = 7%
# step 2: getting the user input
print("Enter the charge for the food: " , end="")
mealCost = eval(input())
# step 3: making calculations
tip =mealCost * tipRate
tax = mealCost * taxRate
total = mealCost + tip + tax
# step 4: print the result
print("Bill Information:\n")
print("\tMeal:\t$", format(mealCost, "0.2f"))
print("\tTip:\t$", format(tip, "0.2f"))
print("\tTax:\t$", format(tax, "0.2f"))
print("\tTotal:\t$", format(total, "0.2f"))