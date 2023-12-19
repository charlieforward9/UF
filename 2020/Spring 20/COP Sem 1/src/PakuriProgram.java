import java.util.Scanner;

public class PakuriProgram {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        //Initialize control variables
        int capacity = 0, menu;
        String species = "";
        String[] pakudex;
        int[] stats;

        //Display the welcome message & prompt max capacity
        System.out.print("Welcome to Pakudex: Tracker Extraordinaire!\nEnter max capacity of the Pakudex: ");
        //Test for valid capacity
        boolean moveOn = false;
        do {
            if (scnr.hasNextInt()) {
                capacity = scnr.nextInt();
                if (capacity > 0) {
                    moveOn = true;
                } else {
                    System.out.println("Please enter a valid size.");
                    System.out.print("Enter max capacity of the Pakudex: ");
                    scnr.next();
                    System.out.println("Please enter a valid size.");
                    System.out.print("Enter max capacity of the Pakudex: ");
                }
            } else {
                System.out.println("Please enter a valid size.");
                System.out.print("Enter max capacity of the Pakudex: ");
                scnr.next();
            }
        } while (!moveOn);
        System.out.println("The Pakudex can hold " + capacity + " species of Pakuri.\n");
        Pakudex fullDeck = new Pakudex(capacity);
        pakudex = fullDeck.getSpeciesArray();

        //Display main menu
        System.out.print("Pakudex Main Menu\n-----------------\n1. List Pakuri\n2. Show Pakuri\n3. Add Pakuri\n4. Evolve Pakuri\n5. Sort Pakuri\n6. Exit\n\nWhat would you like to do? ");
        menu = testMenuValue();
        //Loop through menu options
        while (menu != 6) {
            if ((menu < 1 || menu > 6) && !(menu == 6969420)) {
                System.out.println("Unrecognized menu selection!\n");
            }
            if (menu == 1) {        //Lists the Pakuri in the order they were entered
                if (pakudex == null) {
                    System.out.println("No Pakuri in Pakudex yet!\n");
                } else {
                    System.out.println("Pakuri In Pakudex");
                    for (int i = 0; i < fullDeck.getSize(); i++) {
                        System.out.println((i + 1) + ". " + pakudex[i]);
                    }
                    System.out.println(" ");
                }

            }
            if (menu == 2) {        //Displays all the species' information
                System.out.print("Enter the name of the species to display: ");
                species = scnr.next();
                if (fullDeck.getSize() == 0 && fullDeck.getSpeciesArray() == null) {
                    System.out.println("Error: No such Pakuri!\n");
                } else {
                    for (int i = 0; i < fullDeck.getSize(); ++i) {
                        assert pakudex != null;
                        if (pakudex[i].equals(species)) {
                            stats = fullDeck.getStats(species);
                            System.out.println("\nSpecies: " + pakudex[i] + "\nAttack: " + stats[0] + "\nDefense: " + stats[1] + "\nSpeed: " + stats[2] + "\n");
                            break;
                        } else if (i == fullDeck.getSize() - 1) {
                            System.out.println("Error: No such Pakuri!\n");
                        }
                    }
                }
            }
            if (menu == 3) {        //The name of the Pakuri is collected, then redisplayed and a confirmation is displayed following the successful addition or failure
                moveOn = false;
                if (fullDeck.getSize() == fullDeck.getCapacity()) {                 //Checks if deck is full, if so, move on.
                    System.out.println("Error: Pakudex is full!\n");
                    moveOn = true;
//                    menu = 6969420;
//                    continue;
                }
                if (!moveOn) {                                                      //If the deck is not full...
                    System.out.print("Enter the name of the species to add: ");
                    species = scnr.next();
                    if (pakudex == null) {                                          //If the deck is empty, add species, move on
                        moveOn = fullDeck.addPakuri(species);
                        System.out.println("Pakuri species " + species + " successfully added!\n");
                        pakudex = fullDeck.getSpeciesArray();
                    } else {                                                        //Else, the deck is NOT empty, look for matches
                        for (int i = 0; i < fullDeck.getSize(); i++) {
                            if (pakudex[i].equals(species)) {
                                System.out.println("Error: Pakudex already contains this species\n");
                                moveOn = true;                                      //If match exists, do not add, move on.
                            }
                        }
                    }
                }
                    if (!moveOn) {                                                  //If deck is not full, not empty, and no matches exists, add species and move on.
                        fullDeck.addPakuri(species);
                        System.out.println("Pakuri species " + species + " successfully added!\n");
                        pakudex = fullDeck.getSpeciesArray();
                    }
            }
            if (menu == 4) {        //Causes the species to evolve ( if it exists )
                System.out.print("Enter the name of the species to evolve: ");
                species = scnr.next();
                if (fullDeck.getSize() == 0) {
                    System.out.println("Error: No such Pakuri!\n");
                } else {
                    for (int i = 0; i < fullDeck.getSize(); i++) {
                        assert pakudex != null;
                        if (pakudex[i].equals(species)) {
                            boolean done = fullDeck.evolveSpecies(pakudex[i]);
                            fullDeck.getSpeciesArray();
                            if (done) {
                                System.out.println(species + " has evolved!\n");
                                break;
                            } else {
                                System.out.println("Error: No such Pakuri!\n");
                            }
                        } else if (i == fullDeck.getSize() - 1) {
                            System.out.println("Error: No such Pakuri!\n");
                        }
                    }
                }
            }
            if (menu == 5) {        //Sort Pakuri in Java standard lexicographical order
                fullDeck.sortPakuri();
                System.out.println("Pakuri have been sorted!\n");
            }
            //Redisplay main menu
            System.out.print("Pakudex Main Menu\n-----------------\n1. List Pakuri\n2. Show Pakuri\n3. Add Pakuri\n4. Evolve Pakuri\n5. Sort Pakuri\n6. Exit\n\nWhat would you like to do? ");
            menu = testMenuValue();

            //Terminates the loop
            if (menu == 6969420) {
                System.out.print("Pakudex Main Menu\n-----------------\n1. List Pakuri\n2. Show Pakuri\n3. Add Pakuri\n4. Evolve Pakuri\n5. Sort Pakuri\n6. Exit\n\nWhat would you like to do? ");
                menu = testMenuValue();
            }
        }
        System.out.println("Thanks for using Pakudex! Bye!");
    }

    //Tests for valid input
    public static int testMenuValue() {

        Scanner scnr = new Scanner(System.in);
        int testVal = 0;

        boolean moveOn = false;
        do {
            if (scnr.hasNextInt()) {
                testVal = scnr.nextInt();
                if (testVal > 0) {
                    moveOn = true;
                } else {
                    System.out.println("Unrecognized menu selection!\n");
                    System.out.print("Pakudex Main Menu\n-----------------\n1. List Pakuri\n2. Show Pakuri\n3. Add Pakuri\n4. Evolve Pakuri\n5. Sort Pakuri\n6. Exit\n\nWhat would you like to do? ");
                    scnr.next();
                }
            } else {
                System.out.println("Unrecognized menu selection!\n");
                System.out.print("Pakudex Main Menu\n-----------------\n1. List Pakuri\n2. Show Pakuri\n3. Add Pakuri\n4. Evolve Pakuri\n5. Sort Pakuri\n6. Exit\n\nWhat would you like to do? ");
                scnr.next();
            }
        } while (!moveOn);
        return testVal;
    }
}