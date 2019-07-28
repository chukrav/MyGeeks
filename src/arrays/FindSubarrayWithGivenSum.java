package arrays;

//Find subarray with given sum | Set 1 (Nonnegative Numbers)
//Exercise: https://practice.geeksforgeeks.org/problems/subarray-with-given-sum/0/?ref=self
//Solution : https://www.geeksforgeeks.org/find-subarray-with-given-sum/

public class FindSubarrayWithGivenSum {
    public static void main(String[] args) {
        //SubarraySum arraysum = new SubarraySum();
        //int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
        int arr[] = {1, 2, 3, 7, 5};
        int n = arr.length;
//        int sum = 23;
        int sum = 12;
        subArraySumN(arr, n, sum);

    }
    //     O(n^2)
    // Returns true if the there is a subarray of arr[] with sum equal to
//       'sum' otherwise returns false.  Also, prints the result */
    public static int subArraySum(int arr[], int n, int sum)
    {
        int curr_sum, i, j;

        // Pick a starting point
        for (i = 0; i < n; i++)
        {
            curr_sum = arr[i];

            // try all subarrays starting with 'i'
            for (j = i + 1; j <= n; j++)
            {
                if (curr_sum == sum)
                {
//                    int p = j - 1;
                    int p = j;
                    System.out.println("Sum found between indexes " + (i+1)
                            + " and " + p);
                    return 1;
                }
                if (curr_sum > sum || j == n)
                    break;
                curr_sum = curr_sum + arr[j];
            }
        }

        System.out.println("No subarray found");
        return 0;
    }

    /* Returns true if the there is a subarray of arr[] with sum equal to
       'sum' otherwise returns false.  Also, prints the result */
    public static int subArraySumN(int arr[], int n, int sum)
    {
        int curr_sum = arr[0], start = 0, i;

        // Pick a starting point
        for (i = 1; i <= n; i++)
        {
            // If curr_sum exceeds the sum, then remove the starting elements
            while (curr_sum > sum && start < i-1)
            {
                curr_sum = curr_sum - arr[start];
                start++;
            }

            // If curr_sum becomes equal to sum, then return true
            if (curr_sum == sum)
            {
//                int p = i-1;
                int p = i;
                System.out.println("Sum found between indexes " + (start+1)
                        + " and " + p);
                return 1;
            }

            // Add this element to curr_sum
            if (i < n)
                curr_sum = curr_sum + arr[i];

        }

        System.out.println("No subarray found");
        return 0;
    }


}
