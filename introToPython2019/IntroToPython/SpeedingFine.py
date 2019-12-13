#Program #1 -- Week4 Assignment,  SpeedingFine
# 1: declare variables
limitSpeed, clockedSpeed = 0, 0
legality = "Legal"
over90 = "No"
fine = 0

# 2: getting the user inputs
print("Enter the speed limit and the clocked speed: ", end="")
limitSpeed, clockedSpeed = eval(input())

# 3: making calculations
if (clockedSpeed > limitSpeed):
    legality ="Illegal"
    fine = 85 + (clockedSpeed-limitSpeed)*5
    if (clockedSpeed >90):
        fine += 200 #adding $200 to fine
        over90= "Yes"

# 4: print out the results
print("The clocked speed is:\t", legality)
print("Driving over 90 mph:\t", over90 )
print("The fine is:\t",fine )
