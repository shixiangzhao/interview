package com.shixzh.java.interview.exception;

public class AbuseException {

	public static void main(String[] args) {
		Person p1 = new Person();
		Person p2 = new Person();

		Person[] range = { p1, p2 };
		try {
			int i = 0;
			while (true) {
				range[i++].climb();
			}
		} catch (ArrayIndexOutOfBoundsException e) {//Exception作为循环的终止条件
			//不要企图用java的错误判断机制来提高性能，对数组的遍历并不会有冗余检查，JVM已经优化了。
			System.out.println("cycle end");
		}

	}

	private static class Person {

		public void climb() {
			// TODO Auto-generated method stub

		}
	}
}
