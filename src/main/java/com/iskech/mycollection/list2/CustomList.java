package com.iskech.mycollection.list2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/8 11:46
 * @description：定义List接口 介于list集合是排序有索引的特点，List接口区别于set,map有一些自己个性化的抽象方法。 其个性化的抽象方法也大都与索引，排序相关。
 *     (集合框架中顶级接口的抽象方法大多都被子接口覆盖，有一种说法是为了代码的易读性，这里站在继承的角度 就不再对上层定义的接口进行覆盖了)
 * @modified By：
 * @version: V1.0
 */
public interface CustomList<E> extends CustomCollection<E> {
  /**
   * 在目标集合指定索引处插入元素
   *
   * @param index 被指定的索引
   * @param e 被插入的元素
   * @return <tt>true</tt> 如果集合改变大小则返回true
   */
  void add(int index, E e);
  
  /**
   * 在目标集合中指定索引处插入集合
   *
   * @param index 被指定索引
   * @param collection 被插入集合
   * @return <tt>true</tt> 如果集合改变大小则返回true
   */
  boolean addAll(int index, CustomCollection<? extends E> collection);

  /**
   * 获取指定索引处元素
   *
   * @param index 被指定索引
   * @return 返回匹配到的元素
   */
  E get(int index);

  /**
   * 获取指定元素在目标集合中的索引（如果存在）
   *
   * @param obj 指定元素
   * @return 返回匹配元素在目标集合第一次出现的索引
   */
  int indexOf(Object obj);

  /**
   * 获取指定元素最后一次在目标集合出现的索引
   *
   * @param obj 指定元素
   * @return 返回匹配元素在目标集合第一次出现的索引
   */
  int lastIndexOf(Object obj);

  /**
   * 移除目标集合指定索引处元素
   *
   * @param index 指定索引
   * @return <tt>true</tt> 如果集合改变大小则返回true
   */
  E remove(int index);

  /**
   * 将目标集合索引处元素修改为指定元素
   *
   * @param index 指定索引
   * @param e 指定元素
   * @param <E> 元素类型
   * @return <tt>e<tt/> 返回指定元素（如果修改成功）
   */
   E set(int index, E e);

  /**
   * 将目标元素从指定的索引段截取并返回该截取的元素集合
   *
   * @param fromIndex 截取开始索引
   * @param toIndex 截取结束索引
   * @return 返回截取到的元素集合
   */
  CustomList subList(int fromIndex, int toIndex);

  /**
   * 使用指定的比较器对目标集合重新排序
   *
   * @param c
   */
  default void sort(Comparator<? super E> c) {
    Object[] a = this.toArray();
    Arrays.sort(a, (Comparator) c);
    ListIterator<E> i = this.listIterator();
    for (Object e : a) {
      i.next();
      i.set((E) e);
    }
  }

  /** @return */
  ListIterator<E> listIterator();

  /**
   * @param index
   * @return
   */
  ListIterator<E> listIterator(int index);
}
