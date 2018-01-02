package com.shixzh.java.interview.pattern;

/**
 * 第3条：用私有构造器或者枚举类型强化Singleton属性
 * 享有特权的客户端可以借助AccessibleObject.setAccessible方法，通过反射机制调用私有构造器
 * 要抵御这种攻击，可以修改构造器，让它要求创建第二个实例的时候抛出异常。
 * 
 * @author shixiang.zhao
 */
public class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {

    }

    public void leaveTheBuilding() {

    }
}

/**
 * 公有成员是个静态工厂方法
 * 
 * @author shixiang.zhao
 */
class Elvis1 {

    private static final Elvis1 INSTANCE = new Elvis1();

    private Elvis1() {

    }

    public Elvis1 getInstance() {
        return INSTANCE;
    }

    // implements Serializable需要声明所有实例都是瞬时（transient）的，并提供一个readResolve方法
    private Object readResolve() {
        // Return the one true Elvis and let the garbage collector
        // take care of the Elvis impersonator
        return INSTANCE;
    }

    public void leaveTheBuilding() {

    }
}

/**
 * 接近公有域方法，但更简洁，无偿提供了序列化机制。
 * 单元素的枚举类型已经成为实现Singleton的最佳方法。
 * 
 * @author shixiang.zhao
 */
enum Elvis2 {

    INSTANCE;

    public void leaveTheBuilding() {

    }
}
