package com.shixzh.java.interview.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * class: SynchronizedThread <br>
 *
 * 注：关于Lock对象和synchronized关键字的选择：
 *     a.最好两个都不用，使用一种java.util.concurrent包提供的机制，
 *         能够帮助用户处理所有与锁相关的代码。
 *     b.如果synchronized关键字能满足用户的需求，就用synchronized，因为它能简化代码
 *     c.如果需要更高级的功能，就用ReentrantLock类，此时要注意及时释放锁，否则会出现死锁，通常在finally代码释放锁
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/26 10:37
 */
public class SynchronizedThread {

    /**
     * synchronized关键字：
     * 简洁，但是效率低下，要尽可能的缩小锁的范围
     */
    class Bank {

        private int account = 100;

        public int getAccount() {
            return account;
        }

        /**
         * 用同步方法实现
         *
         * @param money
         */
        public synchronized void save(int money) {
            account += money;
        }

        /**
         * 用同步代码块实现
         *
         * @param money
         */
        public void save1(int money) {
            synchronized (this) {
                account += money;
            }
        }

        /**
         * volatile关键字：
         * 同步可见性，但不保证原子性。
         */
        class Bank1 {
            //需要同步的变量加上volatile
            private volatile int account = 100;

            public int getAccount() {
                return account;
            }

            //这里不再需要synchronized
            public void save(int money) {
                account += money;
            }
        }
    }

    /**
     * ReentrantLock类：
     * 可提供更高级的功能，但记得释放锁
     */
    class Bank2 {
        private int account = 100;
        //需要声明这个锁
        private Lock lock = new ReentrantLock();

        public int getAccount() {
            return account;
        }

        //这里不再需要synchronized
        public void save(int money) {
            lock.lock();
            try {
                account += money;
            } finally {
                lock.unlock();
            }
        }
    }


    /**
     * ThreadLocal与同步机制
     * a.ThreadLocal与同步机制都是为了解决多线程中相同变量的访问冲突问题。
     * b.前者采用以“空间换时间”的方法，后者采用以“时间换空间”的方式
     */
    class Bank3 {
        //使用ThreadLocal类管理共享变量account
        private ThreadLocal<Integer> account = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 100;
            }
        };

        public void save(int money) {
            account.set(account.get() + money);
        }

        public int getAccount() {
            return account.get();
        }
    }


    class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                bank.save1(10);
                // bank.save(10);
                System.out.println(i + "账户余额为：" + bank.getAccount() + " 当前线程：" + Thread.currentThread().getName());
            }
        }

    }

    /**
     * 建立线程，调用内部类
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);

        Thread thread1 = new Thread(new_thread);
        thread1.start();

        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

    public static void main(String[] args) {
        SynchronizedThread st = new SynchronizedThread();
        st.useThread();
    }

}