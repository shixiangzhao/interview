package com.shixzh.java.interview.thread;

import java.util.concurrent.*;

public class CountDownLatchTimer {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<>());
        time(executor, 10);

    }

    public static long time(Executor executor, int concurrency) {
        final CountDownLatch ready = new CountDownLatch(concurrency);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    ready.countDown(); // tell timer we're ready
                    try {
                        int count = 0;
                        start.await(); // wait till peers are ready
                        //action.run();
                        count++;
                        System.out.println(count);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown(); // tell timer we're done
                    }
                }
            });
        }
        long startNanos = 0;
        try {
            ready.await(); // wait for all workers to be ready
            startNanos = System.nanoTime();
            start.countDown(); // And they're off!
            done.await(); // wait for all workers to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return System.nanoTime() - startNanos;
    }
}
