package com.jeason.java.review.datastructure;

/**
 * 基于数组实现一个栈
 * */
public class StackByArray {
    int[] stack = null;
    int count;
    int cap;
    private static final int DEFAULT_CAP = 10;

    public StackByArray() {
        this(DEFAULT_CAP);
    }

    public StackByArray(int cap) {
        if (cap <= 0) {
            this.cap = DEFAULT_CAP;
        }
        this.cap = cap;
        this.count = 0;
        this.stack = new int[cap];
    }

    public boolean push(int val) {
        if (count < stack.length) {
            stack[count++] = val;
            return true;
        } else {
            return false;
        }
    }

    public int pop() {
        if (count == 0) {
            return -1;
        }

        return stack[--count];
    }

    public boolean empty() {
        return count == 0;
    }


    public static void main(String[] args) {
        StackByArray stack = new StackByArray();
        for (int i=1; i < 12; i++) {
            stack.push(i);
        }

        while (!stack.empty()) {
            System.out.printf("%2d\t", stack.pop());
        }
        System.out.println();
    }
}
