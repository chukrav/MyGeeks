package TreesAndGraphsCC;

/*4.4 Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
node never differ by more than one.

    pp 255
*/

public class CheckBalanced {
    /*Although this works. it's not very efficient. On each node. we recurse through its entire subtree. This means
    that getHeight is called repeatedly on the same nodes.The algorithm isO(N log N) since each node is
    "touched"once per node above it.
    */

    public static int getHeight(TreeNode root) {
        if (root == null) return 0;// Base case was return -1; !!!
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;// Base case
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {//Recurse
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree bt = createBinaryTree();
        boolean balanced  = isBalanced(bt.getRoot());
        System.out.println("is balanced: "+balanced);

        balanced = isBalanced2(bt.getRoot());
        System.out.println("is balanced2: "+balanced);
    }

/*We need to cut out some of the calls to getHeight.
If we inspect this method, we may notice that getHeight could actually check if the tree is balanced at
the same time as it's checking heights. What do we do when we discover that the subtree isn't balanced?
Just return an error code.
This improved algorithm works by checking the height of each subtree as we recurse down from the root.
On each node, we recursively get the heights of the left and right subtrees through the checkHeight
method. If the subtree is balanced, then checkHeight will return the actual height of the subtree. If the
subtree is not balanced, then checkHeight will return an error code. We will immediately break and
return an error code from the current call.
For error code used: Integer.MIN_VALUE
This code runs in O(N) time andO(H) space, where H is the height of the tree.  !!!
*/

    public static int checkHeight(TreeNode root) {
        if (root == null) return 0;  //-1
        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Pass error up
        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Pass error up
        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // Found error -> pass it back
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static boolean isBalanced2(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }




    public static BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);
        bt.add(10);
        //bt.add(15);

        return bt;
    }
}
