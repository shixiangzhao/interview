package com.shixzh.java.interview.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZhaoShixiang <br>
 * @date: 2018/11/16 19:50
 */
public class HashMapTest {

    public static void main(String[] args) {
        // test HashMap
        int initialCapacity = 17;
        float loadFactor = 0.7f;
        //HashMap<String, Integer> map = new HashMap(initialCapacity, loadFactor);
        HashMap<String, Integer> map = new HashMap<>();
        testHashMapPut(map);
        testHashMapGet(map);

        HashMap<Family, Integer> fam = new HashMap<>(initialCapacity, loadFactor);
        testHashMapPutFamily(fam);


    }

    private static void testHashMapPutFamily(HashMap<Family,Integer> fam) {
        for (int i = 0; i < 10; i++) {
            Family f = new Family(130 + i, "admin" + i);
            fam.put(f, 140 + i);
        }
    }

    public static void testHashMapPut(Map map) {
        for (int i = 0; i < 10; i++) {
            map.put("admin" + i, 120 + i);
        }
    }

    public static void testHashMapGet(Map map) {
        map.get("admin1");
    }
}

class Family {
    private int id;
    private String name;

    public Family(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        Family fo = (Family) obj;
        return this.id == fo.id && this.name.equals(fo.name);
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}