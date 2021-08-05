package list;

/**
 * 循环链表
 *
 * @author Jack Lee
 * @since 2021-07-05
 **/
public class LinkedListCircular {
    public static void main(String[] args) {
        Node head = init(5);
        System.out.println("链表当前状态：\t" + toString(head));
        System.out.println("链表的长度：\t" + getLength(head));
        System.out.println("第三个节点：\t" + getNode(head, 3));
        System.out.println("第五个节点：\t" + getNode(head, 5));
        System.out.println("第六个节点：\t" + getNode(head, 6));
        System.out.println(insertNode(head, new Node(11)));
        System.out.println("尾部插入节点11：\t" + toString(head));
        // 这里有个坑，如果不在deleteNode方法里面将head返回的话，打印的时候用的还是最初的head
        // why？？？  局部变量的head地址改变了，但是不会影响这里定义的变量
        Node head1 = insertFirstNode(head, new Node(0));
        System.out.println("头部插入节点00：\t" + toString(head1));
        head1 = deleteNode(head1, new Node(7));
        System.out.println("中间删除节点07：\t" + toString(head1));
        head1 = deleteNode(head1, new Node(11));
        System.out.println("尾部删除节点11：\t" + toString(head1));
        // 这里有个坑，如果不在deleteNode方法里面将head2返回的话，打印的时候用的还是最初的head1
        // why？？？  局部变量的head1地址改变了，但是不会影响这里定义的变量
        Node head2 = deleteNode(head1, new Node(0));
        System.out.println("头部删除节点00：\t" + toString(head2));
    }

    public static Node init(int n) {
        Node head = new Node(1);
        Node current = head;
        for (int i = 1; i < n; i++) {
            current.next = new Node(2 * i + 1);
            current = current.next;
        }
        current.next = head;
        return head;
    }

    /**
     * 链表的长度
     *
     * @return 链表长度
     */
    public static int getLength(Node head) {
        if (head == null) {
            return 0;
        }
        Node current = head;
        int size = 0;
        do {
            size++;
            current = current.next;
        } while (current != head);
        return size;
    }

    /**
     * 查找指定位置的节点
     *
     * @param head     头节点
     * @param position 待查找节点位置
     * @return 查找到的节点
     */
    public static Node getNode(Node head, int position) {
        if (head == null || position <= 0 || position > getLength(head)) {
            System.out.println("获取链表的位置有误！返回null");
            return null;
        } else {
            int count = 0;
            Node node = null;
            Node current = head;
            do {
                count++;
                if (count == position) {
                    node = new Node(current.data);
                }
                current = current.next;
            } while (current != head);
            return node;
        }
    }

    /**
     * 查找尾节点
     *
     * @param head 头节点
     * @return 查找到的节点
     */
    private static Node getLastNode(Node head) {
        Node current = head;
        do {
            current = current.next;
        } while (current.next != head);
        return current;
    }

    /**
     * 插入
     *
     * @param head       头节点
     * @param insertNode 待插入节点
     */
    public static Node insertFirstNode(Node head, Node insertNode) {
        Node lastNode = getLastNode(head);
        insertNode.next = head;
        //新插入的最后一个节点指向头节点
        lastNode.next = insertNode;
        return insertNode;
    }

    /**
     * 插入
     *
     * @param head       头节点
     * @param insertNode 待插入节点
     */
    public static Node insertNode(Node head, Node insertNode) {
        Node lastNode = getLastNode(head);
        lastNode.next = insertNode;
        //新插入的最后一个节点指向头节点
        insertNode.next = head;
        return head;
    }

    /**
     * 删除
     *
     * @param head       头节点
     * @param deleteNode 待删除节点
     */
    public static Node deleteNode(Node head, Node deleteNode) {
        // 如果需要删除头节点
        Node lastNode = getLastNode(head);
        while (head.data == deleteNode.data) {
            head = head.next;
        }
        lastNode.next = head;
        // 删除其他节点
        Node preNode = head;
        do {
            //判断current当前指向的结点的下一个结点是否是要删除的结点
            if (preNode.next.data == deleteNode.data) {
                //删除结点
                preNode.next = preNode.next.next;
            } else {
                //current指针后移
                preNode = preNode.next;
            }
        } while (preNode.next != head);
        return head;
    }

    /**
     * 链表打印
     *
     * @param head 头节点
     */
    public static String toString(Node head) {
        if (head == null) {
            return null;
        }
        int length = getLength(head);
        StringBuilder sb = new StringBuilder();
        Node current = head;
        int count = 0;
        do {
            sb.append(current.data).append("\t");
            current = current.next;
            count++;
        } while (current != head && count <= length);
        return sb.toString();
    }

    static class Node {
        final int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{data=" + data + "}";
        }
    }
}
