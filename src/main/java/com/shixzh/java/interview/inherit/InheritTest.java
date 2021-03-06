package com.shixzh.java.interview.inherit;

/**
 * class: InheritTest <br>
 *     A a = new B();
 * 匹配类型用等号前的地址，调用方法用等号后的实体。
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/25 16:12
 */
public class InheritTest {

    public static void main(String[] args) {
        A a = new B();
        test(a);
    }

    public static void test(A a){
        System.out.println("test A");
        a.whoAmI();
    }

    public static void test(B b){
        System.out.println("test B");
        b.whoAmI();
    }
}
class A{
    public void whoAmI(){
        System.out.println("I am A");
    }
}
class B extends A{
    @Override
    public void whoAmI(){
        System.out.println("I am B");
    }

}