package com.iskech.mycollection.list;

import com.iskech.mycollection.list.impl.ArrayList;

import java.util.Arrays;

/**
 * @author ：liujx
 * @date ：Created in 2019/8/30 9:23
 * @description：测试
 * @modified By：
 * @version: V1.0
 */
public class Test {

  public static void main(String[] args) {
    ArrayList <Integer> integers = new ArrayList <>();
    integers.add(1);
    System.out.println(integers.toString());
    integers.clear();
    System.out.println(integers);
    integers.add(2);
    integers.set(0,3);
    System.out.println(integers);
    Integer[] integers1 = {1,2};
    integers.addAll(Arrays.asList(integers1));
    System.out.println(integers);
  }
  
}
