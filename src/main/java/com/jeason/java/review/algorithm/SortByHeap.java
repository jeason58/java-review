package com.jeason.java.review.algorithm;

import com.jeason.java.review.datastructure.HeapByArray;

/**
 * 堆排序（基于大顶堆实现），时间复杂度：O(NlogN)
 * */
public class SortByHeap {

    public static void sortByHeap(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        HeapByArray heap = HeapByArray.buildHeap(nums);
        heap.sort();
        heap.display();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 2, 6, 5, 9, 1, 8};
        sortByHeap(nums);
    }
}
