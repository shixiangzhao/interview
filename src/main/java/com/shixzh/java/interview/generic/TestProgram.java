package com.shixzh.java.interview.generic;

import java.util.Comparator;

class FunctionObject {

	public static <AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp) {
		int maxIndex = 0;
		for (int i = 1; i < arr.length; i++) {
			if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
				maxIndex = i;
			}
		}
		return arr[maxIndex];
	}
}

class CaseInsensitiveCompare implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return o1.compareToIgnoreCase(o2);
	}
}

public class TestProgram {
	public static void main(String[] args) {
		String[] arr = { "ZEBBA", "alligator", "crocodile" };
		System.out.println(FunctionObject.findMax(arr, new CaseInsensitiveCompare()));
	}
}
