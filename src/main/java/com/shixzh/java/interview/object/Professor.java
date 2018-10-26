package com.shixzh.java.interview.object;

/**
 * class: Professor <br>
 * 复制对象中的引用
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/25 20:14
 */
public class Professor implements Cloneable{
    String name;
    int age;

    Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Object clone(){
        Professor p = null;
        try{
            p = (Professor) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}

class Student2 implements Cloneable {
    /**
     * 常量对象
     */
    String name;
    int age;
    /**
     * 学生1 和学生2 的引用值都是一样的
     */
    Professor p;

    Student2(String name, int age, Professor p) {
        this.name = name;
        this.age = age;
        this.p = p;
    }

    @Override
    public Object clone() {
        Student2 o = null;
        try {
            o = (Student2) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }
        // 复制对象中的引用
        o.p = (Professor) p.clone();
        return o;
    }

    public static void main(String[] args) {
        Professor p = new Professor("wangwu", 50);
        Student2 s1 = new Student2("zhangsan", 18, p);
        Student2 s2 = (Student2) s1.clone();
        s2.p.name = "lisi";
        s2.p.age = 30;
        // 修改学生2的教授属性，不影响学生1
        System.out.println("name=" + s1.p.name + "," + "age=" + s1.p.age);
        System.out.println("name=" + s2.p.name + "," + "age=" + s2.p.age);
    }
}