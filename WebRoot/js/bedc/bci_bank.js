$('#dg').datagrid({  
    toolbar:"#tb",
    fitColumns:false,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    url:cxt_path+'/bedc/bciBank/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'bankId',title:'主鍵',width:100,checkbox:true},
    	 {field:'bankcode',title:'银行代码',width:100},
    	 {field:'shortname',title:'银行简称',width:100},
    	 {field:'fullname',title:'银行全称',width:100},
    	 {field:'corpcode',title:'银行企业代码',width:100},
    	 {field:'bankacc',title:'银行账号',width:100},
    	 {field:'inneracc',title:'内部账号',width:100},
    	 {field:'branchid',title:'接入网点',width:100},
    	 {field:'areacode',title:'长途区位',width:100},
    	 {field:'techphone',title:'技术电话',width:100},
    	 {field:'bizphone',title:'业务电话',width:100},
    	 {field:'postcode',title:'邮政编码',width:100},
    	 {field:'address',title:'通信地址',width:100},
    	 {field:'signdate',title:'签约日期',width:100},
    	 {field:'overdate',title:'出约日期',width:100},
    	 {field:'coopstatus',title:'合作状况',width:100},
    	 {field:'signflag',title:'签名标志',width:100},
    	 {field:'createdate',title:'创建日期',width:100},
    	 {field:'createtime',title:'创建时间',width:100},
    	 {field:'updatedate',title:'更新日期',width:100},
    	 {field:'updatetime',title:'更新时间',width:100},
    	 {field:'createusr',title:'创建用户',width:100},
    	 {field:'updateusr',title:'更新用户',width:100},
    	 {field:'setmode',title:'控制归集入账方式',width:100},
    	 {field:'mgntype',title:'管理类型',width:100},
    	 {field:'hisbalmode',title:'历史余额查询方式',width:100},
    	 {field:'bankcustid',title:'财务公司在银行的客户号',width:100},
    	 {field:'pwd',title:'企业授权密码',width:100},
    	 {field:'bcisstatus',title:'工作流处理状态',width:100},
    	 {field:'banktxtoken',title:'银行交易标识',width:100},
    	 {field:'cnapstoken',title:'联行号必输标志',width:100},
    	 {field:'bankuserno',title:'银行授权用户号',width:100}
    ]]    
});

function load(){
   	var bankCode=$('#searchBankCode').val();
   	var shortname=$('#searchShortname').val();
	$('#dg').datagrid({
		 url:cxt_path+'/bedc/bciBank/getPageResult.do',
		 queryParams:{
   			bankcode:bankCode,
   			shortname:shortname
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
	var url=cxt_path + '/bedc/bciBank/add.do';
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
			$.post(cxt_path + '/bedc/bciBank/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/bedc/bciBank/update.do';
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
