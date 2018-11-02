package com.jeason.java.review.algorithm;

/**
 * @Auther: jeason
 * @Date: 2018/11/2 10:04
 * @Description: 判断单链表是否有环
 */
public class JudgeLinkedListIsCircleDemo {
    private static class Node {
        int value;
        Node next;
        Node head;
        Node tail;

        public Node(int value) {
            this.value = value;
            this.head = this;
            this.tail = this;
        }

        public Node(int[] values) {
            if (values == null) {
                throw new IllegalArgumentException("values cannot be bull");
            }
            this.value = values[0];
            this.head = this;
            if (values.length < 2) return;
            Node t = this;
            for (int i=1; i<values.length; i++) {
                t.next = new Node(values[i]);
                t = t.next;
            }
            this.tail = t;
        }

        // 打印链表
        public void print() {
            Node h = this;
            while (h != null) {
                System.out.printf("%2d\t", h.value);
                h = h.next;
            }
            System.out.println();
        }
    }

    public static boolean isCircle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node slow = head.next;
        Node fast = slow.next;

        while (fast != null && slow != null) {
            if (fast == slow) {
                return true;
            } else {
                slow = slow.next;
                fast = fast.next;
                if (fast != null) {
                    fast = fast.next;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Node head = new Node(nums);
        head.print();

        boolean isCircle = isCircle(head);
        System.out.println("isCircle: " + isCircle);
    }
}
