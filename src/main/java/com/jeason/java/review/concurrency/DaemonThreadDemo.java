package com.jeason.java.review.concurrency;


/**
 * @description: 在主线程推出之前，不会关心daemon线程的finally代码是否被执行，会直接退出
 * @author: jeason·wang
 * @date: 2018-07-31 18:22
 **/
public class DaemonThreadDemo {

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
