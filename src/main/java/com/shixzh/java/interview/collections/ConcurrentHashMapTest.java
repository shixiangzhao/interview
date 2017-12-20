package com.shixzh.java.interview.collections;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();
        chm.put("k01", "v01");
        chm.get("k01");
    }

}
