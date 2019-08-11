package TreesAndGraphsCC;

/*4.3 List of Depths: Given a binary tree, design an algorithm which creates a linked list of all
the nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
SOLUTION
Though we might think at first glance that this problem requires a level-by-level traversal,
this isn't actually necessary. We can traverse the graph any way that we'd like,
 provided we know which level we're on as we do so. We can implement a simple modification of
 the pre-order traversal algorithm, where we pass in level + 1 to the next recursive call.
    pp 254    */

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;

public class ListOfDepths {

     void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
         if (root== null) return; // base case

         LinkedList<TreeNode> list = null;
         if (lists.size()== level) { // Level not contained in list
            list = new LinkedList<TreeNode>();
            /* Levels are always traversed in order. So, if this is the first time we've
            * visited level i, we must have seen levels 0 through i - 1. We can
             * therefore safely add the level at the end. */
             lists.add(list);
             } else {
             list = lists.get(level);

             }
         list.add(root);
         createLevelLinkedList(root.left, lists, level + 1);
         createLevelLinkedList(root.right, lists, level + 1);
    }


    ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
         ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
         createLevelLinkedList(root, lists, 0);
         return lists;
         }

    public static void main(String[] args) {

    }

    static class TreeNode{
         int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            value = val;
            left = null;
            right = null;
        }
    }
}
