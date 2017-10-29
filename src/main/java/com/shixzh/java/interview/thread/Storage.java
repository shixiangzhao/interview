package com.shixzh.java.interview.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue阻塞队列大小的配置是可选的，如果我们初始化时指定一个大小，它就是有边界的，如果不指定，它就是无边界的。说是无边界，其实是采用了默认大小为Integer.MAX_VALUE的容量
 * 。它的内部实现是一个链表。 和ArrayBlockingQueue一样，LinkedBlockingQueue
 * 也是以先进先出的方式存储数据，最新插入的对象是尾部，最新移出的对象是头部。
 * 当一个线程试图对一个已经满了的队列进行入队列操作时，它将会被阻塞，除非有另一个线程做了出队列操作；同样，当一个线程试图对一个空队列进行出队列操作时，它将会被阻塞，除非有另一个线程进行了入队列操作。
 * 
 * @author Shixiang
 *
 */
public class Storage {

	BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(10);

	public void push(Product p) throws InterruptedException {
		queues.put(p);
	}

	public Product pop() throws InterruptedException {
		return queues.take();
	}
}
