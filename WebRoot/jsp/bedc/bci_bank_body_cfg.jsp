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
        <div data-options="region:'west',split:true" title="银行交易树" style="width:180px;">
       		<ul id="bankTransTree"></ul>
        </div>
        <div data-options="region:'center'"  title="报文头模板信息" style="padding: 1px;overflow:hidden;">
					<table id="dg"></table>
		<div id="tb" style="display:none;">
		<div id="search_tb" style="margin-top: 5px;margin-bottom:5px;float: right;width: 100%;">
			<form id="search_form">
			<div style="width: 80%;float: left;">
				 <table>
					<tr>
						<td><label>银行代码:</label></td><td> <input id="searchBankCode"  type="text" /> </td>
						<td><label>交易代码:</label></td><td> <input id="searchTranCode"  type="text" /> </td>
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
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del()">删除</a>
			&nbsp; 
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="edit()">修改</a>  
			&nbsp;
		</div>
		
	</div>
           
        </div>
        
        
        <div data-options="region:'east',split:true" title="交易包体预览" style="width:300px;">
        	<textarea id="displayArea" cols="42" rows="25" style="overflow:auto;font-size: 12px;color:rgb(51,51,51);resize:none;border:0px;" readonly="readonly"></textarea>
        </div>
    </div>
	
	<div id="controlDialog">
		<form id="controlForm" method="post">
			<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;">
				<!--行要怎么处理,自己加tr -->
				<tr>
					<td><label>包体模板配置描述:</label></td> 
					<td><input type="text" name="bodyConfigDesc"/></td>
					<td><label>银行编号:</label></td> 
					<td><input id="displayBankCode" type="text" name="displayBankCode" disabled="disabled"/></td>
					<td><label>配置版本号:</label></td> 
					<td><input type="text" name="configVersion"/></td>
					
				<tr/>
				<tr>
					<td><label>交易编号:</label></td> 
					<td><input id="displayTranCode" type="text" name="displayTranCode" disabled="disabled"/></td>
					<td><label>头模板编号:</label></td> 
					<td><input type="text" name="headConfigId" id="headConfigId"/></td>
					<td><label>状态</label></td>
					<td><input id="status" type="text" name="status"/></td>
				<tr/>
				<tr>
					<td><label>模板配置文本:</label></td>  <td colspan="5"><textarea cols="85" rows="14" style="overflow:auto;font-size: 12px;color:rgb(51,51,51);" id="bodyConfigContent"   name="bodyConfigContent"></textarea></td>
				</tr>
				<tr>	
					<td><input id="bodyConfigId" type="hidden" name="bodyConfigId"/></td>
					<td><input id="bankCode" type="hidden" name="bankCode"/></td>
					<td><input id="tranCode" type="hidden" name="tranCode"/></td>
				</tr>
			</table>
		
		</form>
	</div>
	 <div id="bankTransTreeMenu" class="easyui-menu" style="width:120px;">
		<div onclick="addTransBodyConfig()" data-options="iconCls:'icon-add'">添加包体配置</div>
		<div onclick="makeOrPreviewTempConfig()" data-options="iconCls:'icon-preview'">模板制作或预览</div>
	</div>
	
		<div id="makeOrPreviewTempConfigDialog">
		<form id="makeOrPreviewTempConfigForm" method="post">
			<table style="font-size: 12px;color:rgb(51,51,51);text-align: left;">
				<!--行要怎么处理,自己加tr -->
				<tr>
					<td><label>银行编号:</label></td> 
					<td><input id="displayBankCode1" type="text" name="displayBankCode" disabled="disabled"/></td>
					<td><label>交易编号:</label></td> 
					<td><input id="displayTranCode1" type="text" name="displayTranCode" disabled="disabled"/></td>
					<td><label>版本号:</label></td> 
					<td><input  type="text" name="templateVersion" /></td>
				<tr/>
				<tr>
				<td><label>头模板编号:</label></td> 
					<td><input type="text" name="headConfigId" id="headConfigId1"/></td>
					<td><label>包体模板编号:</label></td> 
					<td><input type="text" name="bodyConfigId" id="bodyConfigId1"/></td>
				</tr>
				<tr>
					<td><label>模板配置文本预览:</label></td>  <td colspan="7"><textarea cols="85" rows="17" style="overflow:auto;font-size: 12px;color:rgb(51,51,51);" id="preivewContent"   name="templateClob"></textarea></td>
				</tr>
				<tr>	
					<td><input id="bankCode1" type="hidden" name="bankCode"/></td>
					<td><input id="tranCode1" type="hidden" name="tranCode"/></td>
				</tr>
			</table>
		
		</form>
	</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bedc/bci_bank_body_cfg.js"></script>
</html>