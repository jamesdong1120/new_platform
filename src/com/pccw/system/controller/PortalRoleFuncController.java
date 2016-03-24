package com.pccw.system.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.system.model.PortalRoleFunc;
import com.pccw.system.service.PortalRoleFuncService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/system/portalRoleFunc")
public class PortalRoleFuncController {
	
	private PortalRoleFuncService portalRoleFuncService;
	
	@Autowired
	public void setPortalRoleFuncService(PortalRoleFuncService portalRoleFuncService) {
		this.portalRoleFuncService = portalRoleFuncService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, PortalRoleFunc.class,dateFormat);
		params.putAll(conditions);
		List<PortalRoleFunc> list=portalRoleFuncService.getPageData(params);
		Integer allCount=portalRoleFuncService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<PortalRoleFunc> list=JSON.parseArray(delstr, PortalRoleFunc.class);
		boolean flag=true;
		for(PortalRoleFunc po:list){
			try {
				portalRoleFuncService.deleteByPrimaryKey(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalRoleFunc.class,dateFormat);
   			map.put("seqNo",GenKeyUtils.genDBKey("PORTAL_ROLE_FUNC", "SEQ_NO"));
			portalRoleFuncService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,PortalRoleFunc.class,dateFormat);
			portalRoleFuncService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	
	
	@RequestMapping("/getRoleFuncs")
	public String getRoleFuncs(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String roleId=request.getParameter("roleId");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<PortalRoleFunc> list=portalRoleFuncService.getRoleFuncs(map);
		List<Integer> funcIds=new ArrayList<Integer>();
		for(PortalRoleFunc func:list){
			funcIds.add(func.getFuncId().intValue());
		}
		String data=JSON.toJSONString(funcIds);
		ResponseUtils.respData(response, data);
		return null;
	}
	
   
	
	
	
}