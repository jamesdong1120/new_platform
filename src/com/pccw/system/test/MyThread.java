package com.pccw.system.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pccw.bedc.service.HandlerCommandService;

public class MyThread extends Thread {
	private BeanFactory factory = new ClassPathXmlApplicationContext(
			new String[] { "spring.xml", "spring-mybatis.xml" });
	



@Override
public void run() {
	HandlerCommandService service = (HandlerCommandService) factory
			.getBean("handlerCommandService");
	Map<String,Object> map=new HashMap<String, Object>();
	map.put("bankCode","0111" );
	map.put("tranCode", "0124");
	map.put("TRANNAME", "test");
	//map.put("BEDC_QUERY_ACCNO","TEST_001");
	map.put("BEDC_TRAN_ACCNO", "TEST_002");
	map.put("BEDC_ACNTNO", "123");
	Map<String,String> resultMap=service.handlerCommand(map);
	}
}


