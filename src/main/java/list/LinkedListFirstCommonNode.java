package list;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 两个链表的第一个子节点
 *
 * @author Jack Lee
 * @since 2021-07-12
 **/
public class LinkedListFirstCommonNode {

    public static void main(String[] args) {
        Node[] heads = initLinkedList();
        System.out.println("初始化数组1：" + toString(heads));
        System.out.println("第一个公共节点1：" + findFirstCommonNode(heads[0], heads[1]));

        Node[] heads2 = initLinkedList();
        System.out.println("初始化数组2：" + toString(heads2));
        System.out.println("第一个公共节点2：" + findFirstCommonNode2(heads2[0], heads2[1]));

        Node[] heads3 = initLinkedList();
        System.out.println("初始化数组3：" + toString(heads3));
        System.out.println("第一个公共节点3：" + findFirstCommonNode3(heads3[0], heads3[1]));

        Node[] heads4 = initLinkedList();
        System.out.println("初始化数组4：" + toString(heads4));
        System.out.println("第一个公共节点4：" + findFirstCommonNode4(heads4[0], heads4[1]));

        Node[] heads5 = initLinkedList();
        System.out.println("初始化数组5：" + toString(heads5));
        System.out.println("第一个公共节点5：" + findFirstCommonNode5(heads5[0], heads5[1]));
    }

    /**
     * HashMap法
     * 先将一个链表全部存到Map里，然后再遍历第二个，如果有交点，那么一定能在访问到某个元素的时候检测出来
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return 第一个公共结点
     */
    public static Node findFirstCommonNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node current1 = head1;
        Node current2 = head2;
        HashMap<Node, Integer> hashMap = new HashMap<>(16);
        while (current1 != null) {
            hashMap.put(current1, null);
            current1 = current1.next;
        }
        while (current2 != null) {
            if (hashMap.containsKey(current2)) {
                return current2;
            }
            current2 = current2.next;
        }
        return null;
    }

    /**
     * 集合Set法
     * 能用Hash，那能不能用Set呢？其实思路和上面的一样，
     * 先把第一个链表的节点全部存放到集合set中，然后遍历第二个链表的每一个节点，判断在集合set中是否存在，如果存在就直接返回这个存在的结点。
     * 如果遍历完了，在集合set中还没找到，说明他们没有相交，直接返回null即可。
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return 第一个公共节点
     */
    public static Node findFirstCommonNode2(Node head1, Node head2) {
        Set<Node> set = new HashSet<>();
        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }
        return null;
    }

    /**
     * 使用栈
     * <p>
     * 这里需要使用两个栈，分别将两个链表的结点入两个栈，然后分别出栈，如果相等就继续出栈，不相等的时候就找到了分界线了。
     * 这种方式需要两个O(n)的空间，所以在面试时不占优势。
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return 第一个公共结点
     */
    public static Node findFirstCommonNode3(Node head1, Node head2) {
        Deque<Node> dequeA = new ArrayDeque<>();
        Deque<Node> dequeB = new ArrayDeque<>();
        while (head1 != null) {
            dequeA.push(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            dequeB.push(head2);
            head2 = head2.next;
        }
        Node preNode = null;
        while (!dequeB.isEmpty() && !dequeA.isEmpty()) {
            if (dequeA.peek() == dequeB.peek()) {
                preNode = dequeA.pop();
                dequeB.pop();
            } else {
                break;
            }
        }
        return preNode;
    }

    /**
     * 差和双指针
     * 假如公共子节点一定存在第一轮遍历，假设La长度为L1，Lb长度为L2.则｜L2-L1｜就是两个的差值。
     * 第二轮遍历，长的先走｜L2-L1｜,然后两个链表同时向前走，结点一样的时候就是公共结点了。
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return 第一个公共结点
     */
    public static Node findFirstCommonNode4(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node current1 = head1;
        Node current2 = head2;
        int l1 = 0;
        int l2 = 0;
        while (current1 != null) {
            current1 = current1.next;
            l1++;
        }
        while (current2 != null) {
            current2 = current2.next;
            l2++;
        }
        current1 = head1;
        current2 = head2;
        int sub = l1 > l2 ? l1 - l2 : l2 - l1;
        if (l1 > l2) {
            int a = 0;
            while (a < sub) {
                current1 = current1.next;
                a++;
            }
        }
        if (l1 < l2) {
            int a = 0;
            while (a < sub) {
                current2 = current2.next;
                a++;
            }
        }
        while (current2 != current1) {
            current2 = current2.next;
            current1 = current1.next;
        }
        return current1;
    }

    /**
     * 拼接两个字符串
     * 先看下面的链表A和B：
     * A: 0-1-2-3-4-5
     * B: a-b-4-5
     * 如果分别拼接成AB和BA会怎么样呢？
     * AB：0-1-2-3-4-5-a-b-4-5
     * BA：a-b-4-5-0-1-2-3-4-5
     * 我们发现最后从4开始的就是公共子节点，但是建立新的链表太浪费空间了，我们只要在每个队列访问到头之后调整一下指针就行了
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return 第一个公共结点
     */
    public static Node findFirstCommonNode5(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node p1 = head1;
        Node p2 = head2;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
            if (p1 != p2) {
                if (p1 == null) {
                    p1 = head2;
                }
                if (p2 == null) {
                    p2 = head1;
                }
            }
        }
        return p1;
    }

    private static Node[] initLinkedList() {
        Node[] heads = new Node[2];
        heads[0] = new Node(1);
        Node current1 = heads[0];
        current1.next = new Node(2);
        current1 = current1.next;
        current1.next = new Node(3);
        current1 = current1.next;
        heads[1] = new Node(11);
        Node current2 = heads[1];
        current2.next = new Node(22);
        current2 = current2.next;
        Node node4 = new Node(4);
        current1.next = node4;
        current2.next = node4;
        node4.next = new Node(5);

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
