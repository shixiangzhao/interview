package com.shixzh.java.interview.serializable;

import java.util.concurrent.atomic.AtomicReference;

public class AbstractFoo {

	private int x, y;

	private enum State {
		NEW, INITIALIZING, INITIALIZED
	}

	private final AtomicReference<State> init = new AtomicReference<State>(State.NEW);
	
	
}
