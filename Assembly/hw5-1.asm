; Nathan Frazier HW 5-1 using the template
data segment
    ; add your data here!
    tex db "THIS TEXT IS IN THE WINDOW ", "$"
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

    ;scroll a window from row 5, col 10 to row 20 col 70
    ;with a reverse video attribute
    
    mov ah, 7   ;scroll down
    mov al, 10h ;# of lines  = 16
    mov bh, 70h ;reverse video 
    
    mov ch, 05h ;upper left row = 5
    mov cl, 0ah ;upper left col = 10
    
    mov dh, 14h ;lower right row = 20
    mov dl, 46h ;lower right col = 70
    
    int 10h
    
    ;locate the cursor at row 10, col 20
    
    mov ah,02h  ;set cursor pos ( 02hex = 2 int)
    mov bh, 0h  ;video page
    mov dh, 0ah ;row 10
    mov dl, 14h ;col 20
    int 10h
    
    ;display a line of text, such as, 
    ;"THIS TEXT IS IN THE WINDOW"
    
    lea dx, tex ;store string
    mov ah, 9   ;write string
    int 21h
    
    ; wait for any key....    
    mov ah, 1
    int 21h 
    
    ;scroll a window from row 7, column 12
    ;  to row 18, column 68, with a normal attribute
    mov ah, 7   ;scroll down
    mov al, 0Bh 
    mov bh, 07h ;normal attribute
    
    mov ch, 07h ;upper left row = 7
    mov cl, 0ch ;upper left col = 12
    
    mov dh, 14h ;lower right row
    mov dl, 46h ;lower right col 
    
    int 10h
    
    
    ;write a character A with a blinking attribute
    ;   in the middle of the window
    
    ; Personal note : FIRST reset the cursor position to appear in the window
    ; THEN, use command 9 to write a char & attribute at the same time to current cursor position
    
    mov ah, 02h ; set cursor command #2 
    mov dh, 10h ; DH to desired row (R window row 12h = 18)
    mov dl, 22h ; DL to desired column (R window col 44h = 68)
    mov bh, 0 ; BH to desired video page 0
    int 10h ; call BIOS video routine
    
    mov ah, 09h ; set write char & attribute command #9 NOTE : NOT ASCII CONTROL CODES
    mov al, 'A' ; AL to desired char 41h = 'A'
    mov bh, 0 ; BH set page 0
    mov bl, 87h ; BL to desired attribute, in this case normal blinking 
    mov cx, 32
    int 10h
    
    ;wait for a key stroke, and then clear entire screen
    ;    with a normal attribute
    mov ah, 1  ; command #1 wait for a console char with echo, stores in AL
    int 21h ; execute
    
        ; now clear entire screen
    mov ah, 6 ; command #6 scroll up
    mov al, 0 ; entire window
    mov ch, 0 ; upper left row
    mov cl, 0 ; upper left column
    mov dh, 14h ; lower right row ( MAX = 24 )
    mov dl, 46h ; lower right column ( MAX = 79 )
    mov bh, 07h ; normal attribute code to BH
    int 10h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
