#Nathan Frazier week 7 world population
# EDIT 10/26
in2010 = 6.848 #billion people in jan 2010
rate = 0.0115 #population increase per year in percentage


print("Please enter a year after 2010 : " , end="")
year = eval(input())
while (year <= 2010) :
        print("Year must be after 2010: " , end="")
        year = eval(input())
print("\nYear\tPopulation (billion)\n----------------------")
count = year - 2010 # how many years after 2010?
population = in2010
print("2010\t%4.3f billion" % in2010)
while ( count > 0 ) :
    population += (population*rate)
    print ( count + 2010 ,"\t%4.3f billion" % population )
    count -= 1
