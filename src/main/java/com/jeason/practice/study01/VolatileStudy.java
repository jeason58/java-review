package com.jeason.practice.study01;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-23 10:09
 **/
public class VolatileStudy implements Runnable {
  private static volatile int count = 0;

  @Override
  public void run() {
    for (int i=0; i<1000; i++) {
      count++;
    }
  }


  public static void main(String[] args) {
    VolatileStudy vs = new VolatileStudy();
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
