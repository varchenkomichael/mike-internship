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
        mikeHashMap.put("get2", "test7");
        mikeHashMap.put("get1", "test8");
        assertEquals(mikeHashMap.get("get7"), "test1");
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
    void remove() {
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("Jon", "Wi");
        mikeHashMap.put("KON", "lo");
        mikeHashMap.put("get7", "test1");
        mikeHashMap.put("get6", "test2");
        mikeHashMap.put("get5", "test3");
        mikeHashMap.put("get4", "test4");
        mikeHashMap.put("get3", "test5");
        mikeHashMap.put("get2", "test7");
        mikeHashMap.put("get1", "test8");
        mikeHashMap.remove("Jon");
        assertEquals(mikeHashMap.size(), 8);
    }

    @Test
    void size(){
        MikeHashMap<String, String> mikeHashMap = new MikeHashMap<>();
        mikeHashMap.put("Jon", "Hi");
        mikeHashMap.put("KON", "lo");
        mikeHashMap.put("get7", "test1");
        mikeHashMap.put("get6", "test2");
        mikeHashMap.put("get5", "test3");
        mikeHashMap.put("get4", "test4");
        mikeHashMap.put("get3", "test5");
        mikeHashMap.put("get2", "test7");
        mikeHashMap.put("get1", "test8");
        assertEquals(9, mikeHashMap.size());
    }
}