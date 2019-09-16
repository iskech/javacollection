package com.iskech.mycollection.thinkinginjava.mythread.teller;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author ：liujx
 * @date ：Created in 2019/9/6 10:51
 * @description：顾客实体容器
 * @modified By：
 * @version: V1.0
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {
	
	public CustomerLine(int capacity) {
		super(capacity);
	}
	
	@Override
	public String toString() {
		if (this.size() == 0) {
			return "CustomerLine{}";
		}
		else {
			StringBuffer stringBuffer = new StringBuffer();
			for (Customer customer : this) {
				stringBuffer.append(customer);
			}
			return stringBuffer.toString();
		}
	}
}
