package com.jeason.java.review.interview.baidu;

import java.util.Stack;

/**
 * @Auther: jeason
 * @Date: 2018/11/2 00:19
 * @Description:
 */
public class PreOrderBinTreeDemo {

    // 二叉树节点类
    private static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        public void insertNode(TreeNode node) {
            if (node == null || node.value == value) {
                return;
            }

            if (node.value < value) {
                if (left == null) {
                    left = node;
                } else {
                    left.insertNode(node);
                }
            } else {
                if (right == null) {
                    right = node;
                } else {
                    right.insertNode(node);
                }
            }
        }

        // 先序递归遍历
        public void preOrderByRecursive() {
            System.out.printf("%2d\t", value);
            if (left != null) {
                left.preOrderByRecursive();
            }
            if (right != null) {
                right.preOrderByRecursive();
            }
        }

        // 先序非递归遍历
        public void preOrderNonRecursive() {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = this;
            while (current != null || !stack.isEmpty()) {
                while (current != null) {
                    System.out.printf("%2d\t", current.value);
                    stack.push(current);
                    current = current.left;
                }

                if (!stack.isEmpty()) {
                    current = stack.pop();
                    current = current.right;
                }
            }
        }

        // 中序递归遍历
        public void midOrderByRecursive() {
            if (left != null) {
                left.midOrderByRecursive();
            }
            System.out.printf("%2d\t", value);
            if (right != null) {
                right.midOrderByRecursive();
            }
        }

        // 中序非递归遍历
        public void midOrderNonRecursive() {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = this;
            while (current != null || !stack.isEmpty()) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                if (!stack.isEmpty()) {
                    current = stack.pop();
                    System.out.printf("%2d\t", current.value);
                    current = current.right;
                }
            }
        }

        // 后序递归遍历
        public void afterOrderByRecursive() {
            if (left != null) {
                left.afterOrderByRecursive();
            }
            if (right != null) {
                right.afterOrderByRecursive();
            }
            System.out.printf("%2d\t", value);
        }

        // 后序非递归遍历
        public void afterOrderNonRecursive() {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = this;
            while (current != null || !stack.isEmpty()) {
                stack.push(current);
                current = current.left;
            }

            // code here(待续...)
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 7, 2, 4, 6};
        TreeNode root = new TreeNode(5);
        for (int n : nums) {
            root.insertNode(new TreeNode(n));
        }

        System.out.printf("先序递归遍历：\t");
        root.preOrderByRecursive(); // 先序递归遍历
        System.out.printf("\n先序非递归遍历：\t");
        root.preOrderNonRecursive(); //先序非递归遍历

        System.out.println();

        System.out.printf("\n中序递归遍历：\t");
        root.midOrderByRecursive();
        System.out.printf("\n中序非递归遍历：\t");
        root.midOrderNonRecursive();

        System.out.println();

    }
}
