$('#dg').datagrid({  
    toolbar:"#tb",
    fitColumns:false,
    pagination:true,
    ctrlSelect:true,
    fit:true,
    pageSize:15,//调整分页数据
    pageList:[15,30,45,60],//pageList的数组值是pageSize的正整数倍
    url:cxt_path+'/system/taskScheduleJob/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
         	 {field:'jobId',title:'任务编号',width:100,checkbox:true},
         	 {field:'jobName',title:'任务名称',width:100},
         	 {field:'jobGroup',title:'任务组',width:100},
         	 {field:'jobStatus',title:'是否启用',width:100,formatter:ensureFormatter,align:'center'},
         	 {field:'jobCronExpression',title:'任务运行表达式',width:200},
         	 {field:'jobDescription',title:'任务描述',width:200},
         	 {field:'beanClass',title:'任务类',width:200},
         	 {field:'isConcurrent',title:'是否同时触发',width:100},
         	 {field:'springId',title:'Spring编号',width:100},
         	 {field:'methodName',title:'调用的方法名称',width:100},
         	 {field:'  ',title:'操作',width:100,formatter:controlFormatter,align:'center'},
         	 {field:'createdBy',title:'创建者',width:100,hidden:true},
         	 {field:'createdDate',title:'创建时间',width:100,hidden:true},
         	 {field:'lastUpdatedBy',title:'修改者',width:100,hidden:true},
         	 {field:'lastUpdatedDate',title:'修改时间',width:100,hidden:true}
         ]]    
});

function load(){
	var jobName=$("#jobName").val();
	$('#dg').datagrid({
		 url:cxt_path+'/system/taskScheduleJob/getPageResult.do',
		 queryParams:{
			 jobName:jobName
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
	var url=cxt_path + '/system/taskScheduleJob/add.do';
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
			$.post(cxt_path + '/system/taskScheduleJob/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/system/taskScheduleJob/update.do';
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

function ensureFormatter(value,row,index){
	if(value=='1'){
		return '是';
	}
	else{
		return '否';
	}	
}

function controlFormatter(value,row,index){
	if(row.jobStatus=='1'){
		return '<a href="javascript:;" onclick="changeJobStatus(\''+row.jobId+'\',\'stop\')">stop</a>&nbsp;&nbsp;<a href="javascript:;" onclick="updateCron(\''+row.jobCronExpression+'\')">更新CRON</a>';
	}
	else{
		return '<a href="javascript:;" onclick="changeJobStatus(\''+row.jobId+'\',\'start\')">start</a>';
	}
}


function changeJobStatus(jobId,cmd){
	$.post(cxt_path + '/system/taskScheduleJob/changeJobStatus.do',{jobId:jobId,cmd:cmd},function(data){
		var data = eval('(' + data + ')');  
		if(data.result){
			$.messager.alert('提示',data.successMsg);
			load();
		}else{
			$.messager.alert('提示',data.errorMsg);
		}  
		
	},'text');
}


function updateCron(originVal){
	var updateRecord=$('#dg').datagrid('getSelected');
	if(null!=updateRecord){
		createUpdateCronDialog(originVal);
		$('#updateCronForm').form('load',updateRecord);
	}
	
}

function createUpdateCronDialog(originVal){
	$('#updateCronDialog').dialog({
		title : '更新CRON',
		modal : true,
		height : 150,
		width : 300,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				$('#updateCronForm').form('submit', {
					url :cxt_path + '/system/taskScheduleJob/updateCron.do' ,
					method : 'post', 
					onSubmit:function(param){
						var flag=$('#updateCronForm').form('validate');
						if(!flag){
							$.messager.alert('提示','表单不合法,请根据提示填写完整!'); 
						}
						if($('#jobCronExpression').val()==originVal){
							$.messager.alert('提示','更新的值与原始值一直,无需更新!!!'); 
							flag=false;
						}
						return flag;
					},
					success:function(data){
						var data = eval('(' + data + ')');  
						if(data.result){
							$.messager.alert('提示',data.successMsg);
							$('#updateCronDialog').dialog('close');
							load();
						}else{
							$.messager.alert('提示',data.errorMsg);
						}
					}
				});
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#updateCronDialog').dialog('close');
			}
		} ]
	});
	
}