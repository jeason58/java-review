package com.jeason.java.review.interview.yunxiao;

/**
 * @Auther: jeason
 * @Date: 2018/11/8 14:53
 * @Description: 输出给定数组的最大数和第二大数
 */
public class PrintMaxAndSecMaxDemo {

    public static void printMaxLimit2(int[] A) {
        if (A == null || A.length < 2) {
            throw new IllegalArgumentException("A cannot be null");
        }

        int max = A[0] > A[1] ? A[0] : A[1];
        int secMax = A[0] <= A[1] ? A[0] : A[1];
        int start = 2;
        while (start < A.length) {
            if (A[start] >= max) {
                secMax = max;
                max = A[start];
            } else if (A[start] > secMax) {
                secMax = A[start];
            }
            start++;
        }

        System.out.println("max number: " + max);
        System.out.println("secMax number: " + secMax);
    }

    public static void main(String[] args) {
        int[] A = {3, 7, 2, 6, 40, 43, 90, 1, 8, 5};
        printMaxLimit2(A);
    }
}
