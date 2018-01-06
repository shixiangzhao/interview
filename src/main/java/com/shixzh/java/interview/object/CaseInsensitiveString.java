package com.shixzh.java.interview.object;

import java.util.ArrayList;
import java.util.List;

/**
 * 第8条：覆盖equals时请遵守通用约定
 * Broken - violates symmetry!
 * equals()违反对称性
 * 第12条：考虑实现Comparable接口
 * 
 * @author shixiang.zhao
 */
public class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {

    private final String s;

    public CaseInsensitiveString(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        this.s = s;
    }

    //Broken - violates symmetry!
    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        }
        if (o instanceof String) {//one-way interoperability!
            return s.equalsIgnoreCase((String) o);
        }
        return false;
    }

    //Improvement - good
    public boolean equals1(Object o) {
        return o instanceof CaseInsensitiveString &&
                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        CaseInsensitiveString cis1 = new CaseInsensitiveString("polish");
        String s = "polish";
        System.out.println(cis.equals(cis1));
        System.out.println(cis1.equals(cis));
        System.out.println(cis.equals(s));
        System.out.println(s.equals(cis));

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);
        //一旦违反了equals约定，当其他对象面对你的对象时，你完全不知道这些对象的行为会怎么样。
        System.out.println(list.contains(s));
    }

    @Override
    public int compareTo(CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
}
