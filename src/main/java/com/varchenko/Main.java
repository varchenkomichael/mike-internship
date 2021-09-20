package com.varchenko;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new MikeHashMap<>();
        map.put("key", "value");

        for(Map.Entry<String, String> set : map.entrySet()) {
            System.out.println(set.getValue());
            System.out.println(set.getKey());
            map.get("key");
        }
    }
}
