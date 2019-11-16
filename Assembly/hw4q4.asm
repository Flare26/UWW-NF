; Nathan Frazier Fibbonacci

.data
    array db 15 dup(?)
    
.code
    begin : 
          
         
          lea SI , array
          lea DI , array
          inc DI
          mov array[SI] , 0  ; move 0 to index 0
          mov array[DI] , 1  ; move 1 to index 0 + 1
          
            ;right here DI is 1 index above SI
          
          
          
          
          mov CX, 13 ; 13 numbers to be generated, 0 and 1 already given
          fib:
             mov AL , 0
             mov BL , 0
             mov AL , array[SI]  ; move the val at SI to AL
             mov BL , array[DI]  ; move the val at DI to BL
             add BL , AL    ; add AL and BL, sum will be at BL
             inc SI ; until this point, SI has pointed to 0 now 1
             inc DI ; until this point, DI has pointed to 1 now empty
             mov array[DI] , BL  ; make the memory at empty DI equal to the sum at BL
           
                  
             
          loop fib    
    