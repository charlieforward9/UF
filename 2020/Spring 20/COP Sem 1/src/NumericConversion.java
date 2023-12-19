import java.util.Scanner;
import java.lang.Math;
public class NumericConversion {

    //Main Program
    public static void main(String[] args) {

        int menu;
        long convertedHex;                        //Result var
        short convertedBin;                       //Result var
        String binaryHex;                         //Result var
        String numString;                         //Numeric String to be converted

        Scanner scnr = new Scanner(System.in);

        //Display menu and options
        System.out.print("Decoding Menu\n-------------\n1. Decode hexadecimal\n2. Decode binary\n3. Convert binary to hexadecimal\n4. Quit\n\nPlease enter an option: ");
        menu = scnr.nextInt();                    //Menu input

        //Program main loop
        while (menu != 4) {
            System.out.print("Please enter the numeric string to convert: ");
            numString = scnr.next();              //Numeric String input

            if (menu == 1) {                      //Decode hex
                convertedHex = hexStringDecode(numString);
                System.out.println("Result: " + convertedHex + "\n");
            } else if (menu == 2) {                //Decode binary
                convertedBin = binaryStringDecode(numString);
                System.out.println("Result: " + convertedBin + "\n");
            } else {                               //Convert binary to hex
                binaryHex = binaryToHex(numString);
                System.out.println("Result: " + binaryHex + "\n");
            }

            System.out.print("Decoding Menu\n-------------\n1. Decode hexadecimal\n2. Decode binary\n3. Convert binary to hexadecimal\n4. Quit \n\nPlease enter an option: ");  //Redisplay menu
            menu = scnr.nextInt();
        }
        System.out.println("Goodbye!");
    }

    //Program Methods
    //Decodes an entire hexadecimal string and returns its value
    public static long hexStringDecode(String hex) {
        //Establish variables
        int i;
        int power = 0;
        int val;
        long convertedHex = 0;

        hex = hex.toLowerCase();
        if (hex.charAt(0) == '0' && (hex.charAt(1) == 'b' || hex.charAt(1) == 'x')) {  //Tests if input has a prefix of 0b or 0x
            hex = hex.substring(2);
        }

        for (i = hex.length(); i > 0; i--) {   //Converts each digit starting from the right
            val = hexCharDecode(hex.charAt(i - 1));
            convertedHex += val * Math.pow(16, power++);
        }
        return convertedHex;
    }

    //Decodes a single hexadecimal digit and returns its value
    public static short hexCharDecode(char digit) {
        //Converts hex to value
        int val;
        switch (digit) {
            case 'a':
                val = 10;
                break;
            case 'b':
                val = 11;
                break;
            case 'c':
                val = 12;
                break;
            case 'd':
                val = 13;
                break;
            case 'e':
                val = 14;
                break;
            case 'f':
                val = 15;
                break;
            default:
                val = Character.getNumericValue(digit);
        }
        //Returns the value
        return (short) val;
    }

    //Decodes a binary string and returns its value
    public static short binaryStringDecode(String binary) {
        //Establish variables
        int i;
        int power = 0;
        short convertedBin = 0;

        if (binary.charAt(1) == 'b' || binary.charAt(1) == 'x') {   //Tests if input has a prefix of 0b or 0x
            binary = binary.substring(2);
        }

        for (i = binary.length(); i > 0; i--) {       //Converts each digit starting from the right
            convertedBin += Character.getNumericValue(binary.charAt(i - 1)) * (Math.pow(2, power++));
        }
        return convertedBin;
    }

    //Decodes a binary string, re-encodes it as hexadecimal, and returns the hexadecimal string.
    public static String binaryToHex(String binary) {

        int i = binary.length();
        int count = 0;
        int rem = i % 4;
        int val = 0;
        int power = 0;
        boolean resume = true;
        String hex;
        String result = "";

        if (rem != 0) {     //If the remainder is != 0
            while (rem > 0) {
                binary = "0" + binary;     //Add 0's to the front of the string
                rem--;
            }
        }
        for (i = binary.length(); i > 0; i--) {           //For length of input, iterate the entire length
            val += Character.getNumericValue(binary.charAt(i - 1)) * (Math.pow(2, power++)); //Compute binary to base 10
            count++;
            if ((count % 4) != 0) {
                continue;
            }
            switch (val) {          //Figure out what the value is
                case 10:
                    hex = "A";
                    break;
                case 11:
                    hex = "B";
                    break;
                case 12:
                    hex = "C";
                    break;
                case 13:
                    hex = "D";
                    break;
                case 14:
                    hex = "E";
                    break;
                case 15:
                    hex = "F";
                    break;
                default:
                    hex = Integer.toString(val);
            }
            result = hex + result;
            if (power == 4) {
                power = 0;
                val = 0;
            }
        }
        return result;
    }
}