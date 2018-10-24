package com.jeason.java.review.streamapi;

import java.util.Optional;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-08-01 17:09
 **/
public class OptionalDemo {


  public static void main(String[] args) throws MyOptionalException {

    Optional<String> sOptional = Optional.ofNullable(null);

    String r = sOptional.orElse("Hello, world");
    System.out.println(r);

    r = sOptional.orElseGet(() -> "hello, Optional");
    System.out.println(r);

    r = sOptional.orElseThrow(() ->  new MyOptionalException("value cannot be null"));
    System.out.println(r);
  }


  private static class MyOptionalException extends Exception {

    public MyOptionalException(String message) {
      super(message);
    }
  }
}
