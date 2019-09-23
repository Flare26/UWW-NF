# This program gets a value for feet from the user
# It converts the input to meters
#using input function, python assumes the input is a character sequence so we must use eval method
print("Enter a distance (feet) to convert into (meters) : " , end="")
feet = eval(input())
meters = 0.305*feet
#Unlike Java, we cannot concat numbers to strings. Must seperate by comma
print("Converson finished!" , meters , "m")