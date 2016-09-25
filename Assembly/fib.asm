include Irvine32.inc
;Name: Urmila Sharma
;Reference: book,code examples from online sources 

.data 

Program Name BYTE	"Fibonacci Number calculating program.", 0
Program Ending BYTE	"Fibonacci Numbers are printed.", 0 


.code

fibonacci PROC 
    add ecx,1
    push ebp
    mov  ebp,esp
    sub  esp, 4         
    mov  eax,[ebp+8]    

;first case
    cmp  eax,2          ;comparing 2 with eax
    je   L1				; if equals then L1 function is called 
    cmp  eax,1          ; comparing 2 with eax
    je   L1				; if equals then L1 function is called

;second case 
    dec eax
    push eax            ; after decrementing eax it is pushed 
    call fibonacci 		; fibonacci function is called 
    mov [ebp-4], eax    ; value is stored 

    dec dword ptr [esp] 
    call fibonacci
    add esp, 4          ; stack is restored/cleaned
	add eax, [ebp-4]    ; result is added and stored 
	jmp Final			; execute final section 

L1: 
    mov eax, 1          ; if n == 1 or 2 then it eax will be 1
    
Final:
    mov esp, ebp        ; moving ebp to esp
    pop ebp             ; popping up that ebp 

    ret                 ; it returns eax 
fibonacci ENDP			; end of fibonacci section

main PROC

	; Starting
	mov ebx, OFFSET Program Name
	call WriteString 
	
    mov ecx,0
    push 3             ; input of 3
    call fibonacci     ; calculated fibonacci(eax)
    add esp, 4          

    call WriteDec		; printing fibonacci numbers
    call waitmsg		; show the result 
   
   ; Ending
    mov edx, Program Ending	; ending msg 
    call WriteString
    
    exit				; exit 
main ENDP				; end of main section 

END main