import de.comparus.opensource.longmap.LongMap;
import de.comparus.opensource.longmap.LongMapImpl;

import static org.junit.Assert.*;

public class LongMapImplTest {

    @org.junit.Test
    public void put() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);

        assertEquals(testLongMap.size(), 1);
        assertEquals(testLongMap.get(123L), 25);
    }

    @org.junit.Test
    public void get() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);

        assertNotNull(testLongMap.get(123L));
        assertEquals(testLongMap.get(123L), 25);
    }

    @org.junit.Test
    public void remove() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);
        testLongMap.put(124L, 26);

        testLongMap.remove(123L);

        assertFalse(testLongMap.containsValue(25));
    }

    @org.junit.Test
    public void isEmpty() {
        LongMap testLongMap = new LongMapImpl<>();

        assertTrue(testLongMap.isEmpty());
    }

    @org.junit.Test
    public void containsKey() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);

        assertTrue(testLongMap.containsKey(123L));
    }

    @org.junit.Test
    public void containsValue() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);

        assertTrue(testLongMap.containsValue(25));
    }

    @org.junit.Test
    public void keys() {
        LongMap testLongMap = new LongMapImpl<Integer>();

        assertEquals(testLongMap.keys().length, 0);

        testLongMap.put(123L, 25);

        assertEquals(testLongMap.keys().length, 1);
    }

    @org.junit.Test
    public void values() {
        LongMap testLongMap = new LongMapImpl<Integer>();

        assertEquals(testLongMap.values().length, 0);

        testLongMap.put(123L, 25);

        assertEquals(testLongMap.values().length, 1);
    }

    @org.junit.Test
    public void size() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);
        testLongMap.put(124L, 26);
        testLongMap.put(125L, 27);

        assertEquals(testLongMap.size(), 3);
    }

    @org.junit.Test
    public void clear() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);
        testLongMap.put(124L, 26);
        testLongMap.put(125L, 27);
        testLongMap.clear();

        assertEquals(testLongMap.size(), 0);
        assertEquals(testLongMap.values().length, 0);
        assertEquals(testLongMap.keys().length, 0);
    }

    @org.junit.Test
    public void autoSizing() {
        LongMap testLongMap = new LongMapImpl<Integer>();
        for (int i = 0; i < 30; i++) {
            testLongMap.put(i, Math.random());
        }
        assertEquals(testLongMap.size(), 30);
    }
}