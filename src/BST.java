import java.util.*;

public class BST {
    protected Node root;

    public static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }

    public void insert(int val) {

        Node x = root;
        Node y = x;

        while (x != null) {
            y = x;
            if (val < x.val) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        Node z = new Node(val);

        if (y == null) {
            this.root = z;
        } else if (val < y.val) {
            y.left = z;
        } else {
            y.right = z;
        }
    }


    public void delete(int val) {
        // TODO: Implement
    }


    public void insertMultiple(ArrayList<Integer> values) {
        for (int val : values) {
            this.insert(val);
        }
    }


    public ArrayList<Integer> inOrderTraversal() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        if (root == null) {
           return values;
       }

        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr !=  null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            values.add(curr.val);
            curr = curr.right;
        }

        return values;
    }

    private boolean isValidBST() {
        if (root == null) {
            return true;
        }

        // In order traversal, then check against sorted values
        ArrayList<Integer> values = this.inOrderTraversal();

        ArrayList<Integer> sortedValues = (ArrayList<Integer>) values.clone();
        sortedValues.sort(null);
        return values.equals(sortedValues);
    }


    public boolean equals(BST tree) {
        return this.inOrderTraversal().equals(tree.inOrderTraversal());
    }
}
