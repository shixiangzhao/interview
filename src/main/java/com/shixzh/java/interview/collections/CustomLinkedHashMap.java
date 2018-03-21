package com.shixzh.java.interview.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomLinkedHashMap<K, V> {

	public static void main(String[] args) {
		Map<String, Integer> lhm = new LinkedHashMap<>(16, 0.75f, true);
		lhm.put("ZhangSan", 3);
		lhm.put("LiSi", 2);
		lhm.put("WangWu", 6);
		for (java.util.Map.Entry<String, Integer> e : lhm.entrySet()) {
			System.out.println("key: " + e.getKey() + " value: " + e.getValue());
		}
		int count = lhm.get("LiSi");
        System.out.println("lhm.get(): " + count +"， 顺序发生改变：");
        for (java.util.Map.Entry<String, Integer> e : lhm.entrySet()) {
            System.out.println("key: " + e.getKey() + " value: " + e.getValue());
        }
		System.out.println("==========HashMap=======");
		Map<String, Integer> hm = new HashMap<>();
		hm.put("ZhangSan", 3);
		hm.put("LiSi", 2);
		hm.put("WangWu", 6);
		for (java.util.Map.Entry<String, Integer> e : hm.entrySet()) {
			System.out.println("key: " + e.getKey() + " value: " + e.getValue());
		}
	}

}
