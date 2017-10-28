package com.shixzh.java.interview.linear;

/**
 * 编程实现单向链表，并实现单向链表的反转。比如一个链表是这样的：1->2->3->4->5 通过反转后成为5->4->3->2->1
 * 注：即实现单向链表类，在该类中提供一个单向链表反转的方法，请写出完整代码。
 * 
 * @author Shixiang
 *
 */
public class ReverseUnidirectionalLinkedList {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);

		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5);

		printLinkedList(n1);
		Node newNode = recursivelyReverse(n1);
		printLinkedList(newNode);
		
		Node oldNode = reverse(newNode);
		printLinkedList(oldNode);
	}

	private static Node reverse(Node head) {
		
		Node pre = head;
		Node cur = head.getNext();
		Node post = null;
		//save 3 node: pre, cur, post
		while(cur != null) {
			post = cur.getNext();
			cur.setNext(pre);
			pre = cur;
			cur = post;
		}
		head.setNext(null);
		return pre;
	}

	private static Node recursivelyReverse(Node head) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		
		Node reHead = recursivelyReverse(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return reHead;
	}

	public static void printLinkedList(Node head) {
		while (null != head) {
			System.out.print(head.getData());
			head = head.getNext();
			if (null != head) {
				System.out.print("->");
			}
		}
		System.out.println();
	}
}
