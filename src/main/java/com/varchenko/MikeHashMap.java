package com.varchenko;

import java.util.*;

public class MikeHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] buckets;
    private Node<K, V> current;
    private int currentSize;
    private int threshold;
    private int capacity;

    public MikeHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        buckets = new Node[capacity];
    }

    public MikeHashMap(int capacity) {
        this();
        this.capacity = capacity;
    }

    private void resize() {
        if (threshold >= capacity * LOAD_FACTOR) {
            int bucketSize = capacity * 2;
            Node<K, V>[] oldBucket = buckets;
            buckets = new Node[bucketSize];
            currentSize = 0;
            for (Node<K, V> kvNode : oldBucket) {
                if (kvNode != null)
                    put(kvNode.getKey(), kvNode.getValue());
                currentSize++;
            }
        }
    }

    private int indexFor(Object k) {
        if (k != null) {
            int index = k.hashCode() % DEFAULT_CAPACITY;
            return index >= 0 ? index : -index;
        }
        return 0;
    }

    private void putNotNull(K key, V value) {
        Node<K, V> node = buckets[indexFor(key)];
        if (node.getKey().hashCode() == key.hashCode() && node.getKey().equals(key)) {
            node.setValue(value);
        } else {
            node.setNext(new Node<>(key, value));
        }
    }

    public Node<K, V> findInNode(Node<K, V> node, Object k) {
        Node<K, V> currentNode = node;
        while (currentNode.hasNext()) {
            if (node.getKey().hashCode() == k.hashCode() && node.getKey().equals(k)) {
                return node;
            }
            currentNode = currentNode.next;
        }
        return node;
    }

    @Override
    public V get(Object k) {
        Node<K, V> node = buckets[indexFor(k)];

        if (node != null) {
            if (node.getKey().hashCode() == k.hashCode() && node.getKey().equals(k)) {
                return node.getValue();
            }
            return findInNode(buckets[indexFor(k)], k).getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Node<K, V> node = buckets[indexFor(key)];
        if (node != null) {
            putNotNull(key, value);
            resize();
        } else {
            buckets[indexFor(key)] = new Node<>(key, value);
            currentSize++;
        }
        return buckets[indexFor(key)].getValue();
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = buckets[indexFor(key)];
        if (node != null) {
            if (node.getKey().hashCode() == key.hashCode() && node.getKey().equals(key)) {
                findInNode(node, key).setValue(null);
                currentSize--;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K k : m.keySet()) {
            Node<K, V> node = buckets[indexFor(k)];
            if (node != null) {
                putNotNull(k, m.get(k));
                currentSize++;
                resize();
            }
            put(k, m.get(k));
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
        private Node<K, V> next;
        private final K key;
        private V value;

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

        public boolean hasNext() {
            return (next != null);
        }
    }
}