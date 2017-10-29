package com.shixzh.java.interview.thread;

public class Producer implements Runnable {

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
