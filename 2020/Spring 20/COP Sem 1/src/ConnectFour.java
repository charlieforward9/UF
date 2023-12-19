import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {

        //Declare variables
        Scanner scnr = new Scanner(System.in);
        char p1 = 'x';
        char p2 = 'o';
        char player;
        boolean turn = false; //true : player1 | false : player 2
        boolean finish = false;
        short playerNum;
        int choiceCol;
        int choiceRow;
        int count;

        //Collect user dimension preferences
        System.out.print("What would you like the height of the board to be? ");
        int row = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int col = scnr.nextInt();
        char[][] board = new char[row][col];

        //Establish playing board
        initializeBoard(board);
        System.out.println("\nPlayer 1: " + p1);
        System.out.println("Player 2: " + p2);

        //Main game loop
        while (!(finish)) {
            turn = !(turn);  //Start with player 1
            if (turn) {     //Swap between players
                player = p1;
                playerNum = 1;
            } else {
                player = p2;
                playerNum = 2;
            }
            //Requests user choice and inserts into board
            System.out.print("\nPlayer " + playerNum + ": Which column would you like to choose? ");
            choiceCol = scnr.nextInt();
            System.out.println();
            choiceRow = insertChip(board, choiceCol, player);
            count = printBoard(board);

            //Tests if game is over
            finish = checkIfWinner(board, choiceCol, choiceRow, player);
            if (count == row * col) {
                System.out.println("\nDraw. Nobody wins.");
                break;
            }
            if (finish) {
                System.out.println("\nPlayer " + playerNum + " won the game!");
            }
        }
    }

    //Prints the board with its respective slots taken
    public static int printBoard(char[][] array) {
        int count = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
                if (array[i][j] != '-') {
                    count++;
                }
            }
            System.out.println();
        }
        return count;
    }
    //Prints the blank board
    public static void initializeBoard(char[][] array) {
        //This will set each spot in the array to “-”.
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = '-';
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    //Inserts the players chip into the array, where it belongs and returns the row
    public static int insertChip(char[][] array, int col, char chipType) {
        int row = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                row = i;
                break;
            }
        }
        return row;
    }
    //Checks if either player has won yet
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        //After a token is added, checks whether the token in this location, of the specified chip type, creates four in
        //a row.
        //Will return true if a player won, and false otherwise.
        boolean finish = false;
        int count = 0;
        int lengthRow = array[row].length;

        //Horizontal Test
        for (int i = 0; i < lengthRow; i++) {
            if (array[row][i] == chipType) {
                count++;
            }
            if (count == 4) {
                finish = true;
                break;
            }
        }
        count = 0;  //Resets count to 0 before doing vertical test
        //Vertical Test
        if (!(finish)) {
            for (char[] chars : array) {
                if (chars[col] == chipType) {
                    count++;
                }
                if (count == 4) {
                    finish = true;
                    break;
                }
            }
        }
        return finish;
    }
}