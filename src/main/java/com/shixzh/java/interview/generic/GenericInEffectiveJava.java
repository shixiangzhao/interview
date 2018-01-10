package com.shixzh.java.interview.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 第23条：请不要在新代码中使用原生态类型（相对于泛型<>）
 * 
 * @author Shixiang
 *
 */
public class GenericInEffectiveJava {

	private static final Collection stamps = new ArrayList();
	private static final Collection<Stamp> stamps1 = new ArrayList();

	// Use of raw type for unknown element type - don't do this
	static int numElementsInCommon(Set s1, Set s2) {
		int result = 0;
		for (Object o1 : s1)
			if (s2.contains(o1))
				result++;
		return result;
	}

	// Unbounded wildcard type - typesafe and flexible通配符类型（bounded wildcard type）
	static int numElementsInCommon1(Set<?> s1, Set<?> s2) {
		int result = 0;
		for (Object o1 : s1) {
			if (s2.contains(o1))
				result++;
			// Legitimate use of raw type - instanceof operator
			if (o1 instanceof Set) // row type
				s1 = (Set<?>) o1; // wildcard type
		}
		return result;
	}

	public static void main(String[] args) {
		// Erroneous insertion of coin into stamp collection
		stamps.add(new Coin());
		// Not applicable for the arguments (Coin)
		// stamps1.add(new Coin());

		// Now a raw iterator type - don't do this!
		for (Iterator i = stamps.iterator(); i.hasNext();) {
			Stamp s = (Stamp) i.next();// Throws ClassCaseException
		}
		// for-each loop over a parameterized collection - typesafe
		for (Stamp s : stamps1) { // no cast

		}
		// for loop with parameterized iterator declaration - typesafe
		for (Iterator<Stamp> i = stamps.iterator(); i.hasNext();) {
			Stamp s = i.next();// no cast necessary
		}

	}

}

class Coin {

}

class Stamp {

}
