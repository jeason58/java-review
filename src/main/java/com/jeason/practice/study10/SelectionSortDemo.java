package com.jeason.practice.study10;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 21:06
 * @Description: 选择排序算法实现，不稳定，时间复杂度 = O(N²)
 */
public class SelectionSortDemo {
    // 选择排序（时间复杂度 = O(N²)）
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i=0; i<arr.length-1; i++) {
            int min = i;
            for (int j=i+1; j<arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void main(String[] args) {
        int[] A = {4, 8, 5, 9, 3, 2, 6, 7};
        selectionSort(A);
        System.out.println("after sort: ");
        QuickSortDemo.printArray(A);
    }

}
