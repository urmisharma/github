Title MASM Template(main.asm)

;Description: This program takes the string and reverse it.

; Revision Date: 3/8/2015
; Name : urmila sharma
; HW03



INCLUDE Irvine32.inc

.DATA
    myStr BYTE "Take this String Flip it and reverse it!",0
	mySize = $-myStr-1
	result BYTE SIZEOF myStr DUP(0) 

.CODE
	
    main PROC			;start of main program
	mov esi, 0			; clears esi
	mov ecx, mySize		; ecx contains total size of mystr

	L1:
	mov al,myStr[ecx-1]	; (gets a character)! is moved to al. ecx-1 gives ! instead of 0.
	mov result[esi],al	; (stored) ! is stored in al as the first character.
	inc esi				; esi points to next index 
	LOOP L1				; repeats the checking every character from back and storing to result.

	mov edx, OFFSET result
	CALL WriteString
	   

   main ENDP
   END main
