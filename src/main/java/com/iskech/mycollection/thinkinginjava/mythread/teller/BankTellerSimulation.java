package com.iskech.mycollection.thinkinginjava.mythread.teller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/6 13:39
 * @description：银行工作站台
 * @modified By：
 * @version: V1.0
 */
public class BankTellerSimulation {
	private final static  int MAX_CUSTOMER=50;
	private final static  int MAX_ADJUST_TIME=1;
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(MAX_CUSTOMER);
		CustomerGenerator customerGenerator = new CustomerGenerator(customers);
		TellerManager tellerManager = new TellerManager(executorService, customers, MAX_ADJUST_TIME);
		executorService.execute(customerGenerator);
		executorService.execute(tellerManager);
	}
}
