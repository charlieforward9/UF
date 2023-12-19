import java.util.Scanner;

public class RleProgram {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        //Establishes variables
        int menu;
        byte[] file = null;
        String filename;
        String rawString;

        //Displays the welcome message and color test
        System.out.println("Welcome to the RLE image encoder!\n\nDisplaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);
        System.out.println();

        //Displays the menu and prompts for input
        System.out.println("RLE Menu\n--------\n0. Exit\n1. Load File\n2. Load Test Image\n3. Read RLE String\n4. Read RLE Hex String\n5. Read Data Hex String\n6. Display Image\n7. Display RLE String\n8. Display Hex RLE Data\n9. Display Hex Flat Data\n");
        System.out.print("Select a Menu Option: ");
        menu = scnr.nextInt();

        //Menu loop
        while (menu != 0) {
            switch (menu) {
                case 1: //Load file
                    System.out.print("Enter name of file to load: ");
                    filename = scnr.next();
                    file = ConsoleGfx.loadFile(filename);
                    System.out.println();
                    break;
                case 2: //Displays image
                    file = ConsoleGfx.testImage;
                    System.out.println("Test image data loaded.\n");
                    break;
                case 3: //Decodes RLE string
                    System.out.print("Enter an RLE string to be decoded: ");
                    rawString = scnr.next();
                    System.out.println();
                    file = decodeRle(stringToRle(rawString));
                    break;
                case 4: //Decodes hex string
                    System.out.print("Enter the hex string holding RLE data: ");
                    rawString = scnr.next();
                    System.out.println();
                    file = decodeRle(stringToData(rawString));
                    break;
                case 5: //Converts hex string
                    System.out.print("Enter the hex string holding flat data: ");
                    rawString = scnr.next();
                    System.out.println();
                    file = stringToData(rawString);
                    break;
                case 6: //Displays image
                    System.out.println("Displaying image...");
                    ConsoleGfx.displayImage(file);
                    System.out.println();
                    break;
                case 7: //Encodes raw data
                    System.out.println("RLE representation: " + toRleString(encodeRle(file)) + "\n");
                    break;
                case 8: //Encodes raw data
                    System.out.println("RLE hex values: " + toHexString(encodeRle(file)) + "\n");
                    break;
                case 9: //Displays hex string
                    assert file != null;
                    System.out.println("Flat hex values: " + toHexString(file) + "\n");
            }

            //Displays the menu and prompts for input
            System.out.println("RLE Menu\n--------\n0. Exit\n1. Load File\n2. Load Test Image\n3. Read RLE String\n4. Read RLE Hex String\n5. Read Data Hex String\n6. Display Image\n7. Display RLE String\n8. Display Hex RLE Data\n9. Display Hex Flat Data\n");
            System.out.print("Select a Menu Option: ");
            menu = scnr.nextInt();
        }
    }

    //Method 1: Translates (raw or RLE) data into string w/o delimeters
    public static String toHexString(byte[] data) {

        //Initialize variables
        String hexString = "";

        //Loops through array converting to Hex
        for (byte datum : data) {
            hexString += Integer.toHexString(datum);
        }
        return hexString;
    }

    //Method 2: Returns number of runs in an image set
    public static int countRuns(byte[] flatData) {

        //Initialize variables
        int count = 1;
        int runLength = 0;

        for (int i = 0; i < flatData.length - 1; i++) {
            //Tests conditions to resume run
            if (flatData[i] == flatData[i + 1] && runLength < 15) {
                runLength++;
            } else {
                //Increases run count
                count++;
                runLength = 0;
            }
        }
        return count;
    }

    //Method 3: Returns encoding (in RLE) of the raw data passed in; used to generate RLE representation of data
    public static byte[] encodeRle(byte[] flatData) {

        //Initialize variables
        byte count = 1;
        int index = 0;

        //Determine encoded array size by fiding the number of runs in an image set and multiplying by 2 to account for
        int size = countRuns(flatData) * 2;
        byte[] encoded = new byte[size];

        for (int i = 1; i < flatData.length; i++) {
            //Tests if the run should continue counting
            if (flatData[i] == flatData[i - 1] && count < 15) {
                //Increase count of value
                count++;
            } else {
                //If index != index - 1, assign the encoded array index n with the count and n + 1 with the value
                encoded[index * 2] = count;
                encoded[index * 2 + 1] = flatData[i - 1];
                index += 1;
                count = 1;
            }
        }

        //Accounting for the end of the array which is not included in the loop
        encoded[index * 2] = count;
        encoded[index * 2 + 1] = flatData[flatData.length - 1];
        return encoded;
    }

    //Method 4: Returns decompressed size RLE data; used to generate flat data from RLE encoding
    public static int getDecodedLength(byte[] rleData) {

        //Initialize decoded length variable
        int length = 0;

        //Loops through array to find decoded length
        for (int i = 0; i < rleData.length; i++) {
            if (i % 2 == 0) {
                //For every even indices, the value is added to the length
                length += rleData[i];
            }
        }
        return length;
    }

    //Method 5: Returns the decoded data set from RLE encoded data. This decompresses RLE data for use.
    public static byte[] decodeRle(byte[] rleData) {

        //Initialize variables
        int sum;
        int index = 0;

        //Retrieves the decoded length of the array
        sum = getDecodedLength(rleData);

        //Creates new array with decoded length
        byte[] decoded = new byte[sum];

        //Loops through input array
        for (int i = 0; i < rleData.length - 1; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < rleData[i]; j++) {
                    decoded[index] = rleData[i + 1];
                    index++;
                }
            }
        }

        //Limits run to a width of 15
        if (decoded[0] > 15) {
            byte val = (byte) (decoded[0] / 15);
            decoded[0] = 15;
            decoded[1] += val;
        }
        return decoded;
    }

    //Method 6: Translates a string in hexadecimal format into byte data (can be raw or RLE).
    public static byte[] stringToData(String dataString) {

        //Initializes arrays
        char[] array = dataString.toCharArray();
        byte[] data = new byte[array.length];

        //Cycles through loop converting the hex string to readable data
        for (int i = 0; i < array.length; i++) {
            if (Character.isLetter(array[i])) {
                array[i] = Character.toLowerCase(array[i]);
                switch (array[i]) {
                    case 'a':
                        data[i] = 10;
                        break;
                    case 'b':
                        data[i] = 11;
                        break;
                    case 'c':
                        data[i] = 12;
                        break;
                    case 'd':
                        data[i] = 13;
                        break;
                    case 'e':
                        data[i] = 14;
                        break;
                    case 'f':
                        data[i] = 15;
                        break;
                }
            } else {
                data[i] = (byte) Character.getNumericValue(array[i]);
            }
        }
        return data;
    }

    //Method 7: Translates RLE data into a human-readable representation. For each run, in order, it should display the run length in decimal (1-2 digits); the run value in hexadecimal (1 digit); and a delimiter, ‘:’, between runs.
    public static String toRleString(byte[] rleData) {

        //Initializes variables
        boolean loop = true;
        String hex;
        StringBuilder result = new StringBuilder();

        //Loops through array
        for (byte rleDatum : rleData) {
            if (loop) {
                result.append(rleDatum);
                loop = false;
            } else {
                switch (rleDatum) {
                    case 10:
                        hex = "a";
                        break;
                    case 11:
                        hex = "b";
                        break;
                    case 12:
                        hex = "c";
                        break;
                    case 13:
                        hex = "d";
                        break;
                    case 14:
                        hex = "e";
                        break;
                    case 15:
                        hex = "f";
                        break;
                    default:
                        hex = Byte.toString(rleDatum);
                }
                result.append(hex);
                result.append(":");
                loop = true;
            }
        }
        return result.toString().substring(0,result.length() - 1);
    }

    //Method 8: Translates a string in human-readable RLE format (with delimiters) into RLE byte data.
    public static byte[] stringToRle(String rleString) {

        //Initialize variables
        char[] cArray = rleString.toCharArray();    //Input string converted to an array
        byte count = 0;
        int bIndex = 0;
        byte val;  //Color value
        int size = 0;  //Size of the byte array
        int i;

        //Create a new array with a decimeter at the end of it (to be functional with code) and add cArray to it
        char[] newArray = new char[cArray.length + 1];
        newArray[cArray.length] = ':';
        for (i = 0; i < cArray.length; i++) {
            newArray[i] = cArray[i];
        }

        //Determine bArray size by adding 2 to the size each time a decimeter is counted
        for (char c : newArray) {
            if (c == ':') {
                size += 2;
            }
        }

        //Initialize bArray, adding 2 to account for the last 2 digits of the array
        byte[] bArray = new byte[size];

        //Loop through input array
        for (i = 0; i < newArray.length; i++) {
            if (newArray[i] != ':') {
                //Increase the count if input array[i] != the decimeter
                count++;
            } else {
                //If array[i] == the decimeter, convert
                if (count > 2) {
                    bArray[bIndex] = (byte) Character.getNumericValue(newArray[i - 3] + newArray[i - 2]);
                } else {
                    bArray[bIndex] = (byte) Character.getNumericValue(newArray[i - 2]);
                }
                //Converts hex to byte value
                switch (newArray[i - 1]) {
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
                        val = (byte) Character.getNumericValue(newArray[i - 1]);
                }
                bArray[bIndex + 1] = val;
                bIndex += 2;
                count = 0;
            }
        }
        return bArray;
    }
}

//Prework methods
//        //Tests if 4 consecutive indexes are equal
//        //Useful for method 2(countRuns) and 3(encodeRle).
//        public static boolean consecutiveFours(int[] arr) {
//            int counter = 0;
//
//            for (int i = 0; i < arr.length - 2; i++) {
//                //Tests if index and index + 1 are equal
//                if (arr[i] == arr[i + 1]) {
//                    counter++;
//                    if (counter == 4) {
//                        return true;
//                    }
//                } else {
//                    counter = 0;
//                }
//            }
//            return false;
//        }
//
//    //Sums even indices and stores then in first index of new array, sums odd indices and stores them in the second index of new array
//    //Useful for method 4(getDecodedLength).
//    public static int[] sumByParity(int[] arr) {
//        int i;
//        int[] sums = new int[2];
//        for(i = 0; i < arr.length - 1; i++) {
//            if (i % 2 == 0) {       //Even indices
//                sums[0] += arr[i];
//            } else {                //Odd indices
//                sums[1] += arr[i];
//            }
//        }
//        return sums;
//    }
//
//    //take in an array of integers and expand them into a larger array.
//    //Useful for method 5(decodeRle).
//    public  static  int[] expandByIndex(int[] arr) {
//        int i;
//        int sum = 0;
//        int iter;
//        int index = 0;
//
//        for (i = 0; i < arr.length; i++) {
//            sum += arr[i];
//        }
//
//        int[] expansion = new int[sum];
//
//        for (i = 0; i < arr.length; i++) {
//            for (iter = 0; iter < arr[i]; iter++) {
//                expansion[index] = i;
//                index++;
//            }
//        }
//        return expansion;
//    }

//    //Return the count of numbers in that string, ignoring letters.
//    //Useful for method 6(stringToData).
//    public  static  int numericalCount(String string) {
//        int count = 0;
//        char[] array = string.toCharArray();
//        for (char c: array) {
//            if (Character.isDigit(c)) {
//                count++;
//            }
//        }
//        return count;
//    }






