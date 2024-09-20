public class RBTree {
    // Maybe leave this entire class empty
    final RBTNode nil;
    RBTNode root;

    public static class RBTNode {
        Color color;
        RBTNode left, right;
        RBTNode parent;
        int val;

        public RBTNode(int val, RBTNode parent, Color color) {
            this.val = val;
            this.parent = parent;
            this.color = color;
        }
    }

    public RBTree() {
        nil = new RBTNode(0, null, Color.BLACK);
        root = nil;
    }

    public void insert(int val) {
        // TODO: Your implementation here

        // Solution:
        RBTNode x = root;
        RBTNode y = nil;

        while (x != nil) {
            y = x;
            if (val < x.val) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        RBTNode z = new RBTNode(val, y, Color.RED);

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

    // This will not be given in the assignment
    private void rbInsertFixup(RBTNode z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) { // z's parent is a left child
                RBTNode unc = z.parent.parent.right;
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
                RBTNode unc = z.parent.parent.left;
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

    private void rightRotate(RBTNode z) {
        RBTNode y = z.left;
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

    private void leftRotate(RBTNode z) {
        RBTNode y = z.right;
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
}
