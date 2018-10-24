package com.jeason.java.review.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-23 11:09
 **/
public class HashMapDemo {

  /**
   * HashMap: 内部基于一个动态的Node<K,V>数组，不支持线程同步，可以存放一个key为null的键值对，支持多个value为null的键值对
   * */
  public static void main(String[] args) {
    Map map = new HashMap();
    for (int i=1; i<12; i++) {
      map.put("key"+i, "value"+i);
    }
  }

  public static <K> int hash(K key) {
    int h;
    return (key == null)? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

  public static int indexFor(int hash, int n) {
    return (n - 1) & hash;
  }
}
