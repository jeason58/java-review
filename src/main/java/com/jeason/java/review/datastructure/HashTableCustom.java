package com.jeason.java.review.datastructure;

import java.util.HashMap;

public class HashTableCustom<K, V> {
    private Node<K, V>[] tab;
    private int cap;
    private static final int DEFAULT_CAP = 16;

    static class Node<K, V> {
        K key;
        V val;
        Node next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    public HashTableCustom(int cap) {
        if (cap < DEFAULT_CAP) {
            this.cap = DEFAULT_CAP;
        } else {
            this.cap = 2;
        }
    }

    public V put(K key, V val) {
        int hash = (key.hashCode()) ^ (key.hashCode() >>> 16);
        int index = (cap - 1) & hash;

        if (tab[index] == null) {
            tab[index] = new Node<K, V>(key, val);
        } else {
            Node n = tab[index];
            for (; n != null; ) {
                if (n.key == key || n.key.equals(key)) {
                    V oldVal = (V) n.val;
                    n.val = val;
                    return oldVal;
                }

                if (n.next == null) {
                    break;
                } else {
                    n = n.next;
                }
            }

            n.next = new Node(key, val);
        }

        return null;
    }
}
