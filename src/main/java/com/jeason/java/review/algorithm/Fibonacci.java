package com.jeason.java.review.algorithm;


/**
 * 返回第N个Fibonacci数
 */
public class Fibonacci {

    /**
     * 解法1：递归法（自顶向下）
     */
    public static int fibByRecursive(int n) {
        if (n < 0) {
            return -1;
        }

        if (n == 0 || n == 1) {
            return n;
        }

        return fibByRecursive(n - 1) + fibByRecursive(n - 2);
    }

    /**
     * 解法2：动态规划（自底向上）
     */
    public static int fibByDynamic(int n) {
        if (n < 0) {
            return -1;
        }

        int[] mem = new int[n + 1];
        mem[0] = 0;
        mem[1] = 1;

        for (int i = 2; i <= n; i++) {
            mem[i] = mem[i - 1] + mem[i - 2];
        }

        return mem[n];
    }

    /**
     * 解法3：解法2进一步压缩
     */
    public static int fibByDynamic2(int n) {
        if (n < 0) {
            return -1;
        }

        int fibI_1 = 1; //f(i-1)
        int fibI_2 = 0; //f(i-2)

        int fibI = 1;
        for (int i=2; i<=n; i++) {
            fibI = fibI_1 + fibI_2;
            fibI_2 = fibI_1;
            fibI_1 = fibI;
        }

        return fibI;
    }

    public static void main(String[] args) {
        int fibN = fibByRecursive(6);
        System.out.println(fibN);

        fibN = fibByDynamic(6);
        System.out.println(fibN);

        fibN = fibByDynamic2(6);
        System.out.println(fibN);
    }
}
