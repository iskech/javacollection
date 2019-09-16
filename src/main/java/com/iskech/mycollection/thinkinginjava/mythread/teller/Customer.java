package com.iskech.mycollection.thinkinginjava.mythread.teller;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/6 10:48
 * @description：顾客实体
 * @modified By：
 * @version: V1.0
 */
public class Customer {
  private static int count = 0;
  private final int id = count++;
  /** 消费的时间 */
  private final int customTime;

  public Customer(int customTime) {
    this.customTime = customTime;
  }

  public int getCustomTime() {
    return customTime;
  }
	
	@Override
	public String toString() {
		return "Customer{" + "id=" + id + ", customTime=" + customTime + '}';
	}
}
