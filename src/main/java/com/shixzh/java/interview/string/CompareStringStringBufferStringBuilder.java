package com.shixzh.java.interview.string;

/**
 * String是我们最常用的数据类型，关于它的处理主要通过String,StringBuffer,StringBuilder;
 * String与StringBuffer,StringBuilder的区别是String仍是一个变量的属性，当需要append
 * 时，需要new一个新的对象，像isStringVariable()方法测试的那样，其他两个不需要。
 * StringBuilder与StringBuffer的区别是StringBuffer是一个线程安全类，像append等所有方法
 * 都是synchronized的，所以要慢一点。
 * @author shixiang.zhao
 *
 */
public class CompareStringStringBufferStringBuilder {

    public static void main(String[] args) {
        isStringVariable();
        //isThreadSafety();
    }

    private static void isStringVariable() {
        String s = "abc";
        System.out.println("String: " + s.hashCode());
        s = s + 1;
        System.out.println("String: " + s.hashCode());

        StringBuffer buffer = new StringBuffer("abc");
        System.out.println("StringBuffer: " + buffer.hashCode());
        buffer.append("1");
        System.out.println("StringBuffer: " + buffer.hashCode());

        StringBuilder builder = new StringBuilder("abc");
        System.out.println("StringBuilder: " + builder.hashCode());
        builder.append("1");
        System.out.println("StringBuilder: " + builder.hashCode());
    }

    private static void isThreadSafety() {
        String s = "####____";
        StringBuffer buffer = new StringBuffer(s);
        StringBuilder builder = new StringBuilder(s);

        ReverseStringThread rst1 = new ReverseStringThread(buffer);
        ReverseStringThread rst2 = new ReverseStringThread(buffer);
        ReverseStringThread rst3 = new ReverseStringThread(builder);
        ReverseStringThread rst4 = new ReverseStringThread(builder);

        new Thread(rst1).start();
        new Thread(rst2).start();
        new Thread(rst3).start();
        new Thread(rst4).start();
    }
}

class ReverseStringThread implements Runnable {

    private Object s = null;
    int count = 100;

    public ReverseStringThread(StringBuffer buffer) {
        this.s = buffer;
    }

    public ReverseStringThread(StringBuilder builder) {
        this.s = builder;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (s instanceof StringBuffer) {
                ((StringBuffer) s).reverse();
                System.out.println("BUFFER->" + s);
            } else if (s instanceof StringBuilder) {
                ((StringBuilder) s).reverse();
                System.out.println("        " + s + "<-builder");
            }
        }
    }
}
