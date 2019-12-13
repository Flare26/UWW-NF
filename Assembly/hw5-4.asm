; multi-segment executable file template.
; Nathan Frazier HW 5 - 4 
data segment
    ; add your data here!
    srow     db 5            ; upper left row
    scol     db 10           ; upper left column
    erow     db 20           ; lower right row
    ecol     db 70           ; lower right column

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
    
    

    mov ah, 0   ; set display mode function.
    mov al, 13h ; mode 13h = 320x200 pixels, 256 colors.
    int 10h    
    
        mov ah, 2 ; int 10h - 2 set cursor pos
        mov bh, 0 ; vid pg 0
        mov dh, srow ; start row
        mov dl, scol ; start col
        int 10h ; do it
        
        
            L1:
            mov ah, 0Eh  ; int 10h - 0Eh write char + move 1
            
            mov al, 178 ; move extended ascii
            int 10h ; call
            
            ; check cursor pos
            mov ah, 03h 
            mov bh, 0 ; vid pg
            int 10h  ; returns cursor row to DH and col to DL 
            cmp  dl, [ecol]
            jne L1
            
            ; if @ right bound col, check row
            
            cmp dh, erow ; compare current row to end row
            jle L2
            
            hlt
            
            L2: 
            mov ah, 2
            mov bh, 0
            inc dh
            mov dl, scol ; reset column to leftmost
            int 10h
            jmp L1 ; jump back to L1 after moving down
            
            
             
             
  
ends

end start ; set entry point and stop the assembler.
