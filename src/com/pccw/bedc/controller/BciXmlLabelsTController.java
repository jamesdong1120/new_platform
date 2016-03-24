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
import com.pccw.bedc.model.BciXmlLabelsT;
import com.pccw.bedc.service.BciXmlLabelsTService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciXmlLabelsT")
public class BciXmlLabelsTController {
	
	private BciXmlLabelsTService bciXmlLabelsTService;
	
	@Autowired
	public void setBciXmlLabelsTService(BciXmlLabelsTService bciXmlLabelsTService) {
		this.bciXmlLabelsTService = bciXmlLabelsTService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciXmlLabelsT.class,dateFormat);
		params.putAll(conditions);
		List<BciXmlLabelsT> list=bciXmlLabelsTService.getPageData(params);
		Integer allCount=bciXmlLabelsTService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciXmlLabelsT> list=JSON.parseArray(delstr, BciXmlLabelsT.class);
		boolean flag=true;
		for(BciXmlLabelsT po:list){
			try {
				bciXmlLabelsTService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciXmlLabelsT.class,dateFormat);
   			map.put("xmlLabelId",GenKeyUtils.genDBKey("BCI_XML_LABELS_T", "XML_LABEL_ID"));
			bciXmlLabelsTService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciXmlLabelsT.class,dateFormat);
			bciXmlLabelsTService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
   
	
	
	
}