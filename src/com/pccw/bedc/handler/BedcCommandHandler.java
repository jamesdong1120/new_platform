package com.pccw.bedc.handler;

import java.util.List;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.quickserver.net.server.ClientCommandHandler;
import org.quickserver.net.server.ClientHandler;

import com.alibaba.fastjson.JSON;
import com.pccw.bedc.model.BciXmlFileTemplateT;
import com.pccw.bedc.service.BciXmlFileTemplateTService;
import com.pccw.system.utils.SpringUtils;

public class BedcCommandHandler implements ClientCommandHandler {

	@Override
	public void closingConnection(ClientHandler handler) throws IOException {
		

	}

	@Override
	public void gotConnected(ClientHandler handlder) throws SocketTimeoutException,
			IOException {

	}

	@Override
	public void lostConnection(ClientHandler handler) throws IOException {

	}

	/****************************************
	 * 命令处理方法
	 * command 接收JSON字符串,
	 * 字符串中需要传入
	 * bankCode
	 * tranCode
	 * 多个交易参数,参数需要从bci_xml_params中获取
	 * 
	 ***************************************/
	@Override
	public void handleCommand(ClientHandler handler, String command)
			throws SocketTimeoutException, IOException {
		handler.setCharset("UTF-8");
		if(null!=command&&!"".equals(command)){
			Map<String,String> map=(Map<String, String>) JSON.parse(command);
			//获取银行交易模板
			
			
			
			
			Map<String,String> resultMap=new HashMap<String, String>();
			resultMap.put("errorMsg", "recive success");
			resultMap.put("errorCode", "0");
			
			handler.sendClientMsg(JSON.toJSONString(resultMap));
		}else{
			String errorMsg="no command!Please tell me what you want to do?";
			String errorCode="-1";
			Map<String,String> map=new HashMap<String, String>();
			map.put("errorMsg", errorMsg);
			map.put("errorCode", errorCode);
			handler.sendClientMsg(JSON.toJSONString(map));
		}
		

	}
	
	
	public String defaultProcess(){
		
		return null;
	} 
	
	private BciXmlFileTemplateT getTemplate(Map<String,String> map){
		BciXmlFileTemplateTService bciXmlFileTemplateTService=SpringUtils.getBean("bciXmlFileTemplateTService");
		BciXmlFileTemplateT bciXmlFileTemplateT=bciXmlFileTemplateTService.getBankTranTemple(map);
		 return bciXmlFileTemplateT;
	}
	
	private List<String> getTemplateNeedParams(String templateId){
		return null;
	}
}
