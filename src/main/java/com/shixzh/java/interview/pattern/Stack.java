package com.shixzh.java.interview.pattern;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 第6条：消除过期的对象引用
 * 由于栈内部维护着对这些对象的过期引用（obsolete reference）,所以容易造成内存泄漏。
 * Stack类自己管理内存（manage its own memory），对GC而言，elements数组中的所有对象引用都是同等有效的，
 * 只有程序员知道数组的非活动部分是不重要的，所以需要手动清空这些元素。
 * @author shixiang.zhao
 */
public class Stack {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];
    }

    public Object pop1() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null; //Eliminate obsolete reference
        return result;
    }
    
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
