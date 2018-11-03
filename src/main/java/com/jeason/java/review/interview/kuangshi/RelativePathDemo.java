package com.jeason.java.review.interview.kuangshi;

/**
 * @Auther: jeason
 * @Date: 2018/11/3 17:23
 * @Description: 旷视科技(Face++)笔试题：计算两个给定文件的绝对路径——x，y的相对路径
 * 例如：输入 x="/a/b/c/d", y="a/e/f"
 * 则输出y相对于x的相对路径为："../../e/f"
 */
public class RelativePathDemo {
    public static String getRelativePath(String x, String y) {
        if (x == null || y == null || "".equals(x) || "".equals(y)) {
            throw new IllegalArgumentException("x or y cannot be null");
        }

        if (!x.startsWith("/") || !y.startsWith("/")) {
            throw new IllegalArgumentException("x or y must be absolute path");
        }

        String[] xarr = x.split("/"), yarr = y.split("/");
        int xi = 0;
        // 寻找第一个不相同的位置下标
        while ( xi<(xarr.length-1) && xi<(yarr.length-1) ) {
            if (!xarr[xi].equals(yarr[xi])) {
                break;
            }
            xi ++;
        }

        StringBuffer sb = new StringBuffer();
        int yi = xi;
        // 从不相同的元素下标开始到xarr的倒数第二个元素，有多少个元素就进行多少次的“../”，得到x和y的共同根目录的路径
        while (xi < xarr.length-1) {
            sb.append("../");
            xi ++;
        }
        // 从不相同的元素下标开始到yarr的最后一个元素，全部append到以上得到的共同的根路径后面
        while (yi < yarr.length) {
            sb.append(yarr[yi++]);
            if (yi < yarr.length) {
                sb.append("/");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String x = "/a/b/c/d", y = "/a/e/f";
        String relativePath = getRelativePath(x, y);
        System.out.printf("%-10s %s\t\n", "x:", x);
        System.out.printf("%-10s %s\t\n", "y:", y);
        System.out.printf("%-10s %s\t\n", "relative:", relativePath);
    }
}
