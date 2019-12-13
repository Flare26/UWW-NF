# Nathan Frazier Week 14 - 2 Double Capacity
# curvedScore = (rawScore)(1 / 1.5) × 100(1 - (1 / 1.5))

def main():
    print("Enter scores between 0 - 100 inclusive ( seperate by space ):", end=" ")
    user_str = input()


    input_list = list(filter(None, user_str.split(" ")))  # split into list by space, filters extra spaces
    rawList = [ int(i) for i in  input_list ] # same list, parsed to int
    curvingGrades(rawList)


def curvingGrades(rawList):
    # print rawList using loop
    curvedList =  [None]*len(rawList)  # [obj]*n where n is the no of elements in the array.
    print("Raw scores:")
    for i in range(0, len(rawList)):
        print(rawList[i] , end=" ") # goes from index 0 to max and prints that value
        curvedList[i] = calculateCurve(rawList[i]) # takes score at index, calculates the curve, and reassigns
    print("\nCurved Scores:")
    for i in range(0, len(curvedList)):
        print(format(curvedList[i] , "0.2f") , end=" " ) # goes from index 0 to max and prints that value



def calculateCurve(original_score):
    #store equation
    curved = ( (original_score) ** ( 1 / 1.5 ) ) * ( 100 ** ( 1 - ( 1 / 1.5))) # '**' is raise power operator
    return curved

main()