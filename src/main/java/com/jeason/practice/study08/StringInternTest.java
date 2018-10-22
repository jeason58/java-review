package com.jeason.practice.study08;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 13:54
 * @Description:
 */
public class StringInternTest {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println("s1 == s2: " + (s1 == s2));  // false

        String s3 = s1.intern();
        System.out.println("s1 == s3: " + (s1 == s3));  // true
        System.out.println("s2 == s3: " + (s2 == s3));  // false

        String s4 = s2.intern();
        System.out.println("s1 == s4: " + (s1 == s4));  // true
    }
}
