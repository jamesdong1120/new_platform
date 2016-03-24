$('#bankTransTree').tree({    
    url:cxt_path+'/bedc/bciBank/getInitBankTreeJsonData.do',
    onBeforeExpand:function(node,param){
    	if(node.id==0){
    		$('#bankTransTree').tree('options').url=cxt_path+'/bedc/bciBankBodyCfg/getBankTreeJsonData.do';
    	}else{
    		$('#bankTransTree').tree('options').url=cxt_path+'/bedc/bciBankTrans/getBciBankTransTreeData.do?bankCode='+node.id;
    	}
    	
    },
    onClick: function(node){
    	
    },
    onContextMenu: function(e, node){
		e.preventDefault();
		// 查找节点
		$('#bankTransTree').tree('select', node.target);
		// 显示快捷菜单
		if(node.isLeaf=='1'){
			$('#bankTransTreeMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}
		
	
	}

});

$('#dg').datagrid({  
    toolbar:"#tb",
    fitColumns:true,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    url:cxt_path+'/bedc/bciBankBodyCfg/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'bankCode',title:'银行编号',width:80},
    	 {field:'bodyConfigDesc',title:'包体模板配置描述',width:200},
    	 {field:'configVersion',title:'配置版本号',width:80},
    	 {field:'tranCode',title:'交易编号',width:80},
    	 {field:'headConfigId',title:'头模板编号',width:100},
    	 {field:'status',title:'状态',width:50,formatter:statusFormatter},
    	 {field:'bodyConfigId',title:'操作',width:100,formatter:lookupFormat},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'lastUpdatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'lastUpdatedDate',title:'更新日期',width:100,hidden:true}
    	
    ]]    
});

$("#status").combobox({
	required: true,  
	textField:'text',
	valueField:'value',
	data:[{text:'启用',value:1},{text:'禁用',value:0}]
});

$("#searchBankCode").combogrid({
	panelWidth:400,
	fitColumns:true,
	fit:true,
	idField:'bankcode',
	textField:'bankcode',
	url:cxt_path+'/bedc/bciBank/getALLBankData.do',
	columns:[[
	         {field:'bankcode',title:'银行编号',width:100},
	         {field:'shortname',title:'银行简称',width:100}
	         ]]
});
function load(){
	var bankRecord=$('#searchBankCode').combogrid('grid').datagrid('getSelected');
	if(null!=bankRecord){
		bankCode=bankRecord.bankcode;
	}
	var tranCode=$('#searchTranCode').val();
   	$('#dg').datagrid({
		 url:cxt_path+'/bedc/bciBankBodyCfg/getPageResult.do',
		 queryParams:{
			 bankCode:bankCode,
			 tranCode:tranCode
		 }
	 });
}



/**
 * 重置方法
 */
function reset(){
	$("#search_form").form('reset');
}

/**
 *查询方法 
 */
function doSearch(){
	//alert($(id).val());
	load();
	
}


/**
 *增加方法 
 */
function addTransBodyConfig(){
	var title='增加';
	var url=cxt_path + '/bedc/bciBankBodyCfg/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,400,730);
	$('#controlForm').form('clear');
	var node=$('#bankTransTree').tree('getSelected');
	$("#displayBankCode").val(node.bankCode);
	$("#bankCode").val(node.bankCode);
	$("#displayTranCode").val(node.id);
	$("#tranCode").val(node.id);
	
	$("#headConfigId").combogrid({
		panelWidth:400,
		fitColumns:true,
		fit:true,
		idField:'headConfigId',
		textField:'headConfigId',
		url:cxt_path+'/bedc/bciBankHeadCfg/getBankTranHeadConfig.do?bankCode='+node.bankCode,
		columns:[[
		         {field:'headConfigId',title:'头模板编号',width:100},
		         {field:'headConfigDesc',title:'头模板配置描述',width:100},
		         {field:'configVersion',title:'配置版本号',width:100}
		         ]]
	});
	
}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#dg").datagrid('getChecked');
	
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/bedc/bciBankBodyCfg/delete.do',{delstr:delstr},function(data){
				var data = eval('(' + data + ')');  
				if(data.result){
					load();
					$.messager.alert('提示',data.successMsg);
				}else{
					$.messager.alert('提示',data.errorMsg);
				}  
			},'text');
	}else{
		$.messager.alert('提示','请选择您要删除的记录.');    
	
	}
	
	
}

/**
 *修改方法 
 */
function edit(){
	var checkedRecords=$("#dg").datagrid('getChecked');
	var updateRecord=$('#dg').datagrid('getSelected');
	if(checkedRecords.length==1&&null!=updateRecord){
		var title='修改';
		var url=cxt_path +'/bedc/bciBankBodyCfg/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url);
		$('#controlForm').form('load',updateRecord);
		$('#displayTranCode').val($('#tranCode').val());
		$('#displayBanKCode').val($('#bankCode').val());
	}else{
		if(checkedRecords.length>1){
			$.messager.alert('提示','您同时选择了多条数据做修改,请一条一条做修改.');   
		}else{
			$.messager.alert('提示','您未选择所要修改的数据,请选择后按修改按钮.');  
		}
		
	}
	
}

function lookupFormat(value,row,index){
	return '<a href="javascript:;" onclick="lookup('+value+')">查看报文</a>'
	
}


function lookup(value){
	var bodyConfigId=value;
	$.post(cxt_path + '/bedc/bciBankBodyCfg/selectByPrimaryKey.do',{bodyConfigId:bodyConfigId},function(data){
		$("#displayArea").val(data);
	},'text');
}

function makeOrPreviewTempConfig(){
	var title='模板制作预览'
	var url=cxt_path+'/bedc/bciXmlFileTemplateT/add.do'
	createDefineDialog('makeOrPreviewTempConfigDialog','makeOrPreviewTempConfigForm',title,url,430,750);
	var node=$('#bankTransTree').tree('getSelected');
	$("#displayBankCode1").val(node.bankCode);
	$("#bankCode1").val(node.bankCode);
	$("#displayTranCode1").val(node.id);
	$("#tranCode1").val(node.id);
	$('#preivewContent').val('');
	$("#headConfigId1").combogrid({
		panelWidth:400,
		required:true,
		fitColumns:true,
		fit:true,
		idField:'headConfigId',
		textField:'headConfigId',
		url:cxt_path+'/bedc/bciBankHeadCfg/getBankTranHeadConfig.do?bankCode='+node.bankCode,
		columns:[[
		         {field:'headConfigId',title:'头模板编号',width:80},
		         {field:'headConfigDesc',title:'头模板配置描述',width:140},
		         {field:'configVersion',title:'配置版本号',width:80}
		         ]]
	});
	
	$("#bodyConfigId1").combogrid({
		panelWidth:400,
		required:true,
		fitColumns:true,
		fit:true,
		idField:'bodyConfigId',
		textField:'bodyConfigId',
		url:cxt_path+'/bedc/bciBankBodyCfg/getBankTranBodyConfig.do?bankCode='+node.bankCode+'&tranCode='+node.id,
		columns:[[
		         {field:'bodyConfigId',title:'包体模板编号',width:80},
		         {field:'bodyConfigDesc',title:'包体模板配置描述',width:140},
		         {field:'configVersion',title:'配置版本号',width:80}
		         ]]
	});
}


function createDefineDialog(dialogId,formId,title,url,height,width){
	$('#'+dialogId).dialog({
		title : title,
		modal : true,
		height : height,
		width : width,
		buttons : [ {text:'预览',
					iconCls:'icon-preview',
					handler:function(){
						var flag=$('#'+formId).form('validate');
						if(!flag){
							$.messager.alert('提示','头模板编号或者包体模板编号为空'); 
						}else{
							var headConfigId=$('#headConfigId1').combogrid('grid').datagrid('getSelected').headConfigId;
							var bodyConfigId=$('#bodyConfigId1').combogrid('grid').datagrid('getSelected').bodyConfigId;
							$.post(cxt_path + '/bedc/bciXmlFileTemplateT/makeTemplate.do',{headConfigId:headConfigId,bodyConfigId:bodyConfigId},function(data){
							$('#preivewContent').val(data);
							
						},'text');
						}
					}
		},{
			text : '生成',
			iconCls : 'icon-make',
			handler : function() {
				$('#'+formId).form('submit', {
					url :url ,
					method : 'post', 
					onSubmit:function(param){
						var flag=$('#'+formId).form('validate');
						if(!flag){
							$.messager.alert('提示','头模板编号或者包体模板编号为空'); 
						}else{
						
						
						}
						return flag;
					},
					success:function(data){
						var data = eval('(' + data + ')');  
						if(data.result){
							$.messager.alert('提示',data.successMsg);
							$('#'+dialogId).dialog('close');
						}else{
							$.messager.alert('提示',data.errorMsg);
							//$('#controlDialog').dialog('close');
						}
					}
				});
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#'+dialogId).dialog('close');
			}
		} ]
	});
}