# Program #3 -- College Classification Program
# declare variables
creditsEarned = 0   #int
status = ""         #str

# 2: getting the user input
print("Enter the number of credits earned: ", end="")
creditsEarned = eval(input())
# 3: making calculations
if (creditsEarned >= 26):
    status = "Senior"
elif (creditsEarned >= 16):
    status = "Junior"
elif (creditsEarned >7):
    status = "Sophomore"
else:
    status = "Freshman"
# 4: print out the results
print("You are status is: ", status)
