package com.pccw.bedc.service;

import java.util.Map;

public interface HandlerCommandService {
	Map<String,String> handlerCommand(Map<String,Object> map);
	
	Map<String,Object> handlerBatchCommand(Map<String,Object> map);
}
