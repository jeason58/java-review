package com.jeason.java.review.interview.baidu;

/**
 * @Auther: jeason
 * @Date: 2018/11/1 23:12
 * @Description: 代码实现链表反转（逆置）
 *
 * 思路：声明三个Node节点变量last，current和nxt
 * 分别初始化：last=null, current=head, nxt=head.next
 * last代表当前节点的前驱节点，current代表当前节点，nxt代表当前节点的后继节点
 * 然后遍历链表：
 *  current.next = last;
 *  last = current;
 *  current = nxt;
 *  nxt = nxt.next
 * 如果nxt不为空，重复上述操作，直到nxt为空，出循环
 * 此时last为倒数第二个节点，last的next指向了倒数第三个节点，current指向了最后一个节点，nxt节点指向空
 * 所以此时执行：current.next = last; 即可完成
 * 此时已经成功反转了链表，current节点即为反转后的链表头结点，返回current节点即可
 */
public class ReverseLinkedListDemo {
    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public void print() {
            Node currNode = this;
            while (currNode != null) {
                System.out.printf("%2d\t", currNode.value);
                currNode = currNode.next;
            }
            System.out.println();
        }

        public Node reverse() {
            if (next == null) {
                return this;
            }

            Node last = null, current = this, nxt = next;
            while (nxt != null) {
                current.next = last;
                last = current;
                current = nxt;
                nxt = nxt.next;
            }
            current.next = last;

            return current;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        Node tail = head;
        for (int i=2; i<=10; i++) {
            tail.next = new Node(i);
            tail = tail.next;
        }

        Node h = head;
        h.print();

        h = head.reverse();
        h.print();
    }
}
