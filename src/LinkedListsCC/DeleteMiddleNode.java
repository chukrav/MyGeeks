package LinkedListsCC;

/*2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
        the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
        that node.
        EXAMPLE
        lnput:the node c from the linked list a->b->c->d->e->f
        Result: nothing is returned, but the new linked list looks like a ->b->d->e->f
        The solution is simply to copy the data from the next node over to the current node,
         and then to delete the next node.
      pp 222  */

public class DeleteMiddleNode {

    public static boolean deleteNode(LinkedListNode n) {
        if (n == null || n.next == null) {
            return false; // Failure
        }
        LinkedListNode next = n.next;
        n.data= next.data;
        n.next = next.next;
        return true;
    }

    public static void main(String[] args) {

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
