package list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * 链表反转有两个常见的变形题目
 * 一个是指定链表的某个区间，让你进行反转（NC21）
 * 另一个是将链表每K个一组进行反转，最后不够K个的就直接输出（LeetCode 25）。
 *
 * @author Jack Lee
 * @since 2021-07-13
 **/
public class LinkedListReverse2 {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Node head1 = initLinkedList(5);
        System.out.println("链表部分反转：\t" + toString(head1));
        System.out.println("部分反转结果：\t" + toString(reverseBetween(head1, 2, 4)));
        Node head2 = initLinkedList(10);
        System.out.println("链表K个反转：\t" + toString(head2));
        System.out.println("K个反转结果：\t" + toString(reverseKsGroup(head2, 3)));
        Node head3 = initLinkedList(15);
        System.out.println("链表两两反转：\t" + toString(head3));
        System.out.println("两两反转结果：\t" + toString(swapPairs(head3)));
        Node head4 = initLinkedList(15);
        System.out.println("链表节点重排1：\t" + toString(head4));
        System.out.println("节点重排结果1：\t" + toString(reorderList(head4)));
        Node head5 = initLinkedList(15);
        System.out.println("链表节点重排2：\t" + toString(head5));
        System.out.println("节点重排结果2：\t" + toString(reorderList2(head5)));
    }

    /**
     * NC21对题目的描述是：
     * 将一个链表m位置到n位置之间的区间反转，要求时间复杂度O(n)，空间复杂度O(1)。例如:
     * 给出的链表为1->2->3->4->5->NULL, m=2,n=4,返回1->4->3->2->5->NULL.
     * 注意:
     * 给出的m,n满足以下条件:
     * 1 <m <n ≤ 链表长度
     * 示例：
     * 输入：{1,2,3,4,5},2,4
     * 返回值：{1,4,3,2,5}
     * 基本过程是：
     * 1.先判断m、n是否合法，不合法则直接返回head。需要计算list的length，过程中同时确定m-1（即mPre）和n+1（nNext）的位置。
     * 2.循环反转m-n部分，先把m.next=nNext确定，而mPre.next需要反转结束后，根据m是否为头作不同选择。
     * 3.如果m为头，则直接换头，否则mPre.next = n。
     *
     * @param head 头节点
     * @param m    起始位置
     * @param n    终止位置
     * @return 部分反转后结果
     */
    public static Node reverseBetween(Node head, int m, int n) {
        if (head == null) {
            return null;
        }
        //计算length，同时把mPre和nNext找到出来
        Node tmp = head;
        Node mPre = null;
        Node nNext = null;
        int len = 0;
        for (; tmp != null; tmp = tmp.next) {
            len = len + 1;
            if (len == m - 1) {
                mPre = tmp;
            }
            if (len == n + 1) {
                nNext = tmp;
            }
        }
        //判断1 <=m <= n <= length
        if (m > n || m < 1 || n > len) {
            return head;
        }
        //先把m-n反转，再设置mPre nNext的连接
        //三个辅助指针用于反转
        //指向m
        Node mNode = (mPre == null) ? head : mPre.next;
        Node current = mNode.next;
        //m的next指向n的next
        mNode.next = nNext;
        //开始反转，循环结束，node1指向n
        while (current != nNext) {
            Node temp = current.next;
            current.next = mNode;
            mNode = current;
            current = temp;
        }
        //判断是否换头，根据m是否为head
        if (mPre == null) {
            return mNode;
        } else {
            mPre.next = mNode;
            return head;
        }
    }

    /**
     * K个一组反转链表
     * LeetCode25对该题的要求是：
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     * 大致过程可以分解为
     * 1、找到待翻转的k个节点，如果剩余数量小于 k ，不反转直接返回头结点。
     * 2、对K个进行翻转。并返回翻转后的头结点。这里的问题是区间的界定，这是个左闭又开区间，本轮操作的尾结点其实就是下一轮操作的头结点。
     * 3、对下一轮 k 个节点也进行翻转操作。
     * 4、将上一轮翻转后的尾结点指向下一轮翻转后的头节点，即将每一轮翻转的k的节点连接起来。
     *
     * @param head 头节点
     * @param k    反转节点个数
     * @return 反转后结果
     */
    public static Node reverseKsGroup(Node head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        Node tail = head;
        for (int i = 0; i < k; i++) {
            //剩余数量小于k的话，则不需要反转。
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        // 反转前 k 个元素
        Node newHead = reverse(head, tail);
        //下一轮的开始的地方就是tail
        head.next = reverseKsGroup(tail, k);
        return newHead;
    }

    /**
     * 左闭右开区间
     *
     * @param head 头节点
     * @param tail 尾节点
     * @return 反转结果
     */
    private static Node reverse(Node head, Node tail) {
        Node pre = null;
        while (head != tail) {
            Node next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * leetcode 24 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head 头节点
     * @return 交换后结果
     */
    public static Node swapPairs(Node head) {
        Node prev = new Node(0);
        prev.next = head;
        Node temp = prev;
        while (temp.next != null && temp.next.next != null) {
            Node node1 = temp.next;
            Node node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return prev.next;
    }

    /**
     * leetcode 143  重排链表
     * 给定一个单链表 L：L0->L1->...->Ln-1->Ln，
     * 将其重新排列后变为：L0->Ln->L1->Ln-1->L2->Ln-2->...
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例：
     * 1.给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 2.给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     * 先保存到现象表，调整完之后再写回链表
     *
     * @param head 头节点
     * @return 重排后的头节点
     */
    public static Node reorderList(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        Node node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
        return head;
    }

    /**
     * 寻找链表中点 + 链表逆序 + 合并链表
     * 目标链表即为将原链表的左半端和反转后的右半端合并后的结果
     * 这样我们的任务即可划分为三步：
     * 1.找到原链表的中点。可以使用快慢指针来找。
     * 2.将原链表的右半端反转。
     * 3.将原链表的两端合并。
     *
     * @param head 头节点
     */
    public static Node reorderList2(Node head) {
        if (head == null) {
            return null;
        }
        Node mid = middleNode(head);
        Node list2 = mid.next;
        mid.next = null;
        list2 = reverseList(list2);
        mergeList(head, list2);
        return head;
    }

    private static Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private static void mergeList(Node list1, Node list2) {
        Node l1Tmp;
        Node l2Tmp;
        while (list1 != null && list2 != null) {
            l1Tmp = list1.next;
            l2Tmp = list2.next;
            list1.next = list2;
            list1 = l1Tmp;
            list2.next = list1;
            list2 = l2Tmp;
        }
    }

    private static Node initLinkedList(int n) {
        int currentData = RANDOM.nextInt(10);
        Node head = new Node(currentData);
        Node current = head;
        for (int i = 1; i < n; i++) {
            currentData += RANDOM.nextInt(10) + 1;
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
    }
}
