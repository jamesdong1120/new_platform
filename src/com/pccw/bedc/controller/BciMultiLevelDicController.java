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
import com.pccw.bedc.model.BciMultiLevelDic;
import com.pccw.bedc.service.BciMultiLevelDicService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciMultiLevelDic")
public class BciMultiLevelDicController {
	
	private BciMultiLevelDicService bciMultiLevelDicService;
	
	@Autowired
	public void setBciMultiLevelDicService(BciMultiLevelDicService bciMultiLevelDicService) {
		this.bciMultiLevelDicService = bciMultiLevelDicService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciMultiLevelDic.class,dateFormat);
		params.putAll(conditions);
		List<BciMultiLevelDic> list=bciMultiLevelDicService.getPageData(params);
		Integer allCount=bciMultiLevelDicService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciMultiLevelDic> list=JSON.parseArray(delstr, BciMultiLevelDic.class);
		boolean flag=true;
		for(BciMultiLevelDic po:list){
			try {
				bciMultiLevelDicService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciMultiLevelDic.class,dateFormat);
   			map.put("dicId",GenKeyUtils.genDBKey("BCI_MULTI_LEVEL_DIC", "DIC_ID"));
			bciMultiLevelDicService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciMultiLevelDic.class,dateFormat);
			bciMultiLevelDicService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/getAllData")
	public String getAllData(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dicValue=request.getParameter("q");
		Map<String,Object> map=new HashMap<String, Object>();
		if(null!=dicValue&&!"".equals(dicValue)){
			dicValue=dicValue.trim();
		}
		map.put("dicValue", dicValue);
		List<BciMultiLevelDic> list=bciMultiLevelDicService.getAllData(map);
		String data=JSON.toJSONString(list);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	
	@RequestMapping("/getEffectTreeJsonData")
	public String getEffectTreeJsonData(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String parentDicId=request.getParameter("parentDicId");
		if(null!=parentDicId&&!"".equals(parentDicId)){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("parentDicId", parentDicId);
			String data=bciMultiLevelDicService.getEffectTreeJsonData(map);
			ResponseUtils.respData(response, data);
		}
		
		
		return null;
	}
	
	
}