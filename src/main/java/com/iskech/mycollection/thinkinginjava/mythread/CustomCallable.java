package com.iskech.mycollection.thinkinginjava.mythread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/5 16:27
 * @description：自定义callable
 * @modified By：
 * @version: V1.0
 */
public class CustomCallable implements Callable<String> {
  @Override
  public String call() throws Exception {
	  TimeUnit.SECONDS.sleep(5);
    return Thread.currentThread().getName();
  }
}
