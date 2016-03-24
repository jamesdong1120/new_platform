package com.pccw.system.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) res;  
        HttpSession session = request.getSession();  
        // 如果session不为空，则可以浏览其他页面  
        String url = request.getServletPath();  
        //这里判断目录，后缀名，当然也可以写在web.xml中，用url-pattern进行拦截映射  
        if (!"/system/loginControl/login.do".equals(url)) {  
            if (session.getAttribute("isValidate") == null) {  
                session.invalidate();  
                response.setContentType("text/html;charset=UTF-8"); 
                response.setHeader("sessionstatus", "timeout");
                PrintWriter out = response.getWriter();  
                out.println("timeout");  
            }  
        } else {  
            chain.doFilter(req, res);  
        }  
        
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
