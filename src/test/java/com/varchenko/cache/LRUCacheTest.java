package com.varchenko.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    private LRUCache<String, Integer> cache;

    @BeforeEach
    public void setCache() {
        cache = new LRUCache<>(3);
    }

    @Test
    void checkOldestRemoved() {
        cache.put("q", 1);
        cache.put("w", 2);
        cache.put("e", 3);
        cache.put("r", 4);
        assertNull(cache.get("q"));
        assertEquals(2, cache.get("w"));
        assertEquals(3, cache.get("e"));
        assertEquals(4, cache.get("r"));
    }

    @Test
    void checkRarestRemoved() {
        cache.put("q", 1);
        cache.put("w", 2);
        cache.put("e", 3);
        assertEquals(2, cache.get("w"));
        cache.put("r", 4);
        cache.put("t", 5);
        assertEquals(2, cache.get("w"));
        assertNull(cache.get("e"));
        assertNull(cache.get("q"));
    }
}