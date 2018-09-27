package com.jeason.practice.study05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: jeason·wang
 * @date: 2018-07-31 15:06
 **/
public class StreamApi {

  public static void main(String[] args) {
    List<String> sList = Arrays.asList("java", "python", "php", "javascript", "golang");
    List<Map<String, Object>> mapList = new ArrayList<>();
    boolean isMale = true;
    for (int i=1; i<11; i++) {
      int index = i;
      boolean male = isMale;
      mapList.add(
          new HashMap<String, Object>(){
            {
              put("id", index);
              put("name", "name" + index);
              put("gender", male? "male" : "female");
              put("address", "address" + index);
            }
          }
      );
      isMale = !isMale;
    }

    // operate the sList
    List<String> newList = sList.stream().filter(s -> s.startsWith("java")).collect(Collectors.toList());
    newList.forEach(s -> System.out.printf("%s\t", s));
    System.out.println();

    newList = sList.stream().map(s -> s + "_programmer").collect(Collectors.toList());
    newList.forEach(s -> System.out.printf("%s\t", s));
    System.out.println();


    // operate the mapList
    System.out.println("\n===============================mapList操作===============================");
    mapList.forEach(map -> {
      map.forEach((k, v) -> System.out.printf("%s: %s\t", k, v));
      System.out.println();}
    );
    System.out.println();

    long maleCount = mapList.stream().filter(map -> "male".equals(map.get("gender"))).count();
    System.out.println("male count：" + maleCount);

    List<Integer> idList = mapList.stream().map(m -> (Integer) m.get("id")).collect(Collectors.toList());
    idList.forEach(id -> System.out.printf("%d\t", id));
    System.out.println();

    List<String> nameList = mapList.stream().map(m -> (String) m.get("name")).collect(Collectors.toList());
    nameList.forEach(name -> System.out.printf("%s\t", name));
    System.out.println();
  }

}
