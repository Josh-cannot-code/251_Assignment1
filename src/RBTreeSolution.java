public class RBTreeSolution extends RBTree {

    public RBTreeSolution() {
        nil = new Node(0, null, Color.BLACK);
        root = nil;
    }

    public void insert(int val) {

        Node x = root;
        Node y = nil;

        while (x != nil) {
            y = x;
            if (val < x.val) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        Node z = new Node(val, y, Color.RED);

        if (y == nil) {
            root = z;
        } else if (val < y.val) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = nil;
        z.right = nil;
        rbInsertFixup(z);
    }

    private void rbInsertFixup(Node z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) { // z's parent is a left child
                Node unc = z.parent.parent.right;
                if (unc.color == Color.RED) {
                    // Case 1: move the color issue up two levels
                    z.parent.color = Color.BLACK;
                    unc.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        // Case 2
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else { // z's parent is a right child
                Node unc = z.parent.parent.left;
                if (unc.color == Color.RED) {
                    // Case 1: move the color issue up two levels
                    z.parent.color = Color.BLACK;
                    unc.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        // Case 2
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    private void rightRotate(Node z) {
        Node y = z.left;
        // Swap z with its left child
        y.parent = z.parent;
        if (z.parent.right == z) {
            z.parent.right = y;
        } else if (z.parent.left == z) { // If z in neither right nor left child, it must be the root
            z.parent.left = y;
        }
        z.left = y.right;
        z.left.parent = z;
        y.right = z;
        z.parent = y;

        if (z == root) {
            root = y;
        }
    }

    private void leftRotate(Node z) {
        Node y = z.right;
        // Swap z with its right child
        y.parent = z.parent;
        if (z.parent.right == z) { // If z in neither right nor left child, it must be the root
            z.parent.right = y;
        } else if (z.parent.left == z) {
            z.parent.left = y;
        }
        z.right = y.left;
        y.left.parent = z;
        z.parent = y;
        y.left = z;

        if (z == root) {
            root = y;
        }
    }

    public boolean isValidRBT() {
        return RBUtils.isValidRBTree(this);
    }
}
