package list;

import java.security.SecureRandom;

/**
 * 有序链表合并
 *
 * @author Jack Lee
 * @since 2021-07-09
 **/
public class LinkedListMerge2 {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        Node head11 = initLinkedList(5);
        System.out.println("初始化链表11：\t" + toString(head11));
        Node head12 = initLinkedList(10);
        System.out.println("初始化链表12：\t" + toString(head12));
        System.out.println("合并2个链表10：\t" + toString(merge2Lists(head11, head12)));
        System.out.println();
        Node head21 = initLinkedList(15);
        System.out.println("初始化链表21：\t" + toString(head21));
        Node head22 = initLinkedList(5);
        System.out.println("初始化链表22：\t" + toString(head22));
        System.out.println("合并2个链表20：\t" + toString(merge2Lists2(head21, head22)));
        System.out.println();
        Node head31 = initLinkedList(5);
        System.out.println("初始化链表31：\t" + toString(head31));
        Node head32 = initLinkedList(10);
        System.out.println("初始化链表32：\t" + toString(head32));
        System.out.println("合并2个链表30：\t" + toString(merge2Lists3(head31, head32)));
        System.out.println();
        Node head41 = initLinkedList(15);
        System.out.println("初始化链表41：\t" + toString(head41));
        Node head42 = initLinkedList(10);
        System.out.println("初始化链表42：\t" + toString(head42));
        Node head43 = initLinkedList(12);
        System.out.println("初始化链表43：\t" + toString(head43));
        Node head44 = initLinkedList(17);
        System.out.println("初始化链表44：\t" + toString(head44));
        Node head45 = initLinkedList(20);
        System.out.println("初始化链表45：\t" + toString(head45));
        Node[] heads = {head41, head42, head43, head44, head45};
        System.out.println("合并K个链表40：\t" + toString(mergekLists(heads)));
        System.out.println();
        Node head51 = initLinkedList(5);
        System.out.println("初始化链表51：\t" + toString(head11));
        Node head52 = initLinkedList(10);
        System.out.println("初始化链表52：\t" + toString(head12));
        System.out.println("合并2个链表50：\t" + toString(mergeInBetween(head51, 2, 4, head52)));
    }

    /**
     * LeetCode21 ：将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param list1 有序链表1
     * @param list2 有序链表2
     * @return 合并后的链表
     */
    public static Node merge2Lists(Node list1, Node list2) {
        Node prevHead = new Node(-1);
        Node prevNode = prevHead;
        //这么写，减少while的数量，可能耗时会小一些
        while (list1 != null || list2 != null) {
            //都不为空的情况
            if (list1 != null && list2 != null) {
                if (list1.data < list2.data) {
                    prevNode.next = list1;
                    list1 = list1.next;
                } else if (list1.data > list2.data) {
                    prevNode.next = list2;
                    list2 = list2.next;
                }
                //相等的情况，分别接两个链
                else {
                    prevNode.next = list2;
                    list2 = list2.next;
                    prevNode = prevNode.next;
                    prevNode.next = list1;
                    list1 = list1.next;
                }
                prevNode = prevNode.next;
            }
            // 表2遍历完成，表1不为空
            else if (list1 != null) {
                prevNode.next = list1;
                list1 = list1.next;
                prevNode = prevNode.next;
            }
            // 表1遍历完成，表2不为空
            else {
                prevNode.next = list2;
                list2 = list2.next;
                prevNode = prevNode.next;
            }
        }
        return prevHead.next;
    }

    /**
     * LeetCode21 ：将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。(正常解法)
     *
     * @param list1 有序链表1
     * @param list2 有序链表2
     * @return 合并后的链表
     */
    public static Node merge2Lists2(Node list1, Node list2) {
        Node prevHead = new Node(-1);
        Node prevNode = prevHead;
        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                prevNode.next = list1;
                list1 = list1.next;
            } else {
                prevNode.next = list2;
                list2 = list2.next;
            }
            prevNode = prevNode.next;
        }
        // 最多只有一个还未被合并完，直接接上去就行了
        prevNode.next = list1 == null ? list2 : list1;
        return prevHead.next;
    }

    /**
     * LeetCode21 ：将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。(递归解法)
     *
     * @param list1 有序链表1
     * @param list2 有序链表2
     * @return 合并后的链表
     */
    public static Node merge2Lists3(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.data < list2.data) {
            list1.next = merge2Lists3(list1.next, list2);
            return list1;
        } else {
            list2.next = merge2Lists3(list1, list2.next);
            return list2;
        }
    }

    /**
     * LeetCode23：合并K个升序链表。给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * @param lists 有序链表数组
     * @return 合并后的有序链表
     */
    public static Node mergekLists(Node[] lists) {
        Node res = null;
        for (Node list : lists) {
            res = merge2Lists(res, list);
        }
        return res;
    }

    /**
     * LeetCode1669题：给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     * 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
     * 1669题的意思就是将list1中的[a,b)区间的删掉，然后将list2接进去，你觉得难吗？如果这也是算法的话，我至少可以造出七八道题，例如：
     * （1）定义list1的[a,b)区间为list3，将list3和list2按照升序合并成一个链表。
     * （2）将list2也将区间[a,b)的元素删掉，然后将list1和list2合并成一个链表。
     * （3）定义list2的[a,b)区间为list4，将list2和list4合并成有序链表。
     *
     * @param list1 有序链表1
     * @param a     list1的第a个节点索引
     * @param b     list1的第b个节点索引
     * @param list2 有序链表2
     * @return 合并后的链表
     */
    public static Node mergeInBetween(Node list1, int a, int b, Node list2) {
        Node pre1 = list1;
        Node post1 = list1;
        Node post2 = list2;
        int i = 0;
        int j = 0;
        while (pre1 != null && post1 != null && j < b) {
            if (i != a - 1) {
                pre1 = pre1.next;
                i++;
            }
            if (j != b - 1) {
                post1 = post1.next;
                j++;
            }
        }
        if (pre1 == null || post1 == null || post1.next == null) {
            System.out.println("参数错误，list1的长度n比a或者b小。");
            System.exit(-1);
        }
        post1 = post1.next;
        //寻找list2的尾节点
        while (post2.next != null) {
            post2 = post2.next;
        }
        //链1尾接链2头，链2尾接链1后半部分的头
        pre1.next = list2;
        post2.next = post1;
        return list1;
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
        final int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
