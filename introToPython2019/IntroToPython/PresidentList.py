# Nathan Frazier Week 15 President List
import random

def main():
   selections = selectFive()
   c, i = printSelectedPresidents(selections) # store correct / incorrect for fun
   print("You finished the quiz.\nCorrect: %d\nIncorrect: %d" % (c,i))



def selectFive():
    list = [0] * 45 # create array of 45 all set to 0

    # random.sample() has the ability to return lists!
    selections = random.sample(range(1,45),5) # random.sample takes a range of numbers and returns 5 from that range
    for c in selections:
        list[c] = 1 # set that index to 1 in the list array
    print(selections)
    #print(list)
    return list # return list with selected indexes

def printSelectedPresidents(keyArray): # x is a list, the function is a void function
    indexes = list() # from passed array, get the selected presidents
    lastNames = list() # declare a list to store strings from text file
    firstNames = list()  # declare a list to store strings from text file
    presNumber = list()
    president_txt = open("presidents.txt" , "r")
    correct = 0
    incorrect = 0
    # 0: the 1st, Washington, George


    for i in range(0, len(keyArray)):
        if (keyArray[i] == 1):
            indexes.append(i) # the array contains 1 at this index


    count = 0 # length representation
    line = president_txt.readline() # begin the read
    while (line != ""):
        count += 1

        for i in indexes:
            if ( count == i ): # if the line we are reading corresponds to a highlighted index
                fields = line.split(",")
                # !BONUS NOTE : SLICING PYTHON STRINGS! You can slice with array[start:end] since str is a char array!!
                presNumber.append(fields[0][4:8])  # practical use of index slicing makes getting substrings EASY!!!
                lastNames.append(fields[1].strip()) # strip string left-overs from .split() method
                firstNames.append(fields[2].strip()) # python likes to grab '\n' as part of the str


        line = president_txt.readline() # read next line and jump back to top of while in order to check EOF

    #print("Selected last names =")
    #print(lastNames)
    #print(firstNames)
    #print(presNumber)

    for i in range(0 , len(indexes)):
        print("President %s was the %s president. What was their first name?" % ( lastNames[i] , presNumber[i] ) )
        ans = input().lower().strip() # immediately converts input string to lower case and strips any accidents

        if (ans == firstNames[i].lower()) :
            print("Correct!")
            correct +=1
        else:
            print("Incorrect!")
            incorrect += 1
    return correct, incorrect


main() # call main