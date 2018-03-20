package com.shixzh.java.interview.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 第25条：列表优先于数组 数组是协变的且可以具体化的，泛型是不可变的且可以被擦除的。数组提供了运行时的类型安全，但是没有编译时的类型安全。
 * 对于泛型也一样，可以混合起来使用泛型和数组，如果发现有警告，就用列表取代数组。
 * 
 * @author Shixiang
 */
public class InvariantList {

    // Reduction without generics, and with concurrency flaw!
    static Object reduce(List list, Function f, Object initVal) {
        synchronized (list) {
            Object result = initVal;
            for (Object o : list) {
                result = f.apply(result, o);
            }
            return result;
        }
    }

    // Reduction without generics or concurrency flaw!
    static Object reduce1(List list, Function f, Object initVal) {
        Object[] snapshot = list.toArray(); //Lock list internally
        Object result = initVal;
        for (Object o : list) {
            result = f.apply(result, o);
        }
        return result;
    }

    interface Function {

        Object apply(Object arg1, Object arg2);
    }

    interface Function1<T> {

        T apply(T arg1, T arg2);
    }

    // Naive generic version of reduction - won't compile!
    static <E> E reduce2(List<E> list, Function1<E> f, E initVal) {
        E[] snapshot = (E[]) list.toArray(); //Locks list , Unchecked cast
        E result = initVal;
        for (E o : list) {
            result = f.apply(result, o);
        }
        return result;
    }

    // List-based generic reduction
    static <E> E reduce3(List<E> list, Function1<E> f, E initVal) {
        List<E> snapshot;
        synchronized (list) {
            snapshot = new ArrayList<E>(list);
        }
        E result = initVal;
        for (E e : snapshot) {
            result = f.apply(result, e);
        }
        return result;
    }

    public static void main(String[] args) {
        // Fails at runtime!
        Object[] objectArray = new Long[1];
        objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

        // Won't compile!
        // List<Object> ol = new ArrayList<Long>(); // Incompatible types
        // ol.add("I don't fit in");

        // Why generic array creation is illegal - won't compile
        //List<String>[] stringLists = new List<String>[1];
        //List<Integer> intList = Arrays.asList(42);
        //Object[] objects = stringLists;
        //objects[0] = intList;
        //String s = stringLists[0].get(0);

    }

}
