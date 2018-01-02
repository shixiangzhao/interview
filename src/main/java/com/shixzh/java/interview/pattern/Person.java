package com.shixzh.java.interview.pattern;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 第5条：避免创建不必要的对象
 * 
 * @author shixiang.zhao
 */
public class Person {

    //语句每次执行都会创建一个String实例，如果放在循环里，结果不堪设想
    String s = new String("stringette"); //DON'T DO THIS
    //不会每次执行创建新的实例，而且包含相同的字面常量，对象会被重用
    String s1 = "stringette";

    private final Date birthDate = new Date();

    public boolean isBabyBoomer() {
        //每次调用都会创建一个Calendar，一个TimeZone和俩Date实例，这是不必要的。
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
    }

    public static void main(String[] args) {
        //要优先使用基本类型而不是装箱基本类型，要当心无意识的自动装箱
        //Long sum = 0L; //43s
        long sum = 0L; //6.8s
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}

class Person1 {

    private final Date birthDate = new Date();
    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {
        //Calendar实例的创建代价特别昂贵，所有比原来快了250倍。
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }
}
