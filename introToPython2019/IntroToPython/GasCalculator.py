# Nathan Frazier Week 9_2 Gas Calculator

def main() :
    print("Select a gas grade,\n R for regular, S for special, U for super : " , end="")
    gasGrade = input()

    while gasGrade != 'R' and gasGrade != 'S' and gasGrade != 'U' :
        print("Fuel grade must be R , S or U : " , end="")
        gasGrade = input()
    print("Enter how many gallons of gas : " , end="")
    numberGallons = eval(input())
    while numberGallons <= 0 :
        print("Gallons must be greater than 0! Try again : " , end="")
        numberGallons = eval(input())

    gasCost(gasGrade , numberGallons)

def gasCost (gasGrade, numberGallons):
    cost = 0
    gasrate = 0
    strGrade = "None!"

    if gasGrade == 'R' :
        gasrate = 2.49
        strGrade = "REGULAR"
    elif gasGrade == 'S' :
        gasrate = 2.79
        strGrade = "SPECIAL"
    elif gasGrade == 'U' :
        gasrate = 2.99
        strGrade = "SUPER"

    cost += ( numberGallons * gasrate )
    print ( "You purchased",numberGallons,"gallons of",strGrade,"gas at $",gasrate,"per gallon.")
    print("Your payment is : $ %0.2f" % cost)

main()