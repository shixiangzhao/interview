package com.shixzh.java.interview.string;

/**
 * 第50条如果其他类型更适合，则尽量避免使用字符串
 * 线程局部变量（thread-local variable）机制
 * @author shixiang.zhao
 *
 */
public class ThreadLocal {

    private ThreadLocal() {

    }

    public static class Key { //(Capability)能力表

        Key() {

        }
    }

    public static Key getKey() {
        return new Key();
    }

    //以不可伪造的键（Unforgeable key），或者说能力表代替字符串
    public static void set(Key key, Object value) {

    }

    public static Object get(Key key) {
        return null;
    }

    // String 作为key，客户端同名key可以造假
    //    public static void set(String key, Object value) {
    //
    //    }
    //
    //    public static Object get(String key) {
    //        return new Object();
    //    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

final class ThreadLocal1 {

    public ThreadLocal1() {

    }

    public void set(Object value) {

    }

    public Object get() {
        return null;
    }
}

final class ThreadLocal2<T> {

    public ThreadLocal2() {

    }

    public void set(T value) {

    }

    public T get() {
        return null;
    }
}
