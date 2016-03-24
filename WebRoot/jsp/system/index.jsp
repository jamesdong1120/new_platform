<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit :false,border : false" style="height:750px;">
        <div data-options="region:'north'" style="height:80px;">
        	<img  src="<%=request.getContextPath()%>/images/system/logo/bank_white.png" style="width: 45%;height:75px; ">
        </div>
        <div data-options="region:'south',split:true" style="height:50px;line-height:50px; text-indent:5px;">
        	<center>
        	Copyright © 2016 created by jamesdong. All rights reserved.
        	</center>
        </div>
        <div data-options="region:'west',split:true" title="控制台" style="width:200px;">
        	<ul id="menuTree"></ul>  
        </div>
        <div data-options="region:'center',iconCls:'icon-ok'" style="padding: 1px;overflow:hidden;">
        	<div id="tabs" class="easyui-tabs" data-options="fit:true">   
        	</div>
           
        </div>
    </div>
    
    <div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="refresh()" data-options="iconCls:'icon-reload'">刷新目录</div>
	</div>
    
   
</body>

<script type="text/javascript">
$('#menuTree').tree({    
    url:cxt_path+'/system/portalFunc/getInitMenuTreeJsonData.do',
    onBeforeExpand:function(node,param){
    	$('#menuTree').tree('options').url=cxt_path+'/system/portalFunc/getEffectMenuTreeJsonData.do?parentId='+node.id
    },
    onClick: function(node){
    	if($('#menuTree').tree('isLeaf',node.target)){
    		 if(node.url!='null'){
    			var subtitle=node.text;
    			var url=cxt_path+node.url;
    			addTab(subtitle,url);
        	}else{
        		$.messager.alert('提示','该节点未挂菜单,请先挂菜单.<br/> 谢谢!!!');    
        	} 
    	}
    	
    },
    onContextMenu: function(e, node){
		e.preventDefault();
		// 查找节点
		$('#menuTree').tree('select', node.target);
		// 显示快捷菜单
		if(!$('#menuTree').tree('isLeaf',node.target)){
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}
		
	
	}

});

function addTab(subtitle, url) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', { title: subtitle, content: '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>', closable: true });
        $('#tabs').tabs('getSelected').css('width', 'auto'); //重新tab body宽度为auto，如果你上面的添加语句设置了selected为false，注意使用下面注释的这句来获取你的tab
        //$('#tabs').tabs('getTab', subtitle).css('width', 'auto');
    }
    else $('#tabs').tabs('select', subtitle);
}

function refresh(){
	var node=$('#menuTree').tree('getSelected');
	$('#menuTree').tree('reload',node.target);
}

</script>
</html>