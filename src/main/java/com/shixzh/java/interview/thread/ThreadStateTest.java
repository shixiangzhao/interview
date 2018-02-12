package com.shixzh.java.interview.thread;

/**
 * 在现代的操作系统中，大多支持多任务，每个任务通常有不同的进程来完成。对于一个进程而言，
 * 也可以通过运行多个线程来实现多任务的效果。即使只有一个CPU，也可以利用时间片技术实现
 * 多线程。
 * Java两种实现多线程的方式：一种是继承Thread类，一种是实现Runnable接口
 * ，两者都需要重写run()方法。
 * 线程共有6中状态，即新建、运行（可运行）、阻塞、等待、计时等待和终止。
 * 新建NEW：当使用new创建新线程时，
 * 运行RUNNABLE：当调用start()方法时，
 * 阻塞BLOCKED：当线程需要获得对象的内置锁，而该锁正在被别的线程拥有时，
 * 等待WAITING：当线程等待其他线程通知调度表可以运行时，即调用wait()
 * 计时等待TIMED_WAITING：当调用含有时间参数的方法，如sleep()方法时，
 * 终止TERMINATED：当run()方法运行完毕或出现中断异常时，
 * @author shixiang.zhao
 */
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadState state = new ThreadState();
        Thread thread = new Thread(state);
        System.out.println("新建线程: " + thread.getState());
        thread.start();
        System.out.println("启动线程: " + thread.getState());
        Thread.sleep(100);
        System.out.println("计时等待: " + thread.getState());
        Thread.sleep(1000);
        System.out.println("等待线程: " + thread.getState());
        state.notifyNow();
        System.out.println("唤醒线程: " + thread.getState());
        Thread.sleep(1000);
        System.out.println("终止线程: " + thread.getState());
    }
}

class ThreadState implements Runnable {

    @Override
    public void run() {
        try {
            waitForASecond();
            waitForYears();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void waitForASecond() throws InterruptedException {
        //System.out.println("waitForASecond() is run...");
        wait(500);
    }

    public synchronized void waitForYears() throws InterruptedException {
        //System.out.println("waitForYears() is run...");
        wait();
    }

    public synchronized void notifyNow() {
        //System.out.println("notify() is run...");
        notify();
    }
}
