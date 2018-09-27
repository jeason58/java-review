package com.jeason.practice.study04;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-02 10:37
 **/
public class MyThreadPoolTest {
  private static MyThreadPool threadPool = new MyThreadPool();
  private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

  public static void main(String[] args) {

    for (int i = 1; i <= 10; i++) {
      Thread job = new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + " is running...");
      }, "job_" + i);
      threadPool.execute(job);
    }

    int coreNum = Runtime.getRuntime().availableProcessors();
    System.out.println("coreNum: " + coreNum);
  }

}
