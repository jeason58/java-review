package com.jeason.practice.study06;


import java.util.Objects;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-31 18:22
 **/
public class MyDaemonThread {

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      try {
        int i = 0;
        for (; ;) {
          System.out.println(Thread.currentThread().getName() + " is running..." + "__" + (++i));
          Thread.sleep(3000);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println(Thread.currentThread().getName() + "'s finally block!");
      }
    }, "测试daemon线程");

    t1.setDaemon(true);

    t1.start();

    try {
      Thread.sleep(3000);
      System.out.println("...end!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
