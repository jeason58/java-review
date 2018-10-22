package com.jeason.practice.study10;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 19:42
 * @Description: 快速排序算法实现，不稳定，时间复杂度 = O(N*logN)
 */
public class QuickSortDemo {
    private static int divide(int[] arr, int start, int end) {
        int base = arr[start];
        while (start < end) {
            while (start < end && arr[end] >= base) {
                end --;
            }
            if (start < end) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
                start ++;
            }

            while (start < end && arr[start] <= base) {
                start ++;
            }
            if (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                end --;
            }
        }
        return start;
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (arr == null || start > end || start < 0 || end >= arr.length) {
            return;
        }
        int partition = divide(arr, start, end);
        quickSort(arr, start, partition-1);
        quickSort(arr, partition+1, end);
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.printf("%2d\t", num);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = {4, 9, 7, 5, 5, 5, 8, 6, 2};
        System.out.println("before sort: ");
        printArray(A);
        quickSort(A, 0, A.length-1);
        System.out.println("after sort: ");
        printArray(A);
    }
}
