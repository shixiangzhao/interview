package com.shixzh.java.interview.jvm;

import java.util.ArrayList;
import java.util.List;

//假笨说-又抓了一个导致频繁GC的鬼--数组动态扩容
//http://mp.weixin.qq.com/s/HKdpmmvJKq45QZdV4Q2cYQ
public class FrequentlyGC {

    public static void main(String[] args) {
        allocateMemory();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void allocateMemory() {
        List<byte[]> list = new ArrayList<byte[]>();
        int size = 1024 * 1024 * 480;
        int len = size / (20 * 1024);
        for (int i = 0; i < len; i++) {
            try {
                byte[] bytes = new byte[20 * 1024];
                list.add(bytes);
            } catch (java.lang.OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
    }
}
