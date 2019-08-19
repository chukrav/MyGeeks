package arrays;

import java.util.ArrayList;

import static arrays.testParceInput.usingBufferedReader2;

public class SortAnArrayOf012s {

/* Given an array A of size N containing 0s, 1s, and 2s; you need to sort the array in ascending order.
Example:
Input :
2
5
0 2 1 2 0
3
0 1 0

Output:
0 0 1 2 2
0 0 1
 */

    public static void sort012(Integer arr[], int n)   {
        // Variables to maintain
        // the count of 0's,
        // 1's and 2's in the array
        int count0 = 0, count1 = 0;
        int count2 = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0)
                count0++;
            if (arr[i] == 1)
                count1++;
            if (arr[i] == 2)
                count2++;
        }

        // Putting the 0's in the
        // array in starting.
        for (int i = 0; i < count0; i++)
            arr[i] = 0;

        // Putting the 1's in the
        // array after the 0's.
        for (int i = count0; i <
                (count0 + count1); i++)
            arr[i] = 1;

        // Putting the 2's in the
        // array after the 1's
        for (int i = (count0 + count1);
             i < n; i++)
            arr[i] = 2;

        printArray(arr, n);
    }

    // Prints the array
    public static void printArray(Integer arr[], int n)
    {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Integer[]> listList = new ArrayList<>();
        listList = usingBufferedReader2(listList);
        int testsNum = listList.size();
        for (int i = 1; i < testsNum; i += 2){
            //sort012(listList.get(i+1),listList.get(i)[0]);
            sort012Dutch(listList.get(i+1),listList.get(i)[0]);

        }

    }

    // Sort the input array, the array is assumed to
    // have values in {0, 1, 2}
    /*    — Dutch National Flag Algorithm, or 3-way Partitioning —    */
    static void sort012Dutch(Integer a[], int arr_size)
    {
        int lo = 0;
        int hi = arr_size - 1;
        int mid = 0, temp = 0;
        while (mid <= hi) {
            switch (a[mid]) {
                case 0: {
                    temp = a[lo];
                    a[lo] = a[mid];
                    a[mid] = temp;
                    lo++;
                    mid++;
                    break;
                }
                case 1:
                    mid++;
                    break;
                case 2: {
                    temp = a[mid];
                    a[mid] = a[hi];
                    a[hi] = temp;
                    hi--;
                    break;
                }
            }
        }
        printArray(a, arr_size);
    }
}
