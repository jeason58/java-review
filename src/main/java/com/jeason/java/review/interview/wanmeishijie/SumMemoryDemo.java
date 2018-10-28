package com.jeason.java.review.interview.wanmeishijie;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: jeason
 * @Date: 2018/10/28 21:30
 * @Description: 有4（N）台机器，现在要求用四个线程求出每台机器的内存，然后汇总求出总内存
 */
public class SumMemoryDemo {
    /**
     * 使用CyclicBarrier或者CountDownLatch同步工具类为主线程和子线程之间做同步
     * 共享变量sum使用integer的原子类型AtomicInteger
     * 使用volatile关键字修饰共享变量sum
     * */

    private static int machine_num = 100;
    public static class SumTask extends Thread {
        public static volatile AtomicInteger sum = new AtomicInteger(0);
        private int memory;
        private CyclicBarrier barrier;

        public SumTask(int memory, CyclicBarrier barrier) {
            this.memory = memory;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                sum.getAndAdd(memory);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(machine_num, new Thread(() ->
            System.out.println("sum memory is: " + SumTask.sum)
        ));

        for (int i = 1; i <= machine_num; i++) {
            new SumTask(i, barrier).start();
        }
    }
}
