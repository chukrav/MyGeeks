package TreesAndGraphsCC;

/*4.11 Random Node: You are implementing a binary search tree class from scratch, which, in addition
to insert, find, and delete, has a method getRandomNode() which returns a random node
from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
for getRandomNode, and explain how you would implement the rest of the methods.

      pp 279  */

import java.util.Random;

public class RandomNode {

    /*Option #1 [Slow & Working]
One solution is to copy all the nodes to an array and return a random element in the array.
This solution will take O(N) time and O(N) space, where N is the number of nodes in the tree.
We can guess our interviewer is probably looking for something more optimal, since this is
 a little too straightforward (and should make us wonder why the interviewer gave us a binary tree,
  since we don't need that information).
We should keep in mind as we develop this solution that we probably need to know something about
 the internals of the tree. Otherwise, the question probably wouldn't specify that we're
 developing the tree class from scratch. Get another 4 solution in the book: slow or not working :)
...
Option #6 [Fast & Working]
Rather than just continuing to brainstorm new solutions, let's see if we can fix some of
 the issues in the previous solutions. To do so, we must diagnose-deeply-the root problem in
  a solution.
  We can start with the root. With what probability should we return the root? Since we have N nodes, we
must return the root node with X probability. (In fact, we must return each node with X probability.
After all, we have N nodes and each must have equal probability. The total must be 1 (100%), therefore each
must have X probability.)
We've resolved the issue with the root. Now what about the rest of the problem? With what
probability should we traverse left versus right? It's not 50/50. Even in a balanced tree,
the number of nodes on each side might not be equal. If we have more nodes on the left than
 the right, then we need to go left more often. The odds of picking something from the left
 must have probability LEFT_SIZE * X.This should therefore be the odds of going left.
Likewise, the odds of going right should be RIGHT_SIZE * X.
This means that each node must know the size of the nodes on the left and the size of the nodes
 on the right. Fortunately, our interviewer has told us that we're building this tree class
 from scratch. It's easy to keep track of this size information on inserts and deletes. We can
  just store a size variable in each node. Increment size on inserts and decrement it on deletes.

     */
    static class TreeNode {
        private int data;
        public TreeNode left;
        public TreeNode right;
        private int size = 0; // All nodes under

        public TreeNode(int d) {
            data = d;
            size = 1;
        }

        public TreeNode getRandomNode() {
            int leftSize = left == null? 0: left.size();
            Random random = new Random();
            int index = random.nextInt(size);
            if (index < leftSize) {
                return left.getRandomNode();
            } else if (index == leftSize) {
                return this;
            } else {
                return right.getRandomNode();
            }
        }

        public TreeNode getIthNode(int i) {
            int leftSize = left == null? 0: left.size();
            if (i < leftSize) {
                return left.getIthNode(i);
            } else if (i == leftSize) {
                return this;
            } else {
                /* Skipping over leftSize + 1 nodes, so subtract them. */
                return right.getIthNode(i - (leftSize + 1));
            }
        }

        public void insertinOrder(int d) {
            if (d <= data) {
                if (left == null) {
                    left = new TreeNode(d);
                } else {
                    left.insertinOrder(d);
                }
            } else {
                if (right == null) {
                    right =new TreeNode(d);
                } else {
                    right.insertinOrder(d);
                }
            }
            size++;
        }

        public int size() { return size;}
        public int data() { return data;}

        public TreeNode find(int d) {
            if (d == data) {
                return this;
            } else if (d < data) {
                return left != null? left.find(d) : null;
            } else if (d > data) {
                return right != null ? right.find(d) : null;
            }
            return null;
        }
    }

    /*Option #7 [Fast & Working]
Random number calls can be expensive. If we'd like, we can reduce the number of random number
 calls substantially.
BSTree: 20, 10, 30, 5, 15, 35, 3, 7, 17
We traversed left because we picked a number between O and 5 (inclusive). When we traverse
 left, we again pick a random number between O and 5. Why re-pick? The first number will
 work just fine. But what if we went right instead? We have a number between 7 and
  8 (inclusive) but we would need a number between O and 1 (inclusive).
   That's easy to fix:just subtract out LEFT_SIZE + 1.
  Another way to think about what we're doing is that the initial random number call indicates
  which node (i) to return, and then we're locating the ith node in an in-order traversal.
  Subtracting LEFT_SIZE + 1 from i reflects that, when we go right, we skip over LEFT_SIZE + 1
  nodes in the in-order traversal.
     */

    static class Tree {
        TreeNode root = null;

        public int size() { return root == null ? 0: root.size(); }
        public TreeNode getRandomNode() {
            if (root == null) return null;

            Random random = new Random();
            int i= random.nextInt(size());
            return root.getIthNode(i);
//            return root.getRandomNode();
        }


        public void insertInOrder(int value) {
            if (root == null) {
                root = new TreeNode(value);
            } else {
                root.insertinOrder(value);
            }
        }
    }

    //BSTree: 20, 10, 30, 5, 15, 35, 3, 7, 17
    public static void main(String[] args) {
        Tree tree = new Tree();
        buildTree(tree);
        TreeNode random = tree.getRandomNode();
        System.out.println("random node: "+random.data+", sz: "+random.size);

    }

    public static void buildTree(Tree tree){
        tree.insertInOrder(20);
        tree.insertInOrder(10);
        tree.insertInOrder(30);
        tree.insertInOrder(5);
        tree.insertInOrder(15);
        tree.insertInOrder(35);
        tree.insertInOrder(3);
        tree.insertInOrder(7);
        tree.insertInOrder(17);
    }

}





