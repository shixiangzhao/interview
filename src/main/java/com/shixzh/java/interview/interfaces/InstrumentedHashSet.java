package com.shixzh.java.interview.interfaces;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * 第16条：复合(composition)/转发(forwarding)优先于继承
 * @author shixiang.zhao
 *
 * @param <E>
 */
public class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public InstrumentedHashSet() {

    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println("Expert 3, but: " + s.getAddCount());
        System.out.println(s);

    }

}
