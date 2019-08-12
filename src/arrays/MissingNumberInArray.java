package arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

//Missing number in array: usingBufferedReader(); <- Worked !!!
// https://practice.geeksforgeeks.org/problems/missing-number-in-array/0/?track=md-arrays&batchId=144

public class MissingNumberInArray {
    public static void main(String[] args) {
        //int n;
        //usingScanner();
//        int a[] = { 1, 2, 4, 5, 6 };
//        int miss = getMissingNo2(a, 5);
//        System.out.println(miss);
//        usingScanner();
        usingBufferedReader();

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
            for (int i = 1; i < LEN; i++){
                //ar[i] = s.nextInt();
                res = res ^ i;
                res = res ^ s.nextInt();
            }
            res = res ^ LEN;
            System.out.println("" + res);

        }


    }

    private static void usingBufferedReader()
    {
        //System.out.println("Name: ");
        try{
            BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
            //String inputString = bufferRead.readLine();

            //System.out.println("Name entered : " + inputString);

            int n;
            n = Integer.parseInt(s.readLine());//  .nextInt();
            while (n-- > 0){
                int LEN = Integer.parseInt(s.readLine());
                String input = s.readLine();
                int[] ar = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
//                System.out.println("ar.length: "+ar.length+"LEN: "+LEN);
//            int ar[] = new int[LEN];
                int res = 0;//, ar = 0;
                for (int i = 1; i < LEN; i++){
                    //ar[i] = s.nextInt();
                    res = res ^ i;
                    //res = res ^ Integer.parseInt(s.readLine());
                    res = res ^ ar[i-1];
//                    System.out.println("*** " + res);
                }
                res = res ^ LEN;
//                System.out.println("final: " + res);
                System.out.println("" + res);

            }


        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // Function to find missing number
    static int getMissingNo(int a[], int n) {
        int x1 = a[0];
        int x2 = 1;
        //int tmp;

        /* For xor of all the elements
           in array */
        //System.out.printf("Array: "+a.toString());
        for (int i = 1; i < n; i++) {
            x1 = x1 ^ a[i];
            System.out.println("i: " + i + ", x1: " + x1);
        }

        /* For xor of all the elements
           from 1 to n+1 */
        for (int i = 2; i <= n + 1; i++) {
            x2 = x2 ^ i;
            System.out.println("i: " + i + ", x2: " + x2);
        }

        return (x1 ^ x2);
    }

    static int getMissingNo2(int a[], int n) {
//        int a[] = { 1, 2, 4, 5, 6 };
        int res = 0;
        int x;
        for(int i = 1; i < n; i++)
        {
            x = a[i-1];
            res = res^x;
            res = res^i;
            System.out.println("res: " + res);
        }
        //System.out.println(res^n);
        return (res^n);
    }


}
