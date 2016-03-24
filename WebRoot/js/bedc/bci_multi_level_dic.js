$('#dg').datagrid({  
    toolbar:"#tb",
    fitColumns:true,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    url:cxt_path+'/bedc/bciMultiLevelDic/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'dicId',title:'主键',width:100,checkbox:true},
    	 {field:'dicCode',title:'字典编码',width:100},
    	 {field:'dicValue',title:'字典值',width:100},
    	 {field:'parentDicId',title:'父节点编号',width:100},
    	 {field:'dicStatus',title:'字典状态',width:100,formatter:statusFormatter},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建时间',width:100,hidden:true},
    	 {field:'updatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'updatedDate',title:'更新时间',width:100,hidden:true}
    ]]    
});


$("#dicStatus").combobox({
			required : true,
			panelHeight:50,
			textField : 'text',
			valueField : 'value',
			editable:false,
			data : [{
						text : '启用',
						value : 1
					}, {
						text : '禁用',
						value : 0
					}]
		});

function load(){
	var status;
   	if($('#valid').is(':checked')==true){
   		status=1;
   	}
   	if($('#invalid').is(':checked')==true){
   		status=0;
   	}
   	var dicCode=$('#searchDicCode').val();
   	var dicValue=$('#searchDicValue').val();
	$('#dg').datagrid({
		 url:cxt_path+'/bedc/bciMultiLevelDic/getPageResult.do',
		 queryParams:{
   			dicStatus:status,
   			dicCode:dicCode,
   			dicValue:dicValue
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
	var url=cxt_path + '/bedc/bciMultiLevelDic/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,200,300);
	$('#controlForm').form('clear');
	var url=cxt_path+'/bedc/bciMultiLevelDic/getAllData.do';
	$('#parentDicId').combobox({
    valueField:'dicCode',    
    textField:'dicValue',
    url:url,
    mode:'remote',
    loadMsg:'数据加载中,请稍等...'
});
}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#dg").datagrid('getChecked');
	
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/bedc/bciMultiLevelDic/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/bedc/bciMultiLevelDic/update.do';
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
