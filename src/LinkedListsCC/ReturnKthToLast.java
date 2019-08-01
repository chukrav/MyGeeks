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
    public static int printKthToLast(LinkedlistNode head, int k) {
        if (head== null) {
            return 0;
        }
        int index = printKthToLast(head.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.data);
            return head.data;
        }
        return index;
    }

    public static void main(String[] args) {

        //LinkedList<LinkedlistNode> list = new LinkedList<>();
        LinkedlistNode head = null;
        for (int i = 0; i < 10; i++) {
            head = addToList(new LinkedlistNode(i), head);
        }

        int index = printKthToLast(head, 5);
        System.out.println("--index: "+index);
        Index id = new Index();
        LinkedlistNode node = kthToLast(head,5);
//        LinkedlistNode node = kthToLast(head,5, id);
        System.out.println("--node.data: " + node.data + ", id: "+
                id.value);
        node = nthTolast(head, 5);
        System.out.println("nthTolast node.data: " + node.data);


    }
    /*Approach C: Create a Wrapper Class.

     */
    private static class Index{
        public int value = 0;
    }

    public static  LinkedlistNode kthToLast(LinkedlistNode head, int k) {
        Index idx = new Index();
        return kthToLast(head, k, idx);
    }

    public static LinkedlistNode kthToLast(LinkedlistNode head, int k, Index idx) {
        if (head== null) {
            return null;
        }
        LinkedlistNode node = kthToLast(head.next, k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
//            System.out.println("return head ------------->");
            return head;
        }
//        System.out.println("return idx: "+ idx.value);
        return node;
    }

    /*    Solution #3: Iterative
        We can use two pointers, pl and p2. We place them k nodes apart in the linked list.
     */
    public static LinkedlistNode nthTolast(LinkedlistNode head, int k) {
        LinkedlistNode pl = head;
        LinkedlistNode p2 = head;

     /* Move pl k nodes into the list.*/
        for (int i= 0; i < k; i++) {
            if (pl == null) return null; // Out of bounds
            pl = pl.next;
        }

     /* Move them at the same pace. When pl hits the end, p2 will be at the right
     * element. */
        while (pl!= null) {
            pl = pl.next;
            p2 = p2.next;
        }
        return p2;
    }

//    ----------------------------------

    public static class LinkedlistNode {
        public int data;
        public LinkedlistNode next;
        public LinkedlistNode(int num){
            data = num;
            next = null;
        }
    }

    public static LinkedlistNode addToList(LinkedlistNode node, LinkedlistNode head){
        if (head == null) {
            head = node;
        } else {
            LinkedlistNode tmp = head;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        return  head;
    }
}
