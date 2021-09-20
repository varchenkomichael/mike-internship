package com.varchenko;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MikeHashMapTest {

    @Test
    void get() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("get", "test");
        assertEquals(mikeHashMap.get("get"), "test");
        assertNull(mikeHashMap.get("n"));
    }

    @Test
    void put() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("Jon", "Wi");
        assertEquals(mikeHashMap.get("Jon"), "Wi");
    }

    @Test
    void remove() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("Jon", "Wi");
        mikeHashMap.put("KON", "lo");
        mikeHashMap.remove("Jon");
        assertEquals(mikeHashMap.size(), 1);
    }

    @Test
    void size(){
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("Jon", "Hi");
        assertEquals(1, mikeHashMap.size());
    }
}