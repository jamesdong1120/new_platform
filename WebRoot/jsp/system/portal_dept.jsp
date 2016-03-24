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
        <div  data-options="region:'west',split:true" title="部门树" style="width:300px;">
        		<div style="border-bottom: 5px solid #F5F5F5;height: 40px;line-height: 40px;">
        			<label>组织:&nbsp;&nbsp;</label><input id="orgTreeCombox" type="text"/>
       			 </div>
        		<div style="margin-top: 5px;">
        		<ul id="deptTree"></ul>  
        		</div>
        </div>
        <div data-options="region:'center'"  title="部门信息" style="padding: 1px;overflow:hidden;">
					<table id="dg"></table>
		
	<div id="tb" style="display:none; height: 70px;">
		<div id="search_tb" style="margin-top: 5px;float: right;width: 100%;">
			<form id="search_form">
			<div style="width: 80%;float: left;line-height: 70px;">
				   <label>部门名称:</label> <input id="searchDeptName"  type="text" class="easyui-textbox"/> 
				   &nbsp;&nbsp;
				   <label>所在组织:</label> <input id="searchOrgId"  type="text"/> 
				   &nbsp;&nbsp;
				   <input id="valid" type="checkbox"  onClick="chooseOne(this);"/> <label>有效</label>
				   &nbsp;&nbsp;
				   <input id="invalid" type="checkbox"  onClick="chooseOne(this);"/> <label>无效</label>
				  
			</div>
				
			<div style="text-align: right;width: 20%;float: right;">
				<div style="width: 100%">
					<a id="reset" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="reset()">重置</a>
					&nbsp; 
					<a id="search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">探索</a>
					&nbsp; 
				</div>
				<div style="width: 100%;margin-top: 5px;">
					<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del()">删除</a>
					&nbsp; 
					<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="edit()">修改</a>  
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
			<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;">
				<!--行要怎么处理,自己加tr -->
				<tr>
					<td><label>部门名称:</label></td> <td class="required"><input type="text" name="deptName" class="easyui-validatebox easyui-textbox" data-options="required:true"/></td>
				</tr>
				
				<tr>
					<td><label>部门状态:</label></td> <td class="required"><input id="deptStatus" type="text" name="deptStatus" style="width: 150px;" /></td>
				</tr>
			</table>
			<!-- 功能隐藏域 -->
			<input type="hidden" name="deptParent" id="deptParent"/>
			<input type="hidden" name="deptId" id="deptId"/>
			<input type="hidden" name="orgId" id="orgId"/>
		</form>
	</div>
	
	 <div id="deptMenu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="refresh()" data-options="iconCls:'icon-reload'">刷新部门</div>
		<div onclick="addDept()" data-options="iconCls:'icon-add'">增加部门</div>
	</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/portal_dept.js"></script>
</html>