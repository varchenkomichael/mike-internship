package com.varchenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MikeHashMapTest {

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
        assertEquals("k", mikeHashMap.get(null));
        System.out.println(mikeHashMap);
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
        assertEquals("test8", mikeHashMap.get("get1"));
    }

    @Test
    void putNull() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put(null, "lol");
        assertEquals("lol", mikeHashMap.get(null));
    }

    @Test
    void remove() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("get6", "k");
        mikeHashMap.put("Jon", "Wi");
        mikeHashMap.put("get9", "test1");
        mikeHashMap.remove("Jon");
        assertEquals(2, mikeHashMap.size());
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

    @Test
    void nonDefaultVal() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>(13, 0.69f);
        mikeHashMap.put("n", "v");
        mikeHashMap.put(null, "k");
        assertEquals("k", mikeHashMap.get(null));
    }
    @Test
    void putAll() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        MikeHashMap<String, String> mikeHashMap1 = new MikeHashMap<>();
        mikeHashMap.put("k", "v");
        mikeHashMap.put("k1", "v1");
        mikeHashMap1.putAll(mikeHashMap);
        assertEquals("v", mikeHashMap1.get("k"));
    }
}