; Nathan Frazier hw4 q2
.data
string dw 13    ; length value
       db 'Hello Welcome'
str_copy db 80 dup('') 

.code 
    begin:
    mov ax, @data
    lea si, string
    lea di, str_copy ; move destination mem index to the start of str_copy
    
    mov bx , [si] ; moves the 13dw (16 bit) val at beginning of string to BX
    mov [di] , bx ; move 13dw to the mem loc of destination
    mov dx , [di] ; test that the copy was sucessful
    add si, 2
    add di, 2 ; even though DI is byte, the 13dw is represented as two in memory
    mov cx, bx ; cx = 13 is the str length, this is not including the extra 2 for dw
    
    L1:
    
    mov ax , [si]
    mov [di] , ax
    mov dx , [di] ; test that the copy was successful
    inc di
    inc si
    .
    .
    loop L1
    hlt
 

