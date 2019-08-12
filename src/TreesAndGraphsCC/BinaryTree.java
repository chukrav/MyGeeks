package TreesAndGraphsCC;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private TreeNode root;

    public BinaryTree(){
        root = null;
    }

    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public TreeNode getRoot() {
        return root;
    }

    private boolean containsNodeRecursive(TreeNode current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    /*Once we find the node to delete, there are 3 main different cases:

    a node has no children – this is the simplest case; we just need to replace this node with null
     in its parent node.
    a node has exactly one child – in the parent node, we replace this node with its only child.
    a node has two children – this is the most complex case because it requires a tree reorganization
    */
    private TreeNode deleteCurNode(TreeNode current){
        if (current.left == null && current.right == null) { // No children.
            return null;
        }
//  Case when the node has one child: ------------
        if (current.right == null && current.left != null) {
//        if (current.right == null) {
            return current.left;
        }
        if (current.left == null && current.right != null) {
            return current.right;
        }
//   --------------------------------------------------
/*        Case where the node has two children.
First, we need to find the node that will replace the deleted node.
 We’ll use the smallest node of the node to be deleted’s right sub-tree:
 */
        int smallestValue = findSmallestValue(current.right);
        current.value = smallestValue;
        current.right = deleteRecursive(current.right, smallestValue);
        return current;
    }

    private int findSmallestValue(TreeNode root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    //Finally, let’s create the public method that starts the deletion from the root:
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

     private TreeNode deleteRecursive(TreeNode current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            // Node to delete found
            // ... code to delete the node will go here
            current = deleteCurNode(current);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }
/* Depth-First Search
The in-order traversal consists of first visiting the left sub-tree, then the root node,
and finally the right sub-tree:
tree: 6 4 8 3 5 7 9 from root down (left-to-right)
out: 3 4 5 6 7 8 9
 */
    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    /* Pre-order traversal visits first the root node, then the left subtree, and finally
     the right subtree:
     out: 6 4 3 5 8 7 9
      */
    public void traversePreOrder(TreeNode node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    /* Post-order traversal visits the left subtree, the right subtree, and the root node at the end:
    out: 3 5 4 7 9 8 6
     */
    public void traversePostOrder(TreeNode node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.value);
        }
    }

    /*4.2. Breadth-First Search
    This is another common type of traversal that visits all the nodes of a level
     before going to the next level. This kind of traversal is also called level-order
      and visits all the levels of the tree starting from the root, and from left to right.
      out: 6 4 8 3 5 7 9
    */

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            TreeNode node = nodes.remove();

            System.out.print(" " + node.value);

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right!= null) {
                nodes.add(node.right);
            }
        }
    }
}
