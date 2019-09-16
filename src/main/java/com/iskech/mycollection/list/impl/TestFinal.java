package com.iskech.mycollection.list.impl;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/2 22:47
 * @description：测试final
 * @modified By：
 * @version: V1.0
 */
public class TestFinal {
  private String name;
  private int age;
  MyEnum myEnum;

  public static void main(String[] args) {
    final TestFinal testFinal = new TestFinal();
    testFinal.age = 1;

    new TestFinal().test();
  }

  private void test() {
    for (MyEnum o : MyEnum.values()) {
      System.out.println(o.ordinal());
      switch (o) {
        case ONE:
          System.out.println(o.getKey());
      }
    }
  }
}
