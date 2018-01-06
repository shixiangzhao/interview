package com.shixzh.java.interview.object;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第8条：覆盖equals时请遵守通用约定
 * 违反传递性
 * 
 * @author shixiang.zhao
 */
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private static final Set<Point> unitCircle;
    static {
        unitCircle = new HashSet<Point>();
        unitCircle.add(new Point(1, 0));
        unitCircle.add(new Point(0, 1));
        unitCircle.add(new Point(-1, 0));
        unitCircle.add(new Point(0, -1));
    }

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }

    //违反里氏代换原则（Liskov substitution principle）,适用于父类的方法不适用于子类了。
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }

    //@Override
    public boolean equals1(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }

    @Override
    public int hashCode() {
        return (x + 7) % (y + 17);
    }

    public static void main(String[] args) {
        ColorPoint cp = new ColorPoint(1, 0, Color.WHITE);
        Point p = new Point(1, 0);
        ColorPoint cp1 = new ColorPoint(1, 0, Color.RED);
        Point p1 = new Point(1, 0);
        CounterPoint ctp = new CounterPoint(1, 0);
        System.out.println("cp.equals(p):" + cp.equals(p));
        System.out.println("cp1.equals(p):" + cp1.equals(p));
        System.out.println("cp.equals(cp1):" + cp.equals(cp1));
        System.out.println("p1.equals(p):" + p1.equals(p));
        //重写equals必须重写hashCode否则不能作用于HashSet，HaseTable，HashMap
        System.out.println("unitCircle.contains(p1):" + unitCircle.contains(p1));
        System.out.println("unitCircle.contains(ctp):" + unitCircle.contains(ctp));

    }
}

/**
 * 违反传递性：cp.equals(p)为true，cp1.equals(p)为true，cp.equals(cp1)却为false
 * 
 * @author shixiang.zhao
 */
class ColorPoint extends Point {

    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    // Not override - violates transitivity 违反传递性
    // Broken - violates symmetry!
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        if (!(o instanceof ColorPoint)) {
            return o.equals(this);
        }
        ColorPoint cp = (ColorPoint) o;
        return super.equals(o) && cp.color == color;
    }
}

enum Color {
    RED, WHITE
}

/**
 * 违反里氏代换原则：unitCircle.contains(p1)为true，unitCircle.contains(ctp)却为false
 * 
 * @author shixiang.zhao
 */
class CounterPoint extends Point {

    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public int numberCreated() {
        return counter.get();
    }
}

/**
 * 聚合优于继承，就在于equals()方法可以随意重写
 * @author shixiang.zhao
 */
class ColorPoint1 {

    private Point point;
    private Color color;

    public ColorPoint1(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint1)) {
            return false;
        }
        ColorPoint1 cp1 = (ColorPoint1)o;
        return cp1.point.equals(point) && cp1.color.equals(color);
    }
}
