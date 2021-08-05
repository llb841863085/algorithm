package list;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

/**
 * 链表排序
 * LeetCode148题 ：给定一个无序单链表，实现单链表的排序(按升序排序)。
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * @author Jack Lee
 * @since 2021-07-14
 **/
public class LinkedListSort {
  private static final SecureRandom RANDOM = new SecureRandom();

  public static void main(String[] args) {
    Node head1 = initLinkedList(10);
    System.out.println(toString(head1));
    System.out.println(toString(sortInList(head1)));

    Node head2 = initLinkedList(15);
    System.out.println(toString(head2));
    System.out.println(toString(sortInList2(head2)));

    Node head3 = initLinkedList(5);
    System.out.println(toString(head3));
    System.out.println(toString(sortInList3(head3)));
  }

  /**
   * 偷梁换柱，通过数组排序再赋值回去
   * 过程是这样的：首先循环一次获得链表的长度，然后创建一个数组。
   * 再循环一次，将链表节点的值都赋值给数组对数组进行排序。
   * 最后将数组的值再写回到链表里。
   *
   * @param head 链表头节点
   * @return 排序后的链表头结点
   */
  public static Node sortInList(Node head) {
    if (head == null) {
      return null;
    }
    Node p = head;
    // 获取链表长度
    int n = 0;
    while (p != null) {
      p = p.next;
      n++;
    }
    // 将链表添加到数组
    int[] arr = new int[n];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = head.data;
      head = head.next;
    }
    // 这里给我们省去了无数麻烦。如果有精力，可以现场自己实现一个
    Arrays.sort(arr);
    // 将排序后的结果放到链表里面
    int i = 0;
    Node q = head;
    while (q != null) {
      q.data = arr[i++];
      q = q.next;
    }
    return head;
  }

  /**
   * 归并排序法
   * 这里我们可以使用归并排序的方法来进行，先利用快慢指针找出链表的中点，然后分为两个链表，一直分，直到无法分为止，然后自底而上排序归并。
   *
   * @param head 链表头节点
   * @return 排序后的链表头结点
   */
  public static Node sortInList2(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    // 使用快慢指针找到中点
    Node slow = head;
    Node fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // 分割为两个链表
    Node newList = slow.next;
    slow.next = null;
    //将两个链表继续分割,这里必须用递归，否则一堆拆散的链表难以管理
    Node left = sortInList2(head);
    Node right = sortInList2(newList);
    Node prevHead = new Node(-1);
    Node prevNode = prevHead;
    //归并排序，如果想不明白，可以看完后面的经典排序专题再来看
    while (left != null && right != null) {
      if (left.data < right.data) {
        prevNode.next = left;
        left = left.next;
      } else {
        prevNode.next = right;
        right = right.next;
      }
      prevNode = prevNode.next;
    }
    //判断左右链表是否为空
    prevHead.next = left != null ? left : right;
    return prevHead.next;
  }

  /**
   * 利用冒泡方式进行排序
   * TODO 验证
   *
   * @param head 链表头节点
   * @return 排序后的链表头结点
   */
  public static Node sortInList3(Node head) {
    if (head == null) {
      return null;
    }
    Node oldPrev = head;
    Node newHead = head;
    while (oldPrev != null) {
      newHead = insertSeq(newHead, oldPrev);
      oldPrev = oldPrev.next;
    }
    return newHead;
  }

  public static Node insertSeq(Node newHead, Node oldHead) {
    if (newHead == null) {
      return oldHead;
    }
    //最前面
    if (newHead.data >= oldHead.data) {
      oldHead.next = newHead;
      newHead = oldHead;
      return newHead;
    }
    Node newCur = newHead;
    //中间位置
    while (newCur.data < oldHead.data && newCur.next != null) {
      newCur = newCur.next;
    }
    oldHead.next = newCur.next;
    newCur.next = oldHead;
    return newHead;
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

  private static Node initLinkedList(int n) {
    int currentData = RANDOM.nextInt(10);
    Node head = new Node(currentData);
    Node current = head;
    for (int i = 1; i < n; i++) {
      currentData = RANDOM.nextInt(50) + 1;
      current.next = new Node(currentData);
      current = current.next;
    }
    return head;
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
