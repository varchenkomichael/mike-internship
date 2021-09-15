package com.varchenko;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MikeHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final int SIZE = 16;

    private final MikeEntry<K, V> table [] = new MikeEntry[SIZE];

    private int indexFor(Object k) {
        return k.hashCode() % SIZE;
    }

    @Override
    public V get(Object k) {
        MikeEntry<K, V> mikeEntry = table[indexFor(k)];
        while (mikeEntry != null) {
            if (mikeEntry.key.equals(k)) {
                return mikeEntry.value;
            }
            mikeEntry = mikeEntry.next;
        }
        return null;
    }

    @Override
    public V put(Object key, Object value) {
        MikeEntry<K, V> mikeEntry = table[indexFor(key)];
        if (mikeEntry != null) {
            if (mikeEntry.key.equals(key)) {
               return mikeEntry.value = (V) value;
            } else {
                while (mikeEntry.next != null){
                    mikeEntry = mikeEntry.next;
                }
                 mikeEntry.next = new MikeEntry<>((K) key, (V) value);
            }
        } else {
            MikeEntry<K, V> createNewEntry = new MikeEntry<>((K) key, (V) value);
            table[indexFor(key)] = createNewEntry;
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
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
}