package com.jeason.java.review.algorithm;


/**
 * 利用循环单链表实现一个队列
 * todo：测试有问题，待修复
 * */

public class QueueByCircleLinkedList {
    private final int DEFAULT_CAP = 10;
    private int cap;
    private Node root;
    private Node head, tail;

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public QueueByCircleLinkedList() {
        this.cap = DEFAULT_CAP;
    }

    public QueueByCircleLinkedList(int cap) {
        if (cap <= 0) {
            cap = DEFAULT_CAP;
        }
        this.cap = cap;
    }

    private void lazyInit() {
        if (root != null) {
            return;
        }

        Node p = root;
        for (int i = 0; i < this.cap; i++) {
            if (p == null) {
                root = p = new Node(0);
            } else {
                p.next = new Node(0);
                p = p.next;
            }
        }

        head = root;
    }

    /**
     * 判断链表是否为空
     * 初始状态下：head——>root, tail——>null
     * 入队操作：tail——>head(tail=null时) or tail.next=newNode; tail=tail.next;
     * 出队操作：head=head.next
     * */
    public boolean empty() {
        return (tail == null) || (tail == head && head == root);
    }

    public boolean full() {
        return (tail != null && tail.next == head);
    }

    public synchronized void offer(int n) {
        if (root == null) {
            lazyInit();
        }

        if (tail == null) {
            tail = head;
            tail.value = n;
        } else if (full()) {
            Node node = new Node(n);
            node.next = head;
            tail.next = node;
            tail = tail.next;
        } else {
            tail = tail.next;
            tail.value = n;
        }

    }

    public synchronized int poll() {
        if (empty()) {
            return -1;
        }

        int res = head.value;
        head = head.next;

        return res;
    }

    public static void main(String[] args) {
        QueueByCircleLinkedList queue = new QueueByCircleLinkedList(10);

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                try {
                    queue.offer(i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    int n = queue.poll();
                    if (n == -1) {
                        Thread.sleep(500);
                        continue;
                    }
                    System.out.printf("Thread2 poll: %2d\n", n);
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
