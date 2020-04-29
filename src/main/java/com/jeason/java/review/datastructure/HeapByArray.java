package com.jeason.java.review.datastructure;


/**
 * 基于数组实现堆结构（大顶堆和小顶堆）
 * 堆的定义：
 * 1）堆是一颗完全二叉树；
 * 2）大顶堆的每个节点都大于等于其左右子节点；
 * 3）小顶堆的每个节点都小于等于其左右子节点；
 *
 * 存储结构：
 * 因为堆是一颗完全查找树，而完全二叉树最好的存储方案是数组：
 * 可实现随机访问，能够迅速定位左右子节点和父节点，数组的第一个元素（下标为0）一般不存储节点；
 *
 * */

public class HeapByArray {
    int[] heapTab;
    int cap;
    int count;
    private static final int DEFAULT_HEAP_CAP = 10;

    public HeapByArray(){
        this(DEFAULT_HEAP_CAP);
    }

    public static HeapByArray buildHeap(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }

        HeapByArray heap = new HeapByArray(nums.length);
        for (int i = 0; i < nums.length; i++) {
            heap.insert(nums[i]);
        }

        return heap;
    }

    public HeapByArray(int cap) {
        if (cap <= 0) {
            cap = DEFAULT_HEAP_CAP;
        }
        this.heapTab = new int[cap+1];
        this.cap = cap;
        this.count = 0;
    }

    private void resize() {
        cap = (cap << 1) -1;
        int[] oldTab = heapTab;
        heapTab = new int[cap];
        for (int i = 1; i <= count; i++) {
            heapTab[i] = oldTab[i];
        }
    }

    public int insert(int val) {
        if (count+1 == cap) {
            resize();
        }

        heapTab[++count] = val;
        heapifyRise(count);

        return count;
    }

    public int size() {
        return count;
    }

    //自底向上"堆化"
    private void heapifyRise(int index) {
        int tmp;
        while (heapTab[index/2] > 0 && heapTab[index] > heapTab[index/2]) {
            tmp = heapTab[index];
            heapTab[index] = heapTab[index/2];
            heapTab[index/2] = tmp;
            index /= 2;
        }
    }

    public void display() {
        for (int i = 1; i <= count; i++) {
            System.out.printf("%2d\t", heapTab[i]);
        }
        System.out.println();
    }

    public void sort() {
        int k = count;
        while (k > 1) {
            int tmp = heapTab[1];
            heapTab[1] = heapTab[k];
            heapTab[k--] = tmp;

            int i = 1;
            while (true) {
                int maxI = i;
                if (2*i <= k && heapTab[maxI] < heapTab[2*i]) {
                    maxI = 2*i;
                }
                if (2*i+1 <= k && heapTab[maxI] < heapTab[2*i+1]) {
                    maxI = 2*i + 1;
                }

                if (maxI == i) {
                    break;
                } else {
                    tmp = heapTab[i];
                    heapTab[i] = heapTab[maxI];
                    heapTab[maxI] = tmp;
                    i = maxI;
                }
            }
        }
    }


    public static void main(String[] args) {
        HeapByArray heap = new HeapByArray(9);

        int[] arr = new int[]{3, 5, 7, 2, 8, 4, 9, 1, 6};
        for (int n : arr) {
            heap.insert(n);
        }

        heap.display();
    }
}
