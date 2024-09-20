import java.util.ArrayList;
import java.util.Stack;

public class TestableRB extends RBTree{

   public void insertMultiple(ArrayList<Integer> values) {
       for (int value : values) {
           this.insert(value);
       }
   }

    private int checkDescendantPaths(RBTNode node) {
       if (node == nil)  {
           return 1;
       }

       int rightPaths = checkDescendantPaths(node.right);
       int leftPaths = checkDescendantPaths(node.left);

       if (rightPaths == -1 || leftPaths == -1 || rightPaths != leftPaths) {
           return -1;
       } else {
           return rightPaths + (node.color == Color.BLACK ? 1 : 0);
       }

    }

    public boolean isValidRBTree() {
        // Property 2: root node must be black
        if (root.color != Color.BLACK) {
            return false;
        }

        Stack<RBTNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            RBTNode node = stack.pop();
            if (node == nil) {
               continue;
            }

            // Property 1: each node must be either red or black
            if (node.color != Color.BLACK && node.color != Color.RED) {
                return false;
            }

            // Property 4: the children of a red node must be black
            if (node.color == Color.RED && (node.left.color != Color.BLACK || node.right.color != Color.BLACK) ) {
                return false;
            }

        }

        // Property 5: for each node, all paths from the node to descendant leaves contain the same number of black nodes
        if (checkDescendantPaths(root) == -1) {
            return false;
        }

        // TODO: Check property 3

        // Check that the RBTree is also a BST
        return isValidBST();
    }

    public ArrayList<Integer> inOrderTraversal() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        if (root == nil) {
            return values;
        }

        Stack<RBTNode> stack = new Stack<>();
        RBTNode curr = root;

        while (curr != nil || !stack.isEmpty()) {
            while (curr !=  nil) {
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
        if (root == nil) {
            return true;
        }

        // In order traversal, then check against sorted values
        ArrayList<Integer> values = this.inOrderTraversal();

        ArrayList<Integer> sortedValues = (ArrayList<Integer>) values.clone();
        sortedValues.sort(null);
        return values.equals(sortedValues);
    }

    public boolean isDesiredRBTree(ArrayList<Integer> values) {
       if (!this.isValidRBTree()) {
           return false;
       }
       ArrayList<Integer> copy_values = (ArrayList<Integer>) values.clone();
       copy_values.sort(null);
       return this.inOrderTraversal().equals(copy_values);
    }
}
