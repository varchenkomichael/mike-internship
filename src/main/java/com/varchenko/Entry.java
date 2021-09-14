package com.varchenko;

public class Entry {
    final String key;
    String value;
    Entry next;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }
}
