package com.jeason.java.review.interview.yuncunchu;

/**
 * @Auther: jeason
 * @Date: 2018/10/30 19:22
 * @Description: 给定两个已排序的递增数组，将其合并为一个递增数组并返回
 */
public class MergeArrayDemo {
    public static int[] mergeAndSort(int[] A, int[] B) {
        if (A == null && B == null) throw new IllegalArgumentException("待排序数组不能为null");
        if (A == null || B == null) return A == null ? B : A;

        int[] result = new int[A.length + B.length];
        int position = 0, i =0, j = 0;
        while (i < A.length && j < B.length) {
            if (B[j] < A [i]) {
                result[position++] = B[j++];
            } else if (B[j] == A[i]) {
                result[position++] = B[j++];
                result[position++] = A[i++];
            } else {
                result[position++] = A[i++];
            }
        }

        if (i < A.length) {
            while (i < A.length) {
                result[position++] = A[i++];
            }
        }
        if (j < B.length) {
            while (j < B.length) {
                result[position++] = B[j++];
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] A = {1, 2, 3, 5, 7, 9};
        int[] B = {2, 4, 6, 8, 10};

        int[] sortedArr = mergeAndSort(A, B);
        for (int num : sortedArr) {
            System.out.printf("%2d\t", num);
        }
        System.out.println();
    }
}
