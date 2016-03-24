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
				   				<label>银行代码:</label> <input id="searchBankCode"  type="text" class="easyui-textbox" /> 
				   				&nbsp;&nbsp;
				   				<label>银行简称:</label> <input id="searchShortname"  type="text" class="easyui-textbox"/> 
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
					<td><label>银行代码:</label></td> <td><input type="text" name="bankcode"/></td>
					<td><label>银行简称:</label></td> <td><input type="text" name="shortname"/></td>
					<td><label>银行全称:</label></td> <td><input type="text" name="fullname"/></td>
					<td><label>银行企业代码:</label></td> <td><input type="text" name="corpcode"/></td>
					<td><label>银行账号:</label></td> <td><input type="text" name="bankacc"/></td>
				<tr/>
				<tr>	
					<td><label>内部账号:</label></td> <td><input type="text" name="inneracc"/></td>
					<td><label>接入网点:</label></td> <td><input type="text" name="branchid"/></td>
					<td><label>长途区位:</label></td> <td><input type="text" name="areacode"/></td>
					<td><label>技术电话:</label></td> <td><input type="text" name="techphone"/></td>
					<td><label>业务电话:</label></td> <td><input type="text" name="bizphone"/></td>
				<tr/>
				<tr>		
					<td><label>邮政编码:</label></td> <td><input type="text" name="postcode"/></td>
					<td><label>通信地址:</label></td> <td><input type="text" name="address"/></td>
					<td><label>签约日期:</label></td> <td><input type="text" name="signdate"/></td>
					<td><label>出约日期:</label></td> <td><input type="text" name="overdate"/></td>
					<td><label>合作状况:</label></td> <td><input type="text" name="coopstatus"/></td>
				<tr/>
				<tr>	
					<td><label>签名标志:</label></td> <td><input type="text" name="signflag"/></td>
					<td><label>控制归集入账方式:</label></td> <td><input type="text" name="setmode"/></td>
					<td><label>管理类型:</label></td> <td><input type="text" name="mgntype"/></td>
					<td><label>历史余额查询方式:</label></td> <td><input type="text" name="hisbalmode"/></td>
					<td><label>财务公司在银行的客户号:</label></td> <td><input type="text" name="bankcustid"/></td>
				<tr/>
				<tr>		
					<td><label>企业授权密码:</label></td> <td><input type="text" name="pwd"/></td>
					<td><label>工作流处理状态:</label></td> <td><input type="text" name="bcisstatus"/></td>
					<td><label>银行交易标识:</label></td> <td><input type="text" name="banktxtoken"/></td>
					<td><label>联行号必输标志:</label></td> <td><input type="text" name="cnapstoken"/></td>
					<td><label>银行授权用户号:</label></td> <td><input type="text" name="bankuserno"/></td>
				<tr/>
				<tr>		
					
					<td><label style="display: none">主鍵:</label></td> <td><input type="hidden" name="bankId"/></td>
				</tr>
			</table>
		
		</form>
	</div>
	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bedc/bci_bank.js"></script>
</html>