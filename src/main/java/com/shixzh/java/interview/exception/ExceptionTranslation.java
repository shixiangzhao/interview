package com.shixzh.java.interview.exception;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ExceptionTranslation<E> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public E get(int index) {
		ListIterator<E> i = listIterator(index);
		try {
			return i.next();
		} catch (NoSuchElementException e) {
			throw new IndexOutOfBoundsException();
		}

	}

	private ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

//	public void exceptionChaining() {
//		try {
//			// Use lower-level abstraction to do our bidding
//		} catch (LowerLevelException cause) {
//			throw new HigherLevelException(cause);
//		}
//	}
}

class LowerLevelException extends Exception {
	public LowerLevelException() {
	}
}

class HigherLevelException extends Exception {
	HigherLevelException(LowerLevelException cause) {
		super(cause);
	}
}