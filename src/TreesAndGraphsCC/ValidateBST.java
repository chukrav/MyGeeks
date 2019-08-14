package TreesAndGraphsCC;

/*4.5 Validate BST: Implement a function to check if a binary tree is a binary search tree.
SOLUTION
We can implement this solution in two different ways. The first leverages the in-order traversal, and the
second builds off the property that left <= current < right.

Our first thought might be to do an in-order traversal, copy the elements to an array, and then check to see
if the array is sorted.
The only problem is that it can't handle duplicate values in the tree properly.
However, if we assume that the tree cannot have duplicate values, then this approach works.

      pp 256  */

public class ValidateBST {

    /*We've used an Integer instead of int so that we can know when last_printed has been set to a value.
If you don't like the use of static variables, then you can tweak this code to use a wrapper class for the
integer, as shown below.
     class Wraplnt {
        public int value;
     }  */

    public static Integer last_printed = null;
    public static boolean checkBST(TreeNode n) {
        if (n == null) return true;               // Check I recurse left
        if (!checkBST(n.left)) return false;     // Check current
        if (last_printed != null && n.value <= last_printed) {
            return false;
        }
        last_printed = n.value;  // Check / recurse right
        if (!checkBST(n.right)) return false;

        return true;// All good!
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

//        boolean isBst = checkBST(bst.getRoot());
//        System.out.println("Is BST: "+isBst);
        boolean isBst = checkBST2(bst.getRoot());
        System.out.println("Is BST2: "+isBst);   
    }

    /* Solution #2: The Min / Max Solution
    What does it mean for a tree to be a binary search tree? We know that it must, of course, satisfy the condition
    left. data <= current. data < right. data for each node, but this isn't quite sufficient.
    More precisely, the condition is that all left nodes must be less than or equal to the current node,
    which must be less than all the right nodes.
     */
    public static boolean checkBST2(TreeNode n) {
        return checkBST2(n, null, null);
    }

    public static boolean checkBST2(TreeNode n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }
        if ((min != null && n.value <= min) || (max != null && n.value > max)) {
            return false;
        }

        if (!checkBST2(n.left, min, n.value) || !checkBST2(n.right, n.value, max)) {
            return false;
        }
        return true;
    }


}
