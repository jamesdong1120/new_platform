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
import com.pccw.bedc.model.BciXmlFilesT;
import com.pccw.bedc.service.BciXmlFilesTService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciXmlFilesT")
public class BciXmlFilesTController {
	
	private BciXmlFilesTService bciXmlFilesTService;
	
	@Autowired
	public void setBciXmlFilesTService(BciXmlFilesTService bciXmlFilesTService) {
		this.bciXmlFilesTService = bciXmlFilesTService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciXmlFilesT.class,dateFormat);
		params.putAll(conditions);
		List<BciXmlFilesT> list=bciXmlFilesTService.getPageData(params);
		Integer allCount=bciXmlFilesTService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciXmlFilesT> list=JSON.parseArray(delstr, BciXmlFilesT.class);
		boolean flag=true;
		for(BciXmlFilesT po:list){
			try {
				bciXmlFilesTService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciXmlFilesT.class,dateFormat);
   			map.put("xmlFileId",GenKeyUtils.genDBKey("BCI_XML_FILES_T", "XML_FILE_ID"));
			bciXmlFilesTService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciXmlFilesT.class,dateFormat);
			bciXmlFilesTService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
   
	
	
	
}