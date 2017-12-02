package com.shixzh.java.interview.generic;

public class Shape implements Comparable<Shape>{

	private int size;
	
	public Shape(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public int compareTo(Shape o) {
		return compare(this.size, o.size);
	}
	
	public static int compare(int x, int y) {
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}

}
