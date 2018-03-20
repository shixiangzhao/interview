package com.shixzh.java.interview.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CustomLinkedHashMap<K, V> implements Map<K, V> {

    final boolean accessOrder;

    static class Entry<K, V> extends Node<K, V> {

        Entry<K, V> before, after;

        Entry(int hash, K key, V value, com.shixzh.java.interview.collections.Node<K, V> next) {
            super(hash, key, value, next);
        }
    }

    public CustomLinkedHashMap() {
        super();
        accessOrder = false;
    }

    public static void main(String[] args) {
        Map<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("ZhangSan", 3);
        lhm.put("LiSi", 2);
        lhm.put("WangWu", 6);
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
        System.out.println("========================");
        Map<String, Integer> clhm = new CustomLinkedHashMap<>();
        clhm.put("ZhangSan", 3);
        clhm.put("LiSi", 2);
        clhm.put("WangWu", 6);
        for (java.util.Map.Entry<String, Integer> e : clhm.entrySet()) {
            System.out.println("key: " + e.getKey() + " value: " + e.getValue());
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public V get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public V put(K key, V value) {
        // TODO Auto-generated method stub
        return putVal(hash(key), key, value, false, true);
    }

    private V putVal(int hash, K key, V value, boolean b, boolean c) {
        // TODO Auto-generated method stub
        return null;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public V remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

}

class Node<K, V> implements Map.Entry<K, V> {

    final int hash;
    final K key;
    V value;
    Node<K, V> next;

    Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    public final String toString() {
        return key + "=" + value;
    }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}
