$('#bankTree').tree({    
    url:cxt_path+'/bedc/bciBank/getInitBankTreeJsonData.do',
    onBeforeExpand:function(node,param){
    	if(node.id=='0'){
    		$('#bankTree').tree('options').url=cxt_path+'/bedc/bciBank/getALLBankJsonTreeData.do';
    	}
    	
    },
    onClick: function(node){
    	$("#bankCode").val(node.id);
    	reset();
    	load();
    	
    },
    onContextMenu: function(e, node){
		e.preventDefault();
		// 查找节点
		$('#bankTree').tree('select', node.target);
		//alert(node.id);
		// 显示快捷菜单
		if(node.id==0){
			$('#banTreeMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}else{
			$('#addTransMenu').menu('show', {
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
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'tranId',title:'主键',width:100,checkbox:true},
    	 {field:'tranCode',title:'交易编号',width:100},
    	 {field:'bankCode',title:'银行编号',width:100},
    	 {field:'tranDesc',title:'交易描述',width:100},
    	 {field:'status',title:'状态',width:100,hidden:true},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'lastUpdatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'lastUpdatedDate',title:'更新日期',width:100,hidden:true}
    ]]    
});


function load(){
   	var tranCode=$('#searchTranCode').val();
   	var bankCode=$('#bankCode').val();
	$('#dg').datagrid({
		 url:cxt_path+'/bedc/bciBankTrans/getPageResult.do',
		 queryParams:{
			 tranCode:tranCode,
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
function addTransConfig(){
	var title='增加';
	var url=cxt_path + '/bedc/bciBankTrans/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,250,230);
	$('#controlForm').form('clear');
	var node=$('#bankTree').tree('getSelected');
	$('#tranCode').combotree({
	panelWidth:300,
	required:true,
	url:cxt_path+'/bedc/bciMultiLevelDic/getEffectTreeJsonData.do?parentDicId=-9999',
    checkbox:true,
    multiple:true,
    onCheck:function(node, checked){
    	var record=$('#tranCode').combotree('tree').tree('getChecked');
    	var codeStr="";
    	var nameStr="";
    	for(var i=0;i<record.length;i++){
    		if(record[i].id!=0000){
    			codeStr+=record[i].id+',';
    			nameStr+=record[i].text+',';
    		}
    		
    	}
    	$('#tranCodes').val(codeStr);
    	$('#tranNames').val(nameStr);
    }
});
	
	$('#bankCode').val(node.id);
}

function refreshBank(){
	var node=$('#bankTree').tree('getRoot');
	$('#bankTree').tree('reload',node.target);
}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#dg").datagrid('getChecked');
	
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/bedc/bciBankTrans/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/bedc/bciBankTrans/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url);
		$('#controlForm').form('load',updateRecord);
		$('#displayBankCode').val($('#postBankCode').val());
	}else{
		if(checkedRecords.length>1){
			$.messager.alert('提示','您同时选择了多条数据做修改,请一条一条做修改.');   
		}else{
			$.messager.alert('提示','您未选择所要修改的数据,请选择后按修改按钮.');  
		}
		
	}
	
}
