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
	<div id="tb" style="display:none;">
		<div id="search_tb" style="margin-top: 5px;margin-bottom:5px;float: right;width: 100%;">
			<form id="search_form">
			<div style="width: 80%;float: left;">
				   				<label>参数编号:</label> <input id="paramId"  type="text" /> 
			</div>
				
			<div style="text-align: right;width: 20%;float: right;">
				<a id="reset" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="reset()">重置</a>
				&nbsp; 
				<a id="search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">探索</a>
				&nbsp; 
			</div>
			
			
			</form>
			
		</div>
		<div id="control_tb" style="text-align: right;margin-top: 5px;margin-bottom:5px;width: 100%;">
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="add()">增加</a>  
			&nbsp; 
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del()">删除</a>
			&nbsp; 
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="edit()">修改</a>  
			&nbsp;
		</div>
		
	</div>
	
	<div id="controlDialog">
		<form id="controlForm" method="post">
			<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;">
				<!--行要怎么处理,自己加tr -->
				<tr>
					<td><label>参数编号:</label></td> <td><input type="text" name="paramId"/></td>
					<td><label>参数名称:</label></td> <td><input type="text" name="paramName"/></td>
					<td><label>参数类型:</label></td> <td><input type="text" name="paramType"/></td>
					<td><label>默认值:</label></td> <td><input type="text" name="defaultValue"/></td>
					<td><label>银行编号:</label></td> <td><input type="text" name="bankCode"/></td>
					<td><label>交易编号:</label></td> <td><input type="text" name="tranCode"/></td>
					<td><label>模板编号:</label></td> <td><input type="text" name="templateId"/></td>
					<td><label>创建者:</label></td> <td><input type="text" name="createdBy"/></td>
					<td><label>创建日期:</label></td> <td><input type="text" name="createdDate"/></td>
					<td><label>更新者:</label></td> <td><input type="text" name="lastUpdatedBy"/></td>
					<td><label>更新日期:</label></td> <td><input type="text" name="lastUpdatedDate"/></td>
				</tr>
			</table>
		
		</form>
	</div>
	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bedc/bci_xml_params.js"></script>
</html>