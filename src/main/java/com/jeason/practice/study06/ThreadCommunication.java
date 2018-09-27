package com.jeason.practice.study06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-31 16:51
 **/
public class ThreadCommunication {
  int start = 0;
  boolean flag = true;
  int maxValue = 100;

  public static void main(String[] args) {
    ThreadCommunication tc = new ThreadCommunication();
    ReentrantLock lock = new ReentrantLock();

    // 偶数线程
    new Thread(() -> {
      while (tc.start <= tc.maxValue) {
        lock.lock();
        if (tc.flag) {
          if (tc.start <= tc.maxValue) {
            System.out.println(tc.start++);
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
            System.out.println(tc.start++);
          }
          tc.flag = true;
        }
        lock.unlock();
      }
    }, "odd").start();

    while (Thread.activeCount() > 2) {}
  }

}
