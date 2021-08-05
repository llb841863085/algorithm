package list;

import java.security.SecureRandom;
import java.util.Objects;

/**
 * 链表删除
 *
 * @author Jack Lee
 * @since 2021-08-05
 **/
public class LinkedListDeleteNode {
  private static final SecureRandom RANDOM = new SecureRandom();

  public static void main(String[] args) {
    Node head = initLinkedList();
    System.out.println("初始化结果：\t" + toString(head));
    head = deleteNode(head, 1);
    System.out.println("删除位置结果：\t" + toString(head));
    deleteNonTailNode(getNode(head, 5));
    System.out.println("删非尾节点：\t" + toString(head));
    head = removeElements(head, 8);
    System.out.println("删除元素后：\t" + toString(head));
  }

  /**
   * 删除节点
   *
   * @param head     链表头节点
   * @param position 删除节点位置，取值从1开始
   * @return 删除后的链表头节点
   */
  public static Node deleteNode(Node head, int position) {
    if (head == null) {
      return null;
    }
    int size = getLength(head);
    if (position > size || position <= 0) {
      System.out.println("输入的参数有误");
      return head;
    }
    // 删除头节点
    if (position == 1) {
      return head.next;
    }
    // 删除非头节点
    else {
      Node preNode = head;
      int count = 1;
      while (count < position) {
        preNode = preNode.next;
        count++;
      }
      Node curNode = preNode.next;
      preNode.next = curNode.next;
    }
    return head;
  }

  /**
   * LeetCode 237：删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为要被删除的节点 。
   * 示例1：
   * 输入：head = [4,5,1,9], node = 5
   * 输出：[4,1,9]
   * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
   * 示例2：
   * 输入：head = [4,5,1,9], node = 1
   * 输出：[4,5,9]
   * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
   * 提示：
   * 1、链表至少包含两个节点。
   * 2、链表中所有节点的值都是唯一的。
   * 3、给定的节点为非末尾节点并且一定是链表中的一个有效节点。
   * 4、不要从你的函数中返回任何结果。
   */
  public static void deleteNonTailNode(Node node) {
    if (node == null || node.next == null) {
      System.out.println("删除节点设置错误");
      return;
    }
    node.data = node.next.data;
    node.next = node.next.next;
  }

  /**
   * LeetCode 203  移除链表中的目标元素
   * LeetCode203就是一个常规的删除节点的题了。
   * 我们先看一下完整的题目要求：
   * LeetCode203：给你一个链表的头节点 head 和一个整数 data ，请你删除链表中所有满足 Node.data == data 的节点，并返回新的头节点 。
   * 1.我们创建一个虚拟链表头dummyHead,然后使用temp = dummyHead进行链表操作
   * 2.开始循环head链表
   * 3.当该节点的值不等于data时，将temp的next指向该节点，然后将temp指向它的next节点。
   * 这里注意temp在结束时，需要将它的未节点指向None，避免出现符合条件的最后一次赋值后，链接指向错误问题。
   */
  public static Node removeElements(Node head, int data) {
    Node dummyHead = new Node(0);
    dummyHead.next = head;
    Node temp = dummyHead;
    while (temp.next != null) {
      if (temp.next.data == data) {
        temp.next = temp.next.next;
      } else {
        temp = temp.next;
      }
    }
    return dummyHead.next;
  }

  /**
   * 查找指定位置的节点
   *
   * @param head 头节点
   * @param data 待查找节点位置
   * @return 查找到的节点
   */
  public static Node getNode(Node head, int data) {
    Node current = head;
    while (current != null) {
      if (current.data == data) {
        return current;
      }
      current = current.next;
    }
    System.out.println("没找到对应的节点。");
    return null;
  }

  /**
   * 获取链表长度
   *
   * @param head 链表头节点
   * @return 链表长度
   */
  public static int getLength(Node head) {
    int length = 0;
    Node node = head;
    while (node != null) {
      length++;
      node = node.next;
    }
    return length;
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

  private static Node initLinkedList() {
    int currentData = RANDOM.nextInt(10);
    Node head = new Node(currentData);
    Node current = head;
    for (int i = 1; i < 10; i++) {
      currentData = RANDOM.nextInt(10) + 1;
      current.next = new Node(currentData);
      current = current.next;
    }
    for (int i = 1; i <= 10; i++) {
      current.next = new Node(i);
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
