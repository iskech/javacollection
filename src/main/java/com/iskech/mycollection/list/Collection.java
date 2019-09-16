package com.iskech.mycollection.list;

import java.util.function.Predicate;
import java.util.stream.Stream;
import java.lang.Iterable;
import java.util.Iterator;
public interface Collection<E> extends Iterable<E> {
  /**
   * 添加元素
   *
   * @param e 目标元素
   * @return boolean
   */
  boolean add(E e);

  /**
   * 添加集合
   *
   * @param collection 目标集合
   * @return
   */
  boolean addAll(Collection<? extends E> collection);

  /** 清理集合 */
  void clear();

  /**
   * 判断是否包含目标对象
   *
   * @param obj 目标对象
   * @return boolean
   */
  boolean contains(Object obj);

  /**
   * 是否包含目标集合
   *
   * @param collection 目标集合
   * @return
   */
  boolean containsAll(Collection<?> collection);

  /**
   * 重写equals 方法
   *
   * @param obj
   * @return
   */
  @Override
  boolean equals(Object obj);

  /**
   * 重写hashcode
   *
   * @return
   */
  @Override
  int hashCode();

  /**
   * 集合是否为空
   *
   * @return
   */
  boolean isEmpty();

  /**
   * 返回流
   *
   * @return
   */
  default Stream<E> paralleStream() {
    // todo
    return null;
  }

  /**
   * 移除目标元素
   *
   * @param obj 目标元素
   * @return
   */
  boolean remove(Object obj);

  /**
   * 移除目标集合
   *
   * @param collection 目标集合
   * @return
   */
  boolean removeAll(Collection<?> collection);

  /**
   * @param predicate
   * @return
   */
  boolean removeIf(Predicate<? super E> predicate);

  /**
   * 判断是否包含目标集合
   *
   * @param collection
   * @return
   */
  boolean retainAll(Collection<?> collection);

  /**
   * 获取集合大小
   *
   * @return
   */
  int size();

  /** @return */
  default Stream<E> stream() {
    // todo
    return null;
  }

  /**
   * 将集合转为数组
   *
   * @return
   */
  Object[] toArray();

  /**
   * 将集合转换为指定类型数组
   *
   * @param ts
   * @param <T>
   * @return
   */
  <T> T toArray(T[] ts);
  
  /**
   * 返回迭代器
   * @return
   */
  @Override
  Iterator<E> iterator();
}
