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

    /*Solution #3: Without Links to Parents
Alternatively, you could follow a chain in which p and q are on the same side. That is, if p and q are both on
the left of the node, branch left to look for the common ancestor. If they are both on the right, branch right
to look for the common ancestor. When p and q are no longer on the same side, you must have found the
first common ancestor.
This algorithm runs in O(n) time on a balanced tree. This is because covers is called on 2n nodes in the
first call (n nodes for the left side, and n nodes for the right side). After that the algorithm branches left or
right, at which point covers will be called on 2n/2 nodes, then 2n/4, and so on. This results in a runtime
of O(n).
*/

    TreeNode commonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        /* Error check - one node is not in the tree. */
        if (!covers(root, p) || ! covers(root, q)) {
            return null;
        }
        return ancestorHelper(root, p, q);
    }

    TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root== null || root == p || root== q) {
            return root;
        }

        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft= covers(root.left, q);
        if (pIsOnLeft != qIsOnLeft) {//Nodes are on different side
            return root;
        }
        TreeNode childSide = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q);
    }

    /*   boolean covers(TreeNode root, TreeNode p) {
    if (root== null) return false;
    if (root== p) return true;
    return covers(root.left, p) || covers(root.right, p);
    }
                 */
 /*Solution #4: Optimized
Although Solution #3 is optimal in its runtime, we may recognize that there is still some inefficiency in how
it operates. Specifically, covers searches all nodes under root for p and q, including the nodes in each
subtree (root. left and root.right). Then, it picks one of those subtrees and searches all of its nodes.
Each subtree is searched over and over again.

  */
    /* The below code has a bug.
     The problem with this code occurs in the case where a node is not contained in the tree. */

    TreeNode commonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p && root == q) return root;

        TreeNode x = commonAncestor4(root.left, p, q);
        if (x != null && x != p && x != q) { // Already found ancestor
            return x;
        }

        TreeNode y = commonAncestor4(root.right, p, q);
        if (y != null && y != p && y != q) { // Already found ancestor
            return y;
        }

        if (x != null && y != null) { // p and q found in diff. subtrees
            return root; // This is the common ancestor
        } else if (root == p || root == q) {
            return root;
        } else {
            return x == null ? y: x; /* return the non-null value */
        }
    }

    /*In other words, when we call commonAncestor on the right subtree, the code will return node 5, just as
it should. The problem is that in finding the common ancestor of pand q, the calling function can't distinguish between
 the two cases:
• Case 1: p is a child of q (or, q is a child of p)
• Case 2: p is in the tree and q is not (or, q is in the tree and pis not)
In either of these cases, commonAncestor will return p. In the first case, this is the correct return value, but
in the second case, the return value should be null.
We somehow need to distinguish between these two cases, and this is what the code below does. This
code solves the problem by returning two values: the node itself and a flag indicating whether this node is
actually the common ancestor.

     */

    static class Result {
        public TreeNode node;
        public boolean isAncestor;
        public Result(TreeNode n, boolean isAnc) {
            node = n;
            isAncestor = isAnc;
        }
    }

    TreeNode commonAncestor5(TreeNode root, TreeNode p, TreeNode q) {
        Result r = commonAncHelper5(root, p, q);
        if (r.isAncestor) {
            return r.node;
        }
        return null;
    }

    Result commonAncHelper5(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new Result(null, false);

        if (root == p && root == q) {
            return new Result(root, true);
        }

        Result rx = commonAncHelper5(root.left, p, q);
        if (rx.isAncestor) {//Found common ancestor
            return rx;
        }

        Result ry = commonAncHelper5(root.right, p, q);
        if (ry.isAncestor) {//Found common ancestor
            return ry;
        }

        if (rx.node != null && ry.node != null) {
            return new Result(root, true); // This is the common ancestor
        } else if (root == p || root == q) {
            /* If we're currently at p or q, and we also found one of those nodes in a
             * subtree, then this is truly an ancestor and the flag should be true. */
            boolean isAncestor = rx.node != null || ry.node != null;
            return new Result(root, isAncestor);
        } else {
            return new Result(rx.node != null ? rx.node : ry.node, false);
        }


    }
}
