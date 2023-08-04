class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class B_S_T {
    private TreeNode root;

    B_S_T() {
        root = null;
    }

    public void insert(int data) {
        root = insertRecursively(root, data);
    }

    private TreeNode insertRecursively(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }

        if (data < root.data) {
            root.left = insertRecursively(root.left, data);
        } else if (data > root.data) {
            root.right = insertRecursively(root.right, data);
        } else {
            // This handles insertion of duplicate entries
            // You can choose to ignore duplicates or handle them as per your requirement
        }

        return root;
    }

    public void delete(int data) {
        root = deleteRecursively(root, data);
    }

    private TreeNode deleteRecursively(TreeNode root, int data) {
        if (root == null) {
            return null;
        }

        if (data < root.data) {
            root.left = deleteRecursively(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRecursively(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);

            
            root.right = deleteRecursively(root.right, root.data);
        }

        return root;
    }

    private int minValue(TreeNode root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public boolean search(int data) {
        return searchRecursively(root, data);
    }

    private boolean searchRecursively(TreeNode root, int data) {
        if (root == null) {
            return false;
        }

        if (data == root.data) {
            return true;
        } else if (data < root.data) {
            return searchRecursively(root.left, data);
        } else {
            return searchRecursively(root.right, data);
        }
    }

    public void displayTreeInOrder() {
        System.out.print("InOrder Traversal: ");
        displayInOrder(root);
        System.out.println();
    }

    private void displayInOrder(TreeNode root) {
        if (root != null) {
            displayInOrder(root.left);
            System.out.print(root.data + " ");
            displayInOrder(root.right);
        }
    }

    public void displayTreePreOrder() {
        System.out.print("PreOrder Traversal: ");
        displayPreOrder(root);
        System.out.println();
    }

    private void displayPreOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            displayPreOrder(root.left);
            displayPreOrder(root.right);
        }
    }

    public void displayTreePostOrder() {
        System.out.print("PostOrder Traversal: ");
        displayPostOrder(root);
        System.out.println();
    }

    private void displayPostOrder(TreeNode root) {
        if (root != null) {
            displayPostOrder(root.left);
            displayPostOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        B_S_T bst = new B_S_T();

        bst.insert(45);
        bst.insert(22);
        bst.insert(90);
        bst.insert(18);
        bst.insert(64);
        bst.insert(64);
        bst.insert(73);

        bst.displayTreeInOrder();  
        bst.displayTreePreOrder(); 
        bst.displayTreePostOrder();

        System.out.println("Search 64: " + bst.search(64)); 
        System.out.println("Search 90: " + bst.search(90));

        bst.delete(22);
        bst.displayTreeInOrder();  
    }
}
