package com.jeason.java.review.concurrency;

/**
 * @description:
 * 指令重排序问题测试，
 * 在两个线程中分别做两个无数据依赖的赋值操作，将会引起这两个操作的指令重排序
 * 如下两个线程分别作赋值操作，如果出现当变量a和变量b同时为0的情况，说明JVM对两个操作指令做了重排序
 * @author: jeason·wang
 * @date: 2018-07-30 14:18
 **/
public class InstructionReorderDemo {
  static int i = 0, j = 0;
  static int a = 0, b = 0;

  public static void main(String[] args) {
    Runnable task1 = () -> {
      i = 1;
      a = j;
    };

    Runnable task2 = () -> {
      j = 1;
      b = i;
    };

    int count = 0;
    while (true) {
      i = j = a = b = 0;
      Thread t1 = new Thread(task1);
      Thread t2 = new Thread(task2);

      t1.start();
      t2.start();

      try {
        t1.join();
        t2.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(++count + "#__ i: " + i + ", j: " + j);
      System.out.println(  count + "#__ a: " + a + ", b: " + b);

      if (a ==0 && b == 0) {
        break;
      }
    }

  }

}
