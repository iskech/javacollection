package com.iskech.mycollection.list.impl;
/**
 * @author ：liujx
 * @date ：Created in 2019/9/5 15:04
 * @description：枚举实现接口
 * @modified By：
 * @version: V1.0
 */
public interface NextEnum {

  void test();

  enum Food implements NextEnum {
    HOTDOG,
    RICE,
    POTATO;

    @Override
    public void test() {
      System.out.println("food1");
    }
  }

  enum Food2 implements NextEnum {
    HOTDOG2,
    RICE2,
    POTATO2;

    @Override
    public void test() {
      System.out.println("food2");
    }

    public static void main(String[] args) {
    
    }
  }

  public enum Course {
    one(Food2.class),
    two(Food.class);
    public NextEnum[] values;

    Course(Class<? extends NextEnum> clazz) {
      this.values = clazz.getEnumConstants();
    }
    public NextEnum random(){
    	return null;
    }
    
  }
}
