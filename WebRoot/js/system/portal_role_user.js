$('#dg').datagrid({  
    toolbar:"#tb",
    singleSelect:true,
    fitColumns:true,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    url:cxt_path+'/system/portalUser/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    onCheck:function(rowIndex,rowData){
    	var userId=rowData.userId;
    	$('#userId').val(userId);
    	$('#roleDg').datagrid({
		 url:cxt_path+'/system/portalRole/getUserRoles.do',
		 queryParams:{
   			userId:userId
		 }
	 });
    },
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

$('#roleIds').combogrid({
	width:200,
	panelWidth:350,
	panelHeight:300,
	fit:true,
	multiple:true,
	idField:'roleId',
	textField:'roleName',
	fitColumns:true,
    pagination:true,
    fit:true,
    onCheck:function(rowIndex,rowData){
    	var record=$('#roleIds').combogrid('grid').datagrid('getChecked');
    		var rolesStr="";
    		for(var i=0;i<record.length;i++){
    		rolesStr+=record[i].roleId+",";
    		}
    		$('#checkedRoles').val(rolesStr);
    	
    },
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'roleId',title:'角色编号',width:100,checkbox:true},
    	 {field:'roleName',title:'角色名称',width:100},
    	 {field:'roleStatus',title:'角色状态',width:100,formatter:statusFormatter},
    	 {field:'roleDesc',title:'角色描述',width:100},
    	 {field:'remark',title:'备注',width:100},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'updatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'updatedDate',title:'更新日期',width:100,hidden:true}
    ]]    
})

$('#roleDg').datagrid({
	toolbar:'#roleDgtb',
    fitColumns:true,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'roleId',title:'角色编号',width:100,checkbox:true},
    	 {field:'roleName',title:'角色名称',width:100},
    	 {field:'roleStatus',title:'角色状态',width:100,formatter:statusFormatter},
    	 {field:'roleDesc',title:'角色描述',width:100},
    	 {field:'remark',title:'备注',width:100},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'updatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'updatedDate',title:'更新日期',width:100,hidden:true}
    ]]    
});

function loadUsers(){
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

function load(){
   var checkedRec=$('#dg').datagrid('getChecked');
   if(null!=checkedRec&&checkedRec.length>0){
   	var userId=checkedRec[0].userId;
	$('#roleDg').datagrid({
		 url:cxt_path+'/system/portalRole/getUserRoles.do',
		 queryParams:{
   			userId:userId
		 }});
   }
	
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
	
	loadUsers();
	
}


/**
 *增加方法 
 */
function add(){
	var checkedRec=$('#dg').datagrid('getChecked');
	if(null==checkedRec||checkedRec.length<=0){
		$.messager.alert('提示','请先选择用户');
	}else{
	var title='增加';
	var url=cxt_path + '/system/portalRoleUser/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,400,380);
	$('#controlForm').form('clear');
	var userId=checkedRec[0].userId;
	$('#userId').val(userId);
	$('#roleIds').combogrid({
		 url:cxt_path+'/system/portalRole/getUserEnableSelectedRoles.do',
		 queryParams:{
   			userId:userId
		 }
	 });
	
	}
	

}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#roleDg").datagrid('getChecked');
	var checkedRec=$('#dg').datagrid('getChecked');
	var userId=checkedRec[0].userId;
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/system/portalRoleUser/delete.do',{delstr:delstr,userId:userId},function(data){
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

