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
import com.pccw.bedc.model.BciBankHeadCfg;
import com.pccw.bedc.service.BciBankHeadCfgService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciBankHeadCfg")
public class BciBankHeadCfgController {
	
	private BciBankHeadCfgService bciBankHeadCfgService;
	
	@Autowired
	public void setBciBankHeadCfgService(BciBankHeadCfgService bciBankHeadCfgService) {
		this.bciBankHeadCfgService = bciBankHeadCfgService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciBankHeadCfg.class,dateFormat);
		params.putAll(conditions);
		List<BciBankHeadCfg> list=bciBankHeadCfgService.getPageData(params);
		Integer allCount=bciBankHeadCfgService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciBankHeadCfg> list=JSON.parseArray(delstr, BciBankHeadCfg.class);
		boolean flag=true;
		for(BciBankHeadCfg po:list){
			try {
				bciBankHeadCfgService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciBankHeadCfg.class,dateFormat);
   			map.put("headConfigId",GenKeyUtils.genDBKey("BCI_BANK_HEAD_CFG", "HEAD_CONFIG_ID"));
			bciBankHeadCfgService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankHeadCfg.class,dateFormat);
			bciBankHeadCfgService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	@RequestMapping("/selectByPrimaryKey")
	public String selectByPrimaryKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankHeadCfg.class,dateFormat);
		 BciBankHeadCfg bankHeadCfg= bciBankHeadCfgService.selectByPrimaryKey(map);
		 String data=bankHeadCfg.getHeadConfigContent();
		 ResponseUtils.respData(response, data);
		return null;
	}
	
	@RequestMapping("/getBankTranHeadConfig")
	public String getBankTranHeadConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankHeadCfg.class,dateFormat);
		List<BciBankHeadCfg> list= bciBankHeadCfgService.getBankTranHeadConfig(map);
		String data=JSON.toJSONString(list);
		ResponseUtils.respData(response, data);
		return null;
	}
   
	
	
	
}