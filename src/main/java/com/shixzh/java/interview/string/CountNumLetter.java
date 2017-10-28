package com.shixzh.java.interview.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 一个字符串，包含了空格等标点符号，写一个函数计算出出现次数最多的字母和该字母出现的次数。
 * @author Shixiang
 *
 */
public class CountNumLetter {

	public static void main(String[] args) {
		String testStr = "i love you, do you?";
		Map<Character, Integer> map = countNum(testStr);
		getMaxValue(map);
	}

	public static Map<Character, Integer> countNum(String line) {
		Map<Character, Integer> letterMap = new HashMap<>();
		char[] charLine = line.toCharArray();
		for (int i = 0; i < charLine.length; i++) {
			char key = charLine[i];
			if (key < 'A' || key > 'z') {
				continue;
			}
			if (!letterMap.containsKey(key)) {
				letterMap.put(key, 1);
			} else {
				letterMap.replace(key, letterMap.get(key) + 1);
			}
		}
		return letterMap;
	}

	public static void getMaxValue(Map<Character, Integer> map) {
		int max = 0;
		char key = 0;
		for (Entry<Character, Integer> entry : map.entrySet()) {
			int value = entry.getValue();
			if (value > max) {
				max = value;
				key = entry.getKey();
			}
		}
		System.out.println("The most frequent letter is " + key);
		System.out.println("The times is " + max);
	}
}
