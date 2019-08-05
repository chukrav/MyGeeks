package LinkedListsCC;

public class LoopDetection {

/*    2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
    beginning of the loop.
    DEFINITION
    Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
    as to make a loop in the linked list.
    EXAMPLE
    Input: A - > B - > C - > D - > E - > C [the same C as earlier]
    Output: C

    This is a modification of a classic interview problem: detect if a linked list has a loop. Let's apply the Pattern
    Matching approach.
    An easy way to detect if a linked list has a loop is through the FastRunner / SlowRunner approach.

     pp 234 */

    static LinkedListNode FindBeginning(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;
                /* Find meeting point. This will be LOOP_SIZE - k steps into the linked list. */
        while (fast!= null && fast.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {//Collision
                break;
            }
        }

         /* Error check - no meeting point, and therefore no loop*/
         if (fast == null || fast.next == null) {
             return null;
             }

         /* Move slow to Head. Keep fast at Meeting Point. Each are k steps from the
         * Loop Start. If they move at the same pace, they must meet at Loop Start. */
         slow = head;
         while (slow!= fast) {
            slow = slow.next;
            fast = fast.next;
            }

         /* Both now point to the start of the loop. */
         return fast;
         }

    public static void main(String[] args) {
        LinkedListNode node =  new LinkedListNode(4);
        LinkedListCC L1 = new LinkedListCC();
        L1.add(1);
        L1.add(2);
        L1.add(3);
        L1.add(node);
        L1.add(5);
        L1.add(6);
        L1.add(7);
        L1.add(8);
        L1.add(9);
        L1.add(10);
        L1.add(11);
        L1.add(node);

        LinkedListNode loopHead = FindBeginning(L1.getHead());
        System.out.println("Loop head.data: "+loopHead.data);


    }
}
