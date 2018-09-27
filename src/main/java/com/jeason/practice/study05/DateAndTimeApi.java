package com.jeason.practice.study05;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-31 15:51
 **/
public class DateAndTimeApi {

  public static void main(String[] args) {
    Clock clock = Clock.systemDefaultZone();
    Instant instant = clock.instant();
    Date date = Date.from(instant);

    System.out.println("curr date: " + date);
  }

}
