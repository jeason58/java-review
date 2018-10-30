package com.jeason.java.review.interview.yuncunchu;

/**
 * @Auther: jeason
 * @Date: 2018/10/30 19:42
 * @Description: 将给定IP地址转换成对应int值
 * 例如：1.1.1.1 正确转换的值应该为：1*2^24 + 1*2^16 + 1*2^8 + 1
 */
public class ConvertIP2IntDemo {
    public static int convertIp2Int(String ip) {
        int ipValue = 0;
        String[] partArr = ip.split("\\.");
        int offset = 0;
        for (int i = partArr.length-1; i >= 0; i--) {
            ipValue += (Integer.valueOf(partArr[i]).intValue() << offset);
            offset += (1 << 3);
        }

        return ipValue;
    }

    public static void main(String[] args) {
        String ip = "1.1.1.1";
        System.out.println();
        int ipVal = convertIp2Int(ip);
        System.out.println("convertIp2Int: " + ipVal);
    }
}
