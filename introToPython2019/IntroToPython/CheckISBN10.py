# Nathan Frazier week 11_2 check ISBN 10 program
def main():

    print("Enter the first 9 digits of an ISBN as a string", end=": ")

    string = input()

    print("The ISBN-10 number is :\t", ISBNCheck(string) )

# end MAIN


def ISBNCheck(first9digits) :
    print("----------")
    print("Entered ISBN Check!")
    i = 0  # array index
    length = len(first9digits)
    sum = 0
    print("len(arr)=",length)
    while i < length :
        #print("i =", i)
        x = (i + 1) * int(first9digits[i])

        sum += x
        #print("x =" , x)
        i += 1
    mod = sum%11
    print("d1 + 2d2 + 3d3 + 4d4 + 5d5 + 6d6 + 7d7 + 8d8 + 9d9 = %s" % sum )
    print(sum,"% 11 = ",mod)
    if mod == 10 : mod = 'X'
    return first9digits + str(mod)



main()