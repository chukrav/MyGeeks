package LinkedListsCC;

/*2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
        before all nodes greater than or equal to x. If x is contained within the list the values of x only need
        to be after the elements less than x (see below). The partition element x can appear anywhere in the
        "right partition"; it does not need to appear between the left and right partitions.
        EXAMPLE
        Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
        Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

    pp 223    */

import java.util.LinkedList;

public class Partition {

    /*Pass in the head of the linked list and the value to partition around*/
    public static LinkedListNode partition(LinkedListNode node, int x) {
        LinkedListNode beforeStart= null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

         /*Partition list*/
        while (node!= null) {
            LinkedListNode next= node.next;
            node.next = null;
            if (node.data< x) {
                 /*Insert node into end of before list*/
                if(beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next= node;
                    beforeEnd = node;
                }

            } else {
                /*Insert node into end of after list*/
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next= node;
                    afterEnd= node;
                }
            }

            node = next;
        }

        if (beforeStart== null) {
            return afterStart;
        }

         /* Merge before list and after list */
        beforeEnd.next= afterStart;
        return beforeStart;
    }


    public static void main(String[] args) {

//        Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition= 5]
//        Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

        /*LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(0);
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(""+list.getFirst());
        System.out.println(""+list.getLast());*/
        LinkedListNode head = null;
        head = addToList(new LinkedListNode(3),head);
        head = addToList(new LinkedListNode(5),head);
        head = addToList(new LinkedListNode(8),head);
        head = addToList(new LinkedListNode(5),head);
        head = addToList(new LinkedListNode(10),head);
        head = addToList(new LinkedListNode(2),head);
        head = addToList(new LinkedListNode(1),head);
        printList(head);
        System.out.println("-------------------------");

        int pattern = 5;

//        LinkedListNode headNew = partition(head, pattern);
        LinkedListNode headNew = partition2(head, pattern);
        printList(headNew);

    }

    /*    In this approach, we start a "new"l ist (using the existing nodes). Elements bigger than the pivot element are
        put at the tail and elements smaller are put at the head. Each time we insert an element, we update either
        the head or tail.   */
    public static LinkedListNode partition2(LinkedListNode node, int x) {
        LinkedListNode head = node;
        LinkedListNode tail = node;

        while (node != null) {
            LinkedListNode next = node.next;
            if (node.data < x) {
                /* Insert node at head. */
                node.next = head;
                head = node;
            } else {
                 /* Insert node at tail. */
                tail.next = node;
                tail = node;
            }
            node= next;
        }
        tail.next= null;

        // The head has changed, so we need to return it to the user.
        return head;
    }

    //----------------------------------------------------------------
    private static class LinkedListNode {
        public int data;
        public LinkedListNode next;
        public LinkedListNode(int num){
            data = num;
            next = null;
        }
    }

    public static LinkedListNode addToList(LinkedListNode node, LinkedListNode head){
        if (head == null) {
            head = node;
        } else {
            LinkedListNode tmp = head;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        return  head;
    }

    public static void printList(LinkedListNode node){
        while (node != null){
            System.out.println("  "+node.data);
            node = node.next;
        }
    }
}