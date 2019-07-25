package arrays;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MissingNumberInArray {
    public static void main(String[] args) {
        //int n;
        //usingScanner();
        int a[] = { 1, 2, 4, 5, 6 };
        int miss = getMissingNo(a, 5);
        System.out.println(miss);
    }

    private static void usingScanner()
    {
//        System.out.println("Name: ");
//
//        Scanner scanIn = new Scanner(System.in);
//        String inputString = scanIn.nextLine();
//
//        scanIn.close();
//        System.out.println("Name entered : " + inputString);
        Scanner s = new Scanner(System.in);
        int n;
        n = s.nextInt();
        while (n-- > 0){
            int LEN = s.nextInt();
//            int ar[] = new int[LEN];
            int res = 0, ar = 0;
            for (int i = 0; i < LEN; i++){
                //ar[i] = s.nextInt();
                res = res ^ i;
                res = res ^ s.nextInt();
            }
            res = res ^ LEN;
            System.out.printf("" + res);

        }


    }

    // Function to find missing number
    static int getMissingNo(int a[], int n)
    {
        int x1 = a[0];
        int x2 = 1;

        /* For xor of all the elements
           in array */
        for (int i = 1; i < n; i++)
            x1 = x1 ^ a[i];

        /* For xor of all the elements
           from 1 to n+1 */
        for (int i = 2; i <= n + 1; i++)
            x2 = x2 ^ i;

        return (x1 ^ x2);
    }


}
