package com.pccw.system.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.system.model.PortalFunc;
import com.pccw.system.service.PortalFuncService;
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
@RequestMapping(value = "/system/portalFunc")
public class PortalFuncController {
	
	private PortalFuncService portalFuncService;
	
	private PortalRoleFuncService portalRoleFuncService;
	
	@Autowired
	public void setPortalFuncService(PortalFuncService portalFuncService) {
		this.portalFuncService = portalFuncService;
	}
	
	@Autowired
	public void setPortalRoleFuncService(PortalRoleFuncService portalRoleFuncService) {
		this.portalRoleFuncService = portalRoleFuncService;
	}



	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, PortalFunc.class,dateFormat);
		params.putAll(conditions);
		List<PortalFunc> list=portalFuncService.getPageData(params);
		Integer allCount=portalFuncService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<PortalFunc> list=JSON.parseArray(delstr, PortalFunc.class);
		boolean flag=true;
		Map<String,Object> map=new HashMap<String, Object>();
		for(PortalFunc po:list){
			try {
				portalFuncService.deleteByPrimaryKey(po);
				Map<String,Object> params=new HashMap<String, Object>();
				params.put("funcId", po.getFuncId());
				portalRoleFuncService.removeFuncs(params);
				map.put("parentId", po.getParentId());
				map.put("nodeSort", po.getFuncSort());
				//更新原目录下的节点排序
				portalFuncService.updateFuncSortMinusByRecursion(map);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalFunc.class,dateFormat);
   			map.put("funcId",GenKeyUtils.genDBKey("PORTAL_FUNC", "FUNC_ID"));
   			Integer maxFuncSort=portalFuncService.getMaxFuncSort(map)+1;
   			map.put("funcSort", maxFuncSort);
   			portalFuncService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,PortalFunc.class,dateFormat);
			portalFuncService.updateByPrimaryKey(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	
	@RequestMapping("/getMenuTreeJsonData")
	public String getMenuTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalFunc.class, dateFormat);
		String data=portalFuncService.getChildFuncTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	@RequestMapping("/getEffectMenuTreeJsonData")
	public String getEffectMenuTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalFunc.class, dateFormat);
		String data=portalFuncService.getEffectChildFuncTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	@RequestMapping("/getAllEffectMenuTreeJsonData")
	public String getAllEffectMenuTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalFunc.class, dateFormat);
		String data=portalFuncService.getAllEffectFuncTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	
	@RequestMapping("/getInitMenuTreeJsonData")
	public String getInitMenuTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("funcId", 0);
		String data=portalFuncService.getInitMenuTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
	
	@RequestMapping("/dropFunc")
	public String dropFunc(HttpServletRequest request,
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
				map.put("parentId", parentNodeId);
				map.put("targetNodeSort", targetNodeSort);
				map.put("nodeSort", nodeSort);
				if(Integer.parseInt(nodeSort)>Integer.parseInt(targetNodeSort)){
					portalFuncService.updateFuncSortPlusByRecursion(map);
				}else{
					portalFuncService.updateFuncSortMinusByRecursion(map);
				}
				map.clear();
				map.put("parentId", parentNodeId);
				map.put("funcSort", targetNodeSort);
				map.put("funcId", nodeId);
				portalFuncService.updateByPrimaryKeySelective(map);
			}
			//跨目录
			else{
				map.put("parentId", parentNodeId);
				map.put("nodeSort", nodeSort);
				portalFuncService.updateFuncSortMinusByRecursion(map);
				
				map.clear();
				map.put("parentId", targetParentId);
				map.put("targetNodeSort", targetNodeSort);
				portalFuncService.updateFuncSortPlusByRecursion(map);
				
				map.put("funcSort", targetNodeSort);
				map.put("funcId", nodeId);
				portalFuncService.updateByPrimaryKeySelective(map);
			}
			
			
		}
		//放在targetNode之内
		else if("append".equals(operType)){
			map.put("parentId", parentNodeId);
			map.put("nodeSort", nodeSort);
			//更新原目录下的节点排序
			portalFuncService.updateFuncSortMinusByRecursion(map);
			
			map.put("parentId", targetNodeId);
			Integer sort=portalFuncService.getMaxFuncSort(map)+1;;
			map.put("funcSort",sort);
			map.put("funcId", nodeId);
			portalFuncService.updateByPrimaryKeySelective(map);
			
		}
		return null;
	}
	
	//
	@RequestMapping("/getAllEffectRoleMenuTreeJsonData")
	public String getAllEffectRoleMenuTreeJsonData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		String roleId=request.getParameter("roleId");
		map.put("roleId", new BigDecimal(roleId));
		String data=portalFuncService.getAllEffectRoleMenuTreeJsonData(map);
		ResponseUtils.respData(response, data);
		return null;
	}
   
   
   
	
	
	
}