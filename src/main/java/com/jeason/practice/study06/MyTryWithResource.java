package com.jeason.practice.study06;


/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-01 16:20
 **/
public class MyTryWithResource {

  public static void main(String[] args) {
    try (MyResource resource = new MyResource()) {
      resource.setName("total");
      resource.setValue(100);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  private static class MyResource implements AutoCloseable {
    private String name;
    private int value;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    @Override
    public void close() throws Exception {
      System.out.println("resource is closed!");
    }
  }

}
