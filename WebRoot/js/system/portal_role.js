
$('#roleFuncsTree').combotree({
	url:cxt_path+'/system/portalFunc/getAllEffectMenuTreeJsonData.do',
    checkbox:true,
    multiple:true,
    onCheck:function(node, checked){
    	var record=$('#roleFuncsTree').combotree('tree').tree('getChecked');
    	var recordStr="";
    	for(var i=0;i<record.length;i++){
    		recordStr+=record[i].id+',';
    	}
    	$('#roleFuncs').val(recordStr);
    	
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
    url:cxt_path+'/system/portalRole/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'roleId',title:'角色编号',width:100,checkbox:true},
    	 {field:'roleName',title:'角色名称',width:100},
    	 {field:'roleStatus',title:'角色状态',width:100,formatter:statusFormatter},
    	 {field:'roleDesc',title:'角色描述',width:100},
    	 {field:'remark',title:'备注',width:100},
    	 {field:' ',title:'操作',width:100,formatter:controlFormatter},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'updatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'updatedDate',title:'更新日期',width:100,hidden:true}
    ]]    
});

$("#roleStatus").combobox({
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
   	var roleName=$('#searchRoleName').val();
   	var status;
   	if($('#valid').is(':checked')==true){
   		status=1;
   	}
   	if($('#invalid').is(':checked')==true){
   		status=0;
   	}
	$('#dg').datagrid({
		 url:cxt_path+'/system/portalRole/getPageResult.do',
		 queryParams:{
		 	roleName:roleName,
   			roleStatus:status
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
	var url=cxt_path + '/system/portalRole/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,300,300);
	$('#controlForm').form('clear');
}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#dg").datagrid('getChecked');
	
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/system/portalRole/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/system/portalRole/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url,300,300);
		$('#controlForm').form('load',updateRecord);
		$.post(cxt_path + '/system/portalRoleFunc/getRoleFuncs.do',{roleId:updateRecord.roleId},function(data){
				var data = eval('(' + data + ')');  
				$('#roleFuncsTree').combotree('setValues',data);
			},'text');
		
	}else{
		if(checkedRecords.length>1){
			$.messager.alert('提示','您同时选择了多条数据做修改,请一条一条做修改.');   
		}else{
			$.messager.alert('提示','您未选择所要修改的数据,请选择后按修改按钮.');  
		}
		
	}
	
}

function controlFormatter(value,row,index){
	var roleId=row.roleId;
	return '<a href="javascript:;" onclick="viewRoleFuncs('+roleId+')">查看角色功能</a>'
}

function viewRoleFuncs(value){
	$('#roleEffectFuncTree').tree({
		url:cxt_path+'/system/portalFunc/getAllEffectRoleMenuTreeJsonData.do?roleId='+value
});
}