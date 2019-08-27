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
 This approach takes O(n + m) time and O(n + m) space, where n and mare the number
  of nodes inTl and T2, respectively. Given millions of nodes, we might want to reduce
   the space complexity.
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

    /*The Alternative Approach
An alternative approach is to search through the larger tree, Tl. Each time a node in Tl
 matches the root of T2, call matchTree.The matchTree method will compare the two
 subtrees to see if they are identical.
Analyzing the runtime is somewhat complex. A naive answer would be to say that it is O(nm)
 time, where n is the number of nodes in Tl and mis the number of nodes in T2.
  While this is technically correct, a little more thought can produce a tighter bound.
  We do not actually call matchTree on every node in Tl. Rather, we call it k times,
   where k is the number of occurrences ofT2's root inTl.The runtime is closer too(n + km).
     */

    boolean containsTree2(TreeNode tl, TreeNode t2) {    // T2 is subtree
        if (t2 == null) return true; // The empty tree is always a subtree
        return subTree(tl, t2);
    }

    boolean subTree(TreeNode rl, TreeNode r2) {
        if (rl == null) {
            return false; // big tree empty & subtree still not found.
        } else if (rl.value == r2.value && matchTree(rl, r2)) {
            return true;
        }
        return subTree(rl.left, r2) || subTree(rl.right, r2);
    }

    boolean matchTree(TreeNode rl, TreeNode r2) {
        if (rl == null && r2 == null) {
            return true; // nothing left in the subtree
        } else if (rl == null || r2 == null) {
            return false; // exactly tree is empty, therefore trees don't match
        } else if (rl.value != r2.value) {
            return false; // data doesn't match
        } else {
            return matchTree(rl.left, r2.left) && matchTree(rl.right, r2.right);
        }
    }

    public static void main(String[] args) {

    }



}
