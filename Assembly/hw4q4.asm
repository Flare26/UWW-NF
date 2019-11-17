; Nathan Frazier hw 4 q4 Fibbonacci

.data
    array db 15 dup(?)
    
.code
    begin : 
          
         
          lea SI , array
          lea DI , array
          inc DI
          mov array[SI] , 0  ; move 0 to index array[0]
          mov array[DI] , 1  ; move 1 to index array[0 + 1]
          
            ; so, at this point we have db array, length 15 nulls
            ; SI points to array[0]
            ; DI points to array[1]
          
          
          
          
          mov CX, 13 ; 13 numbers to be generated, 0 and 1 already given
          fib:
             mov AL , 0   ; clear AL
             mov BL , 0   ; clear BL
             mov AL , array[SI]  ; first exe : copy array[0] --> AL
             mov BL , array[DI]  ; first exe : copy array[1] --> BL
             add BL , AL    ; arithmetic on BL : BL = AL + BL 
             
             inc SI ; first exe : SI from array[0] to array[1]
             inc DI ; first exe : DI from array[1] to array[2]
              
             mov array[DI] , BL  ; make the memory at new DI equal to the sum at BL
                                 ; SI now points to what we just moved
                     
          loop fib    
    