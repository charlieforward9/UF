public class HeiferGenerator {

        public static Cow[] getCows() {
            if (cows == null) {
                cows = new Cow[cowImages.length];       //cows is assigned to an array of Cowobjects

                for (int index = 0; index < cows.length; index++) {
                    cows[index] = new Cow(cowNames[index]);         //Creates a new object of Cow class in cow[index]
                    cows[index].setImage(quoteLines + cowImages[index]);    //Sets the cowImage respective to the cow[index]
                }
            }
            return cows;
        }

        private static String[] cowNames = { "heifer", "kitteh" };

        private static String quoteLines =
                "    \\\n" +
                        "     \\\n" +
                        "      \\\n";

        private static String[] cowImages = {
                "        ^__^\n" +
                        "        (oo)\\_______\n" +
                        "        (__)\\       )\\/\\\n" +		//cowImages[0]
                        "            ||----w |\n" +
                        "            ||     ||\n",


                "       (\"`-'  '-/\") .___..--' ' \"`-._\n" +
                        "         ` *_ *  )    `-.   (      ) .`-.__. `)\n" +		//cowImages[1]
                        "         (_Y_.) ' ._   )   `._` ;  `` -. .-'\n" +
                        "      _.. `--'_..-_/   /--' _ .' ,4\n" +
                        "   ( i l ),-''  ( l i),'  ( ( ! .-'\n"
        };

        private static Cow[] cows = null;
}


