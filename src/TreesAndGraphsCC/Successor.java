package TreesAndGraphsCC;

/*4.6 Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
binary search tree. You may assume that each node has a link to its parent.
SOLUTION
Recall that an in-order traversal traverses the left subtree, then the current node, then the right subtree. To
approach this problem, we need to think very, very carefully about what happens.
Let's suppose we have a hypothetical node. We know that the order goes left subtree, then current side,
then right subtree. So, the next node we visit should be on the right side.
But which node on the right subtree? It should be the first node we'd visit if we were doing an in-order
traversal of that subtree. This means that it should be the leftmost node on the right subtree. Easy enough!
But what if the node doesn't have a right subtree? This is where it gets a bit trickier.

       pp 259 */

public class Successor {

    TreeNode inorderSucc(TreeNode n) {
        if (n == null) return null;

         /* Found right children -> return leftmost node of right subtree. */
        if (n.right != null) {
            return leftMostChild(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            // Go up until we're on left instead of right
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    TreeNode leftMostChild(TreeNode n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    public static void main(String[] args) {

        BinaryTree bst = new BinaryTree();
        bst.add(6);
        bst.add(4);
        bst.add(8);
        bst.add(3);
        bst.add(5);
        bst.add(7);
        bst.add(9);

        TreeNode toFind = bst.findNode(5);

        TreeNode succ = inorderSucc2(toFind);
        System.out.println("Successor: "+succ.value);
        // out: Successor: 6

    }

    public static TreeNode inorderSucc2(TreeNode n) {
        if (n == null) return null;
        /* Found right children -> return leftmost node of right subtree. */
        if (n.right != null) {
            return leftMostChild2(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            // Go up until we're on left instead of right
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    static TreeNode leftMostChild2(TreeNode n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }


}
