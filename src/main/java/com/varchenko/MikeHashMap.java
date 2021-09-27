package com.varchenko;


import java.util.*;

// Node -> null, Node -> key null, Node -> key, value
public class MikeHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] buckets;
    private float loadFactor;
    private int capacity;
    private int modCount;
    private int size;

    public MikeHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        buckets = (Node<K, V>[]) new Node[capacity];
    }

    public MikeHashMap(int capacity, float loadFactor) {
        this();
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    private void resize() {
        if (size >= capacity * loadFactor) {
            capacity = capacity + (capacity >> 1);
            Node<K, V>[] oldBuckets = buckets;
            buckets = (Node<K, V>[]) new Node[capacity];
            size = 0;
            for (Node<K, V> kvNode : oldBuckets) {
                if (kvNode != null) {
                    put(kvNode.getKey(), kvNode.getValue());
                }
            }
        }
    }

    private int indexFor(Object k) {
        if (k != null) {
            int index = k.hashCode() & capacity - 1;
            return index >= 0 ? index : -index;
        }
        return 0;
    }

    private Node<K, V> getHead(K key) {
        return buckets[indexFor(key)];
    }

    private Node<K, V> find(Object key) {
        return key != null ? findNotNull((K) key) : findNull();
    }

    private Node<K, V> findNotNull(K key) {
        Node<K, V> cursor = getHead(key);
        if (cursor != null) {
            while (cursor.getKey() != null) {
                if (cursor.getKey().hashCode() == key.hashCode() && cursor.getKey().equals(key)) {
                    return cursor;
                }
                cursor = cursor.next;
            }
        }
        return null;
    }

    private Node<K, V> findNull() {
        Node<K, V> cursor = buckets[0];
        if (cursor != null) {
            while (cursor.hasNext()) {
                cursor = cursor.next;
                if (cursor.getKey() == null) {
                    return cursor;
                }
            }
            return cursor;
        }
        return null;
    }

    @Override
    public V get(Object key) {
        Node<K, V> currentNode = find(key);
        if (currentNode != null) {
            return currentNode.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        resize();
        Node<K, V> head = getHead(key);
        if (head == null) {
            createHead(key, value);
        } else {
            return putEntry(head, key, value);
        }
        return null;
    }

    private V putEntry(Node<K, V> head, K key, V value) {
        Node<K, V> cursor = head;
        if (key != null) {
            while (cursor.hasNext()) {
                cursor = cursor.next;
                if (cursor.getKey().hashCode() == key.hashCode() && cursor.getKey().equals(key)) {
                    cursor.setValue(value);
                } else {
                    size++;
                    modCount++;
                    cursor.setNext(new Node<>(key, value));
                }
            }
        } else {
            cursor = find(null);
            if (cursor == null) {
                cursor = new Node<>(null, value);
                cursor = cursor.next;
                size++;
                modCount++;
            }
            cursor.setValue(value);
        }
        return value;
    }

    private void createHead(K key, V value) {
        buckets[indexFor(key)] = new Node<>(key, value);
        size++;
        modCount++;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> current = find(key);
        Node<K, V> previous = findPrevious((K) key, current);
        if (current != null) {
            current.setValue(null);
            modCount++;
            size--;
            if (previous != null) {
                previous.next = current.next;
            } else {
                current.next = current;
            }
        }
        return null;
    }

    public Node<K, V> findPrevious(K key, Node<K, V> current) {
        Node<K, V> previous = getHead(key);
        if (previous != current) {
            if (previous.next == current) {
                return previous;
            } else {
                while (previous.next != current) {
                    previous = previous.next;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {

            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new FastFailIterator();
            }

            @Override
            public int size() {
                return size;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= buckets.length - 1; i++) {
            builder.append("[").append(i).append("]");
            if (buckets[i] != null) {
                while (buckets[i].hasNext()) {
                    builder.append("-entry")
                            .append(i).append(" (key: ").append(buckets[i].getKey())
                            .append("; ").append("value: ").append(buckets[i]
                                    .getValue()).append(")");
                    buckets[i] = buckets[i + 1];
                }
                builder.append("-entry").append(i).append(" (key: ").append(buckets[i]
                        .getKey()).append("; value: ").append(buckets[i]
                        .getValue()).append(")");
            } else {
                builder.append("  ");
                buckets[i] = buckets[i + 1];
            }
        }
        return builder.toString();
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
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

    private class FastFailIterator implements Iterator<Map.Entry<K, V>> {

        private final Node<K, V> current = buckets[size];
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return (current.getNext() != null);
        }

        @Override
        public Node<K, V> next() {
            checkForModification();
            if (current.getNext() != null) {
                return current.getNext();
            }
            return null;
        }

        final void checkForModification() {
            if (expectedModCount != modCount) throw new ConcurrentModificationException();
        }
    }

    private class FastSaveIterator implements Iterator<Map.Entry<K, V>> {

        private final MikeHashMap<K, V> clone = cloneMap();

        @Override
        public boolean hasNext() {
            return (clone.buckets[size].next != null);
        }

        @Override
        public Node<K, V> next() {
            if (clone.buckets[size].hasNext()) {
                return clone.buckets[size].next ;
            }
            return null;
        }

        final MikeHashMap<K, V> cloneMap() {
            MikeHashMap<K, V> copy = new MikeHashMap<>();
            for (Node<K, V> head : buckets) {
                while (head.hasNext()) {
                    head = head.next;
                    copy.put(head.getKey(), head.getValue());
                }
            }
                return copy;
        }
    }
}