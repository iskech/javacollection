package com.iskech.mycollection.list2.abst;

import com.iskech.mycollection.list2.CustomCollection;
import com.iskech.mycollection.list2.CustomList;

import java.util.*;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/8 18:21
 * @description：抽象list
 * @modified By：
 * @version: V1.0
 */
public abstract class CustomAbstractList<E> extends CustomAbstractCollcetion<E>
    implements CustomList<E> {
  protected transient int modCount = 0;
  /**
   * @param e 被添加的元素
   * @return
   */
  @Override
  public boolean add(E e) {
    this.add(size(), e);
    return true;
  }

  /**
   * @param index 被指定索引
   * @param collection 被插入集合
   * @return
   */
  @Override
  public boolean addAll(int index, CustomCollection<? extends E> collection) {
    rangCheckForAdd(index);
    boolean modify = false;
    for (E e : collection) {
      this.add(index++, e);
      modify = true;
    }
    return modify;
  }

  /** 清空目标集合 */
  @Override
  public void clear() {
    removeRange(0, this.size());
  }

  /**
   * @param obj 比对的目标元素
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    // 若obj与目标集合指向同一对象直接返回true
    if (this == obj) {
      return true;
    }
    // 若obj不属于list类型直接返回true
    if (!(obj instanceof CustomList)) {
      return false;
    }
    // 迭代并逐一比对两个集合的元素
    ListIterator<E> thisListIterator = this.listIterator();
    ListIterator<?> listIterator = ((CustomList<?>) obj).listIterator();
    if (thisListIterator.hasNext() && listIterator.hasNext()) {
      E thisNext = thisListIterator.next();
      Object next = listIterator.next();
      if (!(thisNext == null ? next == null : thisNext.equals(next))) {
        return false;
      }
    }
    // 若两个集合其中有认为迭代完则说明两个集合大小不一致返回false
    return !(thisListIterator.hasNext() || listIterator.hasNext());
  }
	
	@Override
	abstract public E get(int index);

  /**
   * hash 散列算法
   *
   * @return hash散列值
   */
  @Override
  public int hashCode() {
    int hashCode = 1;
    for (E e : this) {
      hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
    }
    return hashCode;
  }

  /**
   * list集合排序有索引，indexOf在list中为通用方法
   *
   * @param obj 指定元素
   * @return 目标集合指定元素索引
   */
  @Override
  public int indexOf(Object obj) {
    ListIterator<E> listIterator = this.listIterator();
    int indexOf = 0;
    while (listIterator.hasNext()) {
      if (obj == null ? listIterator.next() == null : listIterator.next().equals(obj)) {
        // 此时迭代器已经移动到next元素下一单位，所以应该获取到迭代器上一元素索引
        return listIterator.previousIndex();
      }
    }
    return -1;
  }

  @Override
  public Iterator<E> iterator() {
    return new CustomAbstractList.Itr();
  }

  /**
   * @param obj 指定元素
   * @return 返回指定元素在目标集合最后一次出现位置的索引
   */
  @Override
  public int lastIndexOf(Object obj) {
    // 获取迭代器并将迭代器移动到最后一个单位
    ListIterator<E> lastListIerator = listIterator(this.size());
    while (lastListIerator.hasPrevious()) {
      if (obj == null
          ? lastListIerator.previous() == null
          : lastListIerator.previous().equals(obj)) {
        return lastListIerator.nextIndex();
      }
    }
    return -1;
  }

  @Override
  public ListIterator<E> listIterator() {
    return this.listIterator(0);
  }

  @Override
  public ListIterator<E> listIterator(final int index) {
    rangeCheckForAdd(index);
    return new CustomAbstractList.ListItr(index);
  }

  @Override
  public E remove(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public E set(int index, E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public CustomList<E> subList(int fromIndex, int toIndex) {
    return (this instanceof RandomAccess
        ? new RandomAccessSubList<>(this, fromIndex, toIndex)
        : new CustomSubList<>(this, fromIndex, toIndex));
  }

  private void rangeCheckForAdd(int index) {
    if (index > this.size() || index < 0) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }

  /**
   * 移除目标集合指定索引段元素
   *
   * @param fromIndex 开始索引
   * @param toIndex 结束索引
   */
  protected void removeRange(int fromIndex, int toIndex) {
    ListIterator<E> it = listIterator(fromIndex);
    for (int i = 0, n = toIndex - fromIndex; i < n; i++) {
      // 每次迭代都移动一次迭代器指向
      it.next();
      it.remove();
    }
  }

  private void rangCheckForAdd(int index) {
    if (index < 0 || index > this.size()) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size();
  }

  private class ListItr extends Itr implements ListIterator<E> {
    ListItr(int index) {
      cursor = index;
    }

    @Override
    public boolean hasPrevious() {
      return cursor != 0;
    }

    @Override
    public E previous() {
      checkForComodification();
      try {
        int i = cursor - 1;
        E previous = get(i);
        lastRet = cursor = i;
        return previous;
      } catch (IndexOutOfBoundsException e) {
        checkForComodification();
        throw new NoSuchElementException();
      }
    }

    @Override
    public int nextIndex() {
      return cursor;
    }

    @Override
    public int previousIndex() {
      return cursor - 1;
    }

    @Override
    public void set(E e) {
      if (lastRet < 0) {
        throw new IllegalStateException();
      }
      checkForComodification();

      try {
        CustomAbstractList.this.set(lastRet, e);
        expectedModCount = modCount;
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    public void add(E e) {
      checkForComodification();

      try {
        int i = cursor;
        CustomAbstractList.this.add(i, e);
        lastRet = -1;
        cursor = i + 1;
        expectedModCount = modCount;
      } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
      }
    }
  }

  private class Itr implements Iterator<E> {
    /** Index of element to be returned by subsequent call to next. */
    int cursor = 0;

    /**
     * Index of element returned by most recent call to next or previous. Reset to -1 if this
     * element is deleted by a call to remove.
     */
    int lastRet = -1;

    /**
     * The modCount value that the iterator believes that the backing List should have. If this
     * expectation is violated, the iterator has detected concurrent modification.
     */
    int expectedModCount = modCount;

    @Override
    public boolean hasNext() {

      return cursor != size();
    }

    @Override
    public E next() {

      checkForComodification();
      try {
        int i = cursor;
        E next = get(i);
        lastRet = i;
        cursor = i + 1;
        return next;
      } catch (IndexOutOfBoundsException e) {
        checkForComodification();
        throw new NoSuchElementException();
      }
    }

    @Override
    public void remove() {
      if (lastRet < 0) {
        throw new IllegalStateException();
      }
      checkForComodification();

      try {
        CustomAbstractList.this.remove(lastRet);
        if (lastRet < cursor) {
          cursor--;
        }
        lastRet = -1;
        expectedModCount = modCount;
      } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

    final void checkForComodification() {
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
      }
    }
  }
}

class CustomSubList<E> extends CustomAbstractList<E> {
  private final CustomAbstractList<E> l;
  private final int offset;
  private int size;

  CustomSubList(CustomAbstractList<E> list, int fromIndex, int toIndex) {
    if (fromIndex < 0) {
      throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
    }
    if (toIndex > list.size()) {
      throw new IndexOutOfBoundsException("toIndex = " + toIndex);
    }
    if (fromIndex > toIndex) {
      throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
    }
    l = list;
    offset = fromIndex;
    size = toIndex - fromIndex;
    this.modCount = l.modCount;
  }

  @Override
  public E set(int index, E element) {
    rangeCheck(index);
    checkForComodification();
    return l.set(index + offset, element);
  }

  @Override
  public E get(int index) {
    rangeCheck(index);
    checkForComodification();
    return l.get(index + offset);
  }

  @Override
  public int size() {
    checkForComodification();
    return size;
  }

  @Override
  public void add(int index, E element) {
    rangeCheckForAdd(index);
    checkForComodification();
    l.add(index + offset, element);
    this.modCount = l.modCount;
    size++;
  }

  @Override
  public E remove(int index) {
    rangeCheck(index);
    checkForComodification();
    E result = l.remove(index + offset);
    this.modCount = l.modCount;
    size--;
    return result;
  }

  @Override
  protected void removeRange(int fromIndex, int toIndex) {
    checkForComodification();
    l.removeRange(fromIndex + offset, toIndex + offset);
    this.modCount = l.modCount;
    size -= (toIndex - fromIndex);
  }

  @Override
  public boolean addAll(CustomCollection<? extends E> c) {
    return addAll(size, c);
  }

  @Override
  public boolean addAll(int index, CustomCollection<? extends E> c) {
    rangeCheckForAdd(index);
    int cSize = c.size();
    if (cSize == 0) {

      return false;
    }

    checkForComodification();

    l.addAll(offset + index, c);
    this.modCount = l.modCount;
    size += cSize;
    return true;
  }

  @Override
  public Iterator<E> iterator() {
    return listIterator();
  }

  @Override
  public ListIterator<E> listIterator(final int index) {
    checkForComodification();
    rangeCheckForAdd(index);

    return new ListIterator<E>() {
      private final ListIterator<E> i = l.listIterator(index + offset);

      @Override
      public boolean hasNext() {
        return nextIndex() < size;
      }

      @Override
      public E next() {
        if (hasNext()) {

          return i.next();
        } else {
          throw new NoSuchElementException();
        }
      }

      @Override
      public boolean hasPrevious() {
        return previousIndex() >= 0;
      }

      @Override
      public E previous() {
        if (hasPrevious()) {
          return i.previous();
        } else {
          throw new NoSuchElementException();
        }
      }

      @Override
      public int nextIndex() {
        return i.nextIndex() - offset;
      }

      @Override
      public int previousIndex() {
        return i.previousIndex() - offset;
      }

      @Override
      public void remove() {
        i.remove();
        CustomSubList.this.modCount = l.modCount;
        size--;
      }

      @Override
      public void set(E e) {
        i.set(e);
      }

      @Override
      public void add(E e) {
        i.add(e);
        CustomSubList.this.modCount = l.modCount;
        size++;
      }
    };
  }

  @Override
  public CustomList<E> subList(int fromIndex, int toIndex) {
    return new CustomSubList<>(this, fromIndex, toIndex);
  }

  private void rangeCheck(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }

  private void rangeCheckForAdd(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  private void checkForComodification() {
    if (this.modCount != l.modCount) {
      throw new ConcurrentModificationException();
    }
  }
}

class RandomAccessSubList<E> extends CustomSubList<E> implements RandomAccess {
  RandomAccessSubList(CustomAbstractList<E> list, int fromIndex, int toIndex) {
    super(list, fromIndex, toIndex);
  }

  @Override
  public CustomList<E> subList(int fromIndex, int toIndex) {
    return new RandomAccessSubList<>(this, fromIndex, toIndex);
  }
}
