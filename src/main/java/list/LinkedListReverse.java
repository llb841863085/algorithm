package list;

import java.util.Objects;
import java.util.Random;

/**
 * 链表反转
 *
 * @author Jack Lee
 * @since 2021-07-12
 **/
public class LinkedListReverse {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Node head = initLinkedList(10);
        System.out.println(toString(head));
        System.out.println(toString(reverseList(head)));
        Node head2 = initLinkedList(20);
        System.out.println(toString(head2));
        System.out.println(toString(reverseList2(head2)));
    }

    /**
     * 指针法
     *
     * @param head 链表头节点
     * @return 反转后结果
     */
    public static Node reverseList(Node head) {
        Node prev = null;
        Node cur = head;
        while (cur != null) {
            // 保存下一个节点
            Node temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    /**
     * 递归法
     *
     * @param head 链表头节点
     * @return 反转后结果
     */
    public static Node reverseList2(Node head) {
        return reverse(null, head);
    }

    private static Node reverse(Node prev, Node cur) {
        if (cur == null) {
            return prev;
        }
        // 先保存下一个节点
        Node temp = cur.next;
        // 反转
        cur.next = prev;
        // 更新prev、cur位置
        prev = cur;
        cur = temp;
        return reverse(prev, cur);
    }


    private static Node initLinkedList(int n) {
        int currentData = RANDOM.nextInt(10);
        Node head = new Node(currentData);
        Node current = head;
        for (int i = 1; i < n; i++) {
            currentData += RANDOM.nextInt(10);
            current.next = new Node(currentData);
            current = current.next;
        }
        return head;
    }

    /**
     * 链表打印
     *
     * @param head 头节点
     */
    public static String toString(Node head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data).append("\t");
            head = head.next;
        }
        return sb.toString();
    }

    static class Node {
        int data;

        Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return data == node.data && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next);
        }

        @Override
        public String toString() {
            return data + "\t";
        }
    }
}