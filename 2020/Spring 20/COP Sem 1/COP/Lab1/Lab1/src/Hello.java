//Imports the keyboard
import java.util.Scanner;

public class Hello {

    public static void main(String[] args) {
	    //Rename the scanner
        Scanner scnr = new Scanner(System.in);

        //Prompt the user with questions
        System.out.print("Hello. What is your name? ");
        String name = scnr.next();
        System.out.print("It's nice to meet you, " + name + ". How old are you? ");
        String age = scnr.next();
        System.out.print("I see that you are still quite young at only " + age + ". \nWhere do you live? ");
        String home = scnr.next();
        System.out.print("Wow! I've always wanted to go to " + home + ". Thanks for chatting with me. Bye!");

        scnr.close();
    }
}
