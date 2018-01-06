package com.shixzh.java.interview.object;

import java.util.HashMap;
import java.util.Map;

/**
 * 第9条：覆盖equals时总要覆盖hashCode; 第10条：始终要覆盖toString; 第11条：谨慎地覆盖clone
 * 
 * @author Shixiang
 *
 */
public class PhoneNumber {

	private final short areaCode;
	private final short prefix;
	private final short lineNumber;

	public PhoneNumber(int areaCode, int prefix, int lineNumber) {
		rangeCheck(areaCode, 999, "areaCode");
		rangeCheck(prefix, 999, "prefix");
		rangeCheck(lineNumber, 9999, "lineNumber");
		this.areaCode = (short) areaCode;
		this.prefix = (short) prefix;
		this.lineNumber = (short) lineNumber;
	}

	private static void rangeCheck(int arg, int max, String name) {
		if (arg < 0 || arg > max) {
			throw new IllegalArgumentException(name + ": " + arg);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PhoneNumber)) {
			return false;
		}
		PhoneNumber pn = (PhoneNumber) o;
		return pn.lineNumber == lineNumber && pn.prefix == prefix && pn.areaCode == areaCode;

	}

	// 如果计算复杂可以保存hashCode，只在第一次时计算
	private volatile int hashCode;

	@Override
	public int hashCode() {
		int result = hashCode;
		if (result == 0) {
			result = 17;
			// result = 31 * result + c;将各个域算出的hash code合并
			result = 31 * result + areaCode;
			result = 31 * result + prefix;
			result = 31 * result + lineNumber;
			return result;
		}
		return result;
	}

	@Override
	public String toString() {
		// return "(" + areaCode + ")" + prefix + "-" + lineNumber;
		return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
	}

	@Override
	public PhoneNumber clone() {
		try {
			return (PhoneNumber) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(); // Can't happen
		}
	}

	public static void main(String[] args) {
		Map<PhoneNumber, String> map = new HashMap<PhoneNumber, String>();
		map.put(new PhoneNumber(707, 867, 5309), "Jenny");
		// JavaSE6约定：相同对象必须拥有相同的hash code
		System.out.println(
				"Not overrite hashcode(), expert Jenny, but null: " + map.get(new PhoneNumber(707, 867, 5309)));
		System.out.println(new PhoneNumber(707, 867, 5309));
	}

}
