$('#bankTransTree').tree({    
    url:cxt_path+'/bedc/bciBank/getInitBankTreeJsonData.do',
    onBeforeExpand:function(node,param){
    	if(node.id==0){
    		$('#bankTransTree').tree('options').url=cxt_path+'/bedc/bciBankBodyCfg/getBankTreeJsonData.do';
    	}else{
    		$('#bankTransTree').tree('options').url=cxt_path+'/bedc/bciBankTrans/getBciBankTransTreeData.do?bankCode='+node.id;
    	}
    	
    },
    onClick: function(node){
    	
    },
    onContextMenu: function(e, node){
		e.preventDefault();
		// 查找节点
		$('#bankTransTree').tree('select', node.target);
		// 显示快捷菜单
		if(node.isLeaf=='1'){
			$('#bankTransTreeMenu').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}
		
	
	}

});