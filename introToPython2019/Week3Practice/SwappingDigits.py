# Nathan Frazier Week3 problem 2
sentinel = True
while sentinel:
    print("Please input an integer between 1-99 inclusive:", end="")
    n = eval(input())
    if n < 1 or n > 99:
        print("Out of range, please retry.")
    else:
        print("Good input detected!", n, "Preforming calculation...")
        sentinel = False
print("Sentinel value triggered!")
first_digit = n // 10  # How many times 10 goes in to n excluding remainder
second_digit = n % 10  # Remainder only after dividing by 10
print("First digit using // operator :", first_digit)
print("Second digit using % operator :", second_digit)
nswapped = (second_digit * 10) + first_digit
print("Swapped result :", nswapped)
ntriple = 3 * nswapped
print("Value of 3 *", nswapped, "is", ntriple)
