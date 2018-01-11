package com.shixzh.java.interview.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 第25条：列表优先于数组
 * 
 * @author Shixiang
 *
 */
public class InvariantList {

	public static void main(String[] args) {
		// Fails at runtime!
		Object[] objectArray = new Long[1];
		objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

		// Won't compile!
		// List<Object> ol = new ArrayList<Long>(); // Incompatible types
		// ol.add("I don't fit in");

	}

}
