package com.jeason.java.review.algorithm;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 19:42
 * @Description: 快速排序算法实现，不稳定，时间复杂度 = O(N*logN)
 */
public class QuickSortDemo {
    public static void quickSort(int[] arr, int left, int right) {
        if (arr == null || left > right || left < 0 || right >= arr.length) {
            return;
        }

        int base = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && arr[j] >= base) {
                j --;
            }

            while (i < j && arr[i] <= base) {
                i ++;
            }

            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        arr[left] = arr[i];
        arr[i] = base;

        quickSort(arr, left, i-1);
        quickSort(arr, i+1, right);
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.printf("%2d\t", num);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("before sort: ");
        printArray(A);
        quickSort(A, 0, A.length-1);
        System.out.println("after sort: ");
        printArray(A);
    }
}
