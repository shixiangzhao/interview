package com.shixzh.java.interview.generic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 第27条：优先考虑泛型方法
 * 
 * @author Shixiang
 *
 */
public class GenericFunction {

	// Uses raw types - unacceptable!(Item 23)
	public static Set union(Set s1, Set s2) {
		Set result = new HashSet(s1);
		result.addAll(s2);
		return result;
	}

	// Generic method
	public static <E> Set<E> union1(Set<E> s1, Set<E> s2) {
		Set<E> result = new HashSet<E>(s1);
		result.addAll(s2);
		return result;
	}

	// Parameterized type instance creation with constructor
	Map<String, List<String>> anagrams = new HashMap<String, List<String>>();

	// Generic static factory method
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	// Parameterized type instance creation with static factory
	Map<String, List<String>> anagrams1 = newHashMap();

	Map<String, List<String>> anagrams2 = new HashMap<>();

	public interface UnaryFunction<T> {
		T apply(T arg);
	}

	// Generic singleton factory pattern
	private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {
		public Object apply(Object arg) {
			return arg;
		}
	};

	// IDENTITY_fUNCTION is stateless and its type parameter is unbounded so
	// it's safe to share on instance across all types.
	@SuppressWarnings("unchecked")
	public static <T> UnaryFunction<T> identityFunction() {
		return (UnaryFunction<T>) IDENTITY_FUNCTION;
	}

	public interface Comparable<T> {
		int compareTo(T o);
	}
	
	// Return the maximum value in a list - uses recursive type bound递归类型限制
	public static <T extends Comparable<T>> T max(List<T> list) {
		Iterator<T> i = list.iterator();
		T result = i.next();
		while(i.hasNext()) {
			T t = i.next();
			if(t.compareTo(result) > 0)
				result = t;
		}
		return result;
	}

	public static void main(String[] args) {
		Set<String> guys = new HashSet<String>(Arrays.asList("Tom", "Dick", "Harry"));
		Set<String> stooges = new HashSet<String>(Arrays.asList("Tom1", "Dick1", "Harry1"));
		Set<String> aflCio = union(guys, stooges);
		System.out.println(aflCio);

		String[] strings = { "jue", "hemp", "nylon" };
		UnaryFunction<String> sameString = identityFunction();
		for (String s : strings)
			System.out.println(sameString.apply(s));

		Number[] numbers = { 1, 2.0, 3L };
		UnaryFunction<Number> sameNumber = identityFunction();
		for (Number n : numbers)
			System.out.println(sameNumber.apply(n));

	}

}
