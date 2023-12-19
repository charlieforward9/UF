public class NumericConversion {

    Scanner

    System.out.println("Decoding Menu\n-------------\n1. Decode hexadecimal\n2. Decode binary\n 3.Convert binary to hexadecimal")


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
        return (short) val;
    }

    public static String binaryToHex (String binary) {
        //Decodes a binary string, re-encodes it as hexadecimal, and returns the hexadecimal string.

        //Set up the program variables
        int index;
        int temp;
        int length = binary.length();
        int comp;

        //Tests if binary has a prefix of 0B, 0b, 0X or 0x
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
