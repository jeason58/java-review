package com.jeason.java.review.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-23 18:17
 **/
public class ConcurrentHashMapStudy {

  public static void main(String[] args) {
    Map map = new ConcurrentHashMap();

    Object v = map.put(null, "NULL Value");

    System.out.println(v + ": " + map.get(null));
  }
}
