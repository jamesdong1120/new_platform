var orgId;
$('#orgTreeCombox').combotree({  
    url : cxt_path + '/system/portalOrg/getEffectOrgTreeJsonData.do',
    cascadeCheck:false,
	onBeforeSelect:function(node){
		if(0==node.id){
			return false;
		}
	},
	onSelect:function(node){
		orgId=node.id;
		$('#deptTreeCombox').combotree('tree').tree('options').url= cxt_path + '/system/portalDept/getEffectDeptTreeJsonData.do?orgId='+orgId
		$('#deptTreeCombox').combotree('clear');
		$('#deptTreeCombox').combotree('tree').tree('reload');
	}
}); 
$('#deptTreeCombox').combotree({
    cascadeCheck:false,
	onBeforeSelect:function(node){
		if(node!=null&&0==node.id){
			return false;
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
    url:cxt_path+'/system/portalUser/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'userId',title:'用户编号',width:100,checkbox:true},
    	 {field:'userCnName',title:'用户中文名',width:100},
    	 {field:'userLoginName',title:'用户登录名',width:100},
    	 {field:'userLoginPwd',title:'用户登录密码',width:100},
    	 {field:'userJobTitle',title:'用户职称',width:100},
    	 {field:'userStatus',title:'用户状态',width:100,formatter:statusFormatter},
    	 {field:'remark',title:'备注',width:100},
    	 {field:'deptName',title:'部门名称',width:100},
    	 {field:'orgShortName',title:'组织名称',width:100},
    	 {field:'deptId',title:'部门',width:100,hidden:true},
    	 {field:'orgId',title:'组织',width:100,hidden:true},
    	 {field:'createdBy',title:'用户创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'updatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'updatedDate',title:'更新日期',width:100,hidden:true}
    ]]    
});
$("#userStatus").combobox({
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
   	var userCnName=$('#searchUserCnName').val();
   	var status;
   	if($('#valid').is(':checked')==true){
   		status=1;
   	}
   	if($('#invalid').is(':checked')==true){
   		status=0;
   	}
	$('#dg').datagrid({
		 url:cxt_path+'/system/portalUser/getPageResult.do',
		 queryParams:{
		 	userCnName:userCnName,
   			userStatus:status
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
	var url=cxt_path + '/system/portalUser/add.do';
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
			$.post(cxt_path + '/system/portalUser/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/system/portalUser/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url,300,300);
		$('#controlForm').form('load',updateRecord);
		$('#orgTreeCombox').combotree('setValue',updateRecord.orgId);
		$('#deptTreeCombox').combotree('tree').tree('options').url= cxt_path + '/system/portalDept/getDeptTreeJsonData.do?orgId='+updateRecord.orgId
		$('#deptTreeCombox').combotree('clear');
		$('#deptTreeCombox').combotree('tree').tree('reload');
		$('#deptTreeCombox').combotree('setValue',updateRecord.deptId);
	}else{
		if(checkedRecords.length>1){
			$.messager.alert('提示','您同时选择了多条数据做修改,请一条一条做修改.');   
		}else{
			$.messager.alert('提示','您未选择所要修改的数据,请选择后按修改按钮.');  
		}
		
	}
	
}
