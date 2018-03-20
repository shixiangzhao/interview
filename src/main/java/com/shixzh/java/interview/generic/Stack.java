package com.shixzh.java.interview.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

/**
 * 第26条：优先考虑泛型 Initial attempt to generify Stack - won't compile!
 * 
 * @author shixiang.zhao
 */
public class Stack<E> {

	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public Stack() {
		// 不能创建不可具体化的（non-reifiable）类型数组
		// elements = new E[DEFAULT_INITIAL_CAPACITY];

		// The elements array will contain only E instances from push(E).
		// This is sufficient to ensure type safety, but the runtime
		// type of the array won't be E[]; it will always be Object[]!
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[--size];
	}

	public E pop1() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		E result = (E) elements[--size];
		elements[size] = null; // Eliminate obsolete reference
		return result;
	}

	private void ensureCapacity() {
		if (elements.length == size) {
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}

	@Override
	public Stack clone() {
		try {
			Stack result = (Stack) super.clone();
			// 包含引用类型，则递归调用clone()
			result.elements = elements.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	// pushAll method without wildcard type - deficient!
	public void pushAll(Iterable<E> src) {
		for (E e : src)
			push(e);
	}

	// wildcard type for parameter that serves as an E producer
	public void pushAll1(Iterable<? extends E> src) {
		for (E e : src)
			push(e);
	}

	// wildcard type for parameter that serves as an E consumer
	public void popAll(Collection<? super E> dst) {
		while (isEmpty()) {
			dst.add(pop());
		}
	}

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		for (String arg : args)
			stack.push(arg);
		while (!stack.isEmpty())
			System.out.println(stack.pop().toUpperCase());

		Stack<Number> numberStack = new Stack<Number>();
		List<Integer> list = new ArrayList<>();
		list.add(12);
		list.add(34);
		Iterable<Integer> integers = (Iterable<Integer>) list.iterator();
		// numberStack.pushAll(integers);
		numberStack.pushAll1(integers);
	}

	private boolean isEmpty() {
		return size == 0;
	}
}
