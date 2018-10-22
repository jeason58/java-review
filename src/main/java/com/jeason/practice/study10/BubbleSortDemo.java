package com.jeason.practice.study10;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 21:34
 * @Description: 冒泡排序算法实现，稳定，时间复杂度 = O(N²)
 */
public class BubbleSortDemo {

    // 冒泡排序（时间复杂度 = O(N²)）
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int temp;
        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
                if (arr[j+1] < arr[j]) {
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {4, 9, 9, 3, 8, 5, 2, 7};
        bubbleSort(A);
        QuickSortDemo.printArray(A);
    }
}
