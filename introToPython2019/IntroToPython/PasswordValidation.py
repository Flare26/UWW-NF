# Nathan Frazier Week 11_1 Password Validation ( must include isValid(password): )

def main():
    print("Enter a string for password", end=": ")
    alphaChars = 0
    caps = 0
    digits = 0
    # END INIT

    password = input()
    length = len(password)
    valid = isValid(password)
    #get password string
    # count types using iteration
    for ch in password:

        if ch.isdigit():
            digits += 1

        if ch.isupper():
            caps += 1

        if ch.isalpha():
            alphaChars += 1

    response = "The password contains:\n%d number(s), %d letter(s) with %d uppercase, and it has %d characters." % (
    digits, alphaChars, caps, length)
    # format the response to print & add isValid result here
    if valid:
        response += '\n\tThe password is Valid.'
    else:  # if isValid returns False....
        response += '\n\tThe password is Invalid.'

    print(response) # print response, end main

def isValid(password) : #check password criteria
    hasCap = False
    hasDigit = False
    i = 0
    val = False # is the password valid?

    for ch in password :
        if ch.isupper() :
            hasCap = True
        if ch.isalpha() :
            i += 1

    if len(password) >= 8 and i >= 2 : # if character array length is at least 8 AND has alpha character requirements
        if hasCap :
            val = True # if all requirements are met, then change to True
    return val

main() # call main