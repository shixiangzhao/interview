package com.shixzh.java.interview.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringUseless {

	public static void main(String[] args) {
		//stringAsAggregateType1();
		stringAsAggregateType2();
	}

	// 字符串不适合代替枚举和聚集类型
	public static void stringAsAggregateType1() {
		List<String> strList = new ArrayList<>();
		strList.add("str1");
		strList.add("str2");
		Iterator<String> it = strList.iterator();
		while (it.hasNext()) {
			String compoundKey = TestClass.class.getName() + "#" + it.next();
			System.out.println(compoundKey);
		}
	}

	// 聚集类型
	public static void stringAsAggregateType2() {
		List<String> strList = new ArrayList<>();
		strList.add("str1");
		strList.add("str2");
		Iterator<String> it = strList.iterator();
		while (it.hasNext()) {
			AggregateType at = new AggregateType(TestClass.class.getName(), '#', it.next());;
			System.out.println(at);
		}
	}

	private static class AggregateType {
		String className;
		char splitChar;
		String property;

		public AggregateType(String className, char splitChar, String property) {
			this.className = className;
			this.splitChar = splitChar;
			this.property = property;
		}

		@Override
		public String toString() {
			return className + splitChar + property;
		}
	}

}

class TestClass {

}