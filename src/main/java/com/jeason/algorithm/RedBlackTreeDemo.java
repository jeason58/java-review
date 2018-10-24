package com.jeason.algorithm;


/**
 * @description: 红黑树原理，重点：左旋&右旋，可参考jdk1.8中HashMap和TreeMap的实现
 * @author: jeason·wang
 * @date: 2018-07-24 17:37
 **/
public class RedBlackTreeDemo<K, V> {
  private static final boolean BLACK = true;
  private static final boolean RED = false;

  private TreeNode<K, V> root;
  private int size;

  static class TreeNode<K, V> {
    K key;
    V value;
    TreeNode<K, V> parent;
    TreeNode<K, V> left;
    TreeNode<K, V> right;
    boolean color = BLACK;

    public TreeNode(K key, V value) {
      this(key, value, null);
    }

    public TreeNode(K key, V value, TreeNode<K, V> parent) {
      this.key = key;
      this.value = value;
      this.parent = parent;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }

  public RedBlackTreeDemo() {
  }

  public static void main(String[] args) {
    TreeNode<String, Object> root = new TreeNode<>("name", "jeason");

  }

}
