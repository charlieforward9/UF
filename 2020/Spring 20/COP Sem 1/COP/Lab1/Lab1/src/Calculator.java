import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        //Rename the scanner
        Scanner scnr = new Scanner(System.in);
        float result = 0;

        //Prompts the user to enter operands
        System.out.print("Enter first operand: ");
        float first = scnr.nextFloat();

        System.out.print("Enter second operand: ");
        float second = scnr.nextFloat();

        //Display calculator menu
        System.out.println("Calculator Menu\n---------------");
        System.out.println("1. Addition\n2. Subtraction\n3. Multiplication\n4. Division");

        //Processes user request
        System.out.print("Which operation do you want to perform? ");
        int choice = scnr.nextInt();
        switch (choice) {
            case 1:
                result = first + second;
                break;
            case 2:
                result = first - second;
                break;
            case 3:
                result = first * second;
                break;
            case 4:
                result = first / second;
                break;
            default:
                System.out.println("Error: Invalid selection! Terminating program.");
        }
        //Print result if choice is a valid operator
        if (choice < 0 || choice > 4) {
        }
        else {
            System.out.println("The result of the operation is " + result + ". Goodbye!");
        }
    }
}
