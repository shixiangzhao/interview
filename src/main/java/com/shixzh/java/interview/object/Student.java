package com.shixzh.java.interview.object;

/**
 * class: Student <br>
 * 复制对象的属性
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/25 20:08
 */
public class Student implements Cloneable{
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            //Object 中的clone()识别出你要复制的是哪一个对象。
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 18);
        Student s2 = (Student) s1.clone();
        s2.name = "lisi";
        s2.age = 20;
        //修改学生2后，不影响学生1的值。
        System.out.println("name=" + s1.name + "," + "age=" + s1.age);
        System.out.println("name=" + s2.name + "," + "age=" + s2.age);
    }
}