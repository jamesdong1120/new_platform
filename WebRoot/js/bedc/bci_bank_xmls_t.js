$('#dg').datagrid({  
    toolbar:"#tb",
    fitColumns:false,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    url:cxt_path+'/bedc/bciBankXmlsT/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'bankXmlsId',title:'银行XML编号',width:100,checkbox:true},
    	 {field:'bankCode',title:'银行编号',width:100},
    	 {field:'tranCode',title:'指令编号',width:100},
    	 {field:'xmlClob',title:'报文',width:100},
    	 {field:'bankXmlType',title:'报文类型',width:100},
    	 {field:'fromBankXmlId',title:'来源报文ID',width:100},
    	 {field:'status',title:'状态',width:100},
    	 {field:'createBy',title:'创建人',width:100},
    	 {field:'createdDate',title:'创建日期',width:100},
    	 {field:'lastUpdatedBy',title:'更新者',width:100},
    	 {field:'lastUpdatedDate',title:'更新日期',width:100}
    ]]    
});

function load(){
   	var bankXmlsId=$('#bankXmlsId').val();
	$('#dg').datagrid({
		 url:cxt_path+'/bedc/bciBankXmlsT/getPageResult.do',
		 queryParams:{
   			bankXmlsId:bankXmlsId
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
	var url=cxt_path + '/bedc/bciBankXmlsT/add.do';
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
			$.post(cxt_path + '/bedc/bciBankXmlsT/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/bedc/bciBankXmlsT/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url);
		$('#controlForm').form('load',updateRecord);
	}else{
		if(checkedRecords.length>1){
			$.messager.alert('提示','您同时选择了多条数据做修改,请一条一条做修改.');   
		}else{
			$.messager.alert('提示','您未选择所要修改的数据,请选择后按修改按钮.');  
		}
		
	}
	
}
