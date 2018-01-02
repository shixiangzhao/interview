package com.shixzh.java.interview.pattern;

/**
 * Noninstantiable utility class
 * 副作用，不能被子类化，因为所有子类的构造器都必须显式或者隐式的调用超类（superclass）构造器
 * 
 * @author shixiang.zhao
 */
public class UtilityClass {

    // Suppress default constructo0r for nonistantiability
    private UtilityClass() {
        throw new AssertionError();
    }

}
