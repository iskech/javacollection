package com.iskech.mycollection.thinkinginjava.mythread.teller;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/6 11:43
 * @description：出纳员管理类
 * @modified By：
 * @version: V1.0
 */
public class TellerManager implements Runnable {
  private ExecutorService executorService;
  private CustomerLine customers;
  private PriorityQueue<Teller> workingTellerQueue = new PriorityQueue<>();
  private Queue<Teller> doElseSomethingQueue = new LinkedList<>();
  private int adjustPeriod;

  public TellerManager(ExecutorService executorService, CustomerLine customers, int adjustPeriod) {
    this.executorService = executorService;
    this.customers = customers;
    this.adjustPeriod = adjustPeriod;
    Teller teller = new Teller(this.customers);
    executorService.execute(teller);
    this.workingTellerQueue.add(teller);
  }

  @Override
  public void run() {

    try {
      while (!Thread.interrupted()) {
        TimeUnit.SECONDS.sleep(this.adjustPeriod);
        this.adjustTellerNumber();
        for (Teller teller : workingTellerQueue) {
          System.out.println(teller.toString());
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("tellerManager terminating");
  }

  private void adjustTellerNumber() {
    // 根据出纳员与顾客的数量比调整出纳员工作，与休息
    if (this.workingTellerQueue.size() > 0
        && this.customers.size() / this.workingTellerQueue.size() > 2) {
      if (doElseSomethingQueue.size() > 0) {
        Teller remove = doElseSomethingQueue.remove();
        remove.serveCustomLine();
        this.workingTellerQueue.offer(remove);
        System.out.println(
            "调整后工作的出纳员:"
                + this.workingTellerQueue.toString()
                + "调整后休息的出纳员:"
                + this.doElseSomethingQueue.toString());
        return;
      } else {
        Teller teller = new Teller(this.customers);
        executorService.execute(teller);
        workingTellerQueue.add(teller);
        System.out.println(
            "调整后工作的出纳员:"
                + this.workingTellerQueue.toString()
                + "调整后休息的出纳员:"
                + this.doElseSomethingQueue.toString());
        return;
      }
    }
    if (this.workingTellerQueue.size() > 1
        && this.customers.size() / this.workingTellerQueue.size() <= 2) {
      reassignOneTeller();
    }
    if (this.customers.size() == 0) {
      reassignOneTeller();
    }

    System.out.println(
        "调整后工作的出纳员:"
            + this.workingTellerQueue.toString()
            + "调整后休息的出纳员:"
            + this.doElseSomethingQueue.toString());
  }

  private void reassignOneTeller() {
    if (this.workingTellerQueue.size() > 0) {
      // 安排一个出纳员调整
      Teller remove = this.workingTellerQueue.element();
      for (Teller teller : workingTellerQueue) {
        remove = teller.compareToReturn(remove, 1);
      }
      System.out.println(remove.getServedCount());
      remove.doSomethingElse();
      this.doElseSomethingQueue.offer(remove);
    }
  }
}
