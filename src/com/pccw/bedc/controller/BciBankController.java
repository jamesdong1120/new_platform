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
import com.pccw.bedc.model.BciBank;
import com.pccw.bedc.service.BciBankService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciBank")
public class BciBankController {
	
	private BciBankService bciBankService;
	
	@Autowired
	public void setBciBankService(BciBankService bciBankService) {
		this.bciBankService = bciBankService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciBank.class,dateFormat);
		params.putAll(conditions);
		List<BciBank> list=bciBankService.getPageData(params);
		Integer allCount=bciBankService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciBank> list=JSON.parseArray(delstr, BciBank.class);
		boolean flag=true;
		for(BciBank po:list){
			try {
				bciBankService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciBank.class,dateFormat);
   			map.put("bankId",GenKeyUtils.genDBKey("BCI_BANK", "BANK_ID"));
			bciBankService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBank.class,dateFormat);
			bciBankService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/getInitBankTreeJsonData")
	public String getInitBankTreeJsonData(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
			buffer.append("{\"id\":\""+0+"\",\"text\":\"银行\",\"state\":\"closed\",\"isLeaf\":\"0\"}");
		buffer.append("]");
		ResponseUtils.respData(response, buffer.toString());
		return null;
	}
	@RequestMapping("/getALLBankJsonTreeData")
	public String getALLBankJsonTreeData(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String data=bciBankService.getALLBankJsonTreeData();
		ResponseUtils.respData(response, data);
		return null;
	}
	@RequestMapping("/getALLBankData")
	public String getALLBankData(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String data=bciBankService.getALLBankData();
		ResponseUtils.respData(response, data);
		return null;
	}
	
	
	
}