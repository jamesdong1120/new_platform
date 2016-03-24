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
import com.pccw.bedc.model.BciBankTrans;
import com.pccw.bedc.service.BciBankTransService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciBankTrans")
public class BciBankTransController {
	
	private BciBankTransService bciBankTransService;
	
	@Autowired
	public void setBciBankTransService(BciBankTransService bciBankTransService) {
		this.bciBankTransService = bciBankTransService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciBankTrans.class,dateFormat);
		params.putAll(conditions);
		List<BciBankTrans> list=bciBankTransService.getPageData(params);
		Integer allCount=bciBankTransService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciBankTrans> list=JSON.parseArray(delstr, BciBankTrans.class);
		boolean flag=true;
		for(BciBankTrans po:list){
			try {
				bciBankTransService.deleteByPrimaryKey(po);
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
			String tranCodes=request.getParameter("tranCodes");
			String tranNames=request.getParameter("tranNames");
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciBankTrans.class,dateFormat);
   			
			String[] tcs=tranCodes.split(",");
			String[] tns=tranNames.split(",");
			for(int i=0;i<tcs.length;i++){
				map.put("tranId",GenKeyUtils.genDBKey("BCI_BANK_TRANS", "TRAN_ID"));
				map.put("tranCode", tcs[i]);
				map.put("tranDesc", tns[i]);
				bciBankTransService.insert(map);
			}
			
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankTrans.class,dateFormat);
			bciBankTransService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	
	@RequestMapping("/getBciBankTransTreeData")
	public String getBciBankTransTreeData(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
	
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciBankTrans.class,dateFormat);
		String data=bciBankTransService.getBciBankTransTreeData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	
	
}