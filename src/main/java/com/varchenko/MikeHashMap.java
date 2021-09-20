package com.varchenko;

import java.util.*;

public class MikeHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private int currentSize;
    private Node<K, V> current;
    private Node<K, V>[] bucket;

    public MikeHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        bucket = new Node[capacity];
    }

    public MikeHashMap(int capacity) {
        this.capacity = capacity;
    }


    private int indexFor(Object k) {
        if (k != null) {
            int index = k.hashCode() % DEFAULT_CAPACITY;
            return index >= 0 ? index : -index;
        }
        return 0;
    }

    private void putNotNull(K key, V value) {
        Node<K, V> node = bucket[indexFor(key)];
        if (node.getKey().equals(key)) {
            node.setValue(value);
        } else {
            while (node.getNext() != null) {
                node = node.getNext();
                currentSize++;
            }
            node.setNext(new Node<>(key, value));
        }
    }

    @Override
    public V get(Object k) {
        Node<K, V> node = bucket[indexFor(k)];
        if (node != null) {
            if (node.getKey().equals(k)) {
                return node.getValue();
            }
            return bucket[indexFor(k)].getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> node = bucket[indexFor(key)];
        if (node != null) {
            putNotNull(key, value);
        } else {
            bucket[indexFor(key)] = new Node<>(key, value);
            currentSize++;
        }
        return bucket[indexFor(key)].getValue();
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = bucket[indexFor(key)];
        if (node != null) {
            if (node.getKey().equals(key)) {
                node.setValue(null);
                currentSize--;
            } else {
                while (node.getNext() != null) {
                    node = node.getNext();
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K k : m.keySet()) {
            put(k, m.get(k));
            currentSize++;
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<Entry<K, V>>() {
                    @Override
                    public boolean hasNext() {
                        return current.getNext() != null;
                    }

                    @Override
                    public Node<K, V> next() {
                        if (current.getNext() == null) return null;
                        return current.getNext();
                    }
                };
            }

            @Override
            public int size() {
                return currentSize;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return super.removeAll(c);
            }
        };
    }

    @Override
    public int size() {
        return currentSize;
    }


    public static class Node<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public K getKey() {
            return key;
        }
    }
}