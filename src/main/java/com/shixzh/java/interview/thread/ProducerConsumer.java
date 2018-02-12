package com.shixzh.java.interview.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 友友系统面试题：生产者消费者模型
 * LinkedBlockingQueue阻塞队列大小的配置是可选的，如果我们初始化时指定一个大小，它就是有边界的，如果不指定，它就是无边界的。说是无边界，其实是采用了默认大小为Integer.MAX_VALUE的容量
 * 。它的内部实现是一个链表。 和ArrayBlockingQueue一样，LinkedBlockingQueue
 * 也是以先进先出的方式存储数据，最新插入的对象是尾部，最新移出的对象是头部。
 * 当一个线程试图对一个已经满了的队列进行入队列操作时，它将会被阻塞，除非有另一个线程做了出队列操作；同样，当一个线程试图对一个空队列进行出队列操作时，它将会被阻塞，除非有另一个线程进行了入队列操作。
 * 
 * @author Shixiang
 *
 */
public class ProducerConsumer {

	public static void main(String[] args) {

		ExecutorService service = Executors.newCachedThreadPool();

        Storage s = new Storage();

		Producer p = new Producer("张三", s);
		//Producer p2 = new Producer("李四", s);

		Consumer c = new Consumer("王五", s);
		Consumer c2 = new Consumer("老刘", s);
		Consumer c3 = new Consumer("老林", s);

		service.submit(p);
		service.submit(c);
		service.submit(c2);
		service.submit(c3);
	}
}

class Consumer implements Runnable {

    private String name;
    private Storage s;

    public Consumer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    public void run() {
        try {
            while (true) {
                System.out.println(name + " 准备消费产品");
                Product product = s.pop();
                System.out.println(name + " 已消费（" + product.toString() + "）");
                System.out.println("========================");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private String name;
    private Storage s;

    public Producer(String name, Storage s) {
        this.name = name;
        this.s = s;
    }

    public void run() {
        try {
            while (true) {
                Product product = new Product((int)(Math.random() * 10000));
                System.out.println(name + " 准备生产产品（" + product.toString() + "）");
                s.push(product);
                System.out.println(name + " 已生产（" + product.toString() + "）");
                System.out.println("========================");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Product {

    private int id;

    public Product(int id) {
        this.id = id;
    }

    public String toString() {
        return "产品： " + this.id;
    }
}

class Storage {

    BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(10);

    public void push(Product p) throws InterruptedException {
        queues.put(p);
    }

    public Product pop() throws InterruptedException {
        return queues.take();
    }
}
