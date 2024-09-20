import java.util.ArrayList;
import java.util.Stack;

public class RBUtils {

   public static void insertMultiple(RBTree t, ArrayList<Integer> values) {
       for (int value : values) {
           t.insert(value);
       }
   }

    private static int checkDescendantPaths(RBTree t, RBTree.Node node) {
       if (node == t.nil)  {
           return 1;
       }

       int rightPaths = checkDescendantPaths(t, node.right);
       int leftPaths = checkDescendantPaths(t, node.left);

       if (rightPaths == -1 || leftPaths == -1 || rightPaths != leftPaths) {
           return -1;
       } else {
           return rightPaths + (node.color == Color.BLACK ? 1 : 0);
       }

    }

    public static boolean isValidRBTree(RBTree t) {
        // Property 2: root node must be black
        if (t.root.color != Color.BLACK) {
            return false;
        }

        Stack<RBTree.Node> stack = new Stack<>();
        stack.push(t.root);
        while (!stack.isEmpty()) {
            RBTree.Node node = stack.pop();
            if (node == t.nil) {
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
        if (checkDescendantPaths(t, t.root) == -1) {
            return false;
        }

        // TODO: Check property 3

        // Check that the RBTree is also a BST
        return isValidBST(t);
    }

    public static ArrayList<Integer> inOrderTraversal(RBTree t) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        if (t.root == t.nil) {
            return values;
        }

        Stack<RBTree.Node> stack = new Stack<>();
        RBTree.Node curr = t.root;

        while (curr != t.nil || !stack.isEmpty()) {
            while (curr !=  t.nil) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            values.add(curr.val);
            curr = curr.right;
        }

        return values;
    }

    private static boolean isValidBST(RBTree t) {
        if (t.root == t.nil) {
            return true;
        }

        // In order traversal, then check against sorted values
        ArrayList<Integer> values = inOrderTraversal(t);

        ArrayList<Integer> sortedValues = (ArrayList<Integer>) values.clone();
        sortedValues.sort(null);
        return values.equals(sortedValues);
    }

    public static boolean isDesiredRBTree(RBTree t, ArrayList<Integer> values) {
       if (!isValidRBTree(t)) {
           return false;
       }
       ArrayList<Integer> copy_values = (ArrayList<Integer>) values.clone();
       copy_values.sort(null);
       return inOrderTraversal(t).equals(copy_values);
    }
}
