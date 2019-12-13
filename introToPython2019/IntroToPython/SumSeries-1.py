def main():
    print("Enter a positive integer:", end="")
    m = eval(input())
    print("The sum is", sum(m))

#sum function is a value-returning function
def sum(x): #define sum function that sum=1/2 + 2/3 + 3/4 ....
    s =0
    for i in range(1, x+1):
        s += i/(i+1)
    return s

#calling main
main()