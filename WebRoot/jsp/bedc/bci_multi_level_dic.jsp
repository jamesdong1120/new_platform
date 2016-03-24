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
		<table id="dg"></table>
	<div id="tb" style="display:none;height:70px;">
		<div id="search_tb" style="margin-top: 5px;float: right;width: 100%;">
			<form id="search_form">
			<div style="width: 80%;float: left;line-height: 70px;">
				   				<label>字典编码:</label> <input id="searchDicCode"  type="text" class="easyui-textbox"/> 
				   				 &nbsp;&nbsp;
				   				<label>字典值:</label> <input id="searchDicValue"  type="text" class="easyui-textbox"/> 
				   <input id="valid" type="checkbox"  onClick="chooseOne(this);"/> <label>有效</label>
				   &nbsp;&nbsp;
				   <input id="invalid" type="checkbox"  onClick="chooseOne(this);"/> <label>无效</label>
			</div>
				
			<div style="text-align: right;width: 20%;float: right;">
				<div style="width: 100%;">
					<a id="reset" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="reset()">重置</a>
					&nbsp; 
					<a id="search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">探索</a>
					&nbsp;
				</div>
				<div style="width: 100%;margin-top: 5px;">
					<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="add()">增加</a>  
					&nbsp; 
					<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del()">删除</a>
					&nbsp; 
					<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="edit()">修改</a>  
					&nbsp;
				</div> 
			</div>
			
			
			</form>
			
		</div>
		
		
	</div>
	
	<div id="controlDialog" class="hiddenClass">
		<form id="controlForm" method="post">
			<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;width: 100%;">
				<!--行要怎么处理,自己加tr -->
				<tr >
					<td><label>字典编码:</label></td> <td class="required"><input type="text" name="dicCode" class="easyui-textbox easyui-validatebox" /></td>
				</tr>
				<tr >	
					<td><label>字典值:</label></td> <td class="required"><input type="text" name="dicValue" class="easyui-textbox easyui-validatebox"/></td>
				</tr>
				<tr >
					<td><label>父节点编号:</label></td> <td><input name="parentDicId"  id="parentDicId" style="width: 155px;" /></td>
				</tr>
				<tr>
					<td><label>字典状态:</label></td> <td class="required"><input name="dicStatus" id="dicStatus" style="width: 155px;"/></td>
				</tr>
			</table>
				<input type="hidden" name="dicId" />
		</form>
	</div>
	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bedc/bci_multi_level_dic.js"></script>
</html>