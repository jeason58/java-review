package com.jeason.practice.study08;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 14:16
 * @Description: 减少栈内存的参数设置，模拟产生StackOverFlowError
 * @VmArgs：-Xss128k
 */
public class SOFCreateTest {
    private int recursive;

    public void recursiveCall() {
        this.recursive ++;
        recursiveCall();
    }

    public static void main(String[] args) {
        SOFCreateTest sof = new SOFCreateTest();
        sof.recursiveCall();
    }
}
