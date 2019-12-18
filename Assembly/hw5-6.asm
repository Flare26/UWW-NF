; multi-segment executable file template
; Nathan Frazier HW 5 - 6  

; 10h-2 {DH=row}{DL=column}
; 0710:0000 entry point
data segment   ; ULR, ULC, BRR, BRC
    boxes        db  5,10,20,70  ; first box
                 db  08h  
                 
                 db  12,20,18,60 ; second box
                 db  0Ch ; light green
                 
                 db  1,5,3,10    ; third box
                 db  0Dh
                 
                 db  5,60,24,79  ; fourth box
                 db  0Ah
                 
                 db  5,25,18,75  ; fifth box
                 db  0Eh 
                 
    ; declare temp variables 
    boxtally db 0            
    ULrow db ?
    ULcol db ?
    BRrow db ?
    BRcol db ?
    ccode db ? ; COLOR CODE
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
; set segment registers:
    mov ax, data
    mov ds, ax
    mov es, ax

    ; add your code here


    lea si, boxes  ; set SI to starting memory address for table 'boxes'
                   ; Label a function that pushes the next 4 values to the stack ( CL, RW, CL, RW )
                   ; When needed pop these 4 values off the stack before reading in 4 more
                   ; They will pop off the stack in the order: BRC BRR ULC ULR
    
    CHECK:
    cmp boxtally, 5
       je THEEND ; if 5 boxes have been drawn, jump to end Else
     
    
    ; 'STACKEM' needs to ALSO prep the memory then immediately go into DRAW label
    mov cx, 5 
    STACKEM:
       ; first, check if we drew all boxes already
        
       push [si]
       inc si
       loop STACKEM
       
    ; Once the next 4 values are read, make temp copies that will be overwritten every STACKEM
    ; AX is 16 bit and BRcol is 8 bit which = error To sidestep this, AX register for db's is AL Use AL
       
       pop ax
       mov ccode, al ; stores color code first since it went in last
       pop ax
       mov BRcol, al 
       pop ax
       mov BRrow, al
       pop ax
       mov ULcol, al
       pop ax
       mov ULrow, al 
     
       
     ; Data finished parsing from table, set to named temp variables to keep track of
    
    ; BEGIN CURSOR PREP                                                                                               
    BOTTOMRIGHT:
    
        mov ah, 2        ;set cursor 10h-2
        mov dl, BRcol   ; Pop BRC into DL
        mov dh, BRrow     ; Pop BRR into DH
        
        int 10h    ; execute cursor move
        ; WRITE CHAR bottom right corner
        mov ah, 09h     ; write char no attrib
        mov cx, 1        ;print the corner in bottom right once  
        mov al, 217      ; ascii
        mov bl, ccode
        
        int 10h         ; execute char write 
        
       
        ; BEGIN RIGHT EDGE DRAW
        REDGE:
        
        cmp dh, ULrow ; see if cursor has reached upper row value
        je  RC         ; if it has, jump out!  Else, continue
         
        mov ah, 2   ;set cursor 10h-2
        dec dh ; decrement, cursor will jump up 1 line above the B R corner              
         int 10h ; executes the row jump
         
         mov ah, 09h ; write char no attrib      10h-0Ah
         mov al, 179 ; ascii vertical line
         mov bl, ccode    
            int 10h ; execute char write 
         
         
         jmp REDGE ; repeat, LOOPS INFINITELY UNTIL DH = STARTING ROW
        
        RC: ; draw top right corner
        mov ah, 09h
        mov al, 191
        mov bl, ccode
            int 10h
         
        ; END RIGHT EDGE DRAW, move cursor up one more and place upper right corner ascii then start from bottom left, rinse, repeat
        ; bottom left edge (row, col) = BRrow, ULcol - since row remains constant for entire bottom
       ;CURSOR PREP   
        mov ah, 2        ;set cursor 10h-2 
        mov dh, BRrow          
        mov dl, ULcol    
        
        int 10h 
        
        mov ah, 09h      ; write char no attrib 
        mov cx, 1        ; loop factor
        mov al, 192      ; ascii L L corner char 
        mov bl, ccode
        
        int 10h  ; execute char write 10h-0Ah
                 ; while the cursor is already at bottom left, decrement to send it up the rows until it hits top row
        ; BEGIN LEFT EDGE DRAW         
        LEDGE: 
        
        cmp dh, ULrow ; see if cursor has reached top row
        je  ELE         ; if it has, jump out!  Else, continue
         
        mov ah, 2
        dec dh ; decrement, cursor will jump up 1 above the B L corner              
         int 10h ; executes the row jump
         
         mov ah, 09h ; write char no attrib 
         mov al, 179 ; ascii vertical line
         mov bl, ccode    
            int 10h ; execute 10h - 0Ah 
         
         
         jmp LEDGE ; repeat, LOOPS INFINITELY UNTIL DH = STARTING ROW
         
        ; END LEFT EDGE DRAW
        ELE: 
                                                ; cursor is coming in already @ ULrow
                                                ; draw top edge until cursor hits BRcol aka the rightmost column val 
                                                    
        ; write upper left corner char then move cursor + 1 col
        mov ah, 09h
        mov al, 218
        mov bl, ccode
        mov cx, 1
        int 10h ;print the corner in upper left 
        
        mov ah, 02h  ; it would erase the corner without these 2 line
        inc dl  
         int 10h
         
        ;BEGIN TOP EDGE DRAW 
        TEDGE:
        mov ah, 03h ; return current cursor row to DH col to DL
        int 10h
        
        cmp dl, BRcol
        je BECP ; jump to bottom edge label if cursor col = rightmost col
                 ;else                  
        mov ah, 09h         ; print char, advance cursor horizontally
        mov al, 196
        mov bl, ccode        
        mov cx, 1
        int 10h 
        
        mov ah, 02h        ; increment right
        inc dl
            int 10h
            
        jmp TEDGE ; repeat until cursor col = rightmost col
        
        ; BEGIN BOTTOM EDGE CURSOR PREP
        BECP:
        
        mov ah, 02h
        mov dl, ULcol
        inc dl
        mov dh, BRrow 
            int 10h ; execute set 
            
        ; BEGIN BOTTOM EDGE DRAW
        BEDGE:
        mov ah, 03h ; return current cursor row to DH col to DL
        int 10h 
        
        cmp dl, BRcol ; see if cursor has reached top row
        je  TALLY     ; BOX SHOULD BE FINISHED!!! exit the loop and add 1 victory to the tally variable
   
         mov ah, 09h ; write char no attrib 
         mov al, 196
         mov bl, ccode
         mov cx, 1    
            int 10h 
            
         mov ah, 02h
         inc dl
            int 10h 
         
          jmp BEDGE ; repeat, LOOPS INFINITELY UNTIL DH = STARTING ROW
          
          
    TALLY:
    add boxtally, 1
    jmp CHECK ; go check the tally     
    
    THEEND:
    ; wait for any key
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system
    int 21h    
ends

end start ; set entry point and stop the assembler
