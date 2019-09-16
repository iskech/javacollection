package com.iskech.mycollection.list.abst;





//import java.util.Iterator;

import com.iskech.mycollection.list.Collection;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * @author ：liujx
 * @date ：Created in 2019/8/30 10:48
 * @description：集合抽象类
 * @modified By：
 * @version: V1.0
 */
public abstract class AbstractCollection<E> implements Collection<E> {
  @Override
  public boolean add(E e) {
    // 该方法不允许直接调用
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean modified = false;
    //循环迭代调用add方法
    for (E e : c) {
      if (this.add(e)) {
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public void clear() {}

  @Override
  public boolean contains(Object obj) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> collection) {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean remove(Object obj) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> collection) {
    return false;
  }

  @Override
  public boolean removeIf(Predicate<? super E> predicate) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> collection) {
    return false;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public <T> T toArray(T[] ts) {
    return null;
  }

  @Override
  public abstract Iterator<E> iterator() ;
  

}
