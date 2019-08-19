package arrays;

import java.util.ArrayList;
import java.util.Stack;

import static arrays.testParceInput.usingBufferedReader2;

/* Given an array of positive integers. Your task is to find the leaders in the array.
Note: An element of array is leader if it is greater than or equal to all the elements to its
 right side. Also, the rightmost element is always a leader.

 Example:
Input:
3
6
16 17 4 3 5 2
5
1 2 3 4 0
5
7 4 5 7 3
Output:
17 5 2
4 0
7 7 3

 */

public class LeadersInArray {

    public static void main(String[] args) {
        ArrayList<Integer[]> listList = new ArrayList<>();
        listList = usingBufferedReader2(listList);
        int testNum = listList.size();
        for(int i = 1; i < testNum; i += 2){
            //getLeaders(listList.get(i+1), listList.get(i)[0]);
            printLeaders2(listList.get(i+1), listList.get(i)[0]);
            System.out.println("");
        }

    }

    public static Stack<Integer> getLeaders(Integer[] arr, int arr_size){
        try {
            Stack<Integer> stack = new Stack<>();
            for (Integer num : arr) {
                while(!stack.empty() && stack.peek() < num){
                    stack.pop();
                }
                stack.push(num);
            }
            printLeaders(stack);
            return stack;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void printLeaders(Stack<Integer> stack){
        Stack<Integer> rev = new Stack<>();
        while (!stack.empty()){
            rev.push(stack.pop());
        }
        while (!rev.empty()){
            System.out.print(rev.pop() + " ");
        }
        System.out.println("");
    }

    /* Java Function to print leaders in an array */
    public static void printLeaders2(Integer arr[], int size)
    {
        int max_from_right =  arr[size-1];
        Stack<Integer> stack = new Stack<>();

        /* Rightmost element is always leader */
        //System.out.print(max_from_right + " "); //Will print in reverse order.
        stack.push(max_from_right);

        for (int i = size-2; i >= 0; i--) {
            if (max_from_right <= arr[i])
            {
                max_from_right = arr[i];
                //System.out.print(max_from_right + "* "); //Will print in reverse order.
                stack.push(max_from_right);
            }
        }
        while (!stack.empty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
