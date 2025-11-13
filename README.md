# Project 3 @ CSC 201 Fall 2025: Sorting Algorithms

## Pledged Work Policy

This is a ___Pledged Work___ assignment.  This means that the work you submit for grading ___must___ be your work product.  
You may not submit the work of others outside of your team, or the modification of work of others outside of your team.
You are encouraged to talk with each other about general problems.  For example, you may talk to someone about "What does 
it mean when the compiler says there is a semicolon missing on line 20", or "I can not get my assignment template to 
download from GitHub, what did you do?".  However, you may not engage in "Could you send me a copy of your work so I can 
see how to get started?".  You may get full and detailed assistance from me, the Teaching Assistant (TA), and the TAs in 
the Computer Science Center.  If you have any question about the appropriateness of assistance, do not hesitate to 
consult with me.

If I believe you have violated our ___Pledge Work___ agreement, I will pursue this matter through the college Honor Council.

## Overview

Sorting algorithms are fundamental techniques in computer science that organize data in a specific order, optimized for 
data handling, retrieval, and analyses. Key algorithms, including Bubble Sort, Merge Sort, Quick Sort, Heap Sort, and 
Odd-Even Transposition Sort, each employ different strategies with varied efficiency in terms of space and time 
often evaluated with Big-O notation. 

In this project, you will: 

1. Implement the Bubble Sort, Merge Sort, Quick Sort, Heap Sort, and Odd-Even Transposition Sort algorithms.
2. Perform the sorting algorithms using already-sorted, shuffled, and reversed datasets lists as input.
3. Time the sorting algorithm performance for the different set of inputs.
4. Graph and analyze the performance of the sorting algorithms.

## Invocation and I/O Files:

The name of the program is `Proj3` ( provided with a `main` method in`Proj2.java` ).

You are encouraged to run and debug code in __IntelliJ IDEA__. Also, the program can be invoked from the command-line as:

```shell
java Proj3 {dataset-file} {sorting-algorithm-type} {number-of-lines}
```
## 1. **Implement Sorting Algorithms**

I have enclosed one starter code:
1. `Proj3.java` 
2. There are no other starter code files, but you can add more classes as needed (e.g., your dataset class). 

The `Proj3.java` file contains the main class that reads the input file and calls the sorting algorithms, as well as 
several unfinished sorting algorithm methods and helper methods that you will need to implement where it is indicated 
(Search for `// FINISH ME`).

The program takes in three command line arguments: 1) the filename if your dataset, 2) the sorting algorithm type to be 
executed (bubble, merge, quick, heap, transposition) and 3) the number of lines of your dataset to read.

For the Bubble Sort and Odd-Even Transposition Sort Algorithms, the return value is an integer corresponding to the 
number of comparisons made during the sorting processes. For the Odd-Even Transposition Sort Algorithm, all comparisons that 
could be made in parallel are counted as a single comparison.

The following is a pseudocode for the Odd-Even Transposition Sort Algorithm:

```
    while !isSorted
        begin 
            isSorted=true
            A loop to perform Bubble sort on odd indexed element
            A loop to perform Bubble sort on even indexed element
        end 
```

## 2. **Perform Sorting Algorithms on Already-Sorted, Shuffled, and Reversed Lists**

You will read your dataset and store the data in an ArrayList. To sort and randomize your ArrayList, you will use the 
`Collections.sort()`, `Collections.shuffle()`, and `Collections.sort(al, Collections.reverseOrder())` commands, 
respectively.

## 3. **Time Sorting Algorithm Performance**

For the Bubble Sort, Merge Sort, Quick Sort, and Heap Sort algorithms, you will use `System.nanoTime()` to calculate 
the time it takes to run them on already-sorted, shuffled, and reversed lists. 

For the Bubble Sort and Odd-Even Transposition Sort algorithms, you will count the number of comparisons made during 
the sorting processes.

Your program will print out the number of lines evaluated and the times and/or comparisons to sort the already-sorted, 
shuffled, and reversed lists to the screen in a human-readable format (i.e., nice to look at) and also separately to a 
file named `analysis.txt` in CSV format. Each time the program runs, it will append the timing results to 
`analysis.txt`. 

## NOTE from Alli -- I did not follow the instructions here because I didn't want to have to manually clear the analysis file for each run, 
## so I set it to not append but clear after each run of the program

In addition, your program will print out the sorted lists to a file named `sorted.txt`. Each time the program runs, it 
will overwrite the previous sorted lists in `sorted.txt`.

## 4. **Graph and Analyze the Performance of the Sorting Algorithms**

Run your program several times for different number of lines of your dataset, N, by choosing different values of the 
second command line argument. After several runs, your `analysis.txt` file will be filled with timing and comparison data.

Using your favorite graphing software (e.g., MS Excel or Google Sheets), plot the running time (in seconds) or number of 
vs. N for each case. Take a screenshot of your graph and put them here by modifying this file, committing, and pushing 
it to this repository.

Bubble Sort, Merge Sort, Quick Sort, and Heap Sort running time (already sorted):
![img.png](img.png) = bubbleSort
![img_6.png](img_6.png) = transposition Sort
![img_12.png](img_12.png) = mergeSort -- forgot axis titles sorry, it's the same as all others with x axis numLines and y runTime (ns)
![img_15.png](img_15.png) = quickSort THIS WAS ORIGINALLY DONE WITH PIVOT = RIGHT; I changed it later after we went over it in class, and thought the
                            difference in results was interesting so I will add graphs for both
![img_18.png](img_18.png) = heapSort
![img_21.png](img_21.png) = quickSort (estimated mean pivot)

Bubble Sort, Merge Sort, Quick Sort, and Heap Sort running time (shuffled):
![img_1.png](img_1.png) = bubbleSort
![img_7.png](img_7.png) = transposition sort
![img_13.png](img_13.png) = mergeSort
![img_16.png](img_16.png) = quickSort
![img_19.png](img_19.png) = heapSort
![img_22.png](img_22.png) = quickSort (estimated mean pivot)

Bubble Sort, Merge Sort, Quick Sort, and Heap Sort running time (reversed):
![img_2.png](img_2.png) = bubbleSort
![img_8.png](img_8.png) = transposition sort
![img_14.png](img_14.png) = mergeSort
![img_17.png](img_17.png) = quickSort
![img_20.png](img_20.png) = heapSort
![img_23.png](img_23.png) = quickSort (estimated mean pivot)

Bubble Sort and Odd-Even Transposition Sort comparison (already sorted):
![img_3.png](img_3.png) = bubbleSort
![img_9.png](img_9.png) = transposition sort

Bubble Sort and Odd-Even Transposition Sort comparison (shuffled):
![img_4.png](img_4.png) = bubbleSort
![img_10.png](img_10.png) = transposition sort

Bubble Sort and Odd-Even Transposition Sort comparison (reversed):
![img_5.png](img_5.png) = bubbleSort
![img_11.png](img_11.png) = transposition sort

## Submission:

Your project will be developed and graded via GitHub. Your final "push" is your final submission, and it must occur 
before it is due. On Canvas, enter the url to your Github repository. Your project will not be graded without it.

## Recommendations:

I ___strongly suggest___ that you carefully think through your strategy before just jumping into the code.  Once that 
is working, start adding in new features individually.  A good place to start is building your class.

*In order to get full points of Commenting and Code Style, you need to add comments to every method and head comments 
for each file (providing file description, author, date, and acknowledgement).

```
/∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*
∗ @file: filename.java
∗ @description: This program implements . . .
∗ @author: Your Name
∗ @date: November 13, 2025
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗/
```
