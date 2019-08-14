package TreesAndGraphsCC;

public class TreeNode {
    int value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    TreeNode(int value) {
        this.value = value;
        right = null;
        left = null;
        parent = null;
    }

    TreeNode(TreeNode node) {
        this.value = node.value;
        right = null;
        left = null;
        parent = null;
    }

}
