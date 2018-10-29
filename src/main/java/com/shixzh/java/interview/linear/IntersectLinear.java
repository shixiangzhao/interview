package com.shixzh.java.interview.linear;

/**
 * 如何判断两个单向链表相交，如何找相交点？
 * 1. 判断第一个链表的每个节点是否在第二个链表中；
 * 2. 把第二个链表连接到第一个后面，判断得到的链表是否有环，有环则相交；
 * 3. 先遍历第一个链表，记住最后一个节点，再遍历第二个链表，得到最后一个节点时和第一个链表的最后一个节点做比较，如果相同，则相交。（因为相交之后，合为一个链表）
 *
 * @author: ZhaoShixiang <br>
 * @date: 2018/10/29 15:11
 */
public class IntersectLinear {

    public static void main(String[] args) {
        // 第一条链表
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);

        // 第二条链表
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);

        n6.setNext(n7);
        n7.setNext(n8);
        n8.setNext(n9);
        n9.setNext(n3);

        //boolean i1 = isIntersect2(n1, n6);
        //boolean i2 = isIntersect2(n1, n6);
        //boolean i3 = isIntersect3(n1, n6);
        //System.out.println(i3);

        Node intersectNode = getIntersectNode(n1, n6);
        System.out.println("intersectNode = " + intersectNode.getData());
    }

    /**
     * 1. 判断第一个链表的每个节点是否在第二个链表中；
     *
     * @param n1
     * @param n2
     * @return boolean
     * @throws Exception ex
     * @method: isIntersect <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/10/29 15:22
     */
    public static boolean isIntersect(Node n1, Node n2) {
        Node p2 = n2;
        while (n1 != null && n1.getNext() != null) {
            System.out.println("n1 = " + n1.getData());
            while (n2 != null && n2.getNext() != null) {
                System.out.println("Compare n1=" + n1.getData() + " and n2=" + n2.getData());
                if (n1 == n2) {
                    return true;
                }
                n2 = n2.getNext();
            }
            n1 = n1.getNext();
            n2 = p2;
        }
        return false;
    }

    /**
     * 2. 把第二个链表连接到第一个后面，判断得到的链表是否有环，有环则相交；
     *
     * @param n1
     * @param n2
     * @return boolean
     * @throws Exception ex
     * @method: isIntersect2 <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/10/29 16:56
     */
    public static boolean isIntersect2(Node n1, Node n2) {
        Node head = n1;
        //取最后一个节点
        n1 = getLastNode(n1);
        // n1 最后一个节点指向 n2 第一个节点
        n1.setNext(n2);
        return isHaveCycle(head);
    }

    /**
     * 判断链表是否存在环
     *
     * @param head
     * @return boolean
     * @throws Exception ex
     * @method: isHaveCycle <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/10/29 19:48
     */
    private static boolean isHaveCycle(Node head) {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.getNext();
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            System.out.println("slow = " + (slow != null ? slow.getData() : null) + ", fast = " + (fast != null ? fast.getData() : null));
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 3. 先遍历第一个链表，记住最后一个节点，再遍历第二个链表，得到最后一个节点时和第一个链表的最后一个节点做比较，
     * 如果相同，则相交。（因为相交之后，合为一个链表）
     *
     * @param n1
     * @param n2
     * @return boolean
     * @throws Exception ex
     * @method: isIntersect3 <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/10/29 19:34
     */
    public static boolean isIntersect3(Node n1, Node n2) {
        n1 = getLastNode(n1);
        n2 = getLastNode(n2);
        System.out.println("n1 = " + n1.getData() + ", n2 = " + n2.getData());
        if (n1 == n2) {
            return true;
        }
        return false;
    }

    /**
     * 输入头结点，返回该链表的尾节点
     *
     * @param node
     * @return com.shixzh.java.interview.linear.Node
     * @throws Exception ex
     * @method: getLastNode <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/10/29 19:42
     */
    public static Node getLastNode(Node node) {
        while (node.getNext() != null) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * 扩展2：求两个链表相交的第一个节点
     * 思路：在判断是否相交的过程中要分别遍历两个链表，同时记录下各自长度。
     *
     * @param n1
     * @param n2
     * @return com.shixzh.java.interview.linear.Node
     * @throws Exception ex
     * @method: getIntersectNode <br>
     * @version 1.0.0 <br>
     * @author: ZhaoShixiang <br>
     * @date: 2018/10/29 20:11
     */
    public static Node getIntersectNode(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return null;
        }
        Node nHead1 = n1;
        Node nHead2 = n2;
        int len1 = 1;
        int len2 = 1;
        // 统计长度和尾节点
        while (n1.getNext() != null) {
            len1++;
            n1 = n1.getNext();
        }
        while (n2.getNext() != null) {
            len2++;
            n2 = n2.getNext();
        }
        // 尾节点相等，才存在相交
        // 切记：n1, n2 重新恢复为头结点
        if (n1 == n2) {
            int steps = Math.abs(len1 - len2);
            Node head = len1 > len2 ? nHead1 : nHead2;

            // 对齐处理
            while (steps != 0 && head != null) {
                head = head.getNext();
                steps--;
            }
            if (len1 > len2) {
                nHead1 = head;
            } else {
                nHead2 = head;
            }
            System.out.println("n1 = " + nHead1 + ", n2 = " + nHead2);
            // 对齐之后，逐一比较
            while (nHead1 != nHead2) {
                nHead1 = nHead1.getNext();
                nHead2 = nHead2.getNext();
            }
            return nHead1;
        }
        return null;
    }
}