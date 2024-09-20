import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {

    // @DisplayName("Insert Tests")

    // Check to see that the implementation works as a BST
    @Test
    @Tag("hidden")
    @DisplayName("Root is nil")
    void rootIsNil() {
        RBTree t = new RBTreeSolution();
        assertTrue(RBUtils.isValidRBTree(t));
    }

    // Check that only insert, and checkValid methods are visible
    @Test
    @Tag("hidden")
    @DisplayName("Check Methods")
    void checkMethods() {
        RBTree t = new RBTreeSolution();

        ArrayList<java.lang.reflect.Method> methods = new ArrayList<>(List.of(t.getClass().getDeclaredMethods()));
        methods.removeIf(m -> m.getModifiers() != Modifier.PUBLIC);
        assertEquals(2, methods.size());
    }

    // Check rotate on right subtree
    @Test
    @DisplayName("Rotate Right Subtree")
    void testRotateRight() {
        RBTree t = new RBTreeSolution();
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
        RBTree t = new RBTreeSolution();
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
        RBTree t = new RBTreeSolution();
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