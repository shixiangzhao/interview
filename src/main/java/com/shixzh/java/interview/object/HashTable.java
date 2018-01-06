package com.shixzh.java.interview.object;

/**
 * 第11条：谨慎地覆盖clone
 * 
 * @author shixiang.zhao
 *
 */
public class HashTable implements Cloneable {

    //数组里面仍有对象
    private Entry[] buckets = {};

    private static class Entry {

        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //采用iteration代替递归
        Entry deepCopy() {
            Entry result = new Entry(key, value, next);
            for (Entry p = result; p.next != null; p = p.next)
                p.next = new Entry(p.next.key, p.next.value, p.next.next);
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }

        //递归容易导致栈内存溢出
        Entry deepCopy1() {
            return new Entry(key, value, next == null ? null : next.deepCopy());
        }
    }

    @Override
    public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = new Entry[buckets.length];
            for (int i = 0; i < buckets.length; i++)
                if (buckets[i] != null)
                    result.buckets[i] = buckets[i].deepCopy();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    //@Override
    public HashTable clone1() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = buckets.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {

    }

}
