package TreesAndGraphsCC;

/*4.2 Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an
        algorithm to create a binary search tree with minimal height.
   The algorithm is as follows:
1. Insert into the tree the middle element of the array.
2. Insert (into the left subtree) the left subarray elements.
3. Insert (into the right subtree) the right subarray elements.
4. Recurse.
        */

import java.util.LinkedList;

public class MinimalTree {

    static TreeNode createMinimalBST(int array[]) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    static TreeNode createMinimalBST(int arr[], int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid]);
        n.left = createMinimalBST(arr, start, mid - 1);
        n.right = createMinimalBST(arr, mid + 1, end);
        System.out.println(""+n.value);
        return n;
    }


    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
        createMinimalBST(arr, 0, 10);

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

    static class Tree{
//        TreeNode root;
        LinkedList<TreeNode> list;
        public Tree(){
            list = new LinkedList<>();
        }
        public TreeNode getRoot(){
            return list.getFirst();
        }

        public void PrintTree(){

        }

    }
}
