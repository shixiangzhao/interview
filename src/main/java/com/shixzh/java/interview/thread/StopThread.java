package com.shixzh.java.interview.thread;

import java.util.concurrent.TimeUnit;

public class StopThread {

    //状态标记量
    private static boolean stopRequested;
    private static volatile boolean stopRequestedVolatile;

    public static void stopThread1() throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {

            public void run() {
                System.out.println("New Thread:" + Thread.currentThread().getName());
                int i = 0;
                while (!stopRequested) {
                    i++;
                }
                /*
                 * 虚拟机将上述代码转变为：
                 * if (!stopRequested) { //这种优化称为提升（hoisting）
                 * while (true) { //我们原本期待轮询stopRequest的值，实际只读了一次，所以需要同步这个值
                 * i++;
                 * }
                 * }
                 */
            }
        });

        backgroundThread.start();

        //期待这个程序运行1s，之后主线程将stopRequested设置为true，循环终止。其实无法终止。
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

    //正是HopSpot Server VM的hoisting工作，导致这次活性失败（liveness failure）:程序无法前进
    //解决办法，同步访问stopRequested域
    public static void stopThread2() throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {

            public void run() {
                System.out.println("New Thread:" + Thread.currentThread().getName());
                int i = 0;
                while (!stopRequested()) {
                    i++;
                }
            }
        });

        backgroundThread.start();

        //期待这个程序运行1s，之后主线程将stopRequested设置为true，循环终止
        TimeUnit.SECONDS.sleep(1);
        reqestStop();
    }

    //当然，这里写方法不被同步，也可以实现1s结束。如同volatile关键字。
    private static synchronized void reqestStop() {
        stopRequested = true;
    }

    // 只有读写操作都被同步，才会起作用 
    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void stopThread3() throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {

            public void run() {
                System.out.println("New Thread:" + Thread.currentThread().getName());
                int i = 0;
                while (!stopRequestedVolatile) {
                    i++;
                }
            }
        });

        backgroundThread.start();

        //期待这个程序运行1s，之后主线程将stopRequested设置为true，循环终止
        TimeUnit.SECONDS.sleep(1);
        stopRequestedVolatile = true;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main Thread:" + Thread.currentThread().getName());
        //stopThread1();
        //stopThread2();
        stopThread3();
    }

}
