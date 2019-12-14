; multi-segment executable file template.
; Nathan Frazier HW 5 - 4 
data segment
    ; add your data here!
    srow     db 5            ; upper left row
    scol     db 10           ; upper left column
    erow     db 20           ; lower right row
    ecol     db 70           ; lower right column
    llrow    db 20           ; lower left row
    llcol    db 10           ; lower left col
    urrow    db 5            ; upper right row
    urcol    db 70           ; upper right col

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
    
    
    mov ah, 2        ;set cursor bottom right
    mov dh, erow
    mov dl, ecol
    
    int 10h
    
    mov cx, 1        ;print the corner in bottom right
    mov si, cx
    mov ah, 0Ah      ; write char no attrib
    mov al, 217
    
    int 10h          
    
   
                     ; while the cursor is already at bottom right, increment it up the rows until it hits top row
    RE:
    
    cmp dh, srow ; see if cursor has reached top row
    je  ERE         ; if it has, jump out!  Else, continue... 
     
    mov ah, 2
    dec dh ; decrement, cursor will jump up 1 line above the B R corner              
     int 10h ; executes the row jump
     
     mov ah, 0Ah ; write char no attrib 
     mov al, 179 ; ascii vertical line    
        int 10h ; execute char write 
     
     
     jmp RE ; repeat, LOOPS INFINITELY UNTIL DH = STARTING ROW
     
    ; END RIGHT EDGE, CONTINUE WITH CODE 
    ERE: 
      
       
    mov ah, 2        ;set cursor @ L L 
    mov dh, llrow          
    mov dl, llcol    
    
    int 10h 
      
    mov cx, 1        ;print corner @ L L
    mov si, cx
    mov ah, 0Ah
    mov al, 192
    
    int 10h
             ; while the cursor is already at bottom left, increment it up the rows until it hits top row
             
    LE:
    cmp dh, srow ; see if cursor has reached top row
    je  ELE         ; if it has, jump out!  Else, continue...
     
    mov ah, 2
    dec dh ; decrement, cursor will jump up 1 above the B L corner              
     int 10h ; executes the row jump
     
     mov ah, 0Ah ; write char no attrib 
     mov al, 179 ; ascii vertical line    
        int 10h ; execute char write 
     
     
     jmp LE ; repeat, LOOPS INFINITELY UNTIL DH = STARTING ROW
     
    ; END LEFT EDGE 
    ELE: 
             
    mov ah, 2        ;set cursor @ U R 
    mov dh, urrow
    mov dl, urcol
    
    int 10h
    
    mov cx, 1        ;print the corner @ U R
    mov si, cx
    mov ah, 0Ah
    mov al, 191
    
    int 10h       
    
    mov ah, 2        ;set cursor for upper left 
    mov dh, srow
    mov dl, scol
    
    int 10h
    
    mov cx, 1        ;print the corner in upper left     
    mov si, cx                
    mov ah, 0Ah         ; operation 
    mov al, 169         ; set char
    
    int 10h
    
    mov ah, 2        ;set position col top left
    mov dh, 5
    mov dl, 11
    
    
    int 10h      ; When finished, all box corners are printed 
                 ; This loop terminates when cursor hits right corner
    mov cx, 59       
    mov ah, 0Ah
    mov al, 196
    
    int 10h  
    
    mov ah, 2       ; Bottom line of the box will be printed
    mov dh, 20
    mov dl, 11
    
    int 10h
    
    mov cx, 59
    mov ah, 0Ah
    mov al, 196
    
    int 10h       ; Execute bottom line print
    
    mov ah, 2
    mov dh, 20
    mov dl, 11
    
    
    
    
   
ends

end start ; set entry point and stop the assembler.
            
            
             
             
  
ends

end start ; set entry point and stop the assembler.
