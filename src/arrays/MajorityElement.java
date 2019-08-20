package arrays;


/*Given an array A of N elements. Find the majority element in the array.
 A majority element in an array A of size N is an element that appears more than N/2 times in the array.
Example:
Input:
2
5
3 1 3 3 2
3
1 2 3

Output:
3
-1
--------------
1
7
3 3 3 3 3 3 5

Explanation:
Testcase 1: Since, 3 is present more than N/2 times, so it is the majority element.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static arrays.testParceInput.usingBufferedReader2;

public class MajorityElement {

    public static void getMajorityElement(Integer[] arr, int size){
        HashMap<Integer,Integer> elements = new HashMap<>();
        for (Integer n:arr) {
            if (elements.containsKey(n)){
                elements.put(n, elements.get(n) + 1);
            } else{
                elements.put(n, 1);
            }
        }
        int MAGCNT = (int) Math.ceil(size/2);
        int MAGVAL = -1;

        for (Map.Entry n : elements.entrySet()) {
            Integer val = (Integer) n.getValue();
            if (val > MAGCNT){
                MAGCNT = val;
                MAGVAL = (Integer) n.getKey();
            }
        }
        System.out.println("" + MAGVAL);
    }

    public static void main(String[] args) {
//        Integer[] arr = {3, 1, 3, 3, 2};
//        int size = 5;
//        Integer[] arr = {1, 2, 3};
//        int size = 3;

        /*ArrayList<Integer[]> listList = new ArrayList<>();
        listList = usingBufferedReader2(listList);
        int testNum = listList.size();
        for(int i = 1; i < testNum; i += 2){
            getMajorityElement(listList.get(i+1), listList.get(i)[0]);
        }*/

        GGSolution();


    }

    public static void GGSolution(){
         int a[] = new int[1000001];
        //public static void main(String args[]) throws IOException {
        try {

            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(read.readLine());

            while(t-- > 0)
            {
                int n = Integer.parseInt(read.readLine());
                String st[] = read.readLine().trim().split("\\s+");

                int k = 0;
                boolean flag = false;
                for(int i = 0; i < n; i++)
                {
                    k = Integer.parseInt(st[i]);
                    a[k]++;
                    if(a[k] > n/2 && flag == false) {
                        System.out.println(k);
                        flag = true;   // :)))
                    }
                }

                if(flag == false) {
                    System.out.println("-1");
                }
                Arrays.fill(a, 0);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
