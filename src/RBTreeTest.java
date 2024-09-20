import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {

    // Check to see that the implementation works as a BST
    @Test
    @Tag("hidden")
    @DisplayName("Root is nil")
    void rootIsNil() {
        RBTree t = new RBTreeSolution();
        assertTrue(RBUtils.isValidRBTree(t));
    }

    // Check rotate on insert
    @Test
    @DisplayName("Rotate Right")
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

    @Test
    @DisplayName("Check descendant paths condition")
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

        assertFalse(RBUtils.isDesiredRBTree(t, values));
    }
}