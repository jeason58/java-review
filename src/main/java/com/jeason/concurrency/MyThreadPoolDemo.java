package com.jeason.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description: 自定义一个简单的线程池
 * @author: jeason·wang
 * @date: 2018-08-02 10:37
 **/
public class MyThreadPoolDemo {
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

/** 线程池类 */
class MyThreadPool<Job extends Runnable> implements ThreadPool<Job> {
  private static final int MAX_WORKER_NUMBERS = 10;
  private static final int DEFAULT_WORKER_NUMBERS = 5;
  private static final int MIN_WORKER_NUMBERS = 1;

  private final LinkedList<Job> jobs = new LinkedList<>();
  private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

  private int workerNum;
  private AtomicLong threadNum = new AtomicLong();

  public MyThreadPool() {
    this(DEFAULT_WORKER_NUMBERS);
  }

  public MyThreadPool(int workerNum) {
    this.workerNum = workerNum;
    initWorkers(workerNum);
  }

  @Override
  public void execute(Job job) {
    if (job != null) synchronized (jobs) {
      jobs.addLast(job);
      jobs.notify();
    }
  }

  @Override
  public void shutdown() {
    for (Worker worker : workers) {
      worker.shutdown();
    }
  }

  @Override
  public void addWorkers(int num) {
    synchronized (jobs) {
      if (this.workerNum + num > MAX_WORKER_NUMBERS) {
        num = MAX_WORKER_NUMBERS - this.workerNum;
      }

      initWorkers(num);
      this.workerNum += num;
    }
  }

  @Override
  public void removeWorkers(int num) {
    synchronized (jobs) {
      if (num > this.workerNum) {
        throw new IllegalArgumentException("beyond workerNum");
      }

      int count = 0;
      while (count < num) {
        Worker worker = workers.get(count);
        if (workers.remove(worker)) {
          worker.shutdown();
          count ++;
        }
      }

      this.workerNum -= count;
    }
  }

  @Override
  public int getJobSize() {
    return jobs.size();
  }

  private void initWorkers(int num) {
    for (int i = 0; i < num; i++) {
      Worker worker = new Worker();
      workers.add(worker);
      Thread thread = new Thread(worker, "ThreadPool-Worker" + threadNum.incrementAndGet());
      thread.start();
    }
  }

  // 工作者线程类
  private class Worker implements Runnable {
    private volatile boolean running = true;

    @Override
    public void run() {
      Job job = null;
      synchronized (jobs) {
        while (jobs.isEmpty()) {
          try {
            jobs.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        job = jobs.removeFirst();
        if (job != null) {
          try {
            job.run();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }

    public void shutdown() {
      running = false;
    }
  }
}


/** 自定义线程池接口 */
interface ThreadPool<Job extends Runnable> {
  void execute(Job job);

  void shutdown();

  void addWorkers(int num);

  void removeWorkers(int num);

  int getJobSize();
}