import java.util.Objects;
import java.util.Scanner;



public class NumericConversion {

    public static void main(String[] args) {

        int menu;
        char go;
        int val;

        Scanner scnr = new Scanner(System.in);
        System.out.println("Decoding Menu\n-------------\n1. Decode hexadecimal\n2. Decode binary\n 3.Convert binary to hexadecimal\n");
        menu = scnr.nextInt();

        go = scnr.next().charAt(0);
        hexCharDecode(go);
        System.out.println(val);

        while (menu < 4) {
            if (menu == 1) {
                //Decode hexadecimal
            }else if (menu == 2) {
                //Decode binary
            }else {
                //Convert binary to hex
            }
        }

    }


    public static short hexCharDecode(char digit) {
        //Decodes a single hexadecimal digit and returns its value

        //Converts hex to value
        int val;
        int num = 0;
        switch (digit) {
            case 'A':
                val = 10;
                break;
            case 'B':
                val = 11;
                break;
            case 'C':
                val = 12;
                break;
            case 'D':
                val = 13;
                break;
            case 'E':
                val = 14;
                break;
            case 'F':
                val = 15;
                break;
            default:
                num = Character.getNumericValue(digit);
                val = num;

        }
        //Returns the value
        return (short) val;
    }

    public static String binaryToHex (String binary) {
        //Decodes a binary string, re-encodes it as hexadecimal, and returns the hexadecimal string.

        //Set up the program variables
        int index;
        int temp;
        int length = binary.length();
        int comp;

        //Tests if binary has a prefix of 0b or 0x
        binary = binary.toLowerCase();
        if(binary.charAt(1) == 'b' || binary.charAt(1) == 'x') {
            binary = binary.substring(2);
        }

        //Accumulate binary from right to left
        for (index = length; index >= 0; index--) {
            temp = index;
            //Splits conversion into groups of 4 integers
            while (temp > index - 4) {
                comp = Integer.parseInt(String.valueOf(binary.indexOf(temp)));
                System.out.println(comp);
                temp--;
            }
        }
        return binary;
    }
}

