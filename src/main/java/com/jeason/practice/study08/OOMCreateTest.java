package com.jeason.practice.study08;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: 减小堆内存参数设置，模拟产生堆内存溢出
 * @VmArgs：-Xmx4m -Xms4m -Xmn2m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\\oomCreateTest.dump
 * @author: jeason·wang
 * @date: 2018-08-02 12:45
 **/
public class OOMCreateTest {
  static class OOMObject {
    String field = "dgziorghzrog;srog'ShgsoghsighsirghS:Ighsriug";
  }

  public static void main(String[] args) {
    List<OOMObject> list = new ArrayList<>();
    while (true) {
      list.add(new OOMObject());
    }
  }

}
