package com.jeason.java.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-03 14:17
 **/
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
  }

}
