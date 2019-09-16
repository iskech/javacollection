package com.iskech.mycollection.list2;

import java.util.*;

/**
 * @author iskech 集合顶级接口，定义添加，移除，清空，包含，toArray,判空，等基本抽象方法(set,list,map通用的方法抽出)
 *     引进泛型，使得集合可以接收任意类型元素的同时又可以在获取元素时知道元素的真实类型无需向下转型
 * @see Set
 * @see List
 * @see Map
 * @see SortedSet
 * @see SortedMap
 * @see HashSet
 * @see TreeSet
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Collections
 * @see Arrays
 * @see AbstractCollection
 */
public interface CustomCollection<E> extends Iterable<E> {
  /**
   * @param e 被添加的元素
   * @return <tt>true</tt> 如果集合改变则返回true
   */
  boolean add(E e);

  /**
   * @param collection 被添加元素集合
   * @return <tt>true</tt> 如果集合改变则返回true
   */
  boolean addAll(CustomCollection<? extends E> collection);

  /** 清空目标集合 */
  void clear();

  /**
   * @param obj 是否包含该元素
   * @return <tt>true</tt> 如果包含目标元素则返回true
   */
  boolean contains(Object obj);

  /**
   * @param collection 是否包含该集合
   * @return <tt>true</tt> 如果包含目标集合则返回true
   */
  boolean containsAll(CustomCollection<?> collection);

  /** @return <tt>true</tt> 如果目标集合为空则返回true */
  boolean isEmpty();

  /**
   * @param obj 被移除的目标元素
   * @return <tt>true</tt> 如果目标集合大小改变则返回true
   */
  boolean remove(Object obj);

  /**
   * @param collection 被移除的目标元素集合
   * @return <tt>true</tt> 如果目标集合大小改变则返回true
   */
  boolean removeAll(CustomCollection<?> collection);

  /**
   * @param collection 目标集合保留的元素集合（即不被移除的元素集合）
   * @return <tt>true</tt> 如果目标集合大小改变则返回true
   */
  boolean retainAll(CustomCollection<?> collection);

  /** @return 返回目标集合大小 */
  int size();

  /** @return 将目标集合转换为object数组 */
  Object[] toArray();

  /**
   * @param t 比对的元素数组
   * @param <T> 元素类型
   * @return 将目标集合所有匹配数组的元素转换为数组类型
   */
  <T> T[] toArray(T[] t);

  /**
   * @param obj 比对的目标元素
   * @return <tt>true</tt> 如果目标元素与比对的元素匹配则返回true
   */
  @Override
  boolean equals(Object obj);

  /** @return 返回目标散列码 */
  @Override
  int hashCode();

  /**
   * Returns an iterator over the elements in this collection. There are no guarantees concerning
   * the order in which the elements are returned (unless this collection is an instance of some
   * class that provides a guarantee).
   *
   * @return an <tt>Iterator</tt> over the elements in this collection
   */
  @Override
  Iterator<E> iterator();

  /**
   * create by: liujx description: TODO create time: 2019/9/8 11:15 @Param: null
   *
   * @return
   */
 // Stream<E> parallelStream();
  /**
   * create by: liujx description: TODO create time: 2019/9/8 11:21 @Param: null
   *
   * @return
   */
 // boolean removeIf(Predicate<? super E> predicate);
}
