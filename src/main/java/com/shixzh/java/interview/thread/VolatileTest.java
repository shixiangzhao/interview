package com.shixzh.java.interview.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile关键字能保证可见性，但是上面的程序错在没能保证原子性。
 * 可见性只能保证每次读取的共享变量是最新的值
 * 自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。
 * 下面列举几个Java中使用volatile的几个场景:
 * 1.状态标记量
 * 2.double check
 * 
 * @author shixiang.zhao
 */
public class VolatileTest {

    private volatile int inc = 0;
    private static volatile boolean flag;

    public static void main(String[] args) throws InterruptedException {
        testStatusFlag();
        // testIncrease();
    }

    // 1.状态标记量
    public static void testStatusFlag() throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {

            public void run() {
                int i = 0;
                System.out.println(Thread.currentThread().getName());
                while (!flag) {
                    i++;
                }
            }
        });
        backgroundThread.start();

        //期待1s后主线程结束后台线程
        TimeUnit.SECONDS.sleep(1);
        setFlag();
    }

    public static void setFlag() {
        flag = true;
    }

    public static void testIncrease() {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {

                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                };
            }.start();
        }

        while (Thread.activeCount() > 1) //活动的线程数
            Thread.yield(); // 线程让步
        System.out.println("inc = " + test.inc);
    }

    public void increase() {
        inc++;
    }

    // synchronized 之后可以保证原子性
    public synchronized void increase1() {
        inc++;
    }

    Lock lock = new ReentrantLock();

    public void increase2() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    /*
     * 在java 1.5的java.util.concurrent.atomic包下提供了一些原子操作类，
     * 即对基本数据类型的 自增（加1操作），自减（减1操作）、以及加法操作（加一个数），
     * 减法操作（减一个数）进行了封装，保证这些操作是原子性操作。
     * atomic是利用CAS来实现原子性操作的（Compare And Swap），CAS实际上是利用
     * 处理器提供的CMPXCHG指令实现的，而处理器执行CMPXCHG指令是一个原子性操作。
     */
    public AtomicInteger incAto = new AtomicInteger();

    public void increase3() {
        incAto.getAndIncrement();
    }

}

// 2.double check: 使用 volatile 关键字来保证多线程下的单例
class Singleton {

    private volatile static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
