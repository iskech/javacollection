package com.iskech.mycollection.thinkinginjava.mythread.teller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/6 10:55
 * @description：消费者生成线程类
 * @modified By：
 * @version: V1.0
 */
public class CustomerGenerator implements Runnable {
  private CustomerLine customers;
  private Random random = new Random(47);

  public CustomerLine getCustomers() {
    return customers;
  }

  public CustomerGenerator(CustomerLine customers) {
    this.customers = customers;
  }

  @Override
  public void run() {
    // 随机生成消费者并添加到队列
    try {
      while (!Thread.interrupted()) {
        TimeUnit.SECONDS.sleep(random.nextInt(3));
        Customer customer = new Customer(random.nextInt(10));
        System.out.println("添加客户:" + customer.toString());
        synchronized (this) {
          this.customers.add(customer);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("customerGenerator terminating");
  }
}
