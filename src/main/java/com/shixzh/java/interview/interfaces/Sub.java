package com.shixzh.java.interview.interfaces;

import java.util.Date;

/**
 * 第17条：要么为继承而设计，并提供文档说明，要么就禁止继承
 * @author shixiang.zhao
 *
 */
public class Sub extends Super {

    private final Date date; //Blank final, set by constructor

    Sub() {
        date = new Date();
    }

    @Override
    public void overrideMe() {
        System.out.println(date);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        //Super() > Sub.overrideMe() > Sub();
        sub.overrideMe();

    }

}

class Super {

    // Broken - constructor invoke an overridable method
    public Super() {
        overrideMe();
    }

    public void overrideMe() {

    }
}
