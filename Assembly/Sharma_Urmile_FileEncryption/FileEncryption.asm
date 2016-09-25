; A program to encrypt a given file and write the output in another file 
; Authors: Bijay Regmi, Urmila Sharma





INCLUDE Irvine32.inc
INCLUDE macros.inc


BUFFERSIZE = 500                                  ;Defining the buffersize
FILENAMESIZE = 54                                 ;declaring filenamesize to limit the character of input and output file name
KEYSIZE = 16                                      ; declaring the key size in bytes




.DATA
buffer BYTE BUFFERSIZE DUP (?)                         ; declaring buffer to store the data 
choice DWORD ?                                         ; variable to store the choice of user

InputFileName BYTE FILENAMESIZE DUP (?)                ; variable to store the name of input file
OutputFileName BYTE FILENAMESIZE DUP (?)                   ; variable to store the name of output file 

EncryptionKey BYTE 16 DUP (?)                          ; variable to store the encryption key
 
InputFileHandle DWORD ?                           ; to store the input file handle which is needed to read from file and close the file
OutputFileHandle DWORD ?                          ; to store the output file handle which is needed to write to a file and close the file 

NumBytesRead DWORD ?                              ; to store the number of bytes read from the file
EncryptionKeyIndex BYTE 0                         ; to index the Encryption key
CurrentEncryptionKeyValue BYTE ?                  ; to store the value at the given index of EncryptionKey




.CODE               

; This function gets the input file name and stores the file name in the variable InputFileName

GetInputFileName PROC USES EDX EAX ECX
     mWrite <"Before trying to encrypt/decrypt please make sure that the file you want to encrypt/decrypt is in the same direcory. ">
     CALL Crlf
     mWrite <"Please enter the name(with extension) of the file you want to encrypt/decrypt: ">
     MOV EDX, OFFSET InputFileName
     MOV ECX, FILENAMESIZE
     CALL ReadString
     RET
GetInputFileName ENDP






; This function asks user if he/she wants to continue and stores the choice in variable choice

WantToContinue PROC
     mWrite <"Do you want to continue? ">
     CALL Crlf
    mWrite <"Enter 1 to continue ">
     CALL Crlf
     mWrite <"Enter any other key to exit: ">
     CALL ReadDec
     MOV choice, EAX
     RET
WantToContinue ENDP
     





; This function asks for the output file name and stores that file name in the variable OutputFileName

GetOutputFileName PROC USES EDX ECX EAX
     
     mWrite <"Please enter the name(with extension) of the output file: ">
     MOV EDX, OFFSET OutputFileName
     MOV ECX , FILENAMESIZE
     CALL ReadString
     RET
GetOutputFileName ENDP




; This function generates random key for encryption and stores the key in variable EncryptionKey
GenerateKey PROC USES ECX
     CALL Randomize                ; Calls Randomize to get random value
     MOV ECX, 16                   ; Setting counter to loop 16 times
     MOV ESI, 0                    ; setting pointer to point to specified index of EncryptionKey
     L1:                          
          CALL Random32                      ; calls Random32 function to generate 32 bit random number
          MOV EncryptionKey[ESI], AL              ; copies only lower one byte  of EAX i.e AL to the pointed index of EncryptionKey
          INC ESI                                 ; increasing ESI so that it points to next index of EncryptionKey
    LOOP L1                                       
     ret

GenerateKey ENDP



; this function displays the encryption key to the user
DisplayKey proc
     mWrite <"Your encryption key is: ">
     MOV ECX, 16                   ; since there are 16 bytes in encryption key , so moving 16 to ecx to loop 16 times
     MOV ESI, 0                    ; moving 0 to esi. We are going to use esi to point to subsequent byte of EncryptionKey
    
     L1:
          MOVZX EAX,EncryptionKey[ESI]
          MOV EBX, TYPE BYTE
          call WriteHexB           ; calling WriteHexB from the library
          mWrite <" ">             ; adding a space in between each byte for ease of reading
          INC ESI                      ; increament esi so that it can point to next byte of EncryptionKey
     Loop L1
    CALL Crlf                     ; moving to next line
    RET
DisplayKey ENDP






OpenInputTextFile PROC USES EDX EAX 
     MOV EDX, OFFSET InputFilename
     CALL OpenInputFile                           ; Opening the input file by calling OpenInputFile function from the Irvine library
     CMP EAX, INVALID_HANDLE_VALUE                   ; compare if error is produced
     JNE OpenInputFileSuccess                         ; if there is no error opening file then jump to OpenInputFileSuccess
                                             
     mWrite <"Error opening input file. Please try again later. ">    ; if there is error opening file then print this message
     CALL Crlf
     JMP Finish                         ; after printing the error message jump to finish                                                

     OpenInputFileSuccess:
          MOV InputFileHandle, EAX                ; if opening the file is successful then copy the input file handle from EAX to the variable InputFileHandle
     Finish: 
          RET
OpenInputTextFile ENDP





OpenOutputFile PROC USES EDX EAX
     MOV EDX, OFFSET OutputFileName               
     CALL CreateOutputFile                        ;opening the output file in similar fashion as opening the input file
     CMP EAX, INVALID_HANDLE_VALUE                ;compare if the error is produced
     JNE OpenOutputFileSuccess                    ; if there is no error opeining file then jump to OpenOutputFileSuccess
     
     mWrite <"Error opening output file. Please try again later.">              ; if there is error then display this error message
     CALL Crlf
     JMP Finish                                                            ; after printing error message jmp to finish                        

     OpenOutputFileSuccess:
          MOV OutputFileHandle, EAX                         ; if opeining file is successful then copy the output file handle from EAX to variable OutputFileHandle
     Finish:
          RET
OpenOutputFile ENDP





; this function closes the input file 
CloseInputFile PROC 
     MOV EAX, InputFileHandle
     CALL CloseFile                                    ; closing the input file by calling CloseFile function from Irvine Library
CloseInputFile ENDP





; this function closes the output file
CloseOutputFile PROC
     MOV EAX, OutputFileHandle
     CALL CloseFile                                    ; ; closing the output file by calling CloseFile function from Irvine Library
CloseOutputFile ENDP





;this method reads from the input file and fills the buffer
LoadToBuffer PROC USES EAX EDX ECX
     MOV EAX, InputFileHandle           
     MOV EDX, OFFSET buffer             
     MOV ECX, BUFFERSIZE                ; passing the number of bytes to read from file
     CALL ReadFromFile                  ; Reading from the input file by calling ReadFromFile fuction 
     MOV NumBytesRead, EAX              ; Moving the number of bytes read to variable NumBytesRead
     RET
LoadToBuffer ENDP





; this function writes the data in buffer to the output file 
WriteToOutputFile PROC
     MOV EAX, OutputFileHandle               ; passing the handle of the file in which the writing is to be done
     MOV EDX, OFFSET buffer                 ; passing the offset of buffer in edx
     MOV ECX, NumBytesRead              ; passing how many bytes to write in ECX
     CALL WriteToFile                   ; writing to output file by calling the WriteToFile function from library
     RET
WriteToOutputFile ENDP




; This function advances the EncryptionKeyIndex and loads the corresponding value of the EncryptionKey in CurrentEncryptionKeyValue
AdvanceEncryptionKey PROC 
     MOVZX EAX, EncryptionKeyIndex           ; Moving the index to EAX
     inc eax                                 ; increasing EAX
     CMP EAX, KEYSIZE                        ; Comparing EAX to KEYSIZE(i.e 16)
     JE ResetKeyIndex                        ; if EncryptionKeyIndex is equal to 16 then reset the index
     dec eax                                 ; if EncryptionKeyIndex is less than 15 then carrying on. First decreasing the eax as it was increased previously just to check
     inc EncryptionKeyIndex                  ; increasing the value of EncryptionKeyIndex
     MOVZX EBX, EncryptionKeyIndex               ;moving index to EBX
     MOV AL, EncryptionKey[EBX]              ; getting corresponding byte of EncryptionKey and moving to AL
     MOV CurrentEncryptionKeyValue, AL       ; moving the byte from AL to CurrentEncryptionKeyValue
    
     JMP EndFun                                   ; jump to EndFun so that the key does not get reset

     ResetKeyIndex:
          CALL ResetKey

     EndFun:
          RET
AdvanceEncryptionKey ENDP





;this function resets the key index and loads the corresponding byte value of EncryptionKey to CurrentEncryptionKeyValue
ResetKey Proc
     MOV AL, EncryptionKey[0] 
     MOV CurrentEncryptionKeyValue, AL
     MOV EncryptionKeyIndex, 0
     ret
ResetKey ENDP





;Actual encrypt takes here by XOR-ing each byte of data with the key
Encrypt PROC 
     MOV ESI, NumBytesRead              ;moving number of bytes read to esi
	CMP ESI, 0                             
	JE ExitEncrypt                ; if the number of bytes read is equal to zero then there is nothing to encrypt so jump to exit
     call resetkey                 ; initializing the key

	MOV ESI, 0                         ; mov 0 to esi . Esi is used to point to subsequent byte of buffer
	MOV ECX, NumBytesRead                  ; setting the counter of loop which is the number of bytes read
	L1:
	     MOV AL, buffer[ESI]                ; load the specified index of buffer to al
		MOV BL, CurrentEncryptionKeyValue       ; load the current byte of encryption key to bl
		XOR AL, BL                                   ; XOR specified byte of buffer with the current byte of encryption key and store result in al
		MOV buffer[ESI], AL                     ; move the result from al to the same index of buffer
		CALL AdvanceEncryptionKey                    ; advance the encryption key to encrypt the next byte of buffer
		INC ESI                                 ; increase the esi by 1 to point to next byte of buffer
     Loop L1                                      ;loop 

		CALL WriteToOutputFile                      ; after encryption write to output file 
		MOV ESI, NumBytesRead                        ; move the number of bytes read to esi 
		CMP ESI, BUFFERSIZE
		JB ExitEncrypt                                ; if the number of bytes is less than buffer size then exit encrypt as every byte is already read from the input file
		CALL LoadToBuffer                       ; if the number of byte is not less than buffer size then there is still something left to read so load to buffer again
		CALL Encrypt                                ; after loading call Encrypt again to repeat this process 

	ExitEncrypt:
	     ret
Encrypt endp
	 
 
 
 
      
       
  ; this fuction calls every method to run the program smoothly 
  ; the name of function says what is done
 Run PROC
    CALL GetInputFileName
    CALL OpenInputTextFile
    CALL GetOutputFileName
    CALL OpenOutputFile
    call loadtobuffer
    Call Encrypt
    call displaykey
    call closeinputfile
    call closeoutputfile
    call wanttocontinue
    ret
Run EndP

             
     
     
; the main function
main PROC 
     CAll GenerateKey         ; generate key so that encryption is done
     RunAgain:                
          call run                 ; run the program and take the choice of user
          cmp choice, 1                 ; if the user wishes to continue then jump to RunAgain
     je RunAgain
   
    
    EXIT
main ENDP
END main