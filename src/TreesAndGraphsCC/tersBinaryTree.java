package TreesAndGraphsCC;

/* Good tutorial: Trees (binary)
https://www.baeldung.com/java-binary-tree
 */

//import org.junit.Assert.assertTrue;

public class tersBinaryTree {


    public static void main(String[] args) {

        BinaryTree bt = createBinaryTree();
//        assertTrue(bt.containsNode(9));
        System.out.println("  Contains 9: "+bt.containsNode(9));
//        bt.traverseInOrder(bt.getRoot());
        bt.traversePreOrder(bt.getRoot());
        System.out.println("----------");
        bt.traversePostOrder(bt.getRoot());
        //bt.delete(9);
//        assertFalse(bt.containsNode(9));
        //System.out.println("  Contains 9: "+bt.containsNode(9));
        bt.traverseInOrder(bt.getRoot());

        int height = bt.treeHeight(bt.getRoot());
        System.out.println("\nheight: "+height);
        int heightR = bt.getHeight(bt.getRoot());
        System.out.println("height recursive: "+ heightR);


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
        bt.add(15);

        return bt;
    }

}
