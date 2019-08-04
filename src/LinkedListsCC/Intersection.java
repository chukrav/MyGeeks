package LinkedListsCC;

/*2.7 Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
        intersecting node. Note that the intersection is defined based on reference, not value. That is, if the
        kth node of the first linked list is the exact same node (by reference) as the jth node of the second
        linked list, then they are intersecting.

       pp 232  */


public class Intersection {

    static class Result {
        public LinkedListNode tail;
        public int size;
        public Result(LinkedListNode tail, int size) {
            this.tail = tail;
            this.size= size;
        }
    }


    public static void main(String[] args) {
        LinkedListCC L1 = new LinkedListCC();
        L1.addToList(new LinkedListNode(3));
        L1.addToList(new LinkedListNode(1));
        L1.addToList(new LinkedListNode(5));
        L1.addToList(new LinkedListNode(9));

        LinkedListNode N1 = new LinkedListNode(7);
        LinkedListNode N2 = new LinkedListNode(2);
        LinkedListNode N3 = new LinkedListNode(1);

//        L1.addToList(N1);
//        L1.addToList(N2);
//        L1.addToList(N3);
//        L1.printList();

//        System.out.println("=================: ");

        LinkedListCC L2 = new LinkedListCC();
        L2.addToList(new LinkedListNode(4));
        L2.addToList(new LinkedListNode(6));
        L2.addToList(N1);
        L2.addToList(N2);
        L2.addToList(N3);
        L2.printList();
        System.out.println("=================: ");

        System.out.println("L1,head: "+L1.getHead().data);
        L1.addToList(N1);
        L1.addToList(N2);
        L1.addToList(N3);
        L1.printList();
       // System.out.println("L1,head: "+L1.getHead().data);

        //LinkedListNode intersect = findintersection(L1.getHead(), L2.getHead());
        //System.out.println("Intersect Node data: "+intersect.data);


    }

    static LinkedListNode findintersection(LinkedListNode listl, LinkedListNode list2) {
        if (listl == null || list2 == null) return null;
    /* Get tail and sizes. */
        Result resultl = getTailAndSize(listl);
        Result result2 = getTailAndSize(list2);
/* If different tail nodes, then there's no intersection. */
        if (resultl.tail != result2.tail) {
            return null;
        }
    /* Set pointers to the start of each linked list. */
        LinkedListNode shorter = resultl.size < result2.size ? listl : list2;
        LinkedListNode longer = resultl.size < result2.size ? list2 : listl;
/* Advance the pointer for the longer linked list by difference in lengths. */
        longer = getKthNode(longer, Math.abs(resultl.size - result2.size));
/* Move both pointers until you have a collision. */
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

/* Return either one. */
        return longer;
    }

    static Result getTailAndSize(LinkedListNode list) {
        if (list == null) return null;

        int size = 1;
        LinkedListNode current= list;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return new Result(current, size);
    }

    static LinkedListNode getKthNode(LinkedListNode head, int k) {
        LinkedListNode current= head;
        while (k > 0 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }

}
