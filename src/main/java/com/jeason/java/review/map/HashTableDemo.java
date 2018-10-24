package com.jeason.java.review.map;

import java.util.Hashtable;

/**
 * @Auther: jeason
 * @Date: 2018/10/24 22:19
 * @Description:
 */
public class HashTableDemo {

    /**
     * HashTable是一个线程安全字典结构，线程安全采用synchronized来实现，内部结构基于一个静态内部类Entry<K,V>的数组
     * */
    public static void main(String[] args) {
        Hashtable<String, String> table = new Hashtable<>();
        table.put("name", "jaason");
    }
}
