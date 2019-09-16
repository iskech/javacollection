package com.iskech.mycollection.list;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

public interface List<E> extends Collection<E> {
  /**
   * 在指定索引位置插入集合
   *
   * @param index
   * @param collection
   * @return
   */
  boolean addAll(int index, Collection<? extends E> collection);

  /**
   * @since 1.8
   * @param operator
   */
  default void replaceAll(UnaryOperator<E> operator) {
    // todo
  }

  /**
   * 排序
   *
   * @since 1.8
   * @param comparator
   */
  default void sort(Comparator<? super E> comparator) {
    // todo
  }

  /**
   * 获取指定索引元素
   *
   * @param index
   * @param <E>
   * @return
   */
  <E> E get(int index);

  /**
   * 设置元素至指定索引位置
   *
   * @param index
   * @param e
   * @param <E>
   * @return
   */
  <E> E set(int index, E e);

  /**
   * 添加元素至指定索引位置
   *
   * @param index
   * @param e
   */
  void add(int index, E e);

  /**
   * 移除指定索引位置元素
   *
   * @param index
   * @param <E>
   * @return
   */
  <E> E remove(int index);

  /**
   * 获取指定元素索引（如果存在）
   *
   * @param obj
   * @return
   */
  int indexOf(Object obj);

  /**
   * 获取指定元素末尾索引（如果存在）
   *
   * @param obj
   * @return
   */
  int lastIndexOf(Object obj);

  /**
   * 返回列表迭代器
   *
   * @return
   */
  ListIterator<E> listIterator();

  /**
   * 返回指定索引开始列表迭代器
   *
   * @param index
   * @return
   */
  ListIterator<E> listIterator(int index);

  /**
   * 返回子列表（截取列表）
   *
   * @param start 开始索引
   * @param end 结束索引
   * @return
   */
  List<E> subList(int start, int end);
}
