package list;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * 合并K个有序链表
 * LeetCode23：合并K个升序链表：给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author Jack Lee
 * @since 2021-07-09
 **/
public class LinkedListMerge3 {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Node[] heads1 = initLinkedList();
        System.out.println("初始化数组1：\t" + toString(heads1));
        System.out.println("合并链表1：" + toString(mergeKsLists(heads1)));
        System.out.println();

        Node[] heads2 = initLinkedList();
        System.out.println("初始化数组2：\t" + toString(heads2));
        System.out.println("合并链表2：\t" + toString(mergeKsLists2(heads2)));
        System.out.println();

        Node[] heads3 = initLinkedList();
        System.out.println("初始化数组3：\t" + toString(heads3));
        System.out.println("合并链表3：\t" + toString(mergeKsLists3(heads3)));
        System.out.println();

        Node[] heads4 = initLinkedList();
        System.out.println("初始化数组4：\t" + toString(heads4));
        System.out.println("合并链表4：\t" + toString(mergeKsLists4(heads4)));
        System.out.println();

        Node[] heads5 = initLinkedList();
        System.out.println("初始化数组5：\t" + toString(heads5));
        System.out.println("合并链表5：\t" + toString(mergeKsLists5(heads5)));
        System.out.println();

        Node[] heads6 = initLinkedList();
        System.out.println("初始化数组6：\t" + toString(heads6));
        System.out.println("合并链表6：\t" + toString(mergeKsLists6(heads6)));
        System.out.println();
    }

    /**
     * K个指针分别指向K条链表(直接合并)
     *
     * @param lists 链表数组
     * @return 合并后的链表
     */
    public static Node mergeKsLists(Node[] lists) {
        int k = lists.length;
        Node prevHead = new Node(-1);
        Node prevNode = prevHead;
        while (true) {
            Node minNode = null;
            int minPointer = -1;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    continue;
                }
                // 获取当前最小节点和对应的链表
                if (minNode == null || lists[i].data < minNode.data) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }
            // 找不到存放当前最小值的链表
            if (minPointer == -1) {
                break;
            }
            prevNode.next = minNode;
            prevNode = prevNode.next;
            lists[minPointer] = lists[minPointer].next;
        }
        return prevHead.next;
    }

    /**
     * K个指针分别指向K条链表(使用小根堆)
     * 1.我们就定义小根堆的大小为K，然后从每个链表拿一个元素进来构造最小堆。
     * 2.取走根元素（一定是最小值），然后将该元素所在的链表再拿一个元素进来，重新构造小根堆。
     * 3.重复执行上面的步骤，直到所有链表都为空。
     * 每次 O(logK)O(logK) 比较 K个指针求 min, 时间复杂度：O(NlogK)O(NlogK)
     *
     * @param lists 链表数组
     * @return 合并后的链表
     */
    public static Node mergeKsLists2(Node[] lists) {
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.data));
        // 把头结点放到小根堆
        for (Node node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        Node prevHead = new Node(-1);
        Node prevNode = prevHead;
        while (!pq.isEmpty()) {
            // 从小根堆里面取出根节点，即最小值
            Node minNode = pq.poll();
            prevNode.next = minNode;
            prevNode = prevNode.next;
            // 从当前的最小值所在的链表里面取出下一个放入到小根堆
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }
        return prevHead.next;
    }

    public static Node mergeKsLists3(Node[] lists) {
        Node head = null;
        for (Node list : lists) {
            head = merge2Lists(head, list);
        }
        return head;
    }

    public static Node mergeKsLists4(Node[] lists) {
        Node head = null;
        for (Node list : lists) {
            head = merge2Lists1(head, list);
        }
        return head;
    }

    /**
     * 归并法两两合并
     *
     * @param lists 链表数组
     * @return 合并后的链表
     */
    public static Node mergeKsLists5(Node[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int k = lists.length;
        while (k > 1) {
            int idx = 0;
            for (int i = 0; i < k; i += 2) {
                if (i == k - 1) {
                    lists[idx++] = lists[i];
                } else {
                    lists[idx++] = merge2Lists(lists[i], lists[i + 1]);
                }
            }
            k = idx;
        }
        return lists[0];
    }

    /**
     * 递归+二分法进行归并合并
     *
     * @param lists 链表数组
     * @return 合并后的链表
     */
    public static Node mergeKsLists6(Node[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static Node merge(Node[] lists, int lowIndex, int highIndex) {
        if (lowIndex == highIndex) {
            return lists[lowIndex];
        }
        int middleIndex = lowIndex + (highIndex - lowIndex) / 2;
        Node list1 = merge(lists, lowIndex, middleIndex);
        Node list2 = merge(lists, middleIndex + 1, highIndex);
        return merge2Lists(list1, list2);
    }

    /**
     * 逐一合并两条链表(递归合并两条链表)
     *
     * @param list1 有序链表1
     * @param list2 有序链表2
     * @return 合并后的链表
     */
    private static Node merge2Lists(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.data < list2.data) {
            list1.next = merge2Lists(list1.next, list2);
            return list1;
        }
        list2.next = merge2Lists(list1, list2.next);
        return list2;
    }

    /**
     * 逐一合并两条链表(迭代合并两条链表)
     *
     * @param list1 有序链表1
     * @param list2 有序链表2
     * @return 合并后的链表
     */
    private static Node merge2Lists1(Node list1, Node list2) {
        Node prevHead = new Node(0);
        Node prevNode = prevHead;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                prevNode.next = list1;
                list1 = list1.next;
            } else {
                prevNode.next = list2;
                list2 = list2.next;
            }
            prevNode = prevNode.next;
        }
        prevNode.next = list1 == null ? list2 : list1;
        return prevHead.next;
    }

    private static Node[] initLinkedList() {
        int arraySize = RANDOM.nextInt(8) + 2;
        Node[] heads = new Node[arraySize];
        for (int i = 0; i < arraySize; i++) {
            int currentListSize = RANDOM.nextInt(20) + 5;
            int currentData = RANDOM.nextInt(10);
            Node head = new Node(currentData);
            Node current = head;
            for (int j = 1; j < currentListSize; j++) {
                currentData += RANDOM.nextInt(10);
                current.next = new Node(currentData);
                current = current.next;
            }
            heads[i] = head;
        }
        return heads;
    }

    /**
     * 链表数组打印
     *
     * @param heads 链表数组
     */
    public static String toString(Node[] heads) {
        if (heads == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < heads.length; i++) {
            String listString = toString(heads[i]);
            sb.append("\n").append("第").append(i + 1).append("个链表：").append(listString);
        }
        return sb.toString();
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
