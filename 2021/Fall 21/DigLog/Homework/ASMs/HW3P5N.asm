.data
	myArray:	.word 0x1243 0x234
	
.text

.globl main

main:
	#Store the address of myArray in R9
	# sw $t1, myArray($t0)
	lw $t3, myArray($0)
	
	addi $0, $0, 4
	
	lw $t3, myArray($0)
	
	li $a0, 1 #count
	li $t2, 10 #max

Loop: 
	beq $t2, $a0, Exit #If count = max exit
	sll $t1, $a0, 2 #Shift to address position
	add $t1, $t1, $s1 #Add address to base address
	addi $t3, $t1, -4 #Subtract 1 address position from index
	addi $t4, $t1, 4 #Add 1 address pos from index

	lw $t6 0($t3)
	lw $t7, 0($t4)
	add $t5, $t6, $t7
	addi $t5, $t5, -10
	sw $t5, 0($t1)
	
	addi $a0, $a0, 1
	
	j Loop
	
	
Exit:
	beq $zero $zero Exit
	
	