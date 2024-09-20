import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {

    @Test
    @DisplayName("Rotate Right")
    void testRotateRight() {
        TestableRB t = new TestableRB();
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

        t.insertMultiple(values);

        assertTrue(t.isDesiredRBTree(values));
    }

    @Test
    @DisplayName("Check descendant paths condition")
    void checkDescendantPaths() {
        TestableRB t = new TestableRB();
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

        t.insertMultiple(values);
        t.root.left.color = Color.BLACK;

        assertFalse(t.isDesiredRBTree(values));
    }
}