  ; Nathan Frazier hw4 q1 copy 8-bit array into new 16-bit array 
  
.data
 array1  db  10h, 20h, 30h, 40h, 50h   ; array of 4 byte hex nums, array1[0] = [0000]
 array2  dw  5 dup(?) 
                  ; each array entry takes up 2 addresses, array2[0] = [ 0000 to 0001]
    .code    ; si and di are at 1 memory address at a time
        lea si, array1 ; memory offset start of array1 into si 
        lea di, array2 ; memory offset start of array2 into di  
        
        
        mov cx, 5
            L1: 
                 ; [xx] square brackets point to a memory offset location (array1:0000-0004)
                 mov al,[si]   ; data at first mem loc after lea, move to al    
                 mov [di] , al ; current al to the mem loc of di (array2:0005-0009)
                 ;mov dx, [di] ; dbl chk al ---> [di] by showing in dx        
                 inc si  ; increment si by 1 , so it points to array1[1]
                 add di, 2  ; increment di by 2 , it points to array2[1] reg 1 and skips array2[0] reg 2 
                  .
                  .
                  loop L1 ; jump to loop
                  
            mov cx, 5
            lea di, array2 ; reset di value to the mem loc of array 2 start
            L2:
            
            mov dx, [di]
            add di, 2 ; increment di by 2 since it points to array2, which = dw, which uses 2b each indx
            .
            .
            loop L2
            hlt
        