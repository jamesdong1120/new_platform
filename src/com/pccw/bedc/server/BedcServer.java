package com.pccw.bedc.server;

import javax.servlet.http.HttpServlet;

import org.quickserver.net.AppException;
import org.quickserver.net.server.QuickServer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pccw.bedc.service.BankSignService;
import com.pccw.bedc.service.BedcService;

/*******************************************
 * <p>Title:银企的启动类 </p>
 * <p>Description:银企的启动类 </p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月15日 下午1:49:53
 ******************************************/
public class BedcServer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static BeanFactory factory = new ClassPathXmlApplicationContext(
			new String[] { "spring.xml", "spring-mybatis.xml" });
	private static QuickServer quickServer=new QuickServer("com.pccw.bedc.handler.BedcCommandHandler");
	
	/**
	 * 启动服务
	 */
	public static void startup(){
		BedcService bedcService=(BedcService) factory.getBean("bedcService");//socket 启动service
		BankSignService bankSignService=(BankSignService) factory.getBean("bankSignService");//银行签到service
		
		Integer port=bedcService.getConnectPort();
		quickServer.setPort(port);
		quickServer.setName("PCCW@BEDC VERSION 1.0");
		quickServer.setTimeout(600000);
		quickServer.setTimeoutMsg("连接超时,连接关闭");
		try {
			//socket服务开启
			quickServer.startServer();
			//银行签到
			bankSignService.sign();
			
			
		} catch (AppException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 关闭服务
	 */
	public static  void stop(){
		try {
			quickServer.stopServer();
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

}
