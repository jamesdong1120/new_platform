<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
		<div class="easyui-layout" data-options="fit : true,border : false">
          <div data-options="region:'east',split:true,collapsible:false" title="角色列表" style="width:400px;">
        	<table id="roleDg"></table>
        	<div id="roleDgtb" style="display:none;height: 70px;">
		<div style="width: 100%;text-align:center;line-height:70px;">
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="add()">增加角色</a>
			&nbsp; 
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del()">删除角色</a>
			&nbsp; 
		</div>
	</div>
        </div>
        <div data-options="region:'center'"  title="用户信息" style="padding: 1px;overflow:hidden;">
					<table id="dg"></table>
	<div id="tb" style="display:none;height: 70px;">
		<div id="search_tb" style="margin-top: 5px;float: right;width: 100%;">
			<form id="search_form">
			<div style="width: 70%;float: left;line-height: 70px;">
				   				<label>用户名称:</label> <input id="searchUserCnName"  type="text"  class="easyui-textbox" data-options="iconCls:'icon-search'"/>
				   				 &nbsp;&nbsp;
				   				<input id="valid" type="checkbox"  onClick="chooseOne(this);"/> <label>有效</label>
				   
				   				&nbsp;&nbsp;
				   				<input id="invalid" type="checkbox"  onClick="chooseOne(this);"/> <label>无效</label> 
			</div>
			<div style="text-align: right;width: 30%;float: right;">
				<div style="width: 100%;line-height:70px;">
					<a id="reset" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="reset()">重置</a>
					&nbsp; 
					<a id="search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">探索</a>
					&nbsp; 
				</div>
			</div>
			</form>
			
		</div>
	</div>
	
        </div>
    </div>
	
	<div id="controlDialog" class="hiddenClass">
		<form id="controlForm" method="post">
			
				<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;width: 100%">
				<!--行要怎么处理,自己加tr -->
				<tr style="height:25px;">	
					<td style="width: 50px;"><label>角色:</label></td> <td style="width: 220px;"><input type="text"  id="roleIds" /></td><td style="5px;" class="required"></td>
				</tr>
			</table>
				
			<!-- 功能隐藏域 -->
			<input type="hidden" name="userId" id="userId"/>
			<input type="hidden" name="roleIds" id="checkedRoles">
		</form>
	</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/portal_role_user.js"></script>
</html>