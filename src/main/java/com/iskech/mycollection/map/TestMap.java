package com.iskech.mycollection.map;

import java.util.Map;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/10 9:48
 * @description：map测试类
 * @modified By：
 * @version: V1.0
 */
public class TestMap {
  public static void main(String[] args) {
    MyHashMap<Integer, Integer> map = new MyHashMap<>(2);
    map.put(1, 1);
    map.put(2, 2);
    map.put(3, 3);
    map.put(4, 4);
    map.put(5, 5);
    map.put(5, 6);
    map.remove(5);
    map.remove(1);
    map.remove(2);
    map.remove(4);
    System.out.println(map.toString() + map.size + map.modCount);
    Integer integer = map.get(5);
    System.out.println(integer);
    for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
      System.out.println(integerIntegerEntry.toString());
    }
  }
}
