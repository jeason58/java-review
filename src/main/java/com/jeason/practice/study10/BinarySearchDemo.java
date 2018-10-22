package com.jeason.practice.study10;


/**
 * @Auther: jeason
 * @Date: 2018/10/22 20:36
 * @Description: 二分查找算法的递归与非递归实现
 */
public class BinarySearchDemo {
    public static int binarySearch(int[] arr, int key) {
        if (arr == null) {
            return -1;
        }
        int start = 0, end = arr.length-1;
        while (start < end) {
            int middle = (start + end) / 2;
            if (arr[middle] == key) {
                return middle;
            } else if (arr[middle] < key) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }

    public static int binarySearchByRecursive(int[] arr, int key, int start, int end) {
        if (arr == null || start < 0 || end >= arr.length) {
            return -1;
        }
        if (start < end) {
            int middle = (start + end) / 2;
            if (arr[middle] == key) {
                return middle;
            } else if (arr[middle] < key) {
                return binarySearchByRecursive(arr, key, middle+1, end);
            } else {
                return binarySearchByRecursive(arr, key, start, middle-1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int index1 = binarySearch(A, 6);
        System.out.println("index of 6 is: " + index1);

        int index2 = binarySearchByRecursive(A, 6, 0, A.length-1);
        System.out.println("index of 6 is: " + index2);
    }
}
