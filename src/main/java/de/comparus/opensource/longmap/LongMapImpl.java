package de.comparus.opensource.longmap;

import java.util.Arrays;

public class LongMapImpl<V> implements LongMap<V> {

    private Node<V>[] longTable;

    private int size = 0;
    private int DEFAULT_CAPACITY = 16;
    @SuppressWarnings("unchecked")
    private Node<V>[] values = new Node[DEFAULT_CAPACITY];

    public V put(long key, V value) {
        boolean insert = true;
        for (int i = 0; i < size; i++) {
            if (values[i].getKey() == key) {
                values[i].setValue(value);
                insert = false;
            }
        }

        if (insert) {
            ensureCapa();
            values[size++] = new Node<V>(key, value);
        }

        return value;
    }

    private void ensureCapa() {
        if (size == DEFAULT_CAPACITY) {
            int newSize = values.length * 2;
            values = Arrays.copyOf(values, newSize);
        }
    }

    private void condenseArray(int start) {
        for (int i = start; i < size; i++) {
            values[i] = values[i + 1];
        }
    }

    public V get(long key) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null) {
                if (values[i].getKey() == key) {
                    return values[i].getValue();
                }
            }
        }

        return null;
    }

    public V remove(long key) {
        for (int i = 0; i < size; i++) {
            if (key == values[i].getKey()) {
                V elem = values[i].getValue();
                values[i] = null;
                size--;
                condenseArray(i);
                return elem;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        for (int i = 0; i < size; i++) {
            if (key == values[i].getKey()) {
                return true;
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(values[i].getValue())) {
                return true;
            }
        }

        return false;
    }

    public long[] keys() {
        long[] arr = new long[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (values[i].getKey());
        }

        return arr;
    }

    public V[] values() {
        V[] arr = (V[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (values[i].getValue());
        }

        return arr;
    }

    public long size() {
        return size;
    }

    public void clear() {
        if (size > 0) {
            size = 0;
            for (int i = 0; i < size; ++i)
                values[i] = null;
        }
    }
}
