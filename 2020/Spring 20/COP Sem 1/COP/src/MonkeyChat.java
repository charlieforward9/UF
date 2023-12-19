import java.util.Scanner;

public class MonkeyChat {
    public static void main(String[] args) {
        //make a connection
        //System.in is a
        Scanner keyboard = new Scanner(System.in);

        //sout + tab, you will get System.out.println()
        System.out.println("Monkey: Ooo! Ooo! EEEEEE!!");
        System.out.println("How will you respond?");

        String response;
        //next() function will read from keyboard
        //next() function will ignore all the white spaces
        response = keyboard.next();
        System.out.println("You said " + response + ". How rude!");
        


    }
}