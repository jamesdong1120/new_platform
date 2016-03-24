$('#funcTree').tree({
	url : cxt_path + '/system/portalFunc/getInitMenuTreeJsonData.do',
	dnd : true,
	onBeforeExpand : function(node, param) {
		$('#funcTree').tree('options').url = cxt_path
				+ '/system/portalFunc/getMenuTreeJsonData.do?parentId='
				+ node.id
	},
	onClick : function(node) {

	},
	onContextMenu : function(e, node) {
		e.preventDefault();
		// 查找节点
		$('#funcTree').tree('select', node.target);
		// 显示快捷菜单
			$('#funcMenu').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
	},
	onDrop : function(target, source, point) {
		var targetParentId=0;
		var targetNodeId = $('#funcTree').tree('getNode', target).id;
		var targetNodeSort = $('#funcTree').tree('getNode', target).funcSort;
		if(targetNodeId!='0'){
			targetParentId=$('#funcTree').tree('getParent', target).id;	
		}
		
		var nodeId = source.id;
		var parentNodeId = source.parentId;
		var nodeSort = source.funcSort;
		var operType = point;

		$.post(cxt_path + '/system/portalFunc/dropFunc.do', {
					targetParentId : targetParentId,
					targetNodeId : targetNodeId,
					targetNodeSort : targetNodeSort,
					nodeId : nodeId,
					parentNodeId : parentNodeId,
					nodeSort : nodeSort,
					operType : operType
				}, function(data) {
					if ('append' == point) {
						$('#funcTree').tree('reload', target);

					} else if ('top' == point || 'bottom' == point) {
						$('#funcTree').tree('reload',$('#funcTree').tree('getParent', target).target);
					}
				}, 'text');

	},
	onBeforeDrop : function(target, source, point) {
		if ($('#funcTree').tree('getNode', target).id == source.parentId
				&& point == 'append') {
			return false;
		}
		if($('#funcTree').tree('getNode', target).id=='0'&& (point=='top'||point=='bottom')){
			return false;
		}

	}

});

$('#dg').datagrid({
			toolbar : "#tb",
			fitColumns : false,
			pagination : true,
			ctrlSelect : true,
			fit : true,
			pageSize : 15,// 调整分页数据
			pageList : [15, 30, 45, 60],// pageList的数组值是pageSize的正整数倍
			url : cxt_path + '/system/portalFunc/getPageResult.do',
			loadMsg : '数据加载中,请稍等...',
			columns : [[{
						field : 'funcId',
						title : '功能编号',
						width : 100,
						checkbox : true
					}, {
						field : 'funcName',
						title : '功能名称',
						width : 150
					}, {
						field : 'funcUrl',
						title : '功能地址',
						width : 350
					}, {
						field : 'funcSort',
						title : '功能排序',
						width : 80
					}, {
						field : 'parentId',
						title : '父节点编号',
						width : 80
					}, {
						field : 'funcStatus',
						title : '功能状态',
						width : 80,
						formatter:statusFormatter
					}, {
						field : 'createdBy',
						title : '创建者',
						width : 100,
						hidden : true
					}, {
						field : 'createdDate',
						title : '创建时间',
						width : 100,
						hidden : true
					}, {
						field : 'updatedBy',
						title : '最后更新者',
						width : 100,
						hidden : true
					}, {
						field : 'updatedDate',
						title : '最后更新时间',
						width : 100,
						hidden : true
					}]]
		});

$("#status").combobox({
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

function load() {
	var funcName = $('#searchFuncName').val();
	var status;
   	if($('#valid').is(':checked')==true){
   		status=1;
   	}
   	if($('#invalid').is(':checked')==true){
   		status=0;
   	}
	$('#dg').datagrid({
				url : cxt_path + '/system/portalFunc/getPageResult.do',
				queryParams : {
					funcName : funcName,
					funcStatus:status
				}
			});
}

/**
 * 重置方法
 */
function reset() {
	$("#search_form").form('reset');
}

/**
 * 查询方法
 */
function doSearch() {
	// alert($(id).val());
	load();

}

/**
 * 增加方法
 */
function add() {
	var title = '增加';
	var url = cxt_path + '/system/portalFunc/add.do';
	// 查看createControlDialog方法有height和width 可以自定义弹框的长宽高
	createControlDialog(title, url,180,280,refresh);
	$('#controlForm').form('clear');
}

/**
 * 删除方法
 */
function del() {
	var delRecord = $("#dg").datagrid('getChecked');

	if (null != delRecord && "" != delRecord) {
		var delstr = $.toJSON(delRecord);
		$.post(cxt_path + '/system/portalFunc/delete.do', {
					delstr : delstr
				}, function(data) {
					var data = eval('(' + data + ')');
					if (data.result) {
						load();
						$.messager.alert('提示', data.successMsg);
					} else {
						$.messager.alert('提示', data.errorMsg);
					}
				}, 'text');
	} else {
		$.messager.alert('提示', '请选择您要删除的记录.');

	}

}

/**
 * 修改方法
 */
function edit() {
	var checkedRecords = $("#dg").datagrid('getChecked');
	var updateRecord = $('#dg').datagrid('getSelected');
	if (checkedRecords.length == 1 && null != updateRecord) {
		var title = '修改';
		var url = cxt_path + '/system/portalFunc/update.do';
		// 查看createControlDialog方法有height和width 可以自定义弹框的长宽高
		createControlDialog(title, url,180,280);

		$('#controlForm').form('load', updateRecord);
	} else {
		if (checkedRecords.length > 1) {
			$.messager.alert('提示', '您同时选择了多条数据做修改,请一条一条做修改.');
		} else {
			$.messager.alert('提示', '您未选择所要修改的数据,请选择后按修改按钮.');
		}

	}

}

function refresh() {
	var node = $('#funcTree').tree('getSelected');
	if(!$('#funcTree').tree('isLeaf',node.target)){
		$('#funcTree').tree('reload', node.target);
	}else{
		var parentNode=	$('#funcTree').tree('getParent',node.target);
		$('#funcTree').tree('reload', parentNode.target);
	}
}


function addMenuFunc() {
	add();
	var node = $('#funcTree').tree('getSelected');
	$("#parentId").val(node.id);
}
