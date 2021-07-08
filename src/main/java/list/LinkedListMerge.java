package list;

import java.util.Random;

/**
 * 两个有序链表的合并
 *
 * @author Jack Lee
 * @since 2021-07-01
 **/
public class LinkedListMerge {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Node head11 = initLinkedList(5);
        System.out.println("初始化链表11：\t" + toString(head11));
        Node head12 = initLinkedList(10);
        System.out.println("初始化链表12：\t" + toString(head12));
        System.out.println("合并后链表10：\t" + toString(mergeLinkedList2(head11, head12)));
        System.out.println();
        Node head21 = initLinkedList(15);
        System.out.println("初始化链表21：\t" + toString(head21));
        Node head22 = initLinkedList(5);
        System.out.println("初始化链表22：\t" + toString(head22));
        System.out.println("合并后链表20：\t" + toString(mergeLinkedList2(head21, head22)));
        System.out.println();
        Node head31 = initLinkedList(5);
        System.out.println("初始化链表31：\t" + toString(head31));
        Node head32 = initLinkedList(10);
        System.out.println("初始化链表32：\t" + toString(head32));
        System.out.println("合并后链表30：\t" + toString(mergeLinkedList2(head31, head32)));
        System.out.println();
        Node head41 = initLinkedList(15);
        System.out.println("初始化链表41：\t" + toString(head41));
        Node head42 = initLinkedList(5);
        System.out.println("初始化链表42：\t" + toString(head42));
        System.out.println("合并后链表40：\t" + toString(mergeLinkedList1(head41, head42)));
    }

    private static Node mergeLinkedList1(Node head1, Node head2) {
        Node head = new Node(-1);
        Node current00 = head;
        Node current11 = head1;
        Node current22 = head2;
        while (current11 != null || current22 != null) {
            // head2的当前节点数据比head1的小，所以讲head2的当前数据接到head，然后head2的当前节点变成其下一个节点
            if (current11 != null && current22 != null && current11.data > current22.data) {
                current00.next = new Node(current22.data);
                current22 = current22.next;
                current00 = current00.next;
            }
            // head1的当前节点数据比head2的小，所以讲head1的当前数据接到head，然后head1的当前节点变成其下一个节点
            else if (current11 != null && current22 != null && current11.data < current22.data) {
                current00.next = new Node(current11.data);
                current11 = current11.next;
                current00 = current00.next;
            }
            // 将等号提出来，可以少走一次循环
            else if (current11 != null && current22 != null) {
                current00.next = new Node(current11.data);
                current11 = current11.next;
                current00 = current00.next;

                current00.next = new Node(current22.data);
                current22 = current22.next;
                current00 = current00.next;
            }
            // 接上未处理部分
            else {
                current00.next = current11 != null ? current11 : current22;
                break;
            }
        }
        return head.next;
    }

    private static Node mergeLinkedList2(Node head1, Node head2) {
        // 先判断，如果有一个是null，则直接返回另一个
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        Node head = new Node(-1);
        Node current = head;
        while (head1 != null && head2 != null) {
            // head1的当前节点数据比head2的小，所以讲head1的当前数据接到head，然后head1的当前节点变成其下一个节点
            if (head1.data < head2.data) {
                current.next = new Node(head1.data);
                current = current.next;
                head1 = head1.next;
            } else if (head1.data == head2.data) {
                current.next = new Node(head1.data);
                current = current.next;
                head1 = head1.next;

                current.next = new Node(head2.data);
                current = current.next;
                head2 = head2.next;
            }
            // head2的当前节点数据比head1的小，所以讲head2的当前数据接到head，然后head2的当前节点变成其下一个节点
            else {
                current.next = new Node(head2.data);
                current = current.next;
                head2 = head2.next;
            }
        }
        // 将未处理的部分接到head
        current.next = head1 == null ? head2 : head1;
        return head.next;
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
     * @param head 头结点
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
    }
}

