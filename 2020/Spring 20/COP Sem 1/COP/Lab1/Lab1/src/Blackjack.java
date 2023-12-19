//Import the keyboard
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {

        //Rename the objects
        Scanner scnr = new Scanner(System.in);
        P1Random rgn = new P1Random();

        //Establish accumulators
        int numGames = 1;
        int playerWins = 0;
        int dealerWins = 0;
        int numTies = 0;
        int playerHand = 0;
        int dealerHand = 0;
        int cardNum = 0;
        int menu = 1;
        String result;

        System.out.println("START GAME #1\n");
        while (menu != 4) {
            //Start the game
            if (menu == 1) {
                cardNum = rgn.nextInt(13) + 1;

                if (cardNum == 1) {
                    result = "ACE";
                } else if (cardNum < 11) {
                    result = Integer.toString(cardNum);
                } else if (cardNum == 11) {
                    result = "JACK";
                    cardNum = 10;
                } else if (cardNum == 12) {
                    result = "QUEEN";
                    cardNum = 10;
                } else {
                    result = "KING";
                    cardNum = 10;
                }
                playerHand = playerHand + cardNum;

                System.out.println("Your card is a " + result + "!");
                System.out.println("Your hand is: " + playerHand);
                //Determine game status
                if (playerHand < 21) {
                    System.out.println();
                } else if (playerHand == 21) {
                    System.out.println("BLACKJACK! You win!\n");
                    playerWins = playerWins + 1;
                    playerHand = 0;
                    dealerHand = 0;
                    numGames = numGames + 1;
                    System.out.println("START GAME #" + numGames + "\n");
                } else {
                    System.out.println("You exceeded 21! You lose.\n");
                    dealerWins = dealerWins + 1;
                    playerHand = 0;
                    dealerHand = 0;
                    numGames = numGames + 1;
                    System.out.println("START GAME #" + numGames + "\n");
                }

            } else if (menu == 2) {
                dealerHand = rgn.nextInt(11) + 16;
                System.out.println("Dealer's hand: " + dealerHand);
                System.out.println("Your hand is: " + playerHand + "\n");
                if ((dealerHand > 21) || (dealerHand < playerHand)) {
                    System.out.println("You win!\n");
                    playerWins = playerWins + 1;
                    playerHand = 0;
                    dealerHand = 0;
                    numGames = numGames + 1;
                    System.out.println("START GAME #" + numGames + "\n");
                } else if (dealerHand == playerHand) {
                    System.out.println("It's a tie! No one wins!\n");
                    playerHand = 0;
                    dealerHand = 0;
                    numGames = numGames + 1;
                    System.out.println("START GAME #" + numGames + "\n");
                } else if (dealerHand <= 21) {
                    System.out.println("Dealer wins!\n");
                    dealerWins = dealerWins + 1;
                    playerHand = 0;
                    dealerHand = 0;
                    numGames = numGames + 1;
                    System.out.println("START GAME #" + numGames + "\n");
                }
            }
            else if (menu == 3) {
                System.out.println("Number of Player wins: " + playerWins);
                System.out.println("Number of Dealer wins: " + dealerWins);
                System.out.println("Number of tie games: " + numTies);
                System.out.println("Total # of games played is: " + numGames);
                float num1 = playerWins;
                float num2 = numGames;
                float percent = num1 / num2 * 100;
                System.out.print("Percentage of player wins: ");
                System.out.printf("%.1f", percent);
                System.out.println("%\n");
            }
            else {
                System.out.println("Invalid Input!\nPlease enter an integer value between 1 and 4.\n");
            }
            menu = 1;
            if (playerHand != 0) {
                System.out.println("1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit\n");
                System.out.print("Choose an option: ");
                menu = scnr.nextInt();
                System.out.println();
            }
        }
    }
}