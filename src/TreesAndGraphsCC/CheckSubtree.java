package TreesAndGraphsCC;

/*4.1O Check Subtree:Tl and T2 are two very large binary trees, withTl much bigger thanT2. Create an
algorithm to determine ifT2 is a subtree ofTl.
A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of n is identical to T2.
That is, if you cut off the tree at node n, the two trees would be identical.

      pp 276  */

public class CheckSubtree {

    /*The Simple Approach
In this smaller, simpler problem, we could consider comparing string representations of traversals of each
tree. lfT2 is a subtree ofTl, thenT2's traversal should be a substring ofTl. ls the reverse true?
 If so, should we use an in-order traversal or a pre-order traversal?
     */
    boolean containsTree(TreeNode tl, TreeNode t2) {
        StringBuilder stringl = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(tl, stringl);
        getOrderString(t2, string2);

        return stringl.indexOf(string2.toString()) != -1;
    }

    void getOrderString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb. append("X"); // Add null indicator
            return;
        }

        sb.append(node.value + " "); // Add root
        getOrderString(node.left, sb); // Add left
        getOrderString(node.right, sb); // Add right

    }



}
