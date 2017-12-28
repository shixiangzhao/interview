package com.shixzh.java.interview.thread;

public class LazyInitialization {

	// 非延迟初始化
	private final FieldType field1 = computeFieldValue();

	private FieldType field2;

	// 普通的延迟初始化
	synchronized FieldType getField2() {
		if (field2 == null) {
			field2 = computeFieldValue();
		}
		return field2;
	}

	// 如果出于性能的考虑而需要对静态域使用延迟初始化，就使用lazy initialization holder class模式。
	private static class FieldHolder {

		static final FieldType field3 = computeFieldValue();
	}

	static FieldType getField3() {
		return FieldHolder.field3;
	}

	private volatile FieldType field4;

	FieldType getField4() {
		// 使用局部变量result确保field只在已经被初始化的情况下读取一次，比不用快了大约25%
		FieldType result = field4;
		if (result == null) { // First check(no locking)
			synchronized (this) {
				result = field4;
				if (result == null) { // Second check(with locking)
					field4 = result = computeFieldValue();
				}
			}
		}
		return result;
	}

	// Single-check idiom - can cause repeated initialization!
	private volatile FieldType field5;

	private FieldType getField5() {
		FieldType result = field5;
		if (result == null) {
			field5 = result = computeFieldValue();
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private static FieldType computeFieldValue() {
		// TODO Auto-generated method stub
		return null;
	}

}

class FieldType {

}
