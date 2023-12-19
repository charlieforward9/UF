import java.util.Scanner;

public class Analyzer {
    public static void main(String[] args) {
        String[] dataSet = StringData.getData();
        //Sets up search elements
        String[] searchArray = {"not_here", "mzzzz", "aaaaa"};

        System.out.println("Initiating linear and binary searches\n*************************************");
        //Loops through elements
        for(String string : searchArray) {
            search(dataSet, string);
        }

        //Display report questions
        report();
    }

    public static int linearSearch(String[] dataSet, String element) {
        //Uses a linear search to find specified element in the dataSet. Returns index of element, or -1 if not found.
        for (int i = 0; i < dataSet.length; i++) {
            if (dataSet[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(String[] dataSet, String element) {
        //Uses a binary search to find specified element in the dataSet. Returns index of element, or -1if not found.
        int start = 0, mid, end = dataSet.length - 1;

        while (start != end) {
            mid = (start + end) / 2;
            if (dataSet[mid].compareTo(element) == 0) {
                return mid;
            } else if (dataSet[mid].compareTo(element) < 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    public static void search(String[] dataSet, String element) {

        long startTime, endTime, totalTime;
        int index;

        startTime = System.nanoTime();
        index = linearSearch(dataSet, element);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Total time for LINEAR search of " + element + ": " + totalTime + " ns.");

        startTime = System.nanoTime();
        index = binarySearch(dataSet, element);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Total time for BINARY search of " + element + ": " + totalTime + " ns.");
        System.out.println("*******************************************************");
    }
    public static void report() {
        Scanner scnr = new Scanner(System.in);
        System.out.println(" ");

        System.out.println("DISPLAYING REPORT QUESTIONS\n*********************");
        System.out.println("1) Why is a search for \"not_here\" the worst-case for linear search and binary search?");
        scnr.nextLine();
        System.out.println(" ");
        System.out.println("2) Why is a search for \"mzzzz\" the average-case for linear search?");
        scnr.nextLine();
        System.out.println(" ");
        System.out.println("3) Why is a search for \"aaaaa\" the best-case for linear search?");
        scnr.nextLine();
        System.out.println(" ");
        System.out.println("4) How do the results you saw compare to the Big-O complexity for these algorithms?");
        scnr.nextLine();
        System.out.println(" ");
        System.out.println("5) Why do the binary search results appear so similar, while the linear search results are so divergent?");
        scnr.nextLine();
    }
}
//1) Why is a search for "not_here" the worst-case for linear search and binary search?
//Because it doesnt exist in the dataSet, so the algorithm goes through the entire set before returning a result
//2) Why is a search for "mzzzz"the average-case for linear search?
//Because it is in the middle of the array.
//3) Why is a search for "aaaaa"the best-case for linear search?
//Becuase it is at the beginning of the array.
//4) How do the results you saw compare to the Big-O complexity for these algorithms?
//They seem to vary widely. The average complexity for a linear search = O(n) & the worst complexity also = O(n), i would expect the results to be similar, but they are distinct
//5) Why do the binary search results appear so similar, while the linear search results are so divergent?
//Because binary search is able to get to the answer much faster in general, meaning that the results are going to be closer together. Especially "not_here" and "aaaaa", they're at endpoints.