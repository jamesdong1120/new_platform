package com.pccw.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.system.model.PortalOrg;
import com.pccw.system.service.PortalOrgService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/system/portalOrg")
public class PortalOrgController {
	
	private PortalOrgService portalOrgService;
	
	@Autowired
	public void setPortalOrgService(PortalOrgService portalOrgService) {
		this.portalOrgService = portalOrgService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, PortalOrg.class,dateFormat);
		params.putAll(conditions);
		List<PortalOrg> list=portalOrgService.getPageData(params);
		Integer allCount=portalOrgService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<PortalOrg> list=JSON.parseArray(delstr, PortalOrg.class);
		boolean flag=true;
		Map<String,Object> map=new HashMap<String, Object>();
		for(PortalOrg po:list){
			try {
				portalOrgService.deleteByPrimaryKey(po);
				map.put("orgParent", po.getOrgParent());
				map.put("nodeSort", po.getOrgSort());
				//更新原目录下的节点排序
				portalOrgService.updateOrgSortMinusByRecursion(map);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalOrg.class,dateFormat);
   			map.put("orgId",GenKeyUtils.genDBKey("PORTAL_ORG", "ORG_ID"));
   			Integer maxOrgSort=portalOrgService.getMaxOrgSort(map)+1;
   			map.put("orgSort", maxOrgSort);
			portalOrgService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,PortalOrg.class,dateFormat);
			portalOrgService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	
	@RequestMapping("/getOrgTreeJsonData")
	public String getOrgTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalOrg.class, dateFormat);
		String data=portalOrgService.getChildOrgTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	@RequestMapping("/getEffectOrgTreeJsonData")
	public String getEffectOrgTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalOrg.class, dateFormat);
		String data=portalOrgService.getEffectChildOrgTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	
	@RequestMapping("/dropOrg")
	public String dropOrg(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		String targetParentId=request.getParameter("targetParentId");
		String targetNodeId=request.getParameter("targetNodeId");
		String nodeId=request.getParameter("nodeId");
		String parentNodeId=request.getParameter("parentNodeId");
		String operType=request.getParameter("operType");
		String targetNodeSort=request.getParameter("targetNodeSort");
		String nodeSort=request.getParameter("nodeSort");
		Map<String,Object> map=new HashMap<String, Object>();
		System.out.println(operType);
		//放在targetNode之上
		if("top".equals(operType)||"bottom".equals(operType)){
			
			//同目录下位移
			if(targetParentId.equals(parentNodeId)){
				map.put("orgParent", parentNodeId);
				map.put("targetNodeSort", targetNodeSort);
				map.put("nodeSort", nodeSort);
				if(Integer.parseInt(nodeSort)>Integer.parseInt(targetNodeSort)){
					portalOrgService.updateOrgSortPlusByRecursion(map);
				}else{
					portalOrgService.updateOrgSortMinusByRecursion(map);
				}
				map.clear();
				map.put("orgParent", parentNodeId);
				map.put("orgSort", targetNodeSort);
				map.put("orgId", nodeId);
				portalOrgService.updateByPrimaryKeySelective(map);
			}
			//跨目录
			else{
				map.put("orgParent", parentNodeId);
				map.put("nodeSort", nodeSort);
				portalOrgService.updateOrgSortMinusByRecursion(map);
				
				map.clear();
				map.put("orgParent", targetParentId);
				map.put("targetNodeSort", targetNodeSort);
				portalOrgService.updateOrgSortPlusByRecursion(map);
				
				map.put("orgSort", targetNodeSort);
				map.put("orgId", nodeId);
				portalOrgService.updateByPrimaryKeySelective(map);
			}
			
			
		}
		//放在targetNode之内
		else if("append".equals(operType)){
			map.put("orgParent", parentNodeId);
			map.put("nodeSort", nodeSort);
			//更新原目录下的节点排序
			portalOrgService.updateOrgSortMinusByRecursion(map);
			
			map.put("orgParent", targetNodeId);
			Integer sort=portalOrgService.getMaxOrgSort(map)+1;;
			map.put("orgSort",sort);
			map.put("orgId", nodeId);
			portalOrgService.updateByPrimaryKeySelective(map);
			
		}
		return null;
	}
	
	
   
	
	
	
}