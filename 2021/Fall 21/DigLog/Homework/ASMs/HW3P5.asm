.data
	myArray:	.word 1 1 0 1 0 1 0 1
	
.text
.globl main

main:
	#Load count
	li $a3, 0
	#Load limit
	li $t0, 8

loop: 
	#Branch to exit if R7 = R8
	beq $t0, $a3, Exit
	#Compute address by shifting R7 left twice, store @ R9
	sll $t1, $a3, 2
	#Load the value @ address to R10
	lw $t2, myArray($t1)
	
	#Jump w link to prodecure incC
	jal incC
	
	#Iterate
	j loop
	
incC:
	#Increment count
	addi $a3, $a3, 1
	#Increment ones count with value
	add $t3, $t3, $t2
	
	#Jump to return address
	jr $ra
	
Exit:
	beq $zero $zero Exit
	
	