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
import com.pccw.system.model.PortalDept;
import com.pccw.system.service.PortalDeptService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/system/portalDept")
public class PortalDeptController {
	
	private PortalDeptService portalDeptService;
	
	@Autowired
	public void setPortalDeptService(PortalDeptService portalDeptService) {
		this.portalDeptService = portalDeptService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, PortalDept.class,dateFormat);
		params.putAll(conditions);
		List<PortalDept> list=portalDeptService.getPageData(params);
		Integer allCount=portalDeptService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<PortalDept> list=JSON.parseArray(delstr, PortalDept.class);
		boolean flag=true;
		Map<String,Object> map=new HashMap<String, Object>();
		for(PortalDept po:list){
			try {
				portalDeptService.deleteByPrimaryKey(po);
				map.put("deptParent", po.getDeptParent());
				map.put("nodeSort", po.getDeptSort());
				map.put("orgId", po.getOrgId());
				//更新原目录下的节点排序
				portalDeptService.updateDeptSortMinusByRecursion(map);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalDept.class,dateFormat);
   			map.put("deptId",GenKeyUtils.genDBKey("PORTAL_DEPT", "DEPT_ID"));
   			Integer maxDeptSort=portalDeptService.getMaxDeptSort(map)+1;
   			map.put("deptSort", maxDeptSort);
			portalDeptService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,PortalDept.class,dateFormat);
			portalDeptService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	

	@RequestMapping("/getDeptTreeJsonData")
	public String getDeptTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalDept.class, dateFormat);
		String data=portalDeptService.getChildDeptTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	@RequestMapping("/getEffectDeptTreeJsonData")
	public String getEffectDeptTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalDept.class, dateFormat);
		String data=portalDeptService.getEffectChildDeptTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	
	@RequestMapping("/dropDept")
	public String dropDept(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		String targetParentId=request.getParameter("targetParentId");
		String targetNodeId=request.getParameter("targetNodeId");
		String nodeId=request.getParameter("nodeId");
		String parentNodeId=request.getParameter("parentNodeId");
		String operType=request.getParameter("operType");
		String targetNodeSort=request.getParameter("targetNodeSort");
		String nodeSort=request.getParameter("nodeSort");
		String orgId=request.getParameter("orgId");
		Map<String,Object> map=new HashMap<String, Object>();
		
		//放在targetNode之上
		if("top".equals(operType)||"bottom".equals(operType)){
			map.put("orgId", orgId);
			//同目录下位移
			if(targetParentId.equals(parentNodeId)){
				map.put("deptParent", parentNodeId);
				map.put("targetNodeSort", targetNodeSort);
				map.put("nodeSort", nodeSort);
				
				if(Integer.parseInt(nodeSort)>Integer.parseInt(targetNodeSort)){
					portalDeptService.updateDeptSortPlusByRecursion(map);
				}else{
					portalDeptService.updateDeptSortMinusByRecursion(map);
				}
				map.clear();
				map.put("deptParent", parentNodeId);
				map.put("deptSort", targetNodeSort);
				map.put("deptId", nodeId);
				portalDeptService.updateByPrimaryKeySelective(map);
			}
			//跨目录
			else{
				map.put("deptParent", parentNodeId);
				map.put("nodeSort", nodeSort);
				portalDeptService.updateDeptSortMinusByRecursion(map);
				
				map.clear();
				map.put("orgId", orgId);
				map.put("deptParent", targetParentId);
				map.put("targetNodeSort", targetNodeSort);
				portalDeptService.updateDeptSortPlusByRecursion(map);
				
				map.put("deptSort", targetNodeSort);
				map.put("deptId", nodeId);
				portalDeptService.updateByPrimaryKeySelective(map);
			}
			
			
		}
		//放在targetNode之内
		else if("append".equals(operType)){
			map.put("deptParent", parentNodeId);
			map.put("nodeSort", nodeSort);
			map.put("orgId", orgId);
			//更新原目录下的节点排序
			portalDeptService.updateDeptSortMinusByRecursion(map);
			
			map.put("deptParent", targetNodeId);
			Integer sort=portalDeptService.getMaxDeptSort(map)+1;;
			map.put("deptSort",sort);
			map.put("deptId", nodeId);
			portalDeptService.updateByPrimaryKeySelective(map);
			
		}
		return null;
	}
   
	
	
	
}