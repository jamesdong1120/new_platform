$('#orgTreeCombox').combotree({  
	width:200,
    url : cxt_path + '/system/portalOrg/getEffectOrgTreeJsonData.do',
    checkbox:true,
    cascadeCheck:false,
    onBeforeSelect:function(node){
		if(0==node.id){
			return false;
		}
	},
	onSelect:function(node){
		$('#orgId').val(node.id);
		$('#deptTree').tree('options').url = cxt_path
				+ '/system/portalDept/getDeptTreeJsonData.do?orgId='+node.id;
		$('#deptTree').tree('reload');
	}
}); 

$('#searchOrgId').combotree({  
	width:150,
    url : cxt_path + '/system/portalOrg/getEffectOrgTreeJsonData.do',
    checkbox:true,
    cascadeCheck:false,
    onBeforeSelect:function(node){
		if(0==node.id){
			return false;
		}
	}
}); 



$('#deptTree').tree({
	dnd : true,
	onContextMenu : function(e, node) {
		e.preventDefault();
		var orgId=$('#orgId').val();
		if(orgId==''||orgId==null){
			$.messager.alert('提示','组织不能为空');
		}
		else if(orgId=='0'){
			$.messager.alert('提示','部门不能添加进最高根组织');    
		}
		else{
			// 查找节点
		$('#deptTree').tree('select', node.target);
		// 显示快捷菜单
			$('#deptMenu').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
		}
		
	},
	onDrop : function(target, source, point) {
		var targetParentId=0;
		var targetNodeId = $('#deptTree').tree('getNode', target).id;
		var targetNodeSort = $('#deptTree').tree('getNode', target).deptSort;
		if(targetNodeId!='0'){
			targetParentId=$('#deptTree').tree('getParent', target).id;	
		}
		
		var nodeId = source.id;
		var parentNodeId = source.deptParent;
		var nodeSort = source.deptSort;
		var operType = point;
		var orgId=$('#orgId').val();

		$.post(cxt_path + '/system/portalDept/dropDept.do', {
					targetParentId : targetParentId,
					targetNodeId : targetNodeId,
					targetNodeSort : targetNodeSort,
					nodeId : nodeId,
					parentNodeId : parentNodeId,
					nodeSort : nodeSort,
					operType : operType,
					orgId:orgId
				}, function(data) {
					if ('append' == point) {
						$('#deptTree').tree('reload', target);

					} else if ('top' == point || 'bottom' == point) {
						$('#deptTree').tree('reload',$('#deptTree').tree('getParent', target).target);
					}
				}, 'text');

	},
	onBeforeDrop : function(target, source, point) {
		if ($('#deptTree').tree('getNode', target).id == source.deptParent
				&& point == 'append') {
			return false;
		}
		if($('#deptTree').tree('getNode', target).id=='0'&& (point=='top'||point=='bottom')){
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
    url:cxt_path+'/system/portalDept/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'deptId',title:'部门编号',width:100,checkbox:true},
    	 {field:'deptName',title:'部门名称',width:100},
    	 {field:'deptParent',title:'上级部门',width:100},
    	 {field:'deptSort',title:'部门排序',width:100},
    	 {field:'orgId',title:'组织编号',width:100},
    	 {field:'deptStatus',title:'部门状态',width:100,formatter:statusFormatter},
    	 {field:'createdBy',title:'创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'updatedDate',title:'更新时间',width:100,hidden:true},
    	 {field:'updatedBy',title:'更新者',width:100,hidden:true}
    ]]    
});

$("#deptStatus").combobox({
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
   	var deptName=$('#searchDeptName').val();
   	var orgId=$('#searchOrgId').combotree('getValue');
   	var status;
   	if($('#valid').is(':checked')==true){
   		status=1;
   	}
   	if($('#invalid').is(':checked')==true){
   		status=0;
   	}
	$('#dg').datagrid({
		 url:cxt_path+'/system/portalDept/getPageResult.do',
		 queryParams:{
   			deptName:deptName,
   			orgId:orgId,
   			deptStatus:status
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
	var url=cxt_path + '/system/portalDept/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,150,280,refresh);
	$('#controlForm').form('clear');
}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#dg").datagrid('getChecked');
	
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/system/portalDept/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/system/portalDept/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url);
		$('#controlForm').form('load',updateRecord,150,280);
	}else{
		if(checkedRecords.length>1){
			$.messager.alert('提示','您同时选择了多条数据做修改,请一条一条做修改.');   
		}else{
			$.messager.alert('提示','您未选择所要修改的数据,请选择后按修改按钮.');  
		}
		
	}
	
}

function refresh() {
	var node = $('#deptTree').tree('getSelected');
	if(!$('#deptTree').tree('isLeaf',node.target)){
		$('#deptTree').tree('reload', node.target);
	}else{
		var parentNode=	$('#deptTree').tree('getParent',node.target);
		$('#deptTree').tree('reload', parentNode.target);
	}
	
	
}


function addDept() {
	add();
	var node = $('#deptTree').tree('getSelected');
	var orgId=$('#orgTreeCombox').combotree('getValue');
	
	$("#deptParent").val(node.id);
	$('#orgId').val(orgId);
}

