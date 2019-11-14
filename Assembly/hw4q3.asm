; Nathan Frazier Reverse Array

.data
    array db 01h, 02h, 03h, 04h, 05h, 06h, 07h, 08h, 09h, 0Ah
    
.code
    begin: 
          
       lea SI , array
       lea DI , array
       add DI , 9 ; advance DI to index 9
      
       
       
       
        add CX, 5
       reverse:
               mov AL, array[SI]
               mov BL, array[DI]
               mov array[SI], BL ; moves last num to first index
               mov array[DI], AL ; moves first num to last index 
               inc SI
               dec DI
               .
               .
               loop reverse ; this loops edits the memory 0000-0009 ofthe original array
               hlt