package com.jeason.java.review.map;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-24 14:20
 **/
public class LinkedHashMapDemo {

  public static void main(String[] args) {
    HashMap<String, Object> map = new LinkedHashMap();

    map.put("name", "jeasonwang");
    map.put("age", 24);
    map.put("gender", "male");

    map.forEach((k, v) -> System.out.println(k + ": " + v));
  }

}
