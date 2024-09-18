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
}
