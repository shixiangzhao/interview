package com.shixzh.java.interview.thread;

import java.util.Random;

/**
 * 题目要求：构造两线程，要求：
 * (1)两线程并发操作 （这就要求不能使用syschronized关键字）
 * (2)要求两线程分别访问各自的数据MyData对象，互不干扰
 * （这里就可以使用ThreadLocal对象，通过set()和get()即可获得与本线程相关的MyData对象，但注意，其只能关联一个数据，
 * 所以对于多个数据则应该封装到一个类中，其实际上也是通过Map实现的）
 * (3)线程内有A、B两个模块，模块之间共享数据MyData数据
 * 本程序实现方式比ThreadLocalDataIndependent1.java要好。
 * 其中Struts2对于用户的每次请求，都将创建一个action实例进行处理，每个线程都有其独立的数据，其实现方式就是这种。
 **/
public class ThreadLocalDataIndependent2 {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    //这里的data必须定义为局部变量，否则线程间不能实现数据独立
                    int data = new Random().nextInt(1000);
                    MyData2.getInstance().setName("code");
                    MyData2.getInstance().setAge(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {

        public void get() {
            MyData2 myData = MyData2.getInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " get MyData: "
                    + myData.getName() + ", " + myData.getAge());
        }
    }

    static class B {

        public void get() {
            MyData2 myData = MyData2.getInstance();
            System.out.println("B from " + Thread.currentThread().getName() + " get MyData: "
                    + myData.getName() + ", " + myData.getAge());
        }
    }
}

class MyData2 {

    private static ThreadLocal<MyData2> threadMap = new ThreadLocal<>();

    private MyData2() {

    }

    /**
     * 这里无需使用syschronized关键字
     */
    public static MyData2 getInstance() {
        MyData2 myData = threadMap.get();
        if (myData == null) {
            myData = new MyData2();
            threadMap.set(myData);
        }
        return myData;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
