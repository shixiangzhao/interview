package com.shixzh.java.interview.interfaces;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 第16条：复合(composition)/转发(forwarding)优先于继承
 * 
 * @author shixiang.zhao
 *
 * @param <E>
 */
public class InstrumentedHashSet<E> extends HashSet<E> {

	private static final long serialVersionUID = 1L;
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

		// 有了转发类，addAll()方法就可以不回调自己的add()方法啦。
		InstrumentedHashSet1<String> s1 = new InstrumentedHashSet1<>(new HashSet<String>());
		s1.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
		System.out.println("Expert 3, and: " + s1.getAddCount());

		// Set<Date> s3 = new InstrumentedHashSet1<>(new TreeSet<Date>(cmp));
		// Set<E> s4 = new InstrumentedHashSet1<E>(new HashSet<Date>(capacity));

	}

}

class InstrumentedHashSet1<E> extends ForwardingSet<E> {

	private int addCount = 0;

	public InstrumentedHashSet1(Set<E> s) {
		super(s);
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

}

// Reusable forwarding class
class ForwardingSet<E> implements Set<E> {

	// 复合composition/转发forwarding
	private final Set<E> s;

	public ForwardingSet(Set<E> s) {
		this.s = s;
	}

	@Override
	public int size() {
		return s.size();
	}

	@Override
	public boolean isEmpty() {
		return s.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return s.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return s.iterator();
	}

	@Override
	public Object[] toArray() {
		return s.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return s.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return s.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return s.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return s.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return s.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return s.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return s.removeAll(c);
	}

	@Override
	public void clear() {
		s.clear();
	}

}