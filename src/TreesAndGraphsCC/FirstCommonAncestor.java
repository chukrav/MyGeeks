package TreesAndGraphsCC;

/*4.8 First Common Ancestor: Design an algorithm and write code to find the first common ancestor
of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
necessarily a binary search tree.
SOLUTION
Ifthis were a binary search tree, we could modify the find operation for the two nodes and see where the
paths diverge. Unfortunately, this is not a binary search tree, so we must try other approaches.
Let's assume we're looking for the common ancestor of nodes p and q. One question to ask here is if each
node in our tree has a link to its parents.

          pp 268  */

public class FirstCommonAncestor {
    /*Solution #1: With Links to Parents
If each node has a link to its parent, we could trace p and q's paths up until they intersect.
This is essentially the same problem as question 2.7 which find the intersection of two linked lists.
 The"linked list"in this case is the path from each node up to the root.
 This approach will take 0(d) time, where d is the depth of the deeper node.
 */

    TreeNode commonAncestor(TreeNode p, TreeNode q) {
        int delta= depth(p) - depth(q); // get difference in depths
        TreeNode first = delta > 0 ? q : p; // get shallower node
        TreeNode second= delta > 0 ? p : q; // get deeper node
        second= goUpBy(second, Math.abs(delta)); // move deeper node up

         /* Find where paths intersect. */
        while (first != second && first != null && second != null) {
            first= first.parent;
            second= second.parent;
        }
        return first == null || second == null? null : first;
    }


    TreeNode goUpBy(TreeNode node, int delta) {
        while (delta> 0 && node != null) {
            node= node.parent;
            delta--;
        }
        return node;
    }

    int depth(TreeNode node) {
        int depth= 0;
        while (node != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {

    }

/*Solution #2: With Links to Parents (Better Worst-Case Runtime)
Similar to the earlier approach, we could trace p's path upwards and check if any of the nodes cover q.
The first node that covers q (we already know that every node on this path will cover p) must be the first
common ancestor.

*/

    TreeNode commonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
         /* Check if either node is not in the tree, or if one covers the other. */
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        } else if (covers(p, q)) {
            return p;
        }else if (covers(q, p)) {
            return q;
        }


         /* Traverse upwards until you find a node that covers q. */
        TreeNode sibling= getSibling(p);
        TreeNode parent= p.parent;
        while (!covers(sibling, q)) {
            sibling= getSibling(parent);
            parent= parent.parent;
        }
        return parent;
    }

    boolean covers(TreeNode root, TreeNode p) {
        if (root== null) return false;
        if (root == p) return true;
        return covers(root.left, p) || covers(root.right, p);
    }

    TreeNode getSibling(TreeNode node) {
        if (node== null || node.parent == null) {
            return null;
        }

        TreeNode parent = node.parent;
        return parent.left== node? parent.right : parent.left;
    }


}
