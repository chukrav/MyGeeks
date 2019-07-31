package LinkedListsCC;


/*2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
        FOLLOW UP
        How would you solve this problem if a temporary buffer is not allowed?


   pp219  */

import java.util.HashSet;

public class RemoveDups {

    /*    In order to remove duplicates from a linked list, we need to be able to track duplicates. A simple hash table
        will work well here.
        The solution takes O(N) time, where N is the number of elements in the linked list*/
    void deleteDups(LinkedListNode n) {
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }


    public static void main(String[] args) {

    }

    //    ------------------------------------------------
/*Follow Up: No Buffer Allowed
    lfwe don't have a buffer, we can iterate with two pointers: current which iterates through the linked list,
    and runner which checks all subsequent nodes for duplicates.
    This code runs in O ( 1) space, but O ( N^2) time. */
    void deleteDups2(LinkedListNode head) {
        LinkedListNode current = head;
        while (current != null) {
         /* Remove all future nodes that have the same value */
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }


    private class LinkedListNode {
        public int data;
        public LinkedListNode next;
        public LinkedListNode(int num){
            data = num;
            next = null;
        }
    }
}
