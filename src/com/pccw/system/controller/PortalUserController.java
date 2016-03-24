package com.pccw.system.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.system.model.PortalUser;
import com.pccw.system.service.PortalRoleUserService;
import com.pccw.system.service.PortalUserService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/system/portalUser")
public class PortalUserController {
	
	private PortalUserService portalUserService;
	private PortalRoleUserService portalRoleUserService;
	
	@Autowired
	public void setPortalUserService(PortalUserService portalUserService) {
		this.portalUserService = portalUserService;
	}
	@Autowired
	public void setPortalRoleUserService(PortalRoleUserService portalRoleUserService) {
		this.portalRoleUserService = portalRoleUserService;
	}

	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, PortalUser.class,dateFormat);
		params.putAll(conditions);
		List<PortalUser> list=portalUserService.getPageData(params);
		Integer allCount=portalUserService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<PortalUser> list=JSON.parseArray(delstr, PortalUser.class);
		boolean flag=true;
		for(PortalUser po:list){
			try {
				portalUserService.deleteByPrimaryKey(po);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("userId", po.getUserId());
				portalRoleUserService.removeUsers(map);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalUser.class,dateFormat);
   			map.put("userId",GenKeyUtils.genDBKey("PORTAL_USER", "USER_ID"));
			portalUserService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,PortalUser.class,dateFormat);
			portalUserService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
   
	
	
	
}