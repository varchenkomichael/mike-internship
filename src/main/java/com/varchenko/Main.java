package com.varchenko;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        for(Map.Entry<String, String> set : map.entrySet()) {
            System.out.println(set.getValue());
            System.out.println(set.getKey());
        }
    }
}
