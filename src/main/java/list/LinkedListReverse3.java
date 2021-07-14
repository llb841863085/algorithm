package list;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Random;

/**
 * 相加链表
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 * 示例：
 * 输入：[9,3,7],[6,3]
 * 返回值：{1,0,0,0}
 *
 * @author Jack Lee
 * @since 2021-07-14
 **/
public class LinkedListReverse3 {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Node[] heads1 = initLinkedList();
        System.out.println(toString(heads1));
        System.out.println("相加结果1：" + toString(addInList(heads1[0], heads1[1])));
        Node[] heads2 = initLinkedList();
        System.out.println(toString(heads2));
        System.out.println("相加结果2：" + toString(addInList2(heads2[0], heads2[1])));
    }

    /**
     * 使用栈实现
     * 思路是先将两个链表的元素分别压栈，然后再一起出栈，将两个结果分别计算。之后对计算结果取模，模数保存到新的链表中，进位保存到下一轮。
     * 完成之后再进行一次反转就行了。
     *
     * @param head1 数字链表1
     * @param head2 数字链表2
     * @return 两个数字链表相加后的结果链表
     */
    public static Node addInList(Node head1, Node head2) {
        Deque<Node> queue1 = new ArrayDeque<>();
        Deque<Node> queue2 = new ArrayDeque<>();
        while (head1 != null) {
            queue1.push(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            queue2.push(head2);
            head2 = head2.next;
        }
        Node newHead = new Node(-1);
        int carry = 0;
        //这里设置carry!=0,是因为当st1,st2都遍历完时，如果carry=0,就不需要进入循环了
        while (!queue1.isEmpty() || !queue2.isEmpty() || carry != 0) {
            Node a = new Node(0);
            Node b = new Node(0);
            if (!queue1.isEmpty()) {
                a = queue1.pop();
            }
            if (!queue2.isEmpty()) {
                b = queue2.pop();
            }
            //每次的和应该是对应位相加再加上进位
            int sum = a.data + b.data + carry;
            //对累加的结果取余
            int ans = sum % 10;
            //如果大于0，就进位
            carry = sum / 10;
            // 将计算节点插入到头节点的之后，即头插法
            Node cur = new Node(ans);
            cur.next = newHead.next;
            newHead.next = cur;
        }
        return newHead.next;
    }

    /**
     * 使用链表反转实现
     * 如果不用栈，那只能用链表反转了，先将两个链表分别反转，最后计算完之后再将结果反转，一共需要三次。进位等的处理与上面差不多。
     *
     * @param head1 数字链表1
     * @param head2 数字链表2
     * @return 两个数字链表相加后的结果链表
     */
    public static Node addInList2(Node head1, Node head2) {
        head1 = reverse(head1);
        head2 = reverse(head2);
        Node head = new Node(-1);
        Node current = head;
        int carry = 0;
        while (head1 != null || head2 != null) {
            int value = carry;
            if (head1 != null) {
                value += head1.data;
                head1 = head1.next;
            }
            if (head2 != null) {
                value += head2.data;
                head2 = head2.next;
            }
            current.next = new Node(value % 10);
            carry = value / 10;
            current = current.next;
        }
        if (carry > 0) {
            current.next = new Node(carry);
        }
        return reverse(head.next);
    }

    private static Node reverse(Node head) {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    private static Node[] initLinkedList() {
        int arraySize = 2;
        Node[] heads = new Node[arraySize];
        for (int i = 0; i < arraySize; i++) {
            int currentListSize = RANDOM.nextInt(10) + 5;
            Node head = initLinkedList(currentListSize);
            heads[i] = head;
        }
        return heads;
    }

    private static Node initLinkedList(int n) {
        int currentData = RANDOM.nextInt(10);
        Node head = new Node(currentData);
        Node current = head;
        for (int i = 1; i < n; i++) {
            currentData = RANDOM.nextInt(10);
            current.next = new Node(currentData);
            current = current.next;
        }
        return head;
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
    }
}
