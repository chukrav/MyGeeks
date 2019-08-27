package TreesAndGraphsCC;

/*4.12 Paths with Sum: You are given a binary tree in which each node contains an integer value (which
might be positive or negative). Design an algorithm to count the number of paths that sum to a
given value. The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).
        pp 283;

SOLUTION
Let's pick a potential sum-say, 8-and then draw a binary tree based on this. This tree intentionally has a
.number of paths with this sum.

 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PathsWithSum {

    static class TreeNode {
        private int data;
        public TreeNode left;
        public TreeNode right;
        //private int size = 0; // All nodes under

        public TreeNode(int d) {
            data = d;
//            size = 1;
        }

        public void insertInLeft(int d) {
            left = new TreeNode(d);
        }

        public void insertInRight(int d) {
            right = new TreeNode(d);
        }


    }

    static class Tree {
        TreeNode root = null;

        public void traverseLevelOrder() {
            if (root == null) {
                System.out.println("root is null");
                return;
            }

            Queue<TreeNode> nodes = new LinkedList<>();
            nodes.add(root);

            while (!nodes.isEmpty()) {

                TreeNode node = nodes.remove();

                System.out.print(" " + node.data);

                if (node.left != null) {
                    nodes.add(node.left);
                }

                if (node.right!= null) {
                    nodes.add(node.right);
                }
            }
        }

    }

    /*Solution #1: Brute Force
In the brute force approach, we just look at all possible paths. To do this, we traverse to each node. At each
node, we recursively try all paths downwards, tracking the sum as we go. As soon as we hit our target sum,
we increment the total.
Therefore, the runtime is O(N log N) in a balanced tree.
     */
    static int countPathsWithSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        /* Count paths with sum starting from the root. */
        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
        /* Try the nodes on the left and right. */
        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);

        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    /* Returns the number of paths with this sum starting from this node. */
    static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
        if (node == null) return 0;

        currentSum += node.data;

        int totalPaths = 0;
        if (currentSum == targetSum) { // Found a path from the root
            totalPaths++;
        }

        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
        return totalPaths;
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        buildTree(tree);
        tree.traverseLevelOrder();

        int targetSum = 8;
//        int sum = countPathsWithSum(tree.root, targetSum);
        int sum = countPathsWithSum2(tree.root, targetSum);
        System.out.println("\nsum: "+ sum);

    }

    public static void buildTree(Tree tree){
        //tree = new Tree();
        tree.root = new TreeNode(10);
        tree.root.insertInLeft(5);
        tree.root.insertInRight(-3);
//        ---------------------
        tree.root.left.insertInLeft(3);  // 5
        tree.root.left.insertInRight(2); // 5
        tree.root.left.left.insertInLeft(3);  //3
        tree.root.left.left.insertInRight(-2);
        tree.root.left.right.insertInRight(1);
//        ------------------------
        tree.root.right.insertInRight(11);

    }

    /*Solution #2: Optimized
In analyzing the last solution, we may realize that we repeat some work. For a path such as 10 -> 5 ->
3 -> -2, we traverse this path (or parts of it) repeatedly. We do it when we start with node 10, then when
we go to node 5 (looking at 5, then 3, then -2), then when we go to node 3, and then finally when we go to
node -2. Ideally, we'd like to reuse this work.
The runtime for this algorithm is O(N), where N is the number of nodes in the tree. We know it is O(N)
because we travel to each node just once, doing 0(1) work each time. In a balanced tree, the space
complexity is O ( log N) due to the hash table.The space complexity can grow to 0(n) in an unbalanced
tree.
     */

    static int countPathsWithSum2(TreeNode root, int targetSum) {
        return countPathsWithSum2(root, targetSum, 0, new HashMap<Integer, Integer>());
    }

    static int countPathsWithSum2(TreeNode node, int targetSum, int runningSum,
                                  HashMap<Integer, Integer> pathCount) {
        if (node == null) return 0; // Base case

        /* Count paths with sum ending at the current node. */
        runningSum += node.data;
        int sum = runningSum - targetSum;
        int totalPaths = pathCount.getOrDefault(sum, 0);

        /* If runningSum equals targetSum, then one additional path starts at root.
         * Add in this path.*/
        if (runningSum == targetSum) {
            totalPaths++;
        }

        /* Increment pathCount, recurse, then decrement pathCount. */
        incrementHashTable(pathCount, runningSum, 1); // Increment pathCount
        totalPaths += countPathsWithSum2(node.left, targetSum, runningSum, pathCount);
        totalPaths += countPathsWithSum2(node.right, targetSum, runningSum, pathCount);
        incrementHashTable(pathCount, runningSum, -1); // Decrement pathCount

        return totalPaths;
    }

    static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta) {
        int newCount = hashTable.getOrDefault(key, 0) + delta;
        if (newCount == 0) {//Remove when zero to reduce space usage
            hashTable.remove(key);
        } else {
            hashTable.put(key, newCount);
        }
    }


}
