package com.jeason.practice.study10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: jeason
 * @Date: 2018/10/22 23:28
 * @Description: 二叉树的层序遍历与先序遍历
 */
public class BinaryTreeLevelScan {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }

        public void insertNode(TreeNode node) {  // 插入树节点
            if (this.value == node.value) {
                TreeNode leftChild = this.left;
                this.left = node;
                node.left = leftChild;
            } else if (this.value > node.value) {
                if (this.left == null) {
                    this.left = node;
                    return;
                } else {
                    this.left.insertNode(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.insertNode(node);
                }
            }
        }
        // 层序遍历当前二叉树
        public void levelOrderTree() {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(this);
            TreeNode currentNode = null;
            while (!queue.isEmpty()) {
                currentNode = queue.poll();
                System.out.printf("%2d\t", currentNode.value);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        // 前序遍历
        public void preOrderTree() {
            System.out.printf("%2d\t", this.value);
            if (this.left != null) {
                this.left.preOrderTree();
            }
            if (this.right != null) {
                this.right.preOrderTree();
            }
        }
    }


    public static void main(String[] args) {
        int[] A = {5, 4, 3, 2, 7, 9, 8};
        TreeNode root = new TreeNode(A[0]);
        for (int i=1; i<A.length; i++) {
            root.insertNode(new TreeNode(A[i]));
        }

        root.levelOrderTree();
        System.out.println("-------------------");
        root.preOrderTree();
        System.out.println("-------------------");
    }
}
