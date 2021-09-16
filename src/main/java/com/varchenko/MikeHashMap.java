package com.varchenko;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MikeHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;

    private int CAPACITY;

    public MikeHashMap(int CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(int CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public MikeHashMap() {
    }

    private MikeEntry<K, V>[] bucket;

    private MikeEntry<K, V>[] getBucket() {
        return bucket;
    }


    private int indexFor(Object k) {
        if (k != null) {
            return k.hashCode() % DEFAULT_CAPACITY;
        }
        return 0;
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
            if (mikeEntry.getKey().equals(key)) {
                mikeEntry.setValue(value);
            } else {
                while (mikeEntry.getNext() != null) {
                    mikeEntry = mikeEntry.getNext();
                }
                mikeEntry.setNext(new MikeEntry<>(key, value));
            }
        } else {
            MikeEntry<K, V> createNewEntry = new MikeEntry<>(key, value);
            bucket[indexFor(key)] = createNewEntry;
        }
        for (MikeEntry<K, V> entry = bucket[0]; entry != null; entry = entry.getNext()) {
            if (entry.getKey() == null) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        MikeEntry<K, V> mikeEntry = bucket[indexFor(key)];
        if (mikeEntry != null) {
            if (mikeEntry.getKey().equals(key)) {
                mikeEntry.setValue(null);
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
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        };
        MikeEntry<K, V>[] mikeEntry = getBucket();

        for (MikeEntry<K, V> kvMikeEntry : mikeEntry) {
            if (kvMikeEntry != null) {
                entrySet.add(kvMikeEntry);
            }
        }
        return entrySet;
    }

//    @Override
//    public int size() {
//        MikeHashMap<K, V> map = new MikeHashMap<>();
//
//    }

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