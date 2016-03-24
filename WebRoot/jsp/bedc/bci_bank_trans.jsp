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
        <div data-options="region:'west',split:true" title="银行树" style="width:200px;">
        	<ul id="bankTree"></ul>  
        </div>
        <div data-options="region:'center'"  title="交易信息" style="padding: 1px;overflow:hidden;">
					<table id="dg"></table>
	<div id="tb" style="display:none;">
		<div id="search_tb" style="margin-top: 5px;margin-bottom:5px;float: right;width: 100%;">
			<form id="search_form">
			<div style="width: 80%;float: left;">
				<table>
					<tr>
				   		<td><label>交易代码:</label></td><td> <input id="searchTranCode"  type="text"  class="easyui-textbox"/> </td>
					</tr>
				</table>
				   				
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
			&nbsp; 
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del()">删除</a>
			&nbsp; 
			<!-- <a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="edit()">修改</a>  
			&nbsp; -->
		</div>
		
	</div>
           
        </div>
    </div>
    
    <div id="banTreeMenu" class="easyui-menu" style="width:120px;">
		<div onclick="refreshBank()" data-options="iconCls:'icon-reload'">刷新银行</div>
	</div>
	
	<div id="addTransMenu" class="easyui-menu" style="width:120px;">
		<div onclick="addTransConfig()" data-options="iconCls:'icon-add'">增加交易</div>
	</div>
	
	
	<div id="controlDialog" class="hiddenClass">
		<form id="controlForm" method="post">
			<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;">
				<!--行要怎么处理,自己加tr -->
				<tr>
					<td><label>交易编号:</label></td> <td class="required"><input type="text"  id="tranCode"/></td>
				<tr/>
			</table>
			<input id="tranId" type="hidden" name="tranId"/>
			<input id="bankCode" type="hidden" name="bankCode"/>
			<input id="tranCodes" type="hidden" name="tranCodes"/>
			<input id="tranNames" type="hidden" name="tranNames"/>
		</form>
	</div>
	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bedc/bci_bank_trans.js"></script>
</html>