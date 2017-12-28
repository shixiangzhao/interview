package com.shixzh.java.interview.collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapPutDemo {

    public static void main(String[] args) {
        //demo1();
        //demo2();
        demo3();
    }

    /*
     * 并发执行时，线程安全的容器只能保证自身的数据不被破坏，但无法保证业务的行为是否正确。
     * 如demo1：当两个线程同时对key=a的value+1时，因为多个线程对相同的key进行put操作时，
     * 很可能会覆盖彼此的结果，造成记录的次数比实际出现的次数少。
     */
    public static void demo1() {
        final Map<String, Integer> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Integer value = count.get("a");
                    if (null == value) {
                        count.put("a", 1);
                    } else {
                        count.put("a", value + 1);//多个线程同时对value+1
                    }
                }
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();

        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 当然可以用锁解决这个问题，但是也可以使用ConcurrentMap定义的方法:
     * V putIfAbsent(K key, V value)
     * 如果key对应的value不存在，则put进去，返回null。否则不put，返回已存在的value。
     * boolean remove(Object key, Object value)
     * 如果key对应的值是value，则移除K-V，返回true。否则不移除，返回false。
     * boolean replace(K key, V oldValue, V newValue)
     * 如果key对应的当前值是oldValue，则替换为newValue，返回true。否则不替换，返回false。
     */
    public static void demo2() {
        final Map<String, Integer> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = new Runnable() {

            @Override
            public void run() {
                Integer oldValue, newValue;
                for (int i = 0; i < 5; i++) {
                    while (true) {
                        oldValue = count.get("a");
                        if (null == oldValue) {
                            newValue = 1;
                            //如果key对应的value不存在，则put进去，返回null。否则不put，返回已存在的value。
                            if (count.putIfAbsent("a", newValue) == null) {
                                break;
                            }
                        } else {
                            newValue = oldValue + 1;
                            //如果key对应的当前值是oldValue，则替换为newValue，返回true。否则不替换，返回false。
                            if (count.replace("a", oldValue, newValue)) {
                                break;
                            }
                        }
                    }
                }
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();

        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 由于ConcurrentMap中不能保存value为null的值，
     * 所以需要处理不存在和已存在两种情况，不过可以使用AtomicInteger来替代。
     */
    public static void demo3() {
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = new Runnable() {

            @Override
            public void run() {
                AtomicInteger oldValue;
                for (int i = 0; i < 5; i++) {
                    oldValue = count.get("a");
                    if (null == oldValue) {
                        AtomicInteger zeroValue = new AtomicInteger(0);
                        //oldValue = count.put("a", zeroValue);
                        oldValue = count.putIfAbsent("a", zeroValue);
                        if (null == oldValue) {
                            oldValue = zeroValue;
                        }
                    }
                    //AtomicInteger自增操作
                    oldValue.incrementAndGet();
                }
                endLatch.countDown();
            }
        };
        new Thread(task).start();
        new Thread(task).start();

        try {
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
