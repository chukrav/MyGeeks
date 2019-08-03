package LinkedListsCC;

/*2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
        SOLUTION
        To approach this problem, we can picture a palindrome
        like 0 - > 1 - > 2 - > 1 - > 0. We know that,
        since it's a palindrome, the list must be the same backwards
         and forwards.This leads us to our first solution.
         pp 227 */

import java.util.Stack;

public class Palindrome {

    //    Solution #1: Reverse and Compare
    boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed= reverseAndClone(head);
        return isEqual(head, reversed);
    }

    LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head= null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data); // Clone
            n.next= head;
            head = n;
            node= node.next;
        }
        return head;
    }

    boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one== null && two == null;
    }
    //    --------------------------------------
/*    Solution #2: Iterative Approach
We need to push the first half of the elements onto a stack.
we can iterate through the linked list, using the fast runner/ slow
runner technique described in the beginning of the chapter. At each step
in the loop, we push the data from the slow runner onto a stack. When
 the fast runner hits the end of the list, the slow runner will have reached
the middle of the linked list. By this point, the stack will have all the elements
 from the front of the linked
list, but in reverse order.
 */
    boolean isPalindrome2(LinkedListNode head) {
        LinkedListNode fast= head;
        LinkedListNode slow= head;
        Stack<Integer> stack= new Stack<Integer>();

/* Push elements from first half of linked list onto stack. When fast runner
 * (which is moving at 2x speed) reaches the end of the linked list, then we
 * know we're at the middle*/
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast= fast.next.next;
        }

         /* Has odd number of elements, so skip the middle element*/
        if (fast!= null) {
            slow= slow.next;
        }

        while (slow != null) {
            int top= stack.pop().intValue();

             /* If values are different, then it's not a palindrome*/
            if (top != slow.data) {
                return false;
            }
            slow= slow.next;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
