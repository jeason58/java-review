package com.jeason.java.review.algorithm;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 22:01
 * @Description: 插入排序算法实现，稳定，时间复杂度 = O(N²)
 */

public class SortByInsert {

    // 思想：类似于抓扑克牌，左手的牌是已排好序的，右手的牌是未排好序的，
    // 将第一张牌作为左手的已经排好序牌，每次从右手取一张牌，然后依次从右往左与左手的牌比较，
    // 若左手的牌比拿到的这张牌大，则依次右移，直到碰到左手的某张牌X比拿到的这张牌小，便将这张牌插入到X牌的后面
    // 重复上述过程直至右手的牌全部被插入
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i=1; i<arr.length; i++) {
            int get = arr[i];
            int j = i - 1;
            while (j >= 0 && get < arr[j]) {
                arr[j + 1] = arr[j];
                j --;
            }
            arr[j+1] = get;
        }
    }

    public static void main(String[] args) {
        int[] A = {5, 8, 4, 9, 6, 3, 2, 7, 1};
        insertionSort(A);
        System.out.println("after sort: ");
        SortByQuick.printArray(A);
    }
}
