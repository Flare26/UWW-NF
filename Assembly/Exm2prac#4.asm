; multi-segment executable file template.
; Nathan Frazier Exam 2 practice #4
data segment
    ; add your data here!
    
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
    
    mov al, 4Ah
    mov cx, 8
    
    L1:
    shl cl, 1
    mov dl, '0'
    jnc L2
    moc dl, '1'
    
    L2:
    push ax
    mov ah, 2
    int 21h
    pop ax
    loop L1
    
    ; wait for any key....    
    mov ah, 1
    int 21h
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
