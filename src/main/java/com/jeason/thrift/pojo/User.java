package com.jeason.thrift.pojo;

import java.io.Serializable;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-03 09:50
 **/
public class User implements Serializable {
  private static final long serialVersionUID = -8278806441913136809L;
  private int id;
  private String name;
  private String gender;
  private int age;

  public User() {
  }

  public User(int id, String name, String gender, int age) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
