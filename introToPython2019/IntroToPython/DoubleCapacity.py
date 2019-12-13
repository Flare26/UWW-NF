# Nathan Frazier Week 14 - 1 Double Capacity

#This function returns a list that is twice the size of the argument xList.

def main():
    print("Enter some list of integers, seperated by space:" , end=" ")
    user_str = input()
    userList = user_str.split(" ") # split into list by space
    print("User List = %s" % userList )
    newList = DoubleCapacity(userList)
    print("New List = %s" % newList)



def DoubleCapacity (xList):
    doubled = [0] * len(xList) # creates a proportionate number of 0's. Add to xList for a len() of 2*len(xList)
    newL = list(xList + doubled) # new list, with the blank one added to the end. Size is double that of xList
    return newL

main() #call