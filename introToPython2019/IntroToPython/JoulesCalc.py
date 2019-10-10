#Nathan Frazier Assignment 2_01
#This program calculates the joules needed in order to heat water, takes 2 temps for input
print("This program requires 3 input values | mass(kg), temp0(C), temp1(C)")
print("Please enter a mass of water (kilograms)")
waterWeight = float(input())
print("waterWeight =",waterWeight)
#input is comma delimited while assigning to multiple vars like below
finalTemp, initialTemp = eval(input("Please enter a value for finalTemp & initialTemp in Celsius:"))
print("Initial Temperature =",initialTemp)
print("Final Temperature =", finalTemp)
#typecast the strings 'finalTemp' and 'initialTemp' to float() during calculation phase
energyJ = waterWeight *  (float(finalTemp) - float(initialTemp)) * 4184
print("It will take",energyJ,"Joules to heat",waterWeight,"kg of water from",initialTemp,"to",finalTemp,"Celsius")