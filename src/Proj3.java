import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/*∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*
@file: Proj3.java
@description: This class runs tests on different sorting methods, including quickSort,
mergeSort, bubbleSort, odd even transposition sort, and heapSort. In the main method, it takes
an input file of user-defined comparable objects and puts them into an array. It then takes that
array, and sorts, shuffles, or reverse sorts it. Then, it will run parts of the array at
incrementally increasing number of line values through each sorting algorithm and print the runtimes
to a file called analysis.csv. For the cases of bubbleSort and transposition sort, the number
of comparisons made will also be printed.
NOTE - If you run all of the program at once it will take a very long time. Thus many portions of it
are commented out to be run one at a time.
@author: Alli Swyt
@date: November 13, 2025
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*/

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            //recursively mergeSorts each half
            mergeSort(a, left, middle); mergeSort(a, middle+1, right);
            merge(a, left, middle, right);
        }
    }

    //merge helper method
    public static <T extends Comparable<? super T>> void merge(ArrayList<T> a, int left, int mid, int right) {
        //creates temporary left and right arrays to merge
        ArrayList<T> tempLeft = new ArrayList<>();
        for (int i = left; i <= mid; i++) {
            tempLeft.add(a.get(i));
        }
        ArrayList<T> tempRight = new ArrayList<>();
        for (int i = mid + 1; i <= right; i++) {
            tempRight.add(a.get(i));
        }

        //merges the two arrays into the section of the array
        int i = 0; int j = 0; int k = left;
        while (i < tempLeft.size() && j < tempRight.size()) {
            if (tempLeft.get(i).compareTo(tempRight.get(j)) <= 0) {
                a.set(k, tempLeft.get(i)); i++; k++;
            }
            else { a.set(k, tempRight.get(j)); j++; k++; }
        }
        while (i < tempLeft.size()) { a.set(k, tempLeft.get(i)); i++; k++; }
        while (j < tempRight.size()) { a.set(k, tempRight.get(j)); j++; k++; }
    }

    // Quick Sort
    public static <T extends Comparable<? super T>> void quickSort(ArrayList<T> a, int left, int right) {
        if (left < right) {
            int pivot = partition(a, left, right); //recursive call

            //partitions each section
            quickSort(a, left, pivot - 1);
            quickSort(a, pivot + 1, right);
        }
    }

    public static <T extends Comparable<? super T>> int partition (ArrayList<T> a, int left, int right) {
        int mid = left + (right - left) / 2;
        //places the left, center, and right in increasing order
        if (a.get(mid).compareTo(a.get(left)) < 0) {
            swap(a, left, mid);
        }
        if (a.get(right).compareTo(a.get(left)) < 0) {
            swap(a, left, right);
        }
        if (a.get(right).compareTo(a.get(mid)) < 0) {
            swap(a, mid, right);
        }

        //place pivot at position right - 1
        swap(a, mid, right - 1);
        int pivot = right - 1;
        int j = left;
        for (int i = left; i < right - 1; i++) {
            if (a.get(i).compareTo(a.get(pivot)) <= 0) {
                swap(a, j, i);
                j++;
            }
        }
        swap(a, j, right - 1);
        return j;
    }

    //swaps two indexes in an arrayList
    static <T extends Comparable<? super T>> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable<? super T>> void heapSort(ArrayList<T> a, int left, int right) {
        //loop to build a max heap
        for (int i = (right/2) - 1; i >=0; i--) {
            heapify(a, i, right);
        }


        //loop to extract min N times to create an array in ascending order
        for (int i = right; i >=0; i--) {
            swap(a, 0, i);
            if (i != 0) {
                heapify(a, 0, i - 1);
            }
        }

    }

    //heapify from the given index range into a max heap
    public static <T extends Comparable<? super T>> void heapify (ArrayList<T> a, int left, int right) {
        int leftChild = left*2 + 1; int rightChild = left*2 + 2;
        //case that both children exist
        if (leftChild <= right && rightChild <= right) {
            //case that left child is larger than right
            if (a.get(leftChild).compareTo(a.get(rightChild)) > 0) {
                //case that left child is larger than the parent
                if (a.get(leftChild).compareTo(a.get(left)) > 0) {
                    swap(a, left, leftChild);
                    heapify(a, leftChild, right);
                }
            }
            //case that right child is larger than left
            else {
                //case that right child is larger than parent
                if (a.get(rightChild).compareTo(a.get(left)) > 0) {
                    swap(a, left, rightChild);
                    heapify(a, rightChild, right);
                }
            }
        }
        //case that only left child exists
        else if (leftChild <= right) {
            if (a.get(leftChild).compareTo(a.get(left)) > 0) {
                swap(a, left, leftChild);
                heapify(a, leftChild, right);
            }
        }
    }

    // Bubble Sort
    public static <T extends Comparable<? super T>> int bubbleSort(ArrayList<T> a, int size) {
        int comparisons = 0;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < size - 1; i++) {
                if (a.get(i).compareTo(a.get(i+1)) > 0) {
                    swap(a, i, i+1);
                    swapped = true;
                }
                comparisons++;
            }
        }
        return comparisons;
    }

    // Odd-Even Transposition Sort
    //comparisons made in parallel count as one single comparison
    public static <T extends Comparable<? super T>> int transpositionSort(ArrayList<T> a, int size) {
        int comparisons = 0;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            //bubble sort for even indices
            for (int i = 0; i < size-1; i += 2) {
                if (a.get(i).compareTo(a.get(i+1)) > 0) {
                    swap(a, i, i+1);
                    isSorted = false;
                }
            }
            comparisons++;
            //bubble sort for odd indices
            for (int i = 1; i < size-1; i += 2) {
                if (a.get(i).compareTo(a.get(i+1)) > 0) {
                    swap(a, i, i+1);
                    isSorted = false;
                }
            }
            comparisons++;
        }
        return comparisons;
    }

    public static void main(String [] args)  throws IOException {

        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0]; //input file of MAL-anime.csv
        //This section just makes sure the number is within bounds for the dataset
        int numLines = 0;
        try {
            numLines = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Argument was not an integer as expected. Exiting program. Please input an integer for the first argument.");
            System.exit(3);
        }
        if (numLines <= 0 || numLines > 12773) {
            System.out.println("Argument value was out of bounds for this data set. Please input a number from 1 to 12773. Exiting program.");
            System.exit(4);
        }

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        ArrayList<Anime> animeAL = new ArrayList<>();


        //Fills the arraylist (NOTE max lines is 12773)
        for (int k = 0; k < numLines; k++) {
            animeAL.add(new Anime(inputFileNameScanner.nextLine()));
        }

        //the following clears the result file if it has already been in use
        try {
            clearFile("./analysis.csv");
        } catch (IOException e) {
            System.out.println("File does not exist or error in clearing file. If file doesn't exist, it will be created after this is printed.");
        }

        //the following clears the result file if it has already been in use
        try {
            clearFile("./sorted.txt");
        } catch (IOException e) {
            System.out.println("File does not exist or error in clearing file. If file doesn't exist, it will be created after this is printed.");
        }



        /*
        The following portion tests the run times and for bubble sort and quicksort, the number of comparisons made
        It then prints these numbers to a file called analysis.csv. The words at the end of the list correspond
        respectively with what the values are, and the number of lines is printed out before each time or comparison
        value for ease of graphing in excel. I will comment out certain portions as it takes too long to run all sections
        at once.
        THUS PLEASE COMMENT AND UNCOMMENT DIFFERENT PARTS TO SEE WHAT WORKS. RUNNING IT ALL TOGETHER WILL TAKE TOO LONG.
         */

        //BUBBLE SORT TEST SECTION

        /*for (int i = 0; i < numLines; i+=100) {
            Collections.sort(animeAL);

            long startTime = System.nanoTime();
            long bubbleSortComparisons = bubbleSort(animeAL, i);
            long bubbleSortTime = System.nanoTime() - startTime;

            Collections.shuffle(animeAL);
            startTime = System.nanoTime();
            long bubbleSortComparisonsShuffled = bubbleSort(animeAL, i);
            long bubbleSortTimeShuffled = System.nanoTime() - startTime;

            Collections.sort(animeAL, Collections.reverseOrder());
            startTime= System.nanoTime();
            long bubbleSortComparisonsReversed = bubbleSort(animeAL, i);
            long bubbleSortTimeReversed = System.nanoTime() - startTime;


            writeToFile(i + "," + bubbleSortTime + "," + i + "," + bubbleSortComparisons + "," + i + "," + bubbleSortTimeShuffled + "," + i + "," + bubbleSortComparisonsShuffled + "," + i + "," + bubbleSortTimeReversed + "," + i + "," + bubbleSortComparisonsReversed +",Bubble Sort Time(already sorted), numComparisons sorted, time shuffled, numComparisons shuffled, time reversed, numComparisons reversed", "./analysis.csv");
        }

        //test to check that bubbleSort can sort properly
        Collections.shuffle(animeAL);
        bubbleSort(animeAL, numLines);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL);
        bubbleSort(animeAL, numLines);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL, Collections.reverseOrder());
        bubbleSort(animeAL, numLines);
        printArrayList(animeAL, numLines);
        */

        //writeToFile("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++", "./analysis.csv");

        //TRANSPOSITION SORT TEST SECTION

        /*for (int i = 0; i < numLines; i+=100) {
            Collections.sort(animeAL);

            long startTime = System.nanoTime();
            long transpositionComparisons = transpositionSort(animeAL, i);
            long transpositionTime = System.nanoTime() - startTime;

            Collections.shuffle(animeAL);
            startTime = System.nanoTime();
            long transpositionComparisonsShuffled = transpositionSort(animeAL, i);
            long transpositionTimeShuffled = System.nanoTime() - startTime;

            Collections.sort(animeAL, Collections.reverseOrder());
            startTime= System.nanoTime();
            long transpositionComparisonsReversed = transpositionSort(animeAL, i);
            long transpositionTimeReversed = System.nanoTime() - startTime;


            writeToFile(i + "," + transpositionTime + "," + i + "," + transpositionComparisons + "," + i + "," + transpositionTimeShuffled + "," + i + "," + transpositionComparisonsShuffled + "," + i + "," + transpositionTimeReversed + "," + i + "," + transpositionComparisonsReversed +",transposition Sort Time(already sorted), numComparisons sorted, time shuffled, numComparisons shuffled, time reversed, numComparisons reversed", "./analysis.csv");
        }

        //test to check that transpositionSort can sort properly
        Collections.shuffle(animeAL);
        transpositionSort(animeAL, numLines);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL);
        transpositionSort(animeAL, numLines);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL, Collections.reverseOrder());
        transpositionSort(animeAL, numLines);
        printArrayList(animeAL, numLines);

        */


        //writeToFile("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++", "./analysis.csv");


        //MERGESORT TEST SECTION

        /*for (int i = 0; i < numLines; i+=100) {
            Collections.sort(animeAL);

            long startTime = System.nanoTime();
            mergeSort(animeAL, 0, i);
            long mergeSortTime = System.nanoTime() - startTime;

            Collections.shuffle(animeAL);
            startTime = System.nanoTime();
            mergeSort(animeAL, 0, i);
            long mergeSortTimeShuffled = System.nanoTime() - startTime;

            Collections.sort(animeAL, Collections.reverseOrder());
            startTime= System.nanoTime();
            mergeSort(animeAL, 0, i);
            long mergeSortTimeReversed = System.nanoTime() - startTime;


            writeToFile(i + "," + mergeSortTime + "," + i + "," + mergeSortTimeShuffled + "," + i + "," + mergeSortTimeReversed + ","  +",merge Sort Time(already sorted), time shuffled, time reversed", "./analysis.csv");
        }

        //test to check that mergeSort can sort properly
        Collections.shuffle(animeAL);
        mergeSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL);
        mergeSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL, Collections.reverseOrder());
        mergeSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);

        */


        //writeToFile("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++", "./analysis.csv");

        //QUICK SORT TEST SECTION

        for (int i = 0; i < numLines; i+=100) {
            Collections.sort(animeAL);

            long startTime = System.nanoTime();
            quickSort(animeAL, 0, i);
            long quickSortTime = System.nanoTime() - startTime;

            Collections.shuffle(animeAL);
            startTime = System.nanoTime();
            quickSort(animeAL, 0, i);
            long quickSortTimeShuffled = System.nanoTime() - startTime;

            Collections.sort(animeAL, Collections.reverseOrder());
            startTime= System.nanoTime();
            quickSort(animeAL, 0, i);
            long quickSortTimeReversed = System.nanoTime() - startTime;


            writeToFile(i + "," + quickSortTime + "," + i + "," + quickSortTimeShuffled + "," + i + "," + quickSortTimeReversed + ","  +",quick Sort Time(already sorted), time shuffled, time reversed", "./analysis.csv");
        }

        //tests to check that quickSort can sort properly
        Collections.shuffle(animeAL);
        quickSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL);
        quickSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL, Collections.reverseOrder());
        quickSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);

        //writeToFile("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++", "./analysis.csv");

        //HEAP SORT TEST SECTION

        /*for (int i = 0; i < numLines; i+=100) {
            Collections.sort(animeAL);

            long startTime = System.nanoTime();
            heapSort(animeAL, 0, i);
            long heapSortTime = System.nanoTime() - startTime;

            Collections.shuffle(animeAL);
            startTime = System.nanoTime();
            heapSort(animeAL, 0, i);
            long heapSortTimeShuffled = System.nanoTime() - startTime;

            Collections.sort(animeAL, Collections.reverseOrder());
            startTime= System.nanoTime();
            heapSort(animeAL, 0, i);
            long heapSortTimeReversed = System.nanoTime() - startTime;


            writeToFile(i + "," + heapSortTime + "," + i + "," + heapSortTimeShuffled + "," + i + "," + heapSortTimeReversed + ","  +", heap Sort Time(already sorted), time shuffled, time reversed", "./analysis.csv");
        }

        //test to check that heapSort can sort properly
        Collections.shuffle(animeAL);
        heapSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL);
        heapSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);
        Collections.sort(animeAL, Collections.reverseOrder());
        heapSort(animeAL, 0, numLines - 1);
        printArrayList(animeAL, numLines);
        */


    }

    public static void writeToFile(String content, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                System.out.println("Error in creating the file.");
            }
        } //If the file doesn't already exist, a new one will be created

        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content + "\n");
            writer.close();
        } //creates a fileWriter to write to the file and writes to the file
        catch (IOException e) {
            System.out.println("Error in writing to file.");
        } //Exception if the filewriter fails
    }

    public static void clearFile(String filename) throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(filename)) {
                writer.close();
            } //This opens and closes the fileWriter to clear the file it is to write to
            catch (IOException e) {
                System.out.println("Error in clearing the file");
            }
        }
    }

    //method to print the arrayList, use if you'd like to individually test and check that the methods are sorting properly, which I did in testing
    public static <T extends Comparable<? super T>> void printArrayList(ArrayList<T> a, int numLines) {
        for (int i = 0; i < numLines; i++) {
            writeToFile(a.get(i).toString(), "./sorted.txt");
        }
    }
}
