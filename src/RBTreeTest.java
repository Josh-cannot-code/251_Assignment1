import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RBTreeTest {
    // TODO:
    // Make lots of inserts smaller
    // add weights
    // No rotation fixup
    // Names
    private RBTree constructTree() {
        return new RBTreeSolution(); // Change this to use MyRBTree or RBTreeSolution
    }

    @DisplayName("Code Correctness: 1")
    @Tag("score:1")
    @Test
    void rootIsNil() {
        RBTree t = constructTree();
        assertTrue(RBUtils.isValidRBTree(t));
    }

    // Check that only insert, and checkValid methods are visible
    @DisplayName("Code Correctness: 2")
    @Tag("score:1")
    @Test
    void checkMethods() {
        RBTree t = constructTree();

        ArrayList<java.lang.reflect.Method> methods = new ArrayList<>(List.of(t.getClass().getDeclaredMethods()));
        methods.removeIf(m -> m.getModifiers() != Modifier.PUBLIC);
        assertEquals(2, methods.size());
    }

    // No rotate or fixing
    @DisplayName("Insert: 1")
    @Tag("score:1")
    @Test
    void basicInsert1() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(3);
        values.add(5);
        values.add(1);

        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }

   // Handles negative
    @DisplayName("Insert: 2")
    @Tag("score:1")
    @Test
    void basicInsert2() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(300);
        values.add(-5);
        values.add(400);
        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }


    // Inserts maintaining balance
    @DisplayName("Insert: 3")
    @Tag("score:1")
    @Test
    void basicInsert3() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(10);

        values.add(5);
        values.add(15);

        values.add(7);
        values.add(3);
        values.add(13);
        values.add(17);

        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }

    // all negative
    @DisplayName("Insert: 4")
    @Tag("score:1")
    @Test
    void basicInsert4() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(-10);

        values.add(-5);
        values.add(-15);

        values.add(-7);
        values.add(-3);
        values.add(-13);
        values.add(-17);

        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }

    // From the slides pre rotate
    @DisplayName("Insert: 5")
    @Tag("score:1")
    @Test
    void basicInsert5() {
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

        RBUtils.insertMultiple(t, values);

        assertTrue(RBUtils.isDesiredRBTree(t, values));
    }

    // Check rotate on right subtree
    @DisplayName("Insert: 6")
    @Tag("score:2")
    @Test
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
    @DisplayName("Insert: 7")
    @Tag("score:2")
    @Test
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


    @DisplayName("Insert: 8")
    @Tag("score:2")
    @Test
    void lotsOfInserts() {
        RBTree t1 = constructTree();
        RBTree t2 = constructTree();
        RBTree t3 = constructTree();

        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
           values.add(i);
        }

        RBUtils.insertMultiple(t1, values);
        RBUtils.insertMultiple(t2, new ArrayList<>(values.reversed()));
        Collections.shuffle(values, new Random(16180339));
        RBUtils.insertMultiple(t3, values);

        assertAll(
                () -> assertTrue(RBUtils.isDesiredRBTree(t1, values)),
                () -> assertTrue(RBUtils.isDesiredRBTree(t2, values)),
                () -> assertTrue(RBUtils.isDesiredRBTree(t3, values))
        );
    }

    @DisplayName("Time Complexity")
    @Tag("score:5")
    @Test
    void testTimeComplexity() {

        ArrayList<Integer> values = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            values.add(i);
        }

        ArrayList<Long> times = new ArrayList<>();
        ArrayList<Long> sol_times = new ArrayList<>();

        RBTree t = constructTree();
        RBTree t_sol = new RBTreeSolution();
        for (int i = 0; i < 10; i++) {
            t = constructTree();
            t_sol = new RBTreeSolution();

            long start = System.nanoTime();
            RBUtils.insertMultiple(t, values);
            long end = System.nanoTime();
            times.add(end - start);

            long start_sol = System.nanoTime();
            RBUtils.insertMultiple(t_sol, values);
            long end_sol = System.nanoTime();
            sol_times.add(end_sol - start_sol);
        }

        long avg = 0;
        long avg_sol = 0;
        for (int i = 0; i < 10; i++) {
            avg += times.get(i);
            avg_sol += sol_times.get(i);
        }
        avg = avg / 10;
        avg_sol = avg_sol / 10;


        long finalAvg = avg;
        long finalAvg_sol = avg_sol;

        boolean same_complexity = Math.abs(finalAvg - finalAvg_sol) < 500000;

        RBTree finalT_sol = t_sol;
        RBTree finalT = t;
        assertAll(
                () -> assertTrue(RBUtils.isDesiredRBTree(finalT_sol, values)),
                () -> assertTrue(RBUtils.isDesiredRBTree(finalT, values)),
                () -> assertTrue(same_complexity)
        );
    }

    @DisplayName("IsValidRBT: 1")
    @Tag("score:1")
    @Test
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

    @DisplayName("IsValidRBT: 2")
    @Tag("score:1")
    @Test
    void checkBlackRoot() {
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
        t.root.color = Color.RED;

        assertFalse(t.isValidRBT());
    }

    @DisplayName("IsValidRBT: 3")
    @Tag("score:1")
    @Test
    void checkRedNodeRedChild() {
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

        RBUtils.insertMultiple(t, values);
        t.root.right.left.color = Color.RED;

        assertFalse(t.isValidRBT());
    }


    @DisplayName("IsValidRBT: 4")
    @Tag("score:1")
    @Test
    void checkNilBlack() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(7);

        RBUtils.insertMultiple(t, values);
        t.root.right.color = Color.RED;

        assertFalse(t.isValidRBT());
    }


    @DisplayName("IsValidRBT: 5")
    @Tag("score:1")
    @Test
    void checkNodesRedOrBlack() {
        RBTree t = constructTree();
        ArrayList<Integer> values = new ArrayList<>();

        values.add(7);

        RBUtils.insertMultiple(t, values);
        t.root.color = null;

        assertFalse(t.isValidRBT());
    }
}