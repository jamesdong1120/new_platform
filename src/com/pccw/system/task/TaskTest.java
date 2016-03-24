package com.pccw.system.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
	public final Logger log = Logger.getLogger(this.getClass());
 
	public void run1() {
		for (int i = 0; i < 1; i++) {
			System.out.println(i+" run1.............每隔5秒执行一次........................." + (new Date()));
		}

	}

	public void run2() {
		for (int i = 0; i < 1; i++) {
			System.out.println(i+" run2...............每隔一分钟执行一次......................." + (new Date()));
		}
	}
	
	public void run3() {
		for (int i = 0; i < 1; i++) {
			System.out.println(i+" run3..............每隔三分钟执行一次........................" + (new Date()));
		}
	}
	
}
