package com.jeason.java.review.datastructure;


/**
 * 单链表逆置
 * */
public class LinkedListReverse {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode initListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = null, cur = null;
        for (int i=0; i<arr.length; i++) {
            if ( i == 0) {
                cur = new ListNode(arr[i]);
                head = cur;
            } else {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        return head;
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null, cur = head, next = cur.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;

        return cur;
    }

    public static void displayList(ListNode head) {
        while (head != null) {
            System.out.printf("%2d\t", head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = initListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        displayList(head);

        ListNode revHead = reverseList(head);
        displayList(revHead);
    }
}
