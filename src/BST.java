import java.util.*;

@Deprecated
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


}
