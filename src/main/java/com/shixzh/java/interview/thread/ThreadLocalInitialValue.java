package com.shixzh.java.interview.thread;


/**
 * class: ThreadLocalInitialValue <br>
 * ThreadLocal让访问某个变量的线程都拥有自己的局部变量，
 * 但是如果这个局部变量都指向同一个对象呢？这个时候ThreadLocal就失效了。
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/26 20:37
 */
public class ThreadLocalInitialValue {

    private static GroupA a = new GroupA();
    private static final ThreadLocal<GroupA> threadLocal = new ThreadLocal() {
        @Override
        protected GroupA initialValue() {
            return a;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.get().setNumber(threadLocal.get().getNumber() + 5);

                    System.out.println(Thread.currentThread().getName() + ": "
                            + threadLocal.get().getNumber());
                }
            }, "Thread-" + i);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

}

class GroupA {
    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}