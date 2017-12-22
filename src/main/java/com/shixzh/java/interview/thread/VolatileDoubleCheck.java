package com.shixzh.java.interview.thread;

public class VolatileDoubleCheck {

    // 根据最新的 JSR133 的 Java 内存模型，如果将引用类型声明为 volatile，双重检查模式就可以工作了
    private volatile Resource resource;
    private int count;

    public static void main(String[] args) {

    }

    // 对 resource 延迟初始化
    public synchronized Resource getResource() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }

    // 为了减少同步的开销，于是有了双重检查模式。
    public Resource getResource1() {
        if (resource == null) {
            synchronized (this) {
                if (resource == null) {
                    resource = new Resource();
                }
            }
        }
        return resource;
    }

    // 基本类型long和double可以支持双重检查
    public int getCount() {
        if (count == 0) {
            synchronized (this) {
                if (count == 0) {
                    count = computeCount(); //一个耗时的计算  
                }
            }
        }
        return count;
    }

    private int computeCount() {
        // TODO Auto-generated method stub
        return 0;
    }
}

class Resource {

}
