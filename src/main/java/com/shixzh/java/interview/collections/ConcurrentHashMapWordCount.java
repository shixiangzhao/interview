package com.shixzh.java.interview.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentHashMapWordCount {

    private static Map<String, Long> wordCounts = new ConcurrentHashMap<>();
    private static ConcurrentMap<String, AtomicLong> wordCountsAtomicLong = new ConcurrentHashMap<>();
    private static final CountDownLatch endLatch = new CountDownLatch(2);

    public static void main(String[] args) {
        // hello=9, world=3, shi=1,
        String[] wordArrays = { "hello", "hello", "hello", "world", "hello", "hello", "hello", "hello", "hello",
                "world", "world", "hello", "shi" };

        Runnable task = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < wordArrays.length; i++) {
                    //increase1(wordArrays[i]);
                    increase2(wordArrays[i]);
                    increase3(wordArrays[i]);
                }
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        try {
            endLatch.await();
            System.out.println(wordCounts);
            System.out.println(wordCountsAtomicLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long increase1(String word) {
        Long oldValue = wordCounts.get(word);
        Long newValue = (oldValue == null) ? 1L : oldValue + 1;
        wordCounts.put(word, newValue);
        return newValue;

    }

    public static long increase2(String word) {
        Long oldValue, newValue;
        while (true) {
            oldValue = wordCounts.get(word);
            if (oldValue == null) {
                // Add the word firstly, initial the value as 1
                newValue = 1L;
                if (wordCounts.putIfAbsent(word, newValue) == null) {
                    break;
                }
            } else {
                newValue = oldValue + 1; //涉及Long对象的拆箱和装箱操作
                if (wordCounts.replace(word, oldValue, newValue)) {
                    break;
                }
            }
        }
        return newValue;
    }

    /* 每次调用都会涉及Long对象的拆箱和装箱操作，很明显，更好的实现方式是采用AtomicLong */
    public static long increase3(String word) {
        AtomicLong number = wordCountsAtomicLong.get(word);
        if (number == null) {
            AtomicLong newNumber = new AtomicLong(0);
            number = wordCountsAtomicLong.putIfAbsent(word, newNumber);
            if (number == null) {
                number = newNumber;
            }
        }
        return number.incrementAndGet();

    }
}
