package com.shixzh.java.interview.thread;

/**
 * 线程不应该一直处于忙-等（busy-wait）的状态，即反复地检查一个共享对象。
 * 如下反面例子SlowCountDownLatch，比CountDownLatch慢了大约2000倍。
 * 
 * @author Shixiang
 *
 */
public class SlowCountDownLatch {

	private int count;

	public SlowCountDownLatch(int count) {
		if (count < 0) {
			throw new IllegalArgumentException(count + " < 0");
		}
		this.count = count;
	}

	public void await() {
		while (true) {
			synchronized (this) {
				if (count == 0)
					return;
			}
		}
	}

	public synchronized void countDown() {
		if (count != 0)
			count--;
	}
}
