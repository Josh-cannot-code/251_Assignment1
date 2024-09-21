import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {

    private RBTree constructTree() {
        return new RBTreeSolution(); // Change this to use MyRBTree or RBTreeSolution
    }

    // @DisplayName("Insert Tests")

    // Check to see that the implementation works as a BST
    @Test
    @DisplayName("Root is nil")
    void rootIsNil() {
        RBTree t = constructTree();
        assertTrue(RBUtils.isValidRBTree(t));
    }

    // Check that only insert, and checkValid methods are visible
    @Test
    @DisplayName("Check Methods")
    void checkMethods() {
        RBTree t = constructTree();

        ArrayList<java.lang.reflect.Method> methods = new ArrayList<>(List.of(t.getClass().getDeclaredMethods()));
        methods.removeIf(m -> m.getModifiers() != Modifier.PUBLIC);
        assertEquals(2, methods.size());
    }

    // Check insert no rotation
    @Test
    @DisplayName("Basic Insert")
    void basicInsert() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(3);
        values.add(5);
        values.add(1);

        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }

    // Check rotate on right subtree
    @Test
    @DisplayName("Rotate Right Subtree")
    void testRotateRight() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(7);
        values.add(3);
        values.add(18);
        values.add(10);
        values.add(20);
        values.add(8);
        values.add(11);
        values.add(22);
        values.add(15);

        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }

    // Check rotate on left subtree
    @Test
    @DisplayName("Rotate Left Subtree")
    void testRotateLeft() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(30);
        values.add(32);
        values.add(18);
        values.add(22);
        values.add(9);
        values.add(4);
        values.add(20);
        values.add(24);
        values.add(19);

        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }

    // @DisplayName("Check Valid RBT Tests")

    @Test
    @DisplayName("Check descendent paths condition")
    void checkDescendantPaths() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(7);
        values.add(3);
        values.add(18);
        values.add(10);
        values.add(20);
        values.add(8);
        values.add(11);
        values.add(22);
        values.add(15);

        RBUtils.insertMultiple(t, values);
        t.root.left.color = Color.BLACK;

        assertFalse(t.isValidRBT());
    }
}