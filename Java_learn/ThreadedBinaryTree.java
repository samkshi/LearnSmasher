class TreeNode {
    int data;
    TreeNode left, right;
    boolean leftThread, rightThread;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.leftThread = false;
        this.rightThread = false;
    }
}

public class ThreadedBinaryTree {
    private TreeNode root;

    public ThreadedBinaryTree() {
        root = null;
    }

    // Helper method to find the node that comes after the given node in an in-order traversal
    private TreeNode findInOrderSuccessor(TreeNode node) {
        if (node.rightThread)
            return node.right;
        node = node.right;
        while (!node.leftThread)
            node = node.left;
        return node;
    }

    // Helper method to find the node that comes before the given node in an in-order traversal
    private TreeNode findInOrderPredecessor(TreeNode node) {
        if (node.leftThread)
            return node.left;
        node = node.left;
        while (!node.rightThread)
            node = node.right;
        return node;
    }

    // Insert a new node into the threaded binary tree
    public void insert(int data) {
        TreeNode newNode = new TreeNode(data);

        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode current = root;
        TreeNode parent;

        while (true) {
            parent = current;
            if (data < current.data) {
                if (!current.leftThread)
                    current = current.left;
                else
                    break;
            } else {
                if (!current.rightThread)
                    current = current.right;
                else
                    break;
            }
        }

        if (data < parent.data) {
            newNode.left = parent.left;
            newNode.right = parent;
            parent.leftThread = false;
            parent.left = newNode;
        } else {
            newNode.left = parent;
            newNode.right = parent.right;
            parent.rightThread = false;
            parent.right = newNode;
        }
    }

    // Perform in-order traversal of the threaded binary tree
    public void inOrderTraversal() {
        TreeNode current = root;
        while (current != null) {
            current = findInOrderSuccessor(current);
            if (current != null)
                System.out.print(current.data + " ");
        }
        System.out.println();
    }

    // Perform pre-order traversal of the threaded binary tree
    public void preOrderTraversal() {
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.data + " ");
            if (!current.leftThread)
                current = current.left;
            else if (!current.rightThread)
                current = current.right;
            else
                break;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();

        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println("In-order traversal:");
        tree.inOrderTraversal();

        System.out.println("Pre-order traversal:");
        tree.preOrderTraversal();
    }
}
