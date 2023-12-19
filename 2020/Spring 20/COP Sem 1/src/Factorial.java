import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Input a positive number");
        int n = scnr.nextInt();
        System.out.println("The factorial of that number is: " + pureRecursive(n));
    }

    public static long pureRecursive(int n) {
        //A purely recursive function that calculates the factorial of n. This function should call only itself.
        if (n < 1) {
            throw new IllegalArgumentException();
        } else if (n == 1){
            return n;
        } else {
            return n * pureRecursive(n - 1);
        }
    }

    public static long tailRecursive(int n) {
        //A kickoff method for tail recursion; it should call only the tail() method (see below).
        
        return n * tail(n - 1);
    }

    private static long tail(int n) {
        //A private method called by the tail recursion kickoff method. Students may select the parameters for this method,but it must be present and used.This method should only call itself, and only via tail recursion –i.e.,it should call itself on the last line with no other computation after the function call.

        return n;
    }
    public static long iterative(int n) {
        //An iterative version of the factorial calculation.This method should be an “unwound” version of the tail Recursive() method outlined above.It should not call itself or any other method but should instead using a looping structure to calculate the factorial.
        for (int i = 1; i <= n; i++) {
            n *= i;
        }
        return n;
    }
}
