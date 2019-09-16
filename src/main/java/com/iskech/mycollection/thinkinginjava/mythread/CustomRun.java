package com.iskech.mycollection.thinkinginjava.mythread;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/5 15:47
 * @description：实现runable接口
 * @modified By：
 * @version: V1.0
 */
public class CustomRun implements Runnable {
  private int countNum = 10;

  public String state() {
    return this.countNum > 0 ? this.countNum + "" : "liftof!";
  }

  @Override
  public void run() {
    while (countNum >= 0) {
      System.out.println(Thread.currentThread().getName());
      String state = this.state();
      countNum--;
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("发射倒计时：" + state);
    }
  }

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + "goto main");
    CustomRun customRun = new CustomRun();
    CustomRun customRun2 = new CustomRun();
    // customRun.run();
    Thread thread = new Thread(customRun);
    thread.start();
    thread=new Thread(customRun2);
    thread.start();
    System.out.println(Thread.currentThread().getName() + "end main");
  }
}
