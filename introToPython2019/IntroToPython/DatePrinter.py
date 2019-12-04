# Nathan Frazier week 12_3 Date Printer

def main():

    print("Please enter a date in the format MM/DD/YYYY:" , end=" ")
    user_input = input().strip()
    print("You entered: %s %s, %s" % formatDate(user_input)) # formatDate() is returning 3 strings.


def formatDate(user_input):
    month_names = "January,Feburary,March,April,May,June,July,August,September,October,November,December"
    dateList_str = user_input.split('/')
    monthList_str = month_names.split(',')
    month_num = int(dateList_str[0]) # cast str to int, take from the split dateList
    month_str = monthList_str[month_num - 1] # subtract 1 to account for index
    str = month_str,dateList_str[1],dateList_str[2]

    return str

main() # call main