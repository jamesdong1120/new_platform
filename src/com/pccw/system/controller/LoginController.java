package com.pccw.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pccw.system.service.PortalUserService;
import com.pccw.system.utils.ResponseUtils;
import com.pccw.system.utils.SHAUtil;
@Controller
@RequestMapping("/system/loginControl")
public class LoginController {
	private PortalUserService portalUserService;
	
	@Autowired
	public void setPortalUserService(PortalUserService portalUserService) {
		this.portalUserService = portalUserService;
	}


	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		 HttpSession session = request.getSession(); 
		 Map<String,Object> map=new HashMap<String, Object>();
		 map.put("userLoginName", username);
		 map.put("userLoginPwd", SHAUtil.shaEncode(password));
		boolean flag=portalUserService.validateUser(map);
		if(flag){
			session.setAttribute("isValidate", true);
			return "redirect:/jsp/system/index.jsp";
		}else{
			session.setAttribute("isValidate", false);
			return null;
		}
	}

}
