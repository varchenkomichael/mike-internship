package com.varchenko;

public class MikeEntry<K, V> {
    final K key;
    V value;
    MikeEntry<K, V> next;

    public MikeEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }
}
