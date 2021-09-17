package com.varchenko;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MikeHashMapTest {
    private final MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();

    @Test
    void get() {
        mikeHashMap.put("get", "test");
        assertEquals(mikeHashMap.get("get"), "test");
    }

    @Test
    void put() {
        mikeHashMap.put("Jon", "Wi");
        assertEquals(mikeHashMap.get("Jon"), "Wi");
    }

    @Test
    void remove() {
        mikeHashMap.put("Jon", "Wi");
        mikeHashMap.put("KON", "lo");
        mikeHashMap.remove("Jon");
        assertEquals(mikeHashMap.size(), 1);
    }

    @Test
    void size_0() {
        assertEquals(mikeHashMap.size(), 0);
    }
    @Test
    void size_more_0(){
        mikeHashMap.put("Jon", "Hi");
        assertEquals(1, mikeHashMap.size());
    }
}