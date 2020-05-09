package com.jeason.java.review.datastructure;


import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;

/**
 * 利用循环单链表实现一个队列
 * <p>
 * 思想：
 * Queue内部是一个循环单链表，head为队列头指针，tail为队列尾指针，队列尾最后一个节点不存数据
 * 入队操作：tail指向节点存储当前值，tail节点后移
 * 出队操作：head指向的元素出队，head指针后移
 * <p>
 * 判断队列空或满：
 * 1）当head==tail时，队列空
 * 2）由于队列最后一个节点不存储数据，所以当队列满时，tail指针的下一个节点指向head
 */

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
        Node p = (head = new Node(0));
        for (int i = 0; i < this.cap; i++) {
            p.next = new Node(0);
            p = p.next;
        }
        p.next = head;

        tail = head;
    }

    public synchronized void offer(int n) throws InterruptedException {
        if (head == null) {
            lazyInit();
        }

        while (tail.next == head) {
            this.wait();
        }

        tail.value = n;
        tail = tail.next;
        notifyAll();
    }

    public synchronized int poll() throws InterruptedException {
        while (head == tail) {
            this.wait();
        }

        int res = head.value;
        head = head.next;
        notifyAll();
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        QueueByCircleLinkedList queue = new QueueByCircleLinkedList(10);
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                for (int i = 1; i <= 100; i++) {
                    queue.offer(i);
                    System.out.printf("Thread1 - 执行入队: %2d\n", i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    int n = queue.poll();
                    System.out.printf("Thread2 - 执行出队: %2d\n", n);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();

        latch.await();
    }

}
