import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    @Test
    @DisplayName("Sample Test")
    void sampleTest() {
        BST t1 = new BST();
        BST t2 = new BST();
        assertTrue(t1.equals(t2));
    }

    @Test
    @DisplayName("Non-Empty Tree")
    void nonEmptyTreeTest() {
        BST t1 = new BST();
        t1.insert(1);
        t1.insert(2);
        t1.insert(3);
        t1.insert(4);

        BST t2 = new BST();
        t2.insert(1);
        t2.insert(4);
        t2.insert(2);
        t2.insert(3);

        assertTrue(t1.equals(t2));
    }

    @Test
    @DisplayName("Trees Are Different")
    void treesAreDifferentTest() {
        BST t1 = new BST();
        t1.insert(1);
        t1.insert(2);
        t1.insert(3);
        t1.insert(4);
        t1.insert(9);

        BST t2 = new BST();
        t2.insert(1);
        t2.insert(4);
        t2.insert(2);
        t2.insert(3);

        assertFalse(t1.equals(t2));
    }

    @Test
    @DisplayName("Rotate Right")
    void testRotateRight() {
        GradeableRBT t = new GradeableRBT();
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
        values.sort(null);

        assertAll(
                () -> assertTrue(t.isValidRBTree()),
                () -> assertEquals(t.inOrderTraversal(), values)
            );
    }
}