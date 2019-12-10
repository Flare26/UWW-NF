; multi-segment executable file template.

data segment
    ; add your data here!
    prompt db "Enter lowercase characters:$" 
    ctrl db "CTRL-BREAK"
     
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
            
    lea dx, prompt ; memory offset of text to dx
    mov ah, 09h
    int 21h        ; output string at ds:dx 
   
    
    L1:
        mov ah, 8 ; input NO echo, returns in AL
        int 21h
        
        mov dl, al ; move returned char to DL
        
        cmp dl, 0x5a ; compare dl , ASCII 'Z' 0x5a 
        jae A2 ; jump to label A2 if ( above 0x5a on ascii )
        
            A2:
            sub dl, 20h ; subtract 20h, which will be corresponding uppercase
            cmp dl, 'C'; see if they input C
             
       
       
        ; display the character
        mov ah, 2 ; int 21h code #2 requires char to be in DL
        int 21h ; execute char output command 21h-02h
    
    loop L1    ; loop until CTRL-BREAK
    
    BREAK:
        mov ah, 4ch
        int 21h
        
        
       
ends

end start ; set entry point and stop the assembler.
