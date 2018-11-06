package com.shixzh.java.interview.basic;

/**
 * @author: ZhaoShixiang <br>
 * @date: 2018/11/4 21:43
 */
public class ExchangeTwoVar {

    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        exchange2(a, b);
    }

    public static void exchange(int a, int b) {
        a = a - b;
        // b = a - b + b;
        b = a + b;
        // a = -(a - b - b)
        a = b - a;
        System.out.println("a=" + a + ", b=" + b);
    }
    public static void exchange2(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a=" + a + ", b=" + b);
    }
}