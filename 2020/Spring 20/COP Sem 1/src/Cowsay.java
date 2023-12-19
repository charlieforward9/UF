public class Cowsay {

    public static void main(String[] args) {
        //Retrieve cow name array
        Cow[] cows = new Cow[HeiferGenerator.getCows().length];

        //Support command line argument -1
        if (args[0].equals("-l")) {
            listCows(cows);
        }
        else if (args[0].equals("-n")) {       //Support command line argument MESSAGE and -n [COWNAME] MESSAGE
           Cow cow = findCow(args[1], cows);
           if (cow != null) {
               for (int i = 2; i < args.length; i++) {
                   System.out.print(args[i]);
               }
               System.out.print(cow.getImage());
           }
        }
        else {
            for (String arg : args) {
                System.out.print(arg);
                System.out.print(cows[0].getImage());
            }
        }
    }
    //Suggested methods
    private static void listCows(Cow[] cows) {
        //Displays the list of available cows from an array of Cowobjects.
        System.out.println("Cows available: ");
        for (Cow cow : cows) {
            System.out.print(cow + " ");
        }
    }
    private static Cow findCow(String name, Cow[] cows) {
        //Given a name and an array of Cowobjects, return the Cowobject with the specified name.
        Cow testCow = new Cow(name);
        for (Cow cow : cows) {
            if (testCow.equals(cow)) {
                return cow;
            }
        }
        System.out.print("Count not find " + testCow + " cow!");
        return null;
    }
}
