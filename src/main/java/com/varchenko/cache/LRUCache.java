package com.varchenko.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    private static final float DEFAULT_CLEAR_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 5;
    private final Map<K, V> cacheMap;
    private float clearFactor;
    private int capacity;

    public LRUCache(int capacity, float clearFactor) {
        this();
        this.capacity = capacity;
        this.clearFactor = clearFactor;
    }

    public LRUCache() {
        this.capacity = DEFAULT_CAPACITY;
        this.cacheMap = new LinkedHashMap<>(capacity);
        this.clearFactor = DEFAULT_CLEAR_FACTOR;
    }

    @Override
    public void put(K key, V value) {
        if (size() >= capacity) {
            clear();
            cacheMap.put(key, value);
        }
        cacheMap.put(key, value);
    }

    @Override
    public V get(K key) {
        if (cacheMap.containsKey(key)) {
            V value = cacheMap.get(key);
            cacheMap.remove(key);
            cacheMap.put(key, value);
        }
        return cacheMap.get(key);
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    private void clear() {
       int count =  size() - (int) (clearFactor * size());
        for (int i = 0; i < count; i++) {
           removeRarest();
        }
    }

    private void removeRarest() {
        Map.Entry<K, V> rarest = cacheMap.entrySet().iterator().next();
        cacheMap.remove(rarest.getKey());
    }

    @Override
    public String toString() {
        return cacheMap.toString();
    }
}
