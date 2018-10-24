package com.jeason.concurrency;

/**
 * @description: volatile关键字的原理：
 * volatile的作用有两种：1.保证共享变量的修改对其他线程的可见性；2.禁止指令的重排序优化
 * 注：但并不能将i++操作编程原子操作，所以即使使用了volatile关键字，在并发情况下还是会产生线程安全问题
 * @author: jeason·wang
 * @date: 2018-07-23 10:09
 **/
public class VolatileDemo implements Runnable {
  private static volatile int count = 0;

  @Override
  public void run() {
    for (int i=0; i<1000; i++) {
      count++;
    }
  }


  public static void main(String[] args) {
    VolatileDemo vs = new VolatileDemo();
    Thread t1 = new Thread(vs);
    Thread t2 = new Thread(vs);
    Thread t3 = new Thread(vs);
    Thread t4 = new Thread(vs);
    Thread t5 = new Thread(vs);

    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();

    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
      t5.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("count is: " + count);
  }
}
