package com.shixzh.java.interview.thread;

public class LazyInitialization {

    private final FieldType field1 = computeFieldValue();

    private FieldType field2;

    synchronized FieldType getField2() {
        if (field2 == null) {
            field2 = computeFieldValue();
        }
        return field2;
    }

    //如果出于性能的考虑而需要对静态域使用延迟初始化，就使用lazy initialization holder class模式。
    private static class FieldHolder {

        static final FieldType field3 = computeFieldValue();
    }

    static FieldType getField3() {
        return FieldHolder.field3;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private static FieldType computeFieldValue() {
        // TODO Auto-generated method stub
        return null;
    }

}

class FieldType {

}
