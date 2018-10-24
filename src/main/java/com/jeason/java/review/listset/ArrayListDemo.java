package com.jeason.java.review.listset;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Auther: jeason
 * @Date: 2018/10/24 17:36
 * @Description: ArrayList数组内部原理如下：
 * ArrayList基于动态数组，默认构造方法返回的对象内部是一个空数组，每一次add()操作的时候，会对数组容量进行判断，
 * 若需要的最小长度minCapacity小于数组长度时，直接将新元素插入至内部数组的[size++]的位置；
 * 若minCapacity大于数组长度时，将会进行动态扩容，最终通过调用grow()方法扩容，扩容时，将原数组长度扩大1.5倍，得到一个新的长度值newCapacity
 * 判断newCapacity是否大于所需要的最小长度minCapacity，若满足，则生成一个newCapacity长度的新数组，将原来的数组元素拷贝至新数组，将新元素插入到末尾
 * 若newCapacity<minCapacity，则给newCapacity赋值为minCapacity，然后判断newCapacity是否超过最大限制，若未超过，则生成一个长度为
 * newCapacity的数组，将原数组元素拷贝至新数组，然后将新元素插入至末尾
 * 否则将使用最大限制MAX_ARRAY_SIZE作为新数组的长度
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>(10);
        numList.add(10);
        numList.add(20);
        numList.stream().forEach(System.out::println);

        Vector<Integer> list = new Vector<>(20);
        list.add(20);
        list.stream().forEach(System.out::println);
    }
}
