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
import com.pccw.bedc.model.BciBankXmlsT;
import com.pccw.bedc.service.BciBankXmlsTService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciBankXmlsT")
public class BciBankXmlsTController {
	
	private BciBankXmlsTService bciBankXmlsTService;
	
	@Autowired
	public void setBciBankXmlsTService(BciBankXmlsTService bciBankXmlsTService) {
		this.bciBankXmlsTService = bciBankXmlsTService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciBankXmlsT.class,dateFormat);
		params.putAll(conditions);
		List<BciBankXmlsT> list=bciBankXmlsTService.getPageData(params);
		Integer allCount=bciBankXmlsTService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciBankXmlsT> list=JSON.parseArray(delstr, BciBankXmlsT.class);
		boolean flag=true;
		for(BciBankXmlsT po:list){
			try {
				bciBankXmlsTService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciBankXmlsT.class,dateFormat);
			map.put("bankXmlsId",GenKeyUtils.genDBKey("BCI_BANK_XMLS_T", "BANK_XMLS_ID"));
   			bciBankXmlsTService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankXmlsT.class,dateFormat);
			bciBankXmlsTService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
   
	
	
	
}