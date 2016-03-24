package com.pccw.bedc.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.bedc.model.BciBankBodyCfg;
import com.pccw.bedc.service.BciBankBodyCfgService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciBankBodyCfg")
public class BciBankBodyCfgController {
	
	private BciBankBodyCfgService bciBankBodyCfgService;
	
	@Autowired
	public void setBciBankBodyCfgService(BciBankBodyCfgService bciBankBodyCfgService) {
		this.bciBankBodyCfgService = bciBankBodyCfgService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciBankBodyCfg.class,dateFormat);
		params.putAll(conditions);
		List<BciBankBodyCfg> list=bciBankBodyCfgService.getPageData(params);
		Integer allCount=bciBankBodyCfgService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciBankBodyCfg> list=JSON.parseArray(delstr, BciBankBodyCfg.class);
		boolean flag=true;
		for(BciBankBodyCfg po:list){
			try {
				bciBankBodyCfgService.deleteByPrimaryKey(po);
			} catch (Exception e) {
				flag=false;
				e.printStackTrace();
			}
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		boolean flag=true;
		try {
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciBankBodyCfg.class,dateFormat);
   			map.put("bodyConfigId",GenKeyUtils.genDBKey("BCI_BANK_BODY_CFG", "BODY_CONFIG_ID"));
			bciBankBodyCfgService.insert(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		boolean flag=true;
		try {
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankBodyCfg.class,dateFormat);
			bciBankBodyCfgService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/getBankTreeJsonData")
	public String getBankTreeJsonData(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankBodyCfg.class,dateFormat);
		String data=bciBankBodyCfgService.getBankTreeJsonData();
		ResponseUtils.respData(response, data);
		return null;
		
	}
	
	@RequestMapping("/selectByPrimaryKey")
	public String selectByPrimaryKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankBodyCfg.class,dateFormat);
		BciBankBodyCfg data=bciBankBodyCfgService.selectByPrimaryKey(map);
		ResponseUtils.respData(response, data.getBodyConfigContent());
		return null;
		
	}
	
	@RequestMapping("/getBankTranBodyConfig")
	public String getBankTranBodyConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankBodyCfg.class,dateFormat);
		List<BciBankBodyCfg> list=bciBankBodyCfgService.getBankTranBodyConfig(map);
		String data=JSON.toJSONString(list);
		ResponseUtils.respData(response, data);
		return null;
		
	}
	
	
   
	
	
	
}