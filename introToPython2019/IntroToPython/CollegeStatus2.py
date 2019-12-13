# Program #3 -- College Classification Program
# declare variables
creditsEarned = 0   #int
status = ""         #str

# 2: getting the user input
print("Enter the number of credits earned: ", end="")
creditsEarned = eval(input())
# 3: making calculations
if (creditsEarned < 7):   #[0, 7)
    status = "Freshman"
elif (creditsEarned < 16): #[7, 16)
    status = "Sophomore"
elif (creditsEarned <26): #[16, 26)
    status = "Junior"
else:                       # >= 26
    status = "Senior"
# 4: print out the results
print("You are status is: ", status)
