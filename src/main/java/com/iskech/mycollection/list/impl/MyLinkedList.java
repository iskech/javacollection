package com.iskech.mycollection.list.impl;

import java.util.*;

public class MyLinkedList<E> extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable {
  transient int size = 0;

  /**
   * Pointer to first node. Invariant: (first == null && last == null) || (first.prev == null &&
   * first.item != null)
   */
  transient Node<E> first;

  /**
   * Pointer to last node. Invariant: (first == null && last == null) || (last.next == null &&
   * last.item != null)
   */
  transient Node<E> last;

  /**
   * 在集合末尾插入元素
   *
   * @param e
   * @return
   */
  @Override
  public boolean add(E e) {
    linkLast(e);
    return true;
  }

  /**
   * 在目标集合指定索引处插入元素
   *
   * @param index
   * @param element
   */
  @Override
  public void add(int index, E element) {
    // 同arrarylist 一样 每次涉及索引都需要检查索引是否越界
    checkPositionIndex(index);
    // 如果index恰巧=size则直接将元素插入末尾 等效于add(e)
    if (index == this.size) {
      this.linkLast(element);
    } else {
      // 反之则需要在元素链中间插入元素，需要找到被插入的位置并重新维护节点间指向
      linkBefor(element, node(index));
    }
  }

  /**
   * 在目标集合指定索引处插入集合片段
   *
   * @param index
   * @param c
   * @return
   */
  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    // 找出被插入的两个节点（如果有），迭代目标集合并创建对应的节点（维护好prev节点），最后维护两个节点最终关联
    Objects.requireNonNull(c);
    // 检查索引
    checkPositionIndex(index);
    E[] objects = (E[]) c.toArray();
    if (objects.length == 0) {
      return false;
    }
    Node<E> indexNode = node(index);
    Node<E> pred, next;
    pred = indexNode.prev;
    next = indexNode.next;
    for (E object : objects) {
      Node<E> newNode = new Node<>(pred, object, null);
      // 维护pred.next
      if (pred == null) {
        this.first = pred;
      } else {
        pred.next = newNode;
      }
      // 重新设置 pred
      pred = newNode;
    }
    if (next != null) {
      pred.next = next;
      next.prev = pred;
    } else {
      this.last = pred;
    }
    this.size += objects.length;

    return true;
  }
  
 /// public void addAll(Collection<>){}
  

  private void linkBefor(E element, Node<E> node) {
    // 将节点前一个节点取出,并将该节点前一个节点指向目标元素节点，
    // 并将该节点的后一个节点指向目标元素，并将取出的节点的后一个节点设置为插入节点
    Node<E> nodePrev = node.prev;
    Node<E> insertNode = new Node<>(nodePrev, element, node);
    node.prev = insertNode;
    if (nodePrev == null) {
      this.first = insertNode;
    } else {
      nodePrev.next = insertNode;
    }
    this.size++;
  }

  /**
   * 根据索引找出node节点
   *
   * @param index
   * @return
   */
  private Node<E> node(int index) {
    // 使用二分法概念查找
    Node<E> template = null;
    if (index < this.size >> 1) {
      for (int i = 0; i < index; i++) {
        template = this.first.next;
      }
    } else {
      for (int i = this.size - 1; i > index; i--) {
        template = this.last.prev;
      }
    }
    return template;
  }

  /**
   * 检查索引是否越界
   *
   * @param index
   */
  private void checkPositionIndex(int index) {
    if (!this.isPositionIndex(index)) {
      throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
    }
  }

  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= this.size;
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }
  private void linkLast(E e) {
    // 创建一个新节点并维护该节点与目标集合最后一个节点的关系
    // 操作过程不允许并发操作last节点
    final Node<E> last = this.last;
    Node<E> newNode = new Node<>(last, e, null);
    // 在lindkedList中 只要有first就一定有last反之亦然如果他们相同则last.prev无，first.next也无引用
    // 将last节点的next属性指向newNode
    this.last = newNode;
    if (last == null) {
      this.first = newNode;
    } else {
      last.next = newNode;
    }
  }

  public static void main(String[] args) {
    LinkedList<Integer> integers = new LinkedList<>();
    integers.add(1);
    Integer first = integers.getFirst();
    Integer last = integers.getLast();
    integers.add(2);
    integers.add(3);
    Integer[] ints={3,4,5,6};
    integers.addAll(1,Arrays.asList(ints));
    System.out.println(integers);
    System.out.println(integers.size());
    
    final  String name="xx";
    name.substring(0,1);
    System.out.println(name);
  }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "size=" + size +
                ", first=" + first +
                ", last=" + last +
                '}';
    }

    @Override
  public ListIterator<E> listIterator(int index) {
    return null;
  }

  @Override
  public void addFirst(E e) {}

  @Override
  public void addLast(E e) {}

  @Override
  public boolean offerFirst(E e) {
    return false;
  }

  @Override
  public boolean offerLast(E e) {
    return false;
  }

  @Override
  public E removeFirst() {
    return null;
  }

  @Override
  public E removeLast() {
    return null;
  }

  @Override
  public E pollFirst() {
    return null;
  }

  @Override
  public E pollLast() {
    return null;
  }

  @Override
  public E getFirst() {
    return null;
  }

  @Override
  public E getLast() {
    return null;
  }

  @Override
  public E peekFirst() {
    return null;
  }

  @Override
  public E peekLast() {
    return null;
  }

  @Override
  public boolean removeFirstOccurrence(Object o) {
    return false;
  }

  @Override
  public boolean removeLastOccurrence(Object o) {
    return false;
  }

  @Override
  public boolean offer(E e) {
    return false;
  }

  @Override
  public E remove() {
    return null;
  }

  @Override
  public E poll() {
    return null;
  }

  @Override
  public E element() {
    return null;
  }

  @Override
  public E peek() {
    return null;
  }

  @Override
  public void push(E e) {}

  @Override
  public E pop() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public Iterator<E> descendingIterator() {
    return null;
  }
  
  private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }
}
