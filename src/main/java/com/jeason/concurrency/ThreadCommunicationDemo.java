package com.jeason.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 使用共享变量flag完成奇偶线程间的通信，
 * @author: jeason·wang
 * @date: 2018-07-31 16:51
 **/
public class ThreadCommunicationDemo {
  int start = 0;
  boolean flag = true;
  int maxValue = 100;

  public static void main(String[] args) {
    ThreadCommunicationDemo tc = new ThreadCommunicationDemo();
    ReentrantLock lock = new ReentrantLock();

    // 偶数线程
    new Thread(() -> {
      while (tc.start <= tc.maxValue) {
        lock.lock();
        if (tc.flag) {
          if (tc.start <= tc.maxValue) {
            System.out.printf("%4s : %2d\n", Thread.currentThread().getName(), tc.start++);
          }
          tc.flag = false;
        }
        lock.unlock();
      }
    }, "even").start();


    // 奇数线程
    new Thread(() -> {
      while (tc.start <= tc.maxValue) {
        lock.lock();
        if (!tc.flag) {
          if (tc.start <= tc.maxValue) {
            System.out.printf("%4s : %2d\n", Thread.currentThread().getName(), tc.start++);
          }
          tc.flag = true;
        }
        lock.unlock();
      }
    }, "odd").start();

    while (Thread.activeCount() > 2) {}
  }

}
