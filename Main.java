import java.util.Arrays;    // to show the array elements before searching

/**
 * Program to create a random integer array and search for a given value
 */
public class Main {
    public static void main(String[] args) {

        int myVal = 5;  // value we are looking for

        int[] myArray = new int[100];   // create new array length 100

        int min = 2;                    // min value possible for the array elements
        int max = 100;                 // max value possible for the array elements

        // loop to populate the array with random integers from min to max.
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = (int) (min + Math.random() * (max - min));
        }

        System.out.println(Arrays.toString(myArray));     // to show the contents of the array
        double startTime = System.nanoTime();
        int found = loopFind(myArray, myVal);             // search for the value with loopFind method and store the result as a variable (found)
        double endTime = System.nanoTime();
        System.out.println("Result: " + found);           // show the search result
        double duration = (endTime - startTime)/1000000;  // divide by 1000000 to get milliseconds.
        System.out.println("the time it took to search for the value with a loop is " + duration);    // duration of search process

        System.out.println();


        System.out.println(Arrays.toString(myArray));    // to show the contents of the array
        startTime = System.nanoTime();
        found = binaryFind(myArray, myVal);             // search for the value with binaryFind method and store the result as a variable (found)
        endTime = System.nanoTime();
        System.out.println("Result: " + found);         // show the search result
        duration = (endTime - startTime)/1000000;       // divide by 1000000 to get milliseconds.
        System.out.println("the time it took to search for the value with binary search is " + duration);     // duration of search process

    }

    /**
     * Method to search for a given value within an array by using a loop
     *
     * @param arr, array we are searching
     * @param value, provided integer we are looking for
     * @return value if found or -1 otherwise
     */
    public static int loopFind(int[] arr, int value) {
        // loop to compare the given value to every element in the array until found (or not found)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return arr[i];      // If found return the value (if we wanted to instead return the index where value was found we
            }                       // just replace arr[i] for i. It wasn't sure which one was required, so I chose to return the value)
        }
        return -1;      // if not found return -1
    }

    /**
     * Method to search for a given value within an array by using binary search. Array must be sorted first
     *
     * @param arr, array we are searching
     * @param value, provided integer we are looking for
     * @return value if found or -1 otherwise
     */
    public static int binaryFind(int[] arr, int value) {
        sortArr(arr);                                                       // sort array using sortArray method
        return binaryFindHelper(arr, value, 0, arr.length-1);     // call to recursive helper method to search the value
    }

    /**
     * Method to recursively search for a given value within an array through binary search
     *
     * @param arr, array we are searching
     * @param value, provided integer we are looking for
     * @param low, index to start search from
     * @param high, index to define search boundary
     * @return value if found or -1 otherwise
     */
    public static int binaryFindHelper(int[] arr, int value, int low, int high) {
        // if low is greater than high, the value is not located in the array, so we return -1
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;        // define the mid-point of the array section we are looking in.
        // compare value at mid-point with our value. If they are equal, value has been found.
        if (arr[mid] == value) {
            return arr[mid];
        }
        // if our value is lower than the value located at mid-point, continue the search only on the left side of mid-point
        if (value < arr[mid]) {
            return binaryFindHelper(arr, value, low, mid - 1);
        }
        // if our value is higher than the value located at mid-point, continue the search only on the right side of mid-point
        return binaryFindHelper(arr, value, mid+1, high);
    }

    /**
     * Method to sort an array. Which is a step needed to perform our binary search
     *
     * @param arr, array we want to sort
     */
    public static void sortArr(int[] arr) {
        // with a loop compare elements and sort them in the correct position
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}