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
				<label>任务名称:</label> <input id="jobName"  type="text" /> 
			
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
					<td><label>任务名称:</label></td> <td><input type="text" name="jobName" class="easyui-validatebox"  data-options="required:true"/></td>
					<td><label>任务组:</label></td> <td><input type="text" name="jobGroup" class="easyui-validatebox"  data-options="required:true"/></td>
				</tr>
				<tr>
					<td><label>任务状态:</label></td> <td><input type="text" name="jobStatus" class="easyui-validatebox"  data-options="required:true"/></td>
					<td><label>任务运行表达式:</label></td> <td><input type="text" name="jobCronExpression" class="easyui-validatebox"  data-options="required:true"/></td>
				</tr>
				<tr>	
					<td><label>任务描述:</label></td> <td><input type="text" name="jobDescription"/></td>
					<td><label>调用的方法名称:</label></td> <td><input type="text" name="methodName" class="easyui-validatebox"  data-options="required:true"/></td>
				</tr>
				<tr>	
					<td><label>是否同时触发:</label></td> <td><input type="text" name="isConcurrent" class="easyui-validatebox"  data-options="required:true"/></td>
					<td><label>Spring编号:</label></td> <td><input type="text" name="springId" /></td>
				</tr>
				<tr>
					<td><label>任务类:</label></td> <td colspan="3"><input class="easyui-validatebox" name="beanClass"  data-options="required:true" style="width: 300px" /></td>
				</tr>
				<tr>
					<td><label style="display: none;">任务编号:</label></td> <td colspan="3"><input type="hidden" name="jobId" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="updateCronDialog">
		<form id="updateCronForm" method="post">
			<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;">
				<!--行要怎么处理,自己加tr -->
				<tr>
					<td><label>任务运行表达式:</label></td> <td><input id="jobCronExpression" type="text" name="jobCronExpression" class="easyui-validatebox"  data-options="required:true"/></td>
					<td><label style="display: none;">任务编号:</label></td> <td><input type="hidden" name="jobId" /></td>
					<td><label style="display: none;">任务编号:</label></td> <td><input type="hidden" name="jobName" /></td>
					<td><label style="display: none;">任务编号:</label></td> <td><input type="hidden" name="jobGroup" /></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/system/task_schedule_job.js"></script>
</html>