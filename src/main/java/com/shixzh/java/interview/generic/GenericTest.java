package com.shixzh.java.interview.generic;

public class GenericTest {

	public static void main(String[] args) {
		Shape s1 = new Shape(2);
		Shape s2 = new Shape(1);
		Shape s3 = new Shape(3);
		Shape[] ss = { s1, s2, s3 };
		Square sq1 = new Square(5, 2);
		Square sq2 = new Square(6, 2);
		Square sq3 = new Square(7, 2);
		Shape[] sqs = { sq1, sq2, sq3, s1 };
		System.out.println(findMax(ss).getSize());
		System.out.println(findMin(ss).getSize());
		System.out.println(findMax(sqs).getSize());
		System.out.println(findMin(sqs).getSize());

	}

	public static <AnyType> boolean contains(AnyType[] arr, AnyType x) {
		for (AnyType val : arr) {
			if (x.equals(val)) {
				return true;
			}
		}
		return false;
	}

	// 类型界限（type bound）
	public static <AnyType extends Comparable<AnyType>> AnyType findMax(AnyType[] arr) {
		int maxIndex = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].compareTo(arr[maxIndex]) > 0) {
				maxIndex = i;
			}
		}
		return arr[maxIndex];
	}

	// 类型界限（type bound），<? super AnyType>表示具体的泛型类型是AnyType或者AnyType的上层父类类型。
	public static <AnyType extends Comparable<? super AnyType>> AnyType findMin(AnyType[] arr) {
		int minIndex = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].compareTo(arr[minIndex]) < 0) {
				minIndex = i;
			}
		}
		return arr[minIndex];
	}
}
