package com.shixzh.java.interview.thread;

/**
 * Java产生死锁的一个简单例子
 * 思路是创建两个字符串a和b，再创建两个线程A和B，
 * 让每个线程都用synchronized锁住字符串（A先锁a，再去锁b；B先锁b，再锁a），
 * 如果A锁住a，B锁住b，A就没办法锁住b，B也没办法锁住a，这时就陷入了死锁。
 * Lock1获取obj1，Lock2获取obj2，
 * 但是它们都没有办法再获取另外一个obj，因为它们都在等待对方先释放锁，这时就是死锁。
 * 
 * @author shixiang.zhao
 */
public class DeadLock {

    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public static void main(String[] args) {
        Thread t1 = new Thread(new Lock1());
        Thread t2 = new Thread(new Lock2());
        t1.start();
        t2.start();
    }

}

class Lock1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Lock1 running...");
        try {
            while (true) {
                synchronized (DeadLock.obj1) {
                    System.out.println("Lock1 lock obj1");
                    Thread.sleep(3000);
                    synchronized (DeadLock.obj2) {
                        System.out.println("Lock1 lock obj2");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Lock2 implements Runnable {

    @Override
    public void run() {
        System.out.println("Lock2 running...");
        try {
            while (true) {
                synchronized (DeadLock.obj2) {
                    System.out.println("Lock2 lock obj2");
                    Thread.sleep(3000);
                    synchronized (DeadLock.obj1) {
                        System.out.println("Lock2 lock obj1");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
