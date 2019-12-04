# Nathan Frazier week 12_2 Below Average
# Personal notes : range does not include last val, which is perfect for index
# if range were to return the last value in this program, it would technically be an 'out of bounds' index
# .lstrip('-') strips the leading byte of an object if it is a negative sign
# .lstrip() is used because .isdigit() throws an exception while checking a negative number
def belowAvg(numsList):
    #len() returns absolute value of indexes, so the actual last index would = len(numsList) - 1
    sum = 0
    length = len(numsList)
    for value in numsList:
        #print("Adding %d to sum..." % int(value))
        sum += int(value) # If program makes it to here, value must be a digit, but technically it's still STR
    print("AVG = sum / length = %d / %d" % (sum,length))
    AVG = (sum/length)
    return AVG

def main():
    input_isvalid = True # USE AS FLAG ; assume the user will enter a valid list / initialize the value
    print("Enter some integers, seperated by space bar (Enter to continue):", end=" ")
    userin = input() # dont eval yet, need as string to convert to list
    input_list = userin.split(" ") # split into a list, whitespace delimiter
    print("Your input =", input_list)
    # split returns a list index for what's before AND after the delim. Ending with a space makes a null obj.
    input_list = list(filter(None, input_list)) # filter() will take an object, and an iterable. Filters null vals

    # START OPTIONAL ERROR CHECKING
    # check for anything to make the input_isvalid flag = False ( if they enter a non-digit )
    for x_str in input_list:
        if not x_str.lstrip('-').isdigit(): # this will trigger also in the case of a null object, so get rid of them before here
            print("Invalid value:",x_str)
            input_isvalid = False

    # if any list index is not a digit...
    if input_isvalid == False:
        print("There should be only digits in the list. Please restart & try again!")
        exit(0)
    # END ERROR CHECKING

    print("Your input is valid!")
    average_f = belowAvg(input_list) # call our custom method, returns float
    print("Average = %0.2f" % average_f ) # print float in 2 decimal place format
    under_average = list() # initialize blank list for numbers under the avg
    for x_str in input_list:
        if (int(x_str) < average_f):
            under_average.append(x_str) # remove the object if under the average

    print("List values less than the average are:" , under_average) # print the new list

main() # call main