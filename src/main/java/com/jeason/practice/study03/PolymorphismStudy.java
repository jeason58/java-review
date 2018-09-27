package com.jeason.practice.study03;

/**
 * @description: 理解多态
 * @author: jeason·wang
 * @date: 2018-07-24 14:30
 **/
public class PolymorphismStudy {

  static class Animal {
    void eat(String something) {
      System.out.println("animal is eating " + something);
    }

    void start(String sth) {
      eat(sth);
    }

  }

  static class Dog extends Animal {

    @Override
    void eat(String something) {
      System.out.println("dog is eating " + something);
    }
  }

  public static void main(String[] args) {
    Animal animal = new Dog();
    animal.start("meat");
  }

}
