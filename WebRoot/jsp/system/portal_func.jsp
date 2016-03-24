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
        <div data-options="region:'west',split:true" title="功能菜单树" style="width:300px;">
        	<ul id="funcTree"></ul>  
        </div>
        <div data-options="region:'center'"  title="功能信息" style="padding: 1px;overflow:hidden;">
					<table id="dg"></table>
	<div id="tb" style="display:none;height: 70px;">
		<div id="search_tb" style="margin-top: 5px;float: right;width: 100%;">
			<form id="search_form">
			<div style="width: 80%;float: left;line-height: 70px;">
				   				<label>菜单名称:</label> <input id="searchFuncName"  type="text" class="easyui-textbox"/> 
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
					<td><label>功能名称:</label></td> <td class="required"><input type="text" name="funcName" class="easyui-validatebox easyui-textbox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>功能地址:</label></td> <td><input type="text" name="funcUrl" class="easyui-textbox"/></td>
				</tr>
				
				<tr>
					<td><label>功能状态:</label></td> <td class="required"><input id="status" type="text" name="funcStatus" style="width: 150px;"/></td>
				</tr>
			</table>
			<!-- 功能隐藏域 -->
			<input type="hidden" name="parentId" id="parentId"/>
			<input type="hidden" name="funcId" id="funcId"/>
		</form>
	</div>
	
	 <div id="funcMenu" class="easyui-menu" style="width:120px;display: none;">
		<div onclick="refresh()" data-options="iconCls:'icon-reload'">刷新菜单目录</div>
		<div onclick="addMenuFunc()" data-options="iconCls:'icon-add'">增加菜单功能</div>
	</div>
	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/portal_func.js"></script>
</html>