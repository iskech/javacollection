package com.iskech.mycollection;



import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ：liujx
 * @date ：Created in 2019/8/30 17:14
 * @description：测试类
 * @modified By：
 * @version: V1.0
 */
public class Test1 {
  public static void main(String[] args) {
	  ArrayList<Integer> integers2 = new ArrayList<>();
	  integers2.addAll(Arrays.asList(1,2,3));
	  //new ArrayList<Integer>(collections);
	  Integer[] integers=new Integer[0];
	  Integer[] integers1 = integers2.toArray(integers);
	  System.out.println(integers1.length);
  }
}
