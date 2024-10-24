
import java.util.Random;
public class BubbleVSMerge {

    public static void bubbleSort(int[] arr) { //Function to perform the bubble sort method on an integer array

        boolean swapped; //Creates a boolean variable to tell if a pair of integers have already been swapped/sorted
        for (int i = 0; i < arr.length; i++) { //Outer loop controls how many times the loop will be passed over
            swapped = false; //Resets the swapped variable to false
            for (int j = 0; j < arr.length - 1; j++) { //Inner loop passes over array and compares each array index with the index after it to determine if it needs to be swapped/sorted
                if (arr[j] > arr[j + 1]) { //If the index is larger than the index after it, swaps the two of them
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; //Sets the swappes variable to true
                }
            }

            if (!swapped) { //Checks if swapped was never changed to true, if so then ends the outer loop early
                break;
            }
        }
    }

    public static void merge(int[] arr, int start, int middle, int end) { //Function to sort and merge arraya

        int i = start; // Establishes variables to track where the loops are located while iterating through the split arrays
        int j = middle;
        int k = 0; //Keeps track of the index in the temp array
        int[] tempArr = new int[end - start]; //Establishes a temporary array to store the sorted array

        while (i < middle && j < end) { //While loop that goes through an array in two halves, comparing the numbers in the two halves
            if (arr[i] <= arr[j]) { //If the number in the first half is larger than the number in the second half, it gets added to the temp array
                tempArr[k] = arr[i];
                i++;
            } else { //Else, the larger number from the second half gets added to the temp array
                tempArr[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i < middle) { //Adds any remaining numbers in the first half to the temp array if the while loop ended early
            tempArr[k] = arr[i];
            i++;
            k++;
        }

        while (j < end) { //Adds any remaining numbers in the second half to the temp array if the while loop ended early
            tempArr[k] = arr[j];
            j++;
            k++;
        }

        for (i = start; i < end; i++) { //Copies the contents of the temp array to the main array
            arr[i] = tempArr[i - start];
        }

    }

    public static void mergeSort(int[]arr, int start, int end) { 
        if(end - start <= 1){ //If the array is less than or equal to 1, return nothing as the array cannot be sorted
            return;
        }
        int middle = (start + end) / 2; //Calculates the middle of the array to split it in half
        mergeSort(arr, start, middle); //Recursive calls the functions on each side of the array to sort each half
        mergeSort(arr, middle, end);
        merge(arr, start, middle, end); //Combines them into a single array with the merge function
    }

    public static void mergeSort(int[] arr) { //Function that overloads the previous mergeSort function and is used to call it using a single array as a parameter
        mergeSort(arr, 0, arr.length);
    }

    public static boolean isSorted(int[] arr) { //Function that checks an array to see if it has been sorted successfully in ascending order
        for (int i = 0; i < arr.length - 1; i++) { //For loop iterates through the array
            if (arr[i] > arr[i + 1]) { //Returns false if an unsorted variable is detected
                return false;
            }
        }
        return true; //If no unsorted variables are detected, returns true
    }

    public static void main(String[] args) throws Exception {
        Random random = new Random(); //Creates a random object to generate random numbers for the array
        int[] arr = new int[100_000]; //Establishes a random array

        for (int i = 0; i < arr.length; i++){ //Generates random numbers to fill up the array
            arr[i] = random.nextInt();
        }

        int[] arr1 = arr; //Creates another array as a copy of the first so that both sorting methods will sort the exact same data set

        System.out.println("First, the program will sort an array of 100,000 integers with bubble sort");

        long start = System.currentTimeMillis(); 
        bubbleSort(arr); //Sorts the first array
        long end = System.currentTimeMillis();
        if (isSorted(arr) == true) { //Checks if the sort was successful and prints a message if so
            System.out.println("The array has successfully been sorted.");
        }
        System.out.println("It took " + (end - start) + " ms to sort an array of 100,000 integers with bubble sort"); //Prints the time it took to bubble sort the array by subtracting the time it started from the time it ended

        System.out.println("Next, the program will sort an identical array of 100,000 integers with merge sort");
        long start1 = System.currentTimeMillis();
        mergeSort(arr1);
        long end1 = System.currentTimeMillis();
        if (isSorted(arr1) == true) { //Checks if the sort was successful and prints a message if so
            System.out.println("The array has successfully been sorted.");
        }
        System.out.println("It took " + (end1 - start1) + " ms to sort an array of 100,000 integers with merge sort"); //Prints the time it took to merge sort the array by subtracting the time it started from the time it ended
    }
}
