package com.shixzh.java.interview.generic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 第24条：消除非受检警告
 * 
 * @author Shixiang
 *
 * @param <U>
 */
public class UncheckedCastWarning<U> {

	private static int size;
	private U[] elements;

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(elements, size, a.getClass());
		}
		System.arraycopy(elements, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	// Adding local variable to reduce scope of @SuppressWarnings
	public <T> T[] toArray1(T[] a) {
		if (a.length < size) {
			// This cast is correct because because the array we're creating
			// is of the same type as the one passed in, which is T[].
			@SuppressWarnings("unchecked")
			T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
			return result;
		}
		System.arraycopy(elements, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	public static void main(String[] args) {
		Set<Lark> exaltation = new HashSet();
		Set<Lark> exaltation1 = new HashSet<>();
	}

	static class Lark {

	}
}
