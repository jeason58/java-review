package com.jeason.java.review.interview.lagou;


import java.util.Random;

/**
 * @Auther: jeason
 * @Date: 2018/11/5 20:41
 * @Description: 给定2000个long型数组，每个数组中有200个数字，从中找出最大的前1000个数字
 */

// 未完待续...
public class FindTopKDemo {
    public static long[] findTopN(int topN, long[] ...sequences) {
        if (sequences == null || sequences.length <= 0) {
            throw new IllegalArgumentException("sequences cannot be null!");
        }

        long[][] maxNums = new long[sequences.length][2];
        for (int i=0; i<maxNums.length; i++) {
            int lastIndex = sequences[i].length - 1;
            maxNums[i][0] = sequences[i][lastIndex];
            maxNums[i][1] = i;
        }

        // 从大到小排序
        sortBySelection(maxNums);

        long[] result = new long[topN];
        int ri = 0;
        int maxNumi = 0;
        int si = (int) maxNums[maxNumi][1], sii = sequences[si].length - 1;
        while (ri < result.length) {

        }

        return result;
    }

    // 选择排序
    public static void sortBySelection(long[][] nums) {
        if (nums == null || nums.length <= 0) {
            throw new IllegalArgumentException("nums cannot be null");
        }

        for (int i = 1; i < nums.length; i++) {
            long[] get = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j][0] < get[0]) {
                nums[j + 1] = nums[j];
                j --;
            }
            nums[j + 1] = get;
        }
    }

    // 打印数组
    public static void printArray(long[] nums) {
        for (long num : nums) {
            System.out.printf("%2d\t", num);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        long[] a1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 73};
        long[] a2 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        long[] a3 = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        long[] a4 = {10, 20, 30 ,40, 50, 60, 70, 80, 90, 100};
        long[] a5 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        long[] a6 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long[] a7 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        long[] a8 = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        long[] a9 = {10, 20, 30 ,40, 50, 60, 70, 80, 90, 100};
        long[] a10 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        Random random = new Random();
        int i = 0;
        while (i < 1000) {
            System.out.printf("%2d\t", random.nextInt(100));
            if (i % 10 == 9) {
                System.out.println();
            }
            i ++;
        }
    }
}
