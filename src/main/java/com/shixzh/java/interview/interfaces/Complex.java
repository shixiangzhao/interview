package com.shixzh.java.interview.interfaces;

/**
 * 第15条：使可变性最小化
 * 这个类表示一个复数，具有实部和虚部。像Integer, BigInteger, String都是不可变类
 * String的StringBuilder可变配套类，
 * 
 * @author shixiang.zhao
 */
public class Complex {

    //不可变对象本质上是线程安全的，可以被自由的共享，可以用下面方法鼓励客户端重新现有的实例
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);

    private final double re;
    private final double im;

    //    public Complex(double re, double im) {
    //        this.re = re;
    //        this.im = im;
    //    }

    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    //添加公有的静态工厂来替代公有的构造器，让类的所有构造器都变成私有的，这是让不可变的类变成final的另一种办法。
    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    //静态工厂方便添加扩展构造器，比如基于极坐标创建复数
    public static Complex valueOfPolar(double r, double theta) {
        return new Complex(r * Math.cos(theta), r * Math.sin(theta));
    }

    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }

    // 不可变类大都采用这种函数的（functional）做法，返回新的实例而不是修改原来的实例。
    public Complex add(Complex c) {
        return new Complex(re + c.re, im + c.im);
    }

    public Complex subtract(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }

    public Complex multiply(Complex c) {
        return new Complex(re * c.re - im * c.im, re * c.re + im * c.im);
    }

    public Complex divide(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re - im * c.im) / tmp, (re * c.re + im * c.im) / tmp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;
        return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
    }

    private int hashDouble(double val) {
        long longBits = Double.doubleToLongBits(re);
        return (int) (longBits ^ (longBits >>> 32));
    }

    @Override
    public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result + hashDouble(im);
        return result;
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
