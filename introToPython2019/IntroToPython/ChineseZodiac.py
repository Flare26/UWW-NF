# Nathan Frazier week 12_1 Chinese Zodiac

#initialize sequence list 'zodiacs'
zodiacs = [ "Monkey" , "Rooster" , "Dog" , "Pig" , "Rat" , "Ox", "Tiger", "Rabbit", "Dragon", "Snake" , "Horse" , "Sheep" ]

def main():
    print("Enter a year:", end=" ")
    userin = input() #get as string to error check
    year_isvalid = userin.isnumeric() #call string method, year_valid will be boolean
    while not year_isvalid: # if input contains alpha characters...
        userin = input("Please enter a valid year:")
        year_isvalid = userin.isnumeric()

    #once the input has no alpha chars, convert to INT
    userin = int(userin) #replace the value after it gets parsed as INT. STR --> INT
    zod_str = yearToZodiac(userin) # call toZodiac method
    print("The Chinese zodiac for year %d is %s" % (userin, zod_str)) # Use string formatting for result

def yearToZodiac(year):
    index = year % 12 # turn our user input from STR to INT type for calculation
    zodiac_str = zodiacs[index]
    return zodiac_str

main() # call main