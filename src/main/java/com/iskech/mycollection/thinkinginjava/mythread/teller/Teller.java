package com.iskech.mycollection.thinkinginjava.mythread.teller;

import java.util.concurrent.TimeUnit;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/6 11:05
 * @description：出纳员实体
 * @modified By：
 * @version: V1.0
 */
public class Teller implements Runnable, Comparable<Teller> {
  private final CustomerLine customers;
  private int servedCount = 0;
  private boolean isServingCustomer = true;
  private static int zero = 0;
  private final int id = zero++;

  public int getServedCount() {
    return this.servedCount;
  }

  public Teller(CustomerLine customers) {
    this.customers = customers;
  }

  @Override
  public void run() {
    // 服务消费者即顾客
    try {
      while (!Thread.interrupted()) {
        Customer poll = customers.take();
        int customTime = poll.getCustomTime();
        TimeUnit.SECONDS.sleep(customTime);
        synchronized (this) {
          this.servedCount++;
          System.out.println("出纳员：" + this.toString() + "服务客户" + "客户:" + poll.toString());
        }
        synchronized (this) {
          if (!this.isServingCustomer) {
            // 不提供服务状态则线程等待
            wait();
          }
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("teller terminating");
  }

  public synchronized void doSomethingElse() {
    this.isServingCustomer = false;
    this.servedCount = 0;
  }

  public synchronized void serveCustomLine() {
    assert !isServingCustomer : "already serving" + this;
    this.isServingCustomer = true;
    // 唤醒等待的线程
    notifyAll();
  }

  @Override
  public int compareTo(Teller o) {
    // 比较哪一个出纳员工作量更大
    int i = this.servedCount > o.servedCount ? 1 : (this.servedCount == o.servedCount ? 0 : -1);
    return i;
  }

  public Teller compareToReturn(Teller o, int target) {
    int i = this.compareTo(o);
    if (target == i) {
      return this;
    } else if (target < i) {
      return this;
    } else if (target > i) {
      return o;
    }
    return null;
  }

  @Override
  public String toString() {
    return "Teller{"
        + "customers="
        + customers.size()
        + ", servedCount="
        + servedCount
        + ", isServingCustomer="
        + isServingCustomer
        + ", id="
        + id
        + '}';
  }
}
