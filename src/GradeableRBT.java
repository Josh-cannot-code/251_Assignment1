import java.util.ArrayList;
import java.util.Stack;

public class GradeableRBT extends RBTree{
    private int checkDescendantPaths(RBTNode node) {
       if (node == nil)  {
           return 0;
       }

       int rightPaths = checkDescendantPaths(node.right);
       int leftPaths = checkDescendantPaths(node.left);

       if (rightPaths == -1 || leftPaths == -1 || rightPaths != leftPaths) {
           return -1;
       } else {
           return rightPaths + (node.color == Color.BLACK ? 1 : 0);
       }

    }
    // TODO: maybe property 3 if allow for solutions not using nil
    public boolean isValidRBTree() {
        // Property 2
        if (root.color != Color.BLACK) {
            return false;
        }

        Stack<RBTNode> stack = new Stack<RBTNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            RBTNode node = stack.pop();
            if (node == nil) {
               continue;
            }

            // Property 1
            if (node.color != Color.BLACK && node.color != Color.RED) {
                return false;
            }

            // Property 4
            if (node.color == Color.RED && (node.left.color != Color.BLACK || node.right.color != Color.BLACK) ) {
                return false;
            }

        }

        // Property 5
        if (checkDescendantPaths(root) == -1) {
            return false;
        }

        return true;
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


    public boolean equals(GradeableRBT tree) {
        return this.inOrderTraversal().equals(tree.inOrderTraversal());
    }
}
