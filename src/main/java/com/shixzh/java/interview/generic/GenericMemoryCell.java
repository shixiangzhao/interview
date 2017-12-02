package com.shixzh.java.interview.generic;

public class GenericMemoryCell<AnyType> {

	private AnyType storedValue;

	public AnyType read() {
		return storedValue;
	}

	public void write(AnyType x) {
		storedValue = x;
	}
	
	public static void main(String[] args) {
		GenericMemoryCell<Double> cell = new GenericMemoryCell<>();
		cell.write(4.5);
		//GenericMemoryCell<String>[] arr1 = new GenericMemoryCell<>[10];
		GenericMemoryCell<String>[] arr2 = new GenericMemoryCell[10];
		Object [] arr3 = arr2;
		arr3[0] = cell;
		String s = arr2[0].read();
	}
}
