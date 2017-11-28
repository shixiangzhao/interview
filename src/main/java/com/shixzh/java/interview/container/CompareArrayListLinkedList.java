package com.shixzh.java.interview.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 对比ArrayList和LinkedList的时间复杂度
 * ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。 
 * @author shixiang.zhao
 */
public class CompareArrayListLinkedList {

    public static final int N = 100000;

    public static List<Integer> values;

    static {
        Integer vals[] = new Integer[N];

        Random r = new Random();

        for (int i = 0, currval = 0; i < N; i++) {
            vals[i] = new Integer(currval);
            currval += r.nextInt(100) + 1;
        }

        values = Arrays.asList(vals);
    }

    //对于随机访问get和set，ArrayList绝对优于LinkedList，因为LinkedList要移动指针
    static long timeList(List<Integer> lst) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            int index = Collections.binarySearch(lst, values.get(i));
            if (index != i)
                System.out.println("***错误***");
        }
        return System.currentTimeMillis() - start;
    }

    //对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据
    static long timeListReverse(List<Integer> list) {
        long start = System.currentTimeMillis();
        int newInt = 1;
        for (int i = 0; i < N; i++)
            list.add(0, newInt);
        return System.currentTimeMillis() - start;
    }

    public static void main(String args[]) {
        System.out.println("ArrayList消耗时间：" + timeList(new ArrayList<Integer>(values)));
        System.out.println("LinkedList消耗时间：" + timeList(new LinkedList<Integer>(values)));
        System.out.println("=====Reverse=====");
        System.out.println("ArrayList消耗时间：" + timeListReverse(new ArrayList<Integer>(values)));
        System.out.println("LinkedList消耗时间：" + timeListReverse(new LinkedList<Integer>(values)));
    }
}
