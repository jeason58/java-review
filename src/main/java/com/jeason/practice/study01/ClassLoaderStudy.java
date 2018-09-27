package com.jeason.practice.study01;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-19 19:58
 **/
public class ClassLoaderStudy {

  public static void main(String[] args) {
    System.out.println(ConstantClass.VALUE);
  }
}


class ConstantClass {
  static {
    System.out.println("ConstantClass init...");
  }

  public static final String VALUE = "Hello, World";
}