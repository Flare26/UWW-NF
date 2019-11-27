; multi-segment executable file template.

data segment
    ; add your data here!
    
    
    srow     db ?            ; upper left row
    scol     db ?            ; upper left column
    erow     db ?            ; lower right row
    ecol     db ?            ; lower right column
    boxes    db  5,10,20,70  ; first box
             db  12,20,18,60 ; second box
             db  1,5,3,10    ; third box
             db  5,60,24,79  ; fourth box
             db  5,25,18,75  ; fifth box

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
      
    
      
    mov cx, 5 ; loop 5 times 
    
    L1: 
    push CX  ; save CX on the stack , CX can be used for other tasks
    
    ; read and set data for each box and 
   
    call draw_box 
    
    
    pop CX    ; recover CX from the stack
               
    loop L1
   
    
    
    
    ; wait for any key....    
    mov ah, 1
    int 21h
             
             
             
             
    mov ax, 4c00h ; exit to operating system.
    int 21h   
    
    ; draws a box given the upper left corner 
    ; and the lower right corner          
                
    draw_box PROC
        
      ;draw_box code
       
    ret
    draw_box ENDP
    
     
ends

end start ; set entry point and stop the assembler.
