package list;

/**
 * 环形链表两道题和深入讨论
 * <p>
 * 1、判断是否有环
 * 2、如果有环，找出环的首节点
 *
 * @author Jack Lee
 * @since 2021-07-06
 **/
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class LinkedListCycle {
    public static void main(String[] args) {
        Node head = new Node(1);
        Node current = head;
        current.next = new Node(2);
        current = current.next;
        Node cycleBegin = new Node(3);
        current.next = cycleBegin;
        current = current.next;
        current.next = new Node(4);
        current = current.next;
        current.next = new Node(5);
        current = current.next;
        current.next = new Node(6);
        current = current.next;
        System.out.println(toString(head));
        current.next = cycleBegin;
        System.out.println(toString(head));
        System.out.println("是否有环：" + hasCycle(head));
        System.out.println("环首节点：" + detectCycle(head));
    }

    /**
     * LeetCode141的要求是：如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数pos来表示链表尾连接到链表中的位置（索引从0开始）。
     * 如果*pos是-1，则在该链表中没有环。
     * 注意：pos不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * <p>
     * 如果链表中存在环，则返回true。 否则，返回false。
     *
     * @param head 头节点
     * @return 是否有环
     */
    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node fast = head;
        Node slow = head;
        //注意这里的条件必须有两个，因为fast一次要移动两个位置
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * LeetCode142的要求是：在141的基础上，如果存在环，则返回链表开始入环的第一个节点。
     *
     * @param head 头节点
     * @return 环的首结点
     */
    public static Node detectCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            System.out.println("fast：" + fast + "， slow：" + slow);
            //利用快慢指针找相遇点
            if (fast == slow) {
                //设置以相同速度的新指针从起始位置出发
                Node slow2 = head;
                //未相遇循环
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                    System.out.println("fast：" + fast + "，slow：" + slow + "，slow2：" + slow2);
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 链表打印
     *
     * @param head 头节点
     */
    public static String toString(Node head) {
        Node current = head;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (current != null && ++count < 30) {
            sb.append(current.data).append("\t");
            current = current.next;
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
        public String toString() {
            return data + "";
        }
    }
}
