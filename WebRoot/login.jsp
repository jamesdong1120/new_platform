<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html 
xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta content="IE=11.0000" http-equiv="X-UA-Compatible">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
<meta name="GENERATOR" content="MSHTML 11.00.9600.17496">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/system/login.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/login.js"></script>
</head> 
<title>登录页面</title> 
 
<body>
<div class="top_div"></div>
<div class="login_body">
<div style="width: 165px; height: 96px; position: absolute;">
	<div class="tou"></div>
	<div class="initial_left_hand" id="left_hand"></div>
	<div class="initial_right_hand" id="right_hand"></div>
</div>
<form id="loginForm" method="post" action="<%=request.getContextPath()%>/system/loginControl/login.do">
<p style="padding: 30px 0px 10px; position: relative;">
	<span  class="u_logo"></span> <input class="ipt" id="username" type="text" name="username" placeholder="请输入用户名" value=""> 
 </p>
<p style="position: relative;">
	<span class="p_logo"></span><input class="ipt" id="password" type="password" name="password" placeholder="请输入密码" value="">   
</p>
<div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
<p style="margin: 0px 35px 20px 45px;">
	<span style="float: left;">
		<a style="color: rgb(204, 204, 204);" href="javascript:;">忘记密码?</a>
	</span> 
     <span style="float: right;">
              <a class="login_herf" href="javascript:;" onclick="login()">登录</a> 
           </span>        
 </p>
 </form>
 </div>
</div>

<div style="text-align:center;"></div>
</body>
</html>