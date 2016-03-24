$('#orgTree').tree({
	url : cxt_path + '/system/portalOrg/getOrgTreeJsonData.do',
	dnd : true,
	onClick : function(node) {

	},
	onContextMenu : function(e, node) {
		e.preventDefault();
		// 查找节点
		$('#orgTree').tree('select', node.target);
		// 显示快捷菜单
			$('#orgMenu').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
	},
	onDrop : function(target, source, point) {
		var targetParentId=0;
		var targetNodeId = $('#orgTree').tree('getNode', target).id;
		var targetNodeSort = $('#orgTree').tree('getNode', target).orgSort;
		if(targetNodeId!='0'){
			targetParentId=$('#orgTree').tree('getParent', target).id;	
		}
		
		var nodeId = source.id;
		var parentNodeId = source.orgParent;
		var nodeSort = source.orgSort;
		var operType = point;

		$.post(cxt_path + '/system/portalOrg/dropOrg.do', {
					targetParentId : targetParentId,
					targetNodeId : targetNodeId,
					targetNodeSort : targetNodeSort,
					nodeId : nodeId,
					parentNodeId : parentNodeId,
					nodeSort : nodeSort,
					operType : operType
				}, function(data) {
					if ('append' == point) {
						$('#orgTree').tree('reload', target);

					} else if ('top' == point || 'bottom' == point) {
						$('#orgTree').tree('reload',$('#orgTree').tree('getParent', target).target);
					}
				}, 'text');

	},
	onBeforeDrop : function(target, source, point) {
		if ($('#orgTree').tree('getNode', target).id == source.orgParent
				&& point == 'append') {
			return false;
		}
		if($('#orgTree').tree('getNode', target).id=='0'&& (point=='top'||point=='bottom')){
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
    url:cxt_path+'/system/portalOrg/getPageResult.do',
    loadMsg:'数据加载中,请稍等...',
    columns:[[    
   		 {field:'orgId',title:'组织编号',width:100,checkbox:true},
    	 {field:'orgFullName',title:'组织全称',width:100},
    	 {field:'orgShortName',title:'组织简称',width:100},
    	 {field:'orgSort',title:'组织排序位置',width:100},
    	 {field:'orgParent',title:'上级组织编号',width:100},
    	 {field:'orgStatus',title:'组织状态',width:100,formatter:statusFormatter},
    	 {field:'remark',title:'备注',width:100},
    	 {field:'createdBy',title:'组织创建者',width:100,hidden:true},
    	 {field:'createdDate',title:'创建日期',width:100,hidden:true},
    	 {field:'updatedBy',title:'更新者',width:100,hidden:true},
    	 {field:'updatedDate',title:'更新日期',width:100,hidden:true}
    ]]    
});

$("#orgStatus").combobox({
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
   	var orgShortName=$('#searchOrgName').val();
   	var status;
   	if($('#valid').is(':checked')==true){
   		status=1;
   	}
   	if($('#invalid').is(':checked')==true){
   		status=0;
   	}
	$('#dg').datagrid({
		 url:cxt_path+'/system/portalOrg/getPageResult.do',
		 queryParams:{
   			orgShortName:orgShortName,
   			orgStatus:status
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
	var url=cxt_path + '/system/portalOrg/add.do';
	//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,180,250,refresh);
	$('#controlForm').form('clear');
}

/**
 *删除方法 
 */
function del(){
	var delRecord=$("#dg").datagrid('getChecked');
	
	if(null!=delRecord&&""!=delRecord){
		var delstr=$.toJSON(delRecord);
			$.post(cxt_path + '/system/portalOrg/delete.do',{delstr:delstr},function(data){
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
		var url=cxt_path +'/system/portalOrg/update.do';
		//查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url,180,250);
		$('#controlForm').form('load',updateRecord);
	}else{
		if(checkedRecords.length>1){
			$.messager.alert('提示','您同时选择了多条数据做修改,请一条一条做修改.');   
		}else{
			$.messager.alert('提示','您未选择所要修改的数据,请选择后按修改按钮.');  
		}
		
	}
	
}

function refresh() {
	var node = $('#orgTree').tree('getSelected');
	if(!$('#orgTree').tree('isLeaf',node.target)){
		$('#orgTree').tree('reload', node.target);
	}else{
		var parentNode=	$('#orgTree').tree('getParent',node.target);
		$('#orgTree').tree('reload', parentNode.target);
	}
	
	
}


function addOrg() {
	add();
	var node = $('#orgTree').tree('getSelected');
	$("#orgParent").val(node.id);
}
