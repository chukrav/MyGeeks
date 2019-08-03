package LinkedListsCC;

/*2.5 Sum Lists: You have two numbers represented by a linked list,where each node contains a single
        digit. The digits are stored in reverse order,such that the 1's digit is at the head of the list. Write a
        function that adds the two numbers and returns the sum as a linked list.
        EXAMPLE
        Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
        Output: 2 -> 1 -> 9.That is,912.
        FOLLOW UP
        Suppose the digits are stored in forward order. Repeat the above problem.
        Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
        Output: 9 -> 1 -> 2.That is, 912.
        pp 225   */

public class SumLists {

    public static LinkedListNode addlists(LinkedListNode l1, LinkedListNode l2, int carry) {
        if (l1 == null && l2== null && carry== 0) {
            return null;
        }

        LinkedListNode result = new LinkedListNode();
        int value = carry;
        if (l1 != null) {
            value += l1.data;
        }
        if (l2 != null) {
            value += l2.data;
        }
        //Solutions to Chapter 2 I Linked Lists
        result.data = value% 10; /* Second digit of number */

         /*Recurse */
        if (l1 !=null || l2 !=null) {
            LinkedListNode more = addlists(l1 == null ? null : l1.next,
                    l2 == null? null : l2 . next,
                    value >= 10 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }



    public static void main(String[] args) {
//        Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
//        Output: 2 -> 1 -> 9.That is,912.

        LinkedListCC L1 = new LinkedListCC();
        L1.addToList(new LinkedListNode(7));
        L1.addToList(new LinkedListNode(1));
        L1.addToList(new LinkedListNode(6));
//        L1.printList();

        LinkedListCC L2 = new LinkedListCC();
        L2.addToList(new LinkedListNode(5));
        L2.addToList(new LinkedListNode(9));
        L2.addToList(new LinkedListNode(2));
//        L2.printList();

//        LinkedListNode newHead = addlists(L1.getHead(), L2.getHead(),0);
        LinkedListNode newHead = addLists2(L1.getHead(), L2.getHead());
        printList(newHead);

    }
    //------------- Follow Up-----------------------------------
    public static LinkedListNode addLists2(LinkedListNode l1, LinkedListNode l2) {
        int len1 = length(l1);
        int len2 = length(l2);
        /* Pad the shorter list with zeros - see note (1) */
        if (len1 < len2) {
            l1 = padList(l1,len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

         /* Add lists */
        PartialSum sum = addListsHelper(l1, l2);

         /* If there was a carry value left over, insert this at the front of the list.
         * Otherwise, just return the linked list. */
        if (sum.carry== 0) {
            return sum.sum;
        } else {
            LinkedListNode result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }


    static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        if (l1== null && l2== null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
         /* Add smaller digits recursively*/
        PartialSum sum = addListsHelper(l1.next, l2.next);

         /* Add carry to current data*/
        int val = sum.carry + l1.data + l2.data;

         /* Insert sum of current digits*/
        LinkedListNode full_result= insertBefore(sum.sum, val % 10);

         /* Return sum so far, and the carry value*/
        sum.sum = full_result;
        sum.carry = val/ 10;
        return sum;
    }

    /* Pad the list with zeros*/
    static LinkedListNode padList(LinkedListNode node, int padding) {
        LinkedListNode head= node;
        for (int i= 0; i < padding; i++) {
            head= insertBefore(head, 0);
        }
        return head;
    }

    /* Helper function to insert node in the front of a linked list*/
    static LinkedListNode insertBefore(LinkedListNode list, int data) {
        LinkedListNode node= new LinkedListNode(data);
        if (list != null) {
            node.next= list;
        }
        return node;
    }

    //---------------------------------------------

    public static void printList(LinkedListNode node){
        while (node != null){
            System.out.println("  "+node.data);
            node = node.next;
        }
    }

    private static class PartialSum {
        public LinkedListNode sum = null;
        public int carry= 0;
    }

    private static int length(LinkedListNode node) {
        int len = 0;
        LinkedListNode tmp = node;
        while (tmp != null){
            tmp = tmp.next;
            len++;
        }
        return len;
    }


}
