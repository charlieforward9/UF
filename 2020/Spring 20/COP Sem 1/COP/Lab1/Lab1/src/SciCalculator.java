import java.lang.*;
import java.util.Scanner;

public class SciCalculator {
    public static void main(String[] args) {

        //Rename scanner
        Scanner scnr = new Scanner(System.in);

        //Establish variables
        double result = 0;
        String in1 = "0";
        String in2 = "0";
        double op1 = 0;
        double op2 = 0;
        int menu = -1;
        double total = 0;
        int calcs = 0;
        double avg = 0;
        String repeat = "RESULT";

        //Display menu
        while (menu != 0) {
            System.out.println("Current Result: " + result + "\n");
            System.out.println("Calculator Menu\n---------------");
            System.out.println("0. Exit Program\n1. Addition\n2. Subtraction");
            System.out.println("3. Multiplication\n4. Division\n5. Exponentiation");
            System.out.println("6. Logarithm\n7. Display Average\n");
            System.out.print("Enter Menu Selection: ");
            menu = scnr.nextInt();

            //Display calculation totals and averages
            switch (menu) {
                case 7:
                    if (calcs != 0) {
                        System.out.println("Sum of calculations: " + total);
                        System.out.println("Number of calculations: " + calcs);
                        double calc = calcs;
                        avg = total / calc;

                        System.out.print("Average of calculations: ");
                        System.out.printf("%.2f", avg);
                        System.out.println("\n");
                    } else {
                        System.out.println("\nError: No calculations yet to average!\n");
                    }
                    //Request user input again
                    System.out.print("Enter Menu Selection: ");
                    menu = scnr.nextInt();

            }

            //Tests input to request operands
            if (menu > 0 && menu < 7) {
                //Prompts user to enter first operand
                System.out.print("Enter first operand: ");
                in1 = scnr.next();
                if (in1.equals(repeat)) {
                    op1 = result;
                } else {
                    op1 = Double.parseDouble(in1);
                }

                //Prompts user to enter second operand
                System.out.print("Enter second operand: ");
                in2 = scnr.next();
                if (in2.equals(repeat)) {
                    op2 = result;
                } else {
                    op2 = Double.parseDouble(in2);
                }

                //Accumulates calculation counter
                calcs += 1;
            }

            //Runs through menu options
            switch (menu) {
                case 0:
                    break;
                case 1:
                    result = op1 + op2;
                    break;
                case 2:
                    result = op1 - op2;
                    break;
                case 3:
                    result = op1 * op2;
                    break;
                case 4:
                    result = op1 / op2;
                    break;
                case 5:
                    result = Math.pow(op1, op2);
                    break;
                case 6:
                    result = Math.log(op2) / Math.log(op1);
                    break;
                default:
                    System.out.println("\nError: Invalid selection!\n");

                    //Request user input again
                    System.out.print("Enter Menu Selection: ");
                    menu = scnr.nextInt();
            }
            //Collects sum and # calculations
            total += result;

            //Prints extra whitespace
            System.out.println();

        }
        System.out.println("Thanks for using this calculator.Goodbye!");
    }
}

