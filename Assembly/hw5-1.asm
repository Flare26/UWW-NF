; Nathan Frazier HW 5-1 using the template
data segment
    ; add your data here!
    tex db "THIS TEXT IS IN THE WINDOW ", "$"
    char db 'A', '$'
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
    
    mov ah, 02h  ;set cursor pos ( 02hex = 2 int)
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
    mov al, 10h 
    mov bh, 70h ;normal attribute
    
    mov ch, 07h ;upper left row = 7
    mov cl, 0ch ;upper left col = 12
    
    mov dh, 12h ;lower right row
    mov dl, 44h ;lower right col 
    
    int 10h
    
    
    ; The AAAAAAA bug is somewhere in this region.....
    
    mov ah, 02h ; set cursor command #2 
    mov bh, 0h ; DH to desired row (R window row 12h = 18)
    mov dl, 22h
    int 10h 
    
    
    mov ah, 09h ; write char & attribute command 10h - 09h NOTE : NOT ASCII CONTROL CODES   
    mov al, char
    mov bh, 0
    mov bl, 10000111b
  
    int 10h
    
    ;wait for a key stroke, and then clear entire screen
    ;    with a normal attribute
    mov ah, 1  ; command #1 wait for a console char with echo, stores in AL
    int 21h ; execute
    
        ; now clear entire screen
    
    mov ah, 6
    mov al, 0
    mov ch, 0
    mov cl, 0
    mov dh,24
    mov dl,79 ; max screen dimensions
    mov bh, 7 ; normal attrib for blanks
    
    int 10h
    
  
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
    
ends

end start ; set entry point and stop the assembler.
