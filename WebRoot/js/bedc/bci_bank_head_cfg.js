$('#dg').datagrid({  
    toolbar:"#tb",
    fitColumns:false,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    url:cxt_path+'/bedc/bciBankHeadCfg/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[
   		 {field:'bankCode',title:'银行编号',width:100},
    	 {field:'headConfigDesc',title:'头模板配置描述',width:200},
    	 {field:'headConfigContent',title:'模板配置文本',width:100,hidden:true},
    	 {field:'status',title:'状态',width:50,formatter:statusFormatter},
    	 {field:'configVersion',title:'配置版本号',width:100},
    	 {field:'headConfigId',title:'操作',width:100,formatter:lookupFormat},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建时间',width:100,hidden:true},
    	 {field:'lastUpdatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'lastUpdatedDate',title:'更新日期',width:100,hidden:true}
    ]]    
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

$("#status").combobox({
	required: true,
	value:1,
	textField:'text',
	valueField:'value',
	data:[{text:'禁用',value:0},{text:'启用',value:1}],
	
});

$('#bankCode').combogrid({
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
	var bankCode='';
	var bankRecord=$('#searchBankCode').combogrid('grid').datagrid('getSelected');
	if(null!=bankRecord){
		bankCode=bankRecord.bankcode;
	}
	$('#dg').datagrid({
		 url:cxt_path+'/bedc/bciBankHeadCfg/getPageResult.do',
		 queryParams:{
			 bankCode:bankCode
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
function add(){
	var title='增加';
	var url=cxt_path + '/bedc/bciBankHeadCfg/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url);
	$('#controlForm').form('clear');
}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#dg").datagrid('getChecked');
	
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/bedc/bciBankHeadCfg/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/bedc/bciBankHeadCfg/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url);
		$('#controlForm').form('load',updateRecord);
		var headConfigId=$("#headConfigId").val();
		$.post(cxt_path + '/bedc/bciBankHeadCfg/selectByPrimaryKey.do',{headConfigId:headConfigId},function(data){
			$("#headConfigContent").val(data);
		},'text');
		
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
	var headConfigId=value;
	$.post(cxt_path + '/bedc/bciBankHeadCfg/selectByPrimaryKey.do',{headConfigId:headConfigId},function(data){
		$("#displayArea").val(data);
	},'text');
}
