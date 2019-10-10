#Nathan Frazier Week 5_1 Pet Advice

print("Do you live in...\n[1] - House\n[2] - Apartment\n[3] - Dormitory  ")
selection1 = int(input("[choose 1, 2, 3] -----> : "))
print("How many hours do you spend at home per day?\n[1] - 18+\n[2] - 10 to 17\n[3] - 8 to 9\n[4] - 6 to 7\n[5] - 0 to 5")
selection2 = int(input("[choose 1, 2, 3, 4, 5] -----> : "))
confirmation = format( "You selected, [%s] & [%s]" % (selection1, selection2) )
print(confirmation)

pig = (selection1 == 1 and selection2 == 1 )
dog = ( selection1 == 1 and selection2 == 2 )
snake = ( selection1 == 1 and selection2 >= 3  )
cat = ( selection1 == 2 and selection2 <= 2 )
hamster = ( selection1 == 2 and  selection2 >= 3 )
fish = ( selection1 == 3 and selection2 <= 4 )
ants = ( selection1 == 3 and selection2 == 5 )

reccomendation = "NO RECCOMENDATION"

if ( pig ) :
    reccomendation = "Pot-bellied Pig"
elif ( dog ) :
    reccomendation = "Dog"
elif ( snake ) :
    reccomendation = "Snake"
elif ( cat ) :
    reccomendation = "Cat"
elif ( hamster ) :
    reccomendation = "Hamster"
elif ( fish ) :
    reccomendation = "Fish"
elif ( ants ) :
    reccomendation = "Ant Farm"

#FINAL OUTPUT
print("The best pet for you is:",reccomendation)

