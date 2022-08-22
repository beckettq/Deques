package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTests {
    @Test
    public void testNoResize() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            a.addLast(i + 1);
        }
        int num = a.removeLast();
        assertEquals(5, num);
        a.printDeque();
    }

    @Test
    public void removeAndGet() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            a.addLast(2);
        }
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.addLast(4);
        a.printDeque();
        int received = a.get(0);
        int expected = 2;
        assertEquals(received, expected);
        received = a.get(1);
        expected = 4;
        assertEquals(received, expected);
    }

    @Test
    public void testOneGrow() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        LinkedListDeque<Integer> l = new LinkedListDeque<>();
        for (int i = 0; i < 8; i++) {
            a.addLast(8);
            l.addLast(8);
        }
        a.addLast(9);
        l.addLast(9);
        a.printDeque();
        for (int i = 0; i < 9; i++) {
            int num = a.get(i);
            int num2 = l.get(i);
            assertEquals(num, num2);
        }
    }

    @Test
    public void testShrink() {
        Integer x = 100;
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (Integer i = 0; i < 100; i++) {
            a.addLast(x);
        }
        assertEquals(a.size(), 100);
        for (Integer i = 0; i < 78; i++) {
            a.removeFirst();
        }
        a.addLast(x);
        a.printDeque();
        Integer num = a.get(22);
        assertEquals(num, x);
        assertEquals(a.size(), 23);
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        assertTrue(a.equals(b));
        for (int i = 1; i < 10; i++) {
            a.addLast(i);
            b.addLast(i);
        }
        assertTrue(a.equals(b));
    }

    @Test
    public void randTest() {
        int N = 10000;
        LinkedListDeque<Integer> good = new LinkedListDeque<>();
        ArrayDeque<Integer> bad = new ArrayDeque<>();
        String message = "";
        for (int i = 0; i < N; i++) {
            int num = StdRandom.uniform(4);
            if (num == 0) {
                int x = StdRandom.uniform(100);
                good.addFirst(x);
                bad.addFirst(x);
                System.out.print("addFirst(" + x + ")\n");
            } else if (num == 1) {
                int x = StdRandom.uniform(100);
                good.addLast(x);
                bad.addLast(x);
                System.out.print("addLast(" + x + ")\n");
            } else if (num == 2 && !good.isEmpty()) {
                Integer x = good.removeLast();
                Integer y = bad.removeLast();
                System.out.print("removeLast()\n");
                assertEquals(message, x, y);
            } else if (num == 3 && !good.isEmpty()) {
                Integer x = good.removeFirst();
                Integer y = bad.removeFirst();
                System.out.print("removeFirst()\n");
                assertEquals(message, x, y);
            }

        }
    }

    @Test
    public void equalsDiffImplementation() {
        LinkedListDeque<Integer> l = new LinkedListDeque<>();
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            l.addFirst(i * 3);
            a.addFirst(i * 3);
        }
        assertTrue(a.equals(l));
        assertTrue(l.equals(a));
    }

    @Test
    public void testFalse() {
        LinkedListDeque<Integer> l = new LinkedListDeque<>();
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            l.addFirst(i * 7);
            a.addFirst(i * 3);
        }
        assertFalse(a.equals(l));
        assertFalse(l.equals(a));
    }

    @Test
    public void testDiffTypes() {
        LinkedListDeque<Integer> l = new LinkedListDeque<>();
        ArrayDeque<Double> a = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            l.addFirst(i * 7);
            a.addFirst(i * 7.0);
        }
        assertFalse(a.equals(l));
        assertFalse(l.equals(a));
    }

    @Test
    public void sizeDown() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 64; i++) {
            a.addFirst(3);
        }
        for (int i = 0; i < 63; i++) {
            a.removeFirst();
        }
    }

    @Test
    public void hasNext() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 64; i++) {
            a.addLast(2);
        }

    }

}
