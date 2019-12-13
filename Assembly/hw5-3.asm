; multi-segment executable file template.     
; Nathan Frazier Homework 5 # 3

data segment
    ; add your data here!
    string db 'abcdefghijklmno'
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
   
                         
    lea si, string
    
    mov ah, 2 ; set cursor command
    mov dh, 0 ; row
    mov dl, 0 ; col
    
    mov cx, 15  ;outer count 
    mov bl, 01h ; start print: with normal attrib   
    
    print:
        push cx ; save CX to temp
        mov cx, 1 ; we only want to print the char once
        mov ah, 9 ; int 10h command 9 write char & attrib
        mov al, [si] ; current letter to print register
        mov bh, 0 ; vid pg 0
       
        int 10h; call BIOS, this resets CX
        
        pop cx ; return to the outer count 
        inc si ; si to next letter   
        add bl, 01h ; change background / foreground ( 0000 0111 = 07h normal attribute )
        
        mov ah, 2 ; 10h command 2 set cursor pos
        inc dl ; increment cursor position     
        mov bh, 0 ; video page 0
        int 10h ; call BIOS, should move cursor pos+1
        loop print
    
    
         
hlt    
  
    
    
    
  
ends

end start ; set entry point and stop the assembler.
