#Nathan Frazier week 5_2 Admission
GPA = float(input("Please enter a GPA between 0.0 & 4.0 inclusive :"))
if (GPA >= 4.0 or GPA <= 0.0) :
    print("Error : GPA must be within given constraints.")
    exit(0)
SCORE = float(input("Please enter a palcement test score between 0-100 inclusive:"))
if (SCORE <= 0 or SCORE >= 100) :
    print("Test score invalid!")
    exit(0)


goodGPA = False
goodTESTSCORE = False
if ( GPA >= 3.0 ) :
    goodGPA = True

if ( SCORE >= 80.00) :
    goodTESTSCORE = True

if ( goodGPA or goodTESTSCORE ) :
    print("ACCEPTED")
else :
    print("DENIED")
