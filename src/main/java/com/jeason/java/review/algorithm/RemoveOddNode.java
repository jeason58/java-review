package com.jeason.java.review.algorithm;

public class RemoveOddNode {

    static class Node<K, V> {
        int value;
        Node next;

        Node(int val) {
            this.value = val;
            this.next = null;
        }
    }

    public static Node removeOddNode(Node head) {
        Node pre = null, p = head;
        while (p != null) {
            if (pre == null) {
                pre = p.next;
                p = pre.next;
                head = pre;
            } else {
                pre.next = p.next;
                pre = pre.next;
                if (pre != null) {
                    p = pre.next;
                } else {
                    p = null;
                }
            }
        }

        return head;
    }

    public static void displayNode(Node head) {
        for (Node p = head; p != null; p=p.next) {
            System.out.printf("%2d\t", p.value);
        }

        System.out.println();
    }

    public static Node genLinkedNode(int[] nums) {
        Node head = null, p=null;
        for (int i=0; i<nums.length; i++) {
            Node node = new Node(nums[i]);

            if (p == null) {
                head = p = node;
            } else {
                p.next = node;
                p = p.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Node head = genLinkedNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        displayNode(head);

        Node newHead = removeOddNode(head);
        displayNode(newHead);
    }
}
