package LinkedListsCC;

public class ReturnKthToLast {

/*    2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
    SOLUTION
    We will approach this problem both recursively and non-recursively.Remember that recursive solutions are
    often cleaner but less optimal. For example, in this problem, the recursive implementation is about half the
    length of the iterative solution but also takes 0(n) space, where n is the number of elements in the linked
    list.

    Note that for this solution, we have defined k such that passing in k = 1 would return the last element,k
    2 would return to the second to last element, and so on. It is equally acceptable to define k such that
    k = 0 would return the last element

 pp 220  */

    /*    Approach A: Don't Return the Element.
        One way to do this is to change the problem to simply printing the k-th to last element. Then, we can pass
        back the value of the counter simply through return values.
        */
    int printKthToLast(LinkedlistNode head, int k) {
        if (head== null) {
            return 0;
        }
        int index = printKthToLast(head.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.data);
        }
        return index;
    }

    public static void main(String[] args) {

    }



    private class LinkedlistNode {
        public int data;
        public LinkedlistNode next;
        public LinkedlistNode(int num){
            data = num;
            next = null;
        }
    }
}
