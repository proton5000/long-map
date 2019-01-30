package de.comparus.opensource.longmap;

import java.util.Arrays;

public class LongMapImpl<V> implements LongMap<V> {

    private int size = 0;
    private int DEFAULT_CAPACITY = 16;
    @SuppressWarnings("unchecked")
    private Node<V>[] table = new Node[DEFAULT_CAPACITY];

    public V put(long key, V value) {
        Integer index = hash(key);

        Node<V> node = new Node<>(key, value);
        ensureCapacity();
        table[index] = node;
        size++;
        return value;
    }

    private Integer hash(long key) {
        return (Long.hashCode(key) & 0x7FFFFFFF) % table.length;
    }

    private void ensureCapacity() {
        if (size == table.length) {
            int newSize = table.length * 2;
            Node<V>[] tmp = Arrays.copyOf(table, table.length);
            table = new Node[newSize];
            size = 0;
            for ( Node<V>node : tmp ) {
                put(node.key, node.value);
            }
        }
    }

    public V get(long key) {
        Node<V> node;
        return (node = table[hash(key)]) == null ? null : node.value;
    }

    public V remove(long key) {
        Node<V> node;
        int index = hash(key);
        if ((node = table[index]) != null) {
            table[index] = null;
            size--;
            return node.value;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        return table[hash(key)] != null;
    }

    public boolean containsValue(V value) {
        if (size > 0) {
            for (Node<V> node : table) {
                if (node != null) {
                    V nodeValue = node.value;
                    if (nodeValue == value || value != null && value.equals(nodeValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public long[] keys() {
        long[] keys = new long[size];
        int count = 0;
        for (Node<V> node : table) {
            if (node != null) {
                keys[count++] = node.key;
            }
        }
        return keys;
    }

    public V[] values() {
        V[] values = (V[]) new Object[size];
        int count = 0;
        for (Node<V> node : table) {
            if (node != null) {
                values[count++] = node.value;
            }
        }
        return values;
    }

    public long size() {
        return size;
    }

    public void clear() {
        if (size > 0) {
            size = 0;
            for (int i = 0; i < table.length; ++i)
                table[i] = null;
        }
    }
}
