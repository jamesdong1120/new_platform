package com.pccw.bedc.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.bedc.model.BciBankBodyCfg;
import com.pccw.bedc.model.BciBankHeadCfg;
import com.pccw.bedc.model.BciXmlFileTemplateT;
import com.pccw.bedc.service.BciBankBodyCfgService;
import com.pccw.bedc.service.BciBankHeadCfgService;
import com.pccw.bedc.service.BciXmlFileTemplateTService;
import com.pccw.bedc.service.BciXmlParamsService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
import com.pccw.system.utils.TemplateUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/bedc/bciXmlFileTemplateT")
public class BciXmlFileTemplateTController {
	
	private BciXmlFileTemplateTService bciXmlFileTemplateTService;
	
	private BciBankBodyCfgService bciBankBodyCfgService;
	
	private BciBankHeadCfgService bciBankHeadCfgService;
	
private BciXmlParamsService bciXmlParamsService;
	
	@Autowired
	public void setBciXmlParamsService(BciXmlParamsService bciXmlParamsService) {
		this.bciXmlParamsService = bciXmlParamsService;
	}
	
	
	@Autowired
	public void setBciBankBodyCfgService(BciBankBodyCfgService bciBankBodyCfgService) {
		this.bciBankBodyCfgService = bciBankBodyCfgService;
	}
	@Autowired
	public void setBciBankHeadCfgService(BciBankHeadCfgService bciBankHeadCfgService) {
		this.bciBankHeadCfgService = bciBankHeadCfgService;
	}

	@Autowired
	public void setBciXmlFileTemplateTService(BciXmlFileTemplateTService bciXmlFileTemplateTService) {
		this.bciXmlFileTemplateTService = bciXmlFileTemplateTService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, BciXmlFileTemplateT.class,dateFormat);
		params.putAll(conditions);
		List<BciXmlFileTemplateT> list=bciXmlFileTemplateTService.getPageData(params);
		Integer allCount=bciXmlFileTemplateTService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<BciXmlFileTemplateT> list=JSON.parseArray(delstr, BciXmlFileTemplateT.class);
		boolean flag=true;
		for(BciXmlFileTemplateT po:list){
			try {
				bciXmlFileTemplateTService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciXmlFileTemplateT.class,dateFormat);
   			map.put("templateId",GenKeyUtils.genDBKey("BCI_XML_FILE_TEMPLATE_T", "TEMPLATE_ID"));
   			String templateClob=(String) map.get("templateClob");
   			String[] params=StringUtils.substringsBetween(templateClob, "${", "}");
   			for(String param:params){
   				Map<String,Object> paramMap=new HashMap<String, Object>();
   				paramMap.put("paramId",GenKeyUtils.genDBKey("BCI_XML_PARAMS", "PARAM_ID"));
   				paramMap.put("paramName", param);
   				paramMap.put("bankCode", map.get("bankCode"));
   				paramMap.put("tranCode", map.get("tranCode"));
   				paramMap.put("templateId", map.get("templateId"));
   				bciXmlParamsService.insert(paramMap);
   			}
			bciXmlFileTemplateTService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,BciXmlFileTemplateT.class,dateFormat);
			bciXmlFileTemplateTService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/makeTemplate")
	public String makeTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
			Map<String,Object> map=RequestUtils.getConditionByClass(request, BciXmlFileTemplateT.class,dateFormat);
			BciBankHeadCfg bciBankHeadCfg=bciBankHeadCfgService.selectByPrimaryKey(map);
			BciBankBodyCfg bciBankBodyCfg=bciBankBodyCfgService.selectByPrimaryKey(map);
			String data=bciBankHeadCfg.getHeadConfigContent().replace("${body}", bciBankBodyCfg.getBodyConfigContent());
			ResponseUtils.respData(response, data);
		return null;
	}
	
	
	
}