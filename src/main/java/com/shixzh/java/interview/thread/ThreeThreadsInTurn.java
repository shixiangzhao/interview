package com.shixzh.java.interview.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//京东面试题：现在有T1,T2,T3三个线程，你怎样保证T2在T1执行后执行，T3在T2执行后执行？
public class ThreeThreadsInTurn {

    public static void main(String[] args) {
        final Thread t1 = new Thread(new Runnable() {

            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 1");
            }
        }, "T1");
        final Thread t2 = new Thread(new Runnable() {

            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 2");

                try {
                    t1.join(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");
        final Thread t3 = new Thread(new Runnable() {

            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 3");

                try {
                    t2.join(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3");
        // 1) 常规方法，随机执行:
        // 在t2的线程里面添加t1的join方法，抢占时间片，然后在t3的线程里面，抢占t2的时间片，
        // 理论上是 t3执行>t2执行>t1执行，实际上不是。
        // t1.start();
        // t2.start();
        // t3.start();

        // 2) newSingleThreadExecutor 创建一个只有一个线程的线程池来操作不限数量的线程队列
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3);
        executorService.shutdown();
    }
}
