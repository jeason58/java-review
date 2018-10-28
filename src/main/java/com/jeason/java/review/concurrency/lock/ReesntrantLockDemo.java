package com.jeason.java.review.concurrency.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: jeason
 * @Date: 2018/10/25 11:09
 * @Description:
 */
public class ReesntrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(false);
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 1; i <= 100 ; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    System.out.println("i am " + Thread.currentThread().getName());
//                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    latch.countDown();
                }
            }, "t-"+i).start();
        }

        try {
            latch.await();
            System.out.println("over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
