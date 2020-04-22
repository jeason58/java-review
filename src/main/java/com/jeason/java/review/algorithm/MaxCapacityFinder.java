package com.jeason.java.review.algorithm;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * */

public class MaxCapacityFinder {

    /**
     * 解法1：双指针（指针碰撞）:
     * 两个指针i,j分别从最两端向内收，每次h取h[i]和h[j]中较小值，判断当前cap是否大于maxCap，大于则替换
     * 然后将h[i]和h[j]较小的一端向内移动1，即下一次的i和j为：(i+1, j)或(i, j-1)，直到i与j相遇（相差1）则结束
     */
    public int findMaxCapacity(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int maxCap = 0;
        for (int i=0, j=height.length-1; i < j; ) {
            int h = height[i] <= height[j]? height[i] : height[j];
            int curCap =  (j-i) * h;
            if (curCap > maxCap) {
                maxCap = curCap;
            }

            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxCap;
    }


    /**
     * 解法2：
     * 从h最小的x坐标开始，每次取h最小的x坐标i，则当前h下，cap最大的情况对应的j应该是距离i最远的坐标j；
     * 计算当前cap是否大于maxCap，若大于则替换maxCap；
     * 下一次h取次小的h，直到h最大则出循环，最后返回maxCap
     *
     * 唉，想了想还是算了，这种接法好像有问题，每次取距离当前h的i坐标最远的j坐标，那么j坐标永远都是两个端点之一，
     * 这样虽然每次都是当前局部最优，但是会漏掉全局最优解，
     * 如下图中，比如当前循环到了h=4，i=2或i=7，那么取距离最远的j=8或j=1，那么当前maxCap=2*(8-2)=12 或 maxCap=1*(7-1)=7，错过了最大值
     * 到h=5的时候(i, j)=(3, 8)，maxCap=2*(8-3)=10，又错过了最大值
     *
     * 所以其实每一次循环h的时候，maxCap并不是距离最远的j决定的，应该取当前h下maxCap最大的j，但是取面积最大就成了穷举法了，不优
     * 所以这种解法还是有问题
     *
     * |        5
     * |     4  |           4
     * |     |  |     3     |
     * |     |  |  2  |     |  2
     * |  1  |  |  |  |  1  |  |
     * |  |  |  |  |  |  |  |  |
     * |--1--2--3--4--5--6--7--8---->
     * */
    public int findMaxCapacity2() {
        int maxCap = 0;



        return maxCap;
    }


    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};

        MaxCapacityFinder finder = new MaxCapacityFinder();
        int maxCap = finder.findMaxCapacity(height);

        System.out.println(maxCap);
    }
}
