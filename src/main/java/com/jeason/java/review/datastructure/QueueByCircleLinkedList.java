package com.jeason.java.review.datastructure;


/**
 * 利用循环单链表实现一个队列
 *
 * 思想：
 * Queue内部是一个循环单链表，head为队列头指针，tail为队列尾指针
 *
 * 1) 初始状态下：head指向链表头，tail为null
 * 2) 入队操作：
 *   - 首先判断tail是否null，若tail=null，表明队列为空，将tail=head；
 *   - 否则判断tail.next是否为head，若tail.next=head，表明队列已满，则新增节点；
 *   - 否则，tail = tail.next; tail.value = newValue; （尾指针后移，并且赋值为入队的新值）
 *
 * 3) 出队操作：
 *   - 若队列已空，直接返回-1；
 *   - 将当前head.value返回，并且判断head是否==tail，若head==tail，则表明当前出队元素是最后一个元素，将tail赋值为null；
 *   - 否则，head=head.next（头指针后移）
 *
 *
 * 4) 判断队列是否已满：tail.next == head ?
 * 5) 判断队列是否已空：tail == null ?
 * */

public class QueueByCircleLinkedList {
    private final int DEFAULT_CAP = 10;
    private int cap;
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
        if (head != null) {
            return;
        }

        Node p = (head = new Node(0));
        for (int i = 0; i < this.cap; i++) {
            p.next = new Node(0);
            p = p.next;
        }
    }

    /**
     * 判断链表是否为空
     * 初始状态下：head——>root, tail——>null
     * 入队操作：tail——>head(tail=null时) or tail.next=newNode; tail=tail.next;
     * 出队操作：head=head.next
     * */
    public boolean empty() {
        return (tail == null);
    }

    public boolean full() {
        return (tail != null && tail.next == head);
    }

    public synchronized void offer(int n) {
        if (head == null) {
            lazyInit();
        }

        if (empty()) {
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

        if (head == tail) {
            tail = null;
        } else {
            head = head.next;
        }

        return res;
    }

    public static void main(String[] args) {
        QueueByCircleLinkedList queue = new QueueByCircleLinkedList(10);

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                try {
                    queue.offer(i);
                    System.out.printf("Thread1 - 执行入队: %2d\n", i);
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
                    System.out.printf("Thread2 - 执行出队: %2d\n", n);
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
