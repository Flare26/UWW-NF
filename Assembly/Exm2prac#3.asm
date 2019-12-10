; multi-segment executable file template.
; Nathan Frazier Exam 2 Practice #3
data segment
    ; add your data here!
    array dw -1,2000,-4000,32767,500,-50
    value1 dw ?
    value2 dw ?
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
    
    mov di, offset array
    mov ax, [di]
    mov value1, ax
    mov value2, ax
    mov cx, 6
    
    A1:
    mov ax, [di]
    cmp ax, value2
    jle A2  ; jump less than eqal
    
    A2:
    cmp ax, value1
    jge A3  ; jump greater than equal
    mov value1, ax
    
    A3:
    add di, 2
    loop A1
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
