package com.iskech.mycollection.list.impl;

import com.iskech.mycollection.list.Collection;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author ：liujx
 * @date ：Created in 2019/8/31 21:53
 * @description：自定义ArrayList
 * @modified By：
 * @version: V1.0
 */
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
  private static final long serialVersionUID = 8683452581122892189L;
  // 数组，真实容纳元素的对象
  private Object[] elementData;
  // 记录集合元素真实大小，并记录当前元素末尾索引位置
  private int size = 0;
  // 用于快速失败 todo
  private int modcount = 0;

  private static final int DEFAULT_CAPACITY = 10;

  private static final Object[] EMPTY_ELEMENTDATA = {};

  private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    ;
  }

  @Override
  public int size() {
    return 0;
  }

  /**
   * 添加指定元素至集合末尾位置
   *
   * @param e
   * @return
   */
  @Override
  public boolean add(E e) {
    // size + size
    // 确保元数组大小，不够则扩容 size+1 -elementData 判断
    this.ensureCapacity(this.size + 1);
    // 扩容 older_length >>1 +older_length or size+1
    // 扩容需要考虑size+1是否已经超出max_length =int_max_length -8
    // 超出则取 list_max_length or int_max_length
    // 将元素添加至数组size索引位置
    this.elementData[size] = e;
    this.size++;
    return true;
  }

  /**
   * 添加元素至集合指定索引位置，并将该索引位置以后元素统一右移一个单位
   *
   * @param index
   * @param e
   * @return
   */
  @Override
  public void add(int index, E e) {
    // 检查是否索引越界
    this.rangeCheck(index);
    // 检查是否需要扩容
    this.ensureCapacity(this.size + 1);
    // 将元数组索引index位置以后的元素统一右移动一个位置
    System.arraycopy(this.elementData, index, this.elementData, index + 1, 1);
    // 将元数组指定索引位置值设置为e
    this.elementData[index] = e;
  }

  /**
   * 在目标集合末尾添加集合
   *
   * @param c
   * @return
   */
  public boolean addAll(Collection<? extends E> c) {
    Objects.requireNonNull(c);
    // 检查是否需要扩容
    this.ensureCapacity(this.size + c.size());
    // 将集合转为数组并拷贝至目标集合末尾
    E[] newArrays = (E[]) c.toArray();
    // 注意该方法用法将指定数组指定索引后的元素拷贝到目标指定索引位置以后
    int numLength = newArrays.length;
    System.arraycopy(newArrays, 0, this.elementData, this.size, numLength);
    this.size += numLength;
    return numLength > 0;
  }

  /**
   * 在目标集合指定索引处插入集合，并将原集合索引以后元素移动集合大小单位
   *
   * @param index
   * @param c
   * @return
   */
  public boolean addAll(int index, Collection<? extends E> c) {
    Objects.requireNonNull(c);
    // 检查是否索引越界
    this.rangeCheck(index);
    // 检查是否需要扩容
    this.ensureCapacity(this.size + c.size());
    // 先移动指定索引位置以后元素，后将指定集合覆盖指定索引位置后元素（避免先覆盖后移动导致的元数组元素丢失）
    E[] toArray = (E[]) c.toArray();
    int numLength = toArray.length;
    // 优化：若指定索引大于等于size则不需要移动元数组
    // System.arraycopy(this.elementData, index, this.elementData, index + numLength, numLength);
    if (index - this.size >= 0) {
      System.arraycopy(this.elementData, index, this.elementData, index + numLength, numLength);
    }
    System.arraycopy(toArray, 0, this.elementData, index, numLength);
    this.size += numLength;
    LinkedList<Integer> integers = new LinkedList<>();
    integers.isEmpty();
    integers.size();
    return numLength > 0;
  }

  /** 清空目标集合至空集合 */
  @Override
  public void clear() {
    // modcoutn ++ todo
    // 迭代元数组并将所有索引引用值赋值为null，java GC会清除对应的null引用
    for (int i = 0; i < this.size; i++) {
      this.elementData[i] = null;
      modcount++;
    }
    // 最后将size更新
    this.size = 0;
  }

  /**
   * 拷贝集合
   *
   * @return
   */
  @Override
  public ArrayList<E> clone() {
    try {
      // 调用父clone方法创建arraylist 对象
      ArrayList clone = (ArrayList<?>) super.clone();
      // 将元数组拷贝并赋值给clone创建的arraylist对象
      Object[] copyOf = Arrays.copyOf(this.elementData, elementData.length);
      clone.elementData = copyOf;
      clone.size = copyOf.length;
      clone.modcount = 0;
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e);
    }
  }

  /**
   * 返回指定元素第一次出现在集合的索引
   *
   * @param obj
   * @return
   */
  @Override
  public int indexOf(Object obj) {
    // 迭代元数组并使用equals比对是否所有的元素是否匹配目标元素匹配成功返回当前迭代元素索引
    for (int index = 0; index < size; index++) {
      if (obj == null) {
        if (elementData[index] == null) {
          return index;
        }
      } else {
        if (obj.equals(elementData[index])) {
          return index;
        }
      }
    }
    return -1;
  }

  /**
   * 目标集合是否包含指定元素
   *
   * @param obj
   * @return
   */
  @Override
  public boolean contains(Object obj) {
    return indexOf(obj) >= 0;
  }

  /**
   * 扩容
   *
   * @param minCapacity
   */
  public void ensureCapacity(int minCapacity) {
    int minExpand = elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA ? 0 : DEFAULT_CAPACITY;
    if (minCapacity > minExpand) {
      // 扩容
      grow(minCapacity);
    }
  }

  /**
   * forEach 迭代集合元数组
   *
   * @param consumer
   */
  @Override
  public void forEach(Consumer<? super E> consumer) {
    Objects.requireNonNull(consumer);
    // 迭代过程中需要注意多线程下 元数组，size等被修改
    final E[] elementData = (E[]) this.elementData;
    final int size = this.size;
    final int modcountModify = this.modcount;
    // todo
    for (int i = 0; modcountModify == modcount && i < size; i++) {
      consumer.accept(elementData[i]);
    }
    if (modcountModify != this.modcount) {
      throw new ConcurrentModificationException();
    }
  }

  /**
   * 获取指定索引位置元素
   *
   * @param index
   * @return
   */
  @Override
  public E get(int index) {
    // 检查索引是否越界
    rangeCheck(index);
    return this.elementData(index);
  }

  /**
   * 修改集合指定索引值为目标值并将原值返回
   *
   * @param index
   * @param value
   * @return
   */
  @Override
  public E set(int index, E value) {
    E olderValue = this.elementData(index);
    this.elementData[index] = value;
    return olderValue;
  }

  /**
   * 判断集合是否为空
   *
   * @return
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * 返回迭代器，每一类集合实现类都有私有的迭代器类型 todo 迭代器后续理解
   *
   * @return
   */
  @Override
  public Iterator<E> iterator() {
    return new ArrayList<E>.Itr();
  }

  /**
   * 获取指定元素在集合中最后一次出现的索引位置
   *
   * @param obj
   * @return
   */
  @Override
  public int lastIndexOf(Object obj) {
    // indexOf使用正序迭代，该方法相反
    for (int i = this.size - 1; i >= 0; i--) {
      if (obj == null) {
        if (this.elementData[i] == null) {
          return i;
        }
      } else {
        if (obj.equals(this.elementData[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * 移除集合指定索引处元素
   *
   * @param index
   * @return
   */
  @Override
  public E remove(int index) {
    // 检查是否索引越界
    rangeCheck(index);
    // todo
    this.modcount++;
    // 移除元素即为将该索引位置以后元素向左移动一个单位，并将末尾引用指空
    E olderValue = this.elementData(index);
    System.arraycopy(this.elementData, index + 1, this.elementData, index, (this.size - index) - 1);
    this.elementData[--size] = null;
    return olderValue;
  }

  /**
   * 移除集合指定元素（如果存在）
   *
   * @param obj
   * @return
   */
  @Override
  public boolean remove(Object obj) {
    // 移除该元素前提是先找到该元素是否存在并获取其位置即索引
    // todo fast remove
    int modifyIndex = -1;
    for (int i = this.size - 1; i >= 0; i--) {
      if (obj == null) {
        if (this.elementData[i] == null) {
          System.arraycopy(this.elementData, i + 1, this.elementData, i, (this.size - i) - 1);
          this.elementData[--this.size] = null;
          return true;
        }
      } else {
        if (obj.equals(this.elementData[i])) {
          System.arraycopy(this.elementData, i + 1, this.elementData, i, (this.size - i) - 1);
          this.elementData[--this.size] = null;
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 将目标集合移除指定元素集合
   *
   * @param c
   * @return
   */
  @Override
  public boolean removeAll(java.util.Collection<?> c) {
    // 迭代目标集合并判断迭代出的元素是否在移除集合中，若在则将元素添加至元数组首部
    // 需要保留的元素的新索引；
    return batchRemove(c, false);
  }

  private boolean batchRemove(java.util.Collection<?> c, boolean b) {
    Objects.requireNonNull(c);
    boolean modify = false;
    int w = 0;
    // 当前迭代到元素的索引
    int r = 0;
    try {
      for (; r < this.size; r++) {
        if (c.contains(this.elementData[r]) == b) {
          // 将需要保留的元素写到数组首部
          this.elementData[w++] = this.elementData[r];
        }
      }
    } finally {
      // 如果迭代操作出现异常则将未迭代操作的元素拷贝到元数组w索引以后位置
      if (r != this.size) {
        System.arraycopy(elementData, r, elementData, w, this.size - r);
        w += this.size - r;
      }
      // 将不需要的元素索引引用为空
      if (w != this.size) {
        this.modcount += this.size - w;
        modify = true;
        for (; w < this.size; size--) {
          elementData[size] = null;
        }
      }
    }
    return modify;
  }

  /**
   * 保留目标集合中所有指定集合元素
   *
   * @param c
   * @return
   */
  @Override
  public boolean retainAll(java.util.Collection<?> c) {
    Objects.requireNonNull(c);
    // 与removeAll 互为镜像操作
    return batchRemove(c, true);
  }

  /**
   * 根据指定比较器排序目标集合
   *
   * @param comparator
   */
  @Override
  public void sort(Comparator<? super E> comparator) {
    final int expectedModcount = this.modcount;
    Objects.requireNonNull(comparator);
    // 使用arrays 工具类排序 todo
    Arrays.sort((E[]) this.elementData, 0, size, comparator);
    // 如果在排序完成后元数组被操作则需要抛异常
    if (this.modcount != expectedModcount) {
      throw new ConcurrentModificationException();
    }
  }

  private E elementData(int index) {
    return (E) elementData[index];
  }

  private void rangeCheck(int index) {
    if (index > this.size) {
      throw new IndexOutOfBoundsException();
    }
  }

  private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    // 指定最小容器大小大于当前元数组大小则需要扩容
    if (minCapacity - elementData.length > 0) {
      grow(minCapacity);
    }
  }

  private void grow(int minCapacity) {
    // 扩容规则  元数组大小右移一位并累加原大小
    int olderLength = elementData.length;
    int newLength = olderLength + olderLength >> 1;
    if (newLength - minCapacity < 0) {
      newLength = minCapacity;
    }
    // 判断新的容器大小是否超出list_max_length
    if (newLength > MAX_ARRAY_SIZE) {
      newLength = hugeCapacity(newLength);
    }
    // 对元数组进行扩容
    this.elementData = Arrays.copyOf(elementData, newLength);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) {
      throw new OutOfMemoryError();
    }
    return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
  }

  private class Itr implements Iterator<E> {
    int cursor; // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    int expectedModCount = modCount;

    Itr() {}

    @Override
    public boolean hasNext() {
      return cursor != size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E next() {
      checkForComodification();
      int i = cursor;
      if (i >= size) {
        throw new NoSuchElementException();
      }
      Object[] elementData = ArrayList.this.elementData;
      if (i >= elementData.length) {
        throw new ConcurrentModificationException();
      }
      cursor = i + 1;
      return (E) elementData[lastRet = i];
    }

    @Override
    public void remove() {
      if (lastRet < 0) {
        throw new IllegalStateException();
      }
      checkForComodification();

      try {
        ArrayList.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
        expectedModCount = modCount;
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void forEachRemaining(Consumer<? super E> consumer) {
      Objects.requireNonNull(consumer);
      final int size = ArrayList.this.size;
      int i = cursor;
      if (i >= size) {
        return;
      }
      final Object[] elementData = ArrayList.this.elementData;
      if (i >= elementData.length) {
        throw new ConcurrentModificationException();
      }
      while (i != size && modCount == expectedModCount) {
        consumer.accept((E) elementData[i++]);
      }
      // update once at end of iteration to reduce heap write traffic
      cursor = i;
      lastRet = i - 1;
      checkForComodification();
    }

    final void checkForComodification() {
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
      }
    }
  }

  @Override
  public String toString() {
    return "ArrayList{"
        + "elementData="
        + Arrays.toString(elementData)
        + ", size="
        + size
        + ", modcount="
        + modcount
        + '}';
  }
}
