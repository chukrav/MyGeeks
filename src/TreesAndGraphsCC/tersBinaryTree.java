package TreesAndGraphsCC;

/* Good tutorial: Trees (binary)
https://www.baeldung.com/java-binary-tree
 */

//import org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class tersBinaryTree {


    public static void main(String[] args) {

        BinaryTree bt = createBinaryTree();
//        assertTrue(bt.containsNode(9));
        System.out.println("  Contains 9: "+bt.containsNode(9));
//        bt.traverseInOrder(bt.getRoot());
        bt.traversePreOrder(bt.getRoot());
        System.out.println("----------");
        bt.traversePostOrder(bt.getRoot());
        bt.delete(9);
//        assertFalse(bt.containsNode(9));
        System.out.println("  Contains 9: "+bt.containsNode(9));
        bt.traverseInOrder(bt.getRoot());


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

        return bt;
    }

}
