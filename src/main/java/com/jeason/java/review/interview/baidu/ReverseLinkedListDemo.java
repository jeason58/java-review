package com.jeason.java.review.interview.baidu;

/**
 * @Auther: jeason
 * @Date: 2018/11/1 23:12
 * @Description: 链表反转（逆置）
 */
public class ReverseLinkedListDemo {
    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public void print() {
            Node currNode = this;
            while (currNode != null) {
                System.out.printf("%2d\t", currNode.value);
                currNode = currNode.next;
            }
            System.out.println();
        }

        public Node reverse() {
            if (next == null) {
                return this;
            }

            Node last = null, current = this, nxt = next;
            while (nxt != null) {
                current.next = last;
                last = current;
                current = nxt;
                nxt = nxt.next;
            }
            current.next = last;

            return current;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        Node tail = head;
        for (int i=2; i<=10; i++) {
            tail.next = new Node(i);
            tail = tail.next;
        }

        Node h = head;
        h.print();

        h = head.reverse();
        h.print();
    }
}
