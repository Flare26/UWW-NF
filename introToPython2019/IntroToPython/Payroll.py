# Program #4 -- Payroll Program
# 1: declare variables
level = 0 #int
gross, totlaDeduction , netPay =0, 0, 0
medical, dental, retirment = 0, 0, 0

#named constants
FULLWEEK = 40
MED , DEN, RET = 85, 55, 0.03
PAY1, PAY2, PAY3 = 20, 28, 35

# 2: getting the user inputs
print("Enter your skill level (1 , 2, or 3):", end="")
level = eval(input())

#determine the pay rate based on the skill level
if level ==1:
    rate = PAY1
elif level == 2:
    rate = PAY2
else:
    rate = PAY3

# Calculate the gross pay
gross = FULLWEEK*rate

#prompt for medical insurance
print("Do you want the medical insurance (Y or N):", end="")
medOpt =input()
if (medOpt == "Y"):
    medical = MED

print("Do you want the dental insurance (Y or N):", end ="")
denOpt =input()
if (denOpt == "Y"):
    dental = DEN

print("Do you want the retirment plan (Y or N):", end ="")
retOpt =input()
if (retOpt == "Y"):
    retirment = gross *RET

# calculate the deductions
totlaDeduction = medical + dental + retirment
netPay = gross - totlaDeduction
# 4: print out the results
print("\nDescription:")
print("\tHours worked:\t\t$", FULLWEEK)
print("\tHourly pay rate:\t$",format(rate, "0.2f"))
print("\tGross Pay:\t\t\t$", format(gross, "0.2f"))

print("\nDeductions:")
print("\tMedical:\t\t\t$", format(medical, "0.2f"))
print("\tDental:\t\t\t\t$",format(dental, "0.2f"))
print("\tRetirement:\t\t\t$", format(retirment, "0.2f"))
print("\tTotal Deductions:\t$",format(totlaDeduction, "0.2f"))
print("\n\tNet Pay:\t\t\t$",format(netPay, "0.2f") )
