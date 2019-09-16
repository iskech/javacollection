package com.iskech.mycollection.list;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public interface Iterable<T> {
  /**
   * 返回一个迭代器
   *
   * @return an Iterator.
   */
  Iterator<T> iterator();
  
  default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    /*for (T t : this) {
      action.accept(t);
    }*/
  }

  default Spliterator<T> spliterator() {
    return null;
  }
}
