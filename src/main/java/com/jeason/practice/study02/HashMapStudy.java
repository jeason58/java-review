package com.jeason.practice.study02;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-23 11:09
 **/
public class HashMapStudy {

  public static void main(String[] args) {
    Map map = new HashMap(19, 0.8f);
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
