package de.comparus.opensource.longmap;

public class Main {

    public static void main(String[] args) {
        LongMap testLongMap = new LongMapImpl<Integer>();
        testLongMap.put(123L, 25);
        testLongMap.put(124L, 24);

        System.out.println(testLongMap.size());
        System.out.println(testLongMap.get(123L));
    }
}
