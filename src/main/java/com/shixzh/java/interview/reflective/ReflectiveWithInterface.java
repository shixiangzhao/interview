package com.shixzh.java.interview.reflective;

import java.util.Arrays;
import java.util.Set;

/**
 * 第53条，接口优于反射机制
 * 1. 例子会产生3个运行时错误，如果不用反射，就会是编译时错误。
 * 2. 根据类名产生的实例需要20行冗余代码，
 * 
 * @author shixiang.zhao
 *
 */
public class ReflectiveWithInterface {

    // Reflective instantiation with interface access
    public static void main(String[] args) {
        // Translate the class name into a Class object
        Class<?> cl = null;
        try {
            cl = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not fount.");
            System.exit(1);
        }

        // Instantiate the class
        Set<String> s = null;
        try {
            s = (Set<String>) cl.newInstance();
        } catch (IllegalAccessException e) {
            System.out.println("Class not accessible.");
            System.exit(1);
        } catch (InstantiationException e) {
            System.out.println("Class not instantiable.");
            System.exit(1);
        }

        // Exercise the set
        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);

    }

}
class TestReflective {
    
}
