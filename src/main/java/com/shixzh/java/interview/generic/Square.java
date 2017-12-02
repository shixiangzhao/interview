package com.shixzh.java.interview.generic;

public class Square extends Shape {

	private int length;
	
	public Square(int size, int length) {
		super(size);
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
