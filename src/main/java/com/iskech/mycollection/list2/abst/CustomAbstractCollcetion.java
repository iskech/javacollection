package com.iskech.mycollection.list2.abst;

import com.iskech.mycollection.list2.CustomCollection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/8 13:42
 * @description：集合顶级抽象类，抽象实现了部分通用方法
 * @modified By：
 * @version: V1.0
 */
@SuppressWarnings("ALL")
public abstract class CustomAbstractCollcetion<E> implements CustomCollection<E> {
  /**
   * jdk 中直接实现了部分不通用的接口方法并通过抛异常防止被误调用 这里站在抽象类的设计角度，将不通用的接口方法覆盖为抽象方法不提供实现
   *
   * @param e 被添加的元素
   * @return
   */
  @Override
  public boolean add(E e) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param collection 被添加元素集合
   * @return
   */
  @Override
  public boolean addAll(CustomCollection<? extends E> c) {
    boolean modified = false;
    for (E e : c) {
      if (add(e)) {
        modified = true;
      }
    }
    return modified;
  }

  /** 清空目标集合 */
  @Override
  public void clear() {
    // 使用迭代器迭代集合
    Iterator<E> iterator = this.iterator();
    while (iterator.hasNext()) {
      iterator.remove();
    }
  }

  /**
   * @param obj 是否包含该元素
   * @return
   */
  @Override
  public boolean contains(Object obj) {
    Iterator<E> iterator = this.iterator();
    while (iterator.hasNext()) {
      // 使用三元运算符可以简化简单的if-else语句
      if (obj == null ? iterator.next() == null : obj.equals(iterator.next())) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param collection 是否包含该集合
   * @return
   */
  @Override
  public boolean containsAll(CustomCollection<?> collection) {
    Iterator<?> iterator = collection.iterator();
    while (iterator.hasNext()) {
      if (!this.contains(iterator.next())) {
        return false;
      }
    }
    return true;
  }

  /** @return <tt>true<tt/> 若目标集合为空则返回true */
  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }

  @Override
  public boolean remove(Object o) {
    Iterator<E> it = iterator();
    if (o == null) {
      while (it.hasNext()) {
        if (it.next() == null) {
          it.remove();
          return true;
        }
      }
    } else {
      while (it.hasNext()) {
        if (o.equals(it.next())) {
          it.remove();
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean removeAll(CustomCollection<?> c) {
    Objects.requireNonNull(c);
    boolean modified = false;
    Iterator<?> it = iterator();
    while (it.hasNext()) {
      if (c.contains(it.next())) {
        it.remove();
        modified = true;
      }
    }
    return modified;
  }

  /**
   * @param collection 目标集合保留的元素集合（即不被移除的元素集合）
   * @return
   */
  @Override
  public boolean retainAll(CustomCollection<?> collection) {
    Objects.requireNonNull(collection);
    boolean modify = false;
    Iterator<E> iterator = this.iterator();
    while (iterator.hasNext()) {
      if (!collection.contains(iterator.next())) {
        iterator.remove();
        modify = true;
      }
    }
    return modify;
  }

  @Override
  public abstract int size();

  @Override
  public Object[] toArray() {
    Object[] objects = new Object[this.size()];
    // 迭代将目标集合元素逐一添加到新的数组
    Iterator<E> iterator = this.iterator();
    int index = 0;
    while (iterator.hasNext()) {
      if (!iterator.hasNext()) { // fewer elements than expected
        return Arrays.copyOf(objects, index);
      }
      objects[0] = iterator.next();
    }
    return objects;
  }

  @Override
  public <T> T[] toArray(T[] t) {
    // todo
    return null;
  }

  @Override
  public abstract Iterator<E> iterator();

  @Override
  public String toString() {
    Iterator<E> it = iterator();
    if (!it.hasNext()) {
      return "[]";
    }

    StringBuilder sb = new StringBuilder();
    sb.append('[');
    while (it.hasNext()) {
      E e = it.next();
      sb.append(e == this ? "(this Collection)" : e);
      sb.append(',').append(' ');
    }

    return sb.append(']').toString();
  }
}
