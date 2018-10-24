package com.jeason.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 18:02
 * @Description:
 */
public class CountDownLatchDemo {
    private static final int ThreadNum = 10000;
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(ThreadNum);
        for (int i=0; i<ThreadNum; i++) {
            new Thread(() -> {
                try {
                    count.incrementAndGet();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
            System.out.println("count: " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
