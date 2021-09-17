package com.varchenko;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MikeHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private int currentSize;
    private MikeEntry<K, V> current;
    private MikeEntry<K, V>[] bucket;

    public MikeHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        bucket = new MikeEntry[capacity];
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
    private void put_not_null (K key, V value){
        MikeEntry<K, V> mikeEntry = bucket[indexFor(key)];
        if (mikeEntry.getKey().equals(key)) {
            mikeEntry.setValue(value);
        } else {
            while (mikeEntry.getNext() != null) {
                mikeEntry = mikeEntry.getNext();
                currentSize++;
            }
            mikeEntry.setNext(new MikeEntry<>(key, value));
        }
    }

    @Override
    public V get(Object k) {
        MikeEntry<K, V> mikeEntry = bucket[indexFor(k)];
        while (mikeEntry != null) {
            if (mikeEntry.getKey().equals(k)) {
                return mikeEntry.getValue();
            }
            mikeEntry = mikeEntry.getNext();
        }
        for (MikeEntry<K, V> entry = bucket[0]; entry != null; entry = entry.getNext()) {
            if (entry.getKey() == null) return entry.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        MikeEntry<K, V> mikeEntry = bucket[indexFor(key)];
        if (mikeEntry != null) {
            put_not_null(key, value);
        } else {
            bucket[indexFor(key)] = new MikeEntry<>(key, value);
            currentSize++;
        }
        return bucket[indexFor(key)].getValue();
    }

    @Override
    public V remove(Object key) {
        MikeEntry<K, V> mikeEntry = bucket[indexFor(key)];
        if (mikeEntry != null) {
            if (mikeEntry.getKey().equals(key)) {
                mikeEntry.setValue(null);
                currentSize--;
            } else {
                while (mikeEntry.getNext() != null) {
                    mikeEntry = mikeEntry.getNext();
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
                    public MikeEntry<K, V> next() {
                        if (current.getNext() == null) throw new NoSuchElementException();
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

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return Map.super.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Map.super.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Map.super.replaceAll(function);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return Map.super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return Map.super.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return Map.super.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return Map.super.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return Map.super.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return Map.super.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return Map.super.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return Map.super.merge(key, value, remappingFunction);
    }


    public static class MikeEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        MikeEntry<K, V> next;

        public MikeEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public MikeEntry<K, V> getNext() {
            return next;
        }

        public void setNext(MikeEntry<K, V> next) {
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