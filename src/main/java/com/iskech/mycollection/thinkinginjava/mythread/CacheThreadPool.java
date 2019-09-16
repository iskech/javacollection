package com.iskech.mycollection.thinkinginjava.mythread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/5 16:21
 * @description：excutor实现线程池
 * @modified By：
 * @version: V1.0
 */
public class CacheThreadPool {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // 使用executors 创建
    ExecutorService executorService = Executors.newCachedThreadPool();
    // 线程池创建之初就初始化线程
    ExecutorService executorService1 = Executors.newFixedThreadPool(20);
    for (int i = 0; i < 20; i++) {
      // executorService.execute(new CustomRun());
      Future<String> submit = executorService1.submit(new CustomCallable());
      System.out.println(submit.get());
    }
  }
}
