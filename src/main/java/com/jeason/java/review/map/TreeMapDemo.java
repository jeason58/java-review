package com.jeason.java.review.map;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-24 13:45
 **/
public class TreeMapDemo {

  public static void main(String[] args) {
    Map map = new TreeMap();
    int loop = 100;

    try {
      Thread t1 = new Thread(() -> {
        for (int i = 1; i <= loop; i += 2) {
          map.put(Thread.currentThread().getName() + i, "value" + i);
          System.out.println(Thread.currentThread().getName() + " putted a value");
        }
      }, "t1$");

      Thread t2 = new Thread(() -> {
        for (int i = 2; i <= loop; i += 2) {
          map.put(Thread.currentThread().getName() + i, "value" + i);
          System.out.println(Thread.currentThread().getName() + " putted a value");
        }
      }, "t2$");

      t1.start();
      t2.start();

      t1.join();
      t2.join();

      System.out.println(" ---------------- print ----------------");
      Set<Entry> entrySet = map.entrySet();
      for (Entry entry : entrySet) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
