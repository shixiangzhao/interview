package com.shixzh.java.interview.string;

public class CompareStringStringBufferStringBuilder {

    public static void main(String[] args) {
        isStringVariable();
        isThreadSafety();
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
