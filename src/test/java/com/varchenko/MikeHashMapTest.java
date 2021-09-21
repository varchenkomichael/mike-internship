package com.varchenko;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MikeHashMapTest {

    public static void main(String[] args) {

    }

    @Test
    void get() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("get7", "test1");
        mikeHashMap.put("get6", "test2");
        mikeHashMap.put("get5", "test3");
        mikeHashMap.put("get4", "test4");
        mikeHashMap.put("get3", "test5");
        mikeHashMap.put("cna", "cm");
        mikeHashMap.put(null, "test");
        mikeHashMap.put(null, "k");
        String expected = mikeHashMap.get(null);
        assertEquals(expected, "k");
    }

    @Test
    void put() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("get7", "test1");
        mikeHashMap.put("get6", "test2");
        mikeHashMap.put("get5", "test3");
        mikeHashMap.put("get4", "test4");
        mikeHashMap.put("get3", "test5");
        mikeHashMap.put("get2", "test7");
        mikeHashMap.put("get1", "test8");
        assertEquals(mikeHashMap.get("get1"), "test8");
    }

    @Test
    void putNull() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put(null, "lol");
        assertEquals(mikeHashMap.get(null), "lol");
    }

    @Test
    void remove() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("Jon", "Wi");
        mikeHashMap.put("get7", "test1");
        mikeHashMap.put("get6", "test2");
        mikeHashMap.put("get5", "test3");
        mikeHashMap.put("get4", "test4");
        mikeHashMap.put("get3", "test5");
        mikeHashMap.put("get12", "test7");
        mikeHashMap.put("get13", "test7");
        mikeHashMap.put("get2", "test7");
        mikeHashMap.put("get1", "test7");
        mikeHashMap.remove("Jon");
        assertEquals(mikeHashMap.size(), 9);
    }

    @Test
    void size() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("Jon", "Hi");
        mikeHashMap.put("KONl", "lo");
        mikeHashMap.put("get1", "test1");
        mikeHashMap.put("get2", "test2");
        mikeHashMap.put("get3", "test3");
        mikeHashMap.put("get4", "test4");
        mikeHashMap.put("get5", "test5");
        mikeHashMap.put("get6", "test7");
        System.out.println(mikeHashMap);
        assertEquals(8, mikeHashMap.size());
    }
}