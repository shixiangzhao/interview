package com.shixzh.java.interview.pattern;

public class Student {

    private final String name;
    private final int age;

    // Builder的核心就是使用特定的类来构造自身，是的构造过程更加灵活，而且可以在每个参数的构造方法中加入校验
    private Student(StudentBuilder sb) {
        this.name = sb.name;
        this.age = sb.age;
    }

    public static class StudentBuilder implements Builder1<Student> {

        private String name;
        private int age;

        public StudentBuilder buildName(String n) {
            this.name = n;
            return this;
        }

        public StudentBuilder buildAge(int a) {
            this.age = a;
            return this;
        }

        @Override
        public Student build() {
            return new Student(this);
        }
    }

    public static void main(String[] args) {
        Student stu = new Student.StudentBuilder().buildName("WangXiao").build();
    }
}

interface Builder1<T> {

    T build();
}
