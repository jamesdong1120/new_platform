<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.4.3/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.4.3/themes/icon.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript">
var cxt_path='<%=request.getContextPath()%>';
$.ajaxSetup({ 
	error: function (XMLHttpRequest, textStatus, errorThrown){
	if(XMLHttpRequest.status==403){
	alert('您没有权限访问此资源或进行此操作');
	return false;
		}
	},  
	complete:function(XMLHttpRequest,textStatus){   
	        var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus， 
	                if(sessionstatus=='timeout'){   
	                      //如果超时就处理 ，指定要跳转的页面  
	            // var top = getTopWinow(); //获取当前页面的顶层窗口对象
	             alert('登录超时或非法登录, 请重新登录.'); 
	                 top.location.href=cxt_path+"/login.jsp"; //跳转到登陆页面
	             }
	         }   
	   }); 

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-easyui-1.4.3/jquery.json.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-easyui-1.4.3/jquery.edatagrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
.hiddenClass{
	display: none;
}
.easyui-textbox{
	width: 150px;
}

</style>

<script type="text/javascript">
$(function(){
	 var allRequired=$('.required');
	for(var i=0;i<allRequired.length;i++){
			$(allRequired[i]).append('<font color="red">*</font>')
	} 
	
	
});

//监听回车事件
/* document.onkeydown=keyListener; 
function keyListener(e){ 
e = e ? e : event; 
if(e.keyCode == 13){ 
	load();
	} 
} */ 
 

function createControlDialog(title,url,height,width,fn){
	$('#controlDialog').removeClass('hiddenClass');
	$('#controlDialog').dialog({
		title : title,
		modal : true,
		height : height,
		width : width,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				$('#controlForm').form('submit', {
					url :url ,
					method : 'post', 
					onSubmit:function(param){
						var flag=$('#controlForm').form('validate');
						if(!flag){
							$.messager.alert('提示','表单不合法,请根据提示填写完整!'); 
						}
						return flag;
					},
					success:function(data){
						var data = eval('(' + data + ')');  
						if(data.result){
							$.messager.alert('提示',data.successMsg);
							$('#controlDialog').dialog('close');
							load();
							if(null!=fn){
								fn();	
							}
						}else{
							$.messager.alert('提示',data.errorMsg);
							//$('#controlDialog').dialog('close');
						}
					}
				});
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#controlDialog').dialog('close');
			}
		} ]
	});
}


function statusFormatter(value,row,index){
	if(value=='1'){
		return '<font color="green">启用<font>';
	}
	else{
		return  '<font color="red">禁用<font>';;
	}	
}

function chooseOne(cb){
	var allCb=$(':checkbox');
	for(var i=0;i<allCb.length;i++){
		if (allCb[i]!=cb){
			allCb[i].checked = false;  
		}else{
			allCb[i].checked = cb.checked;
		} 
	}
}
</script>
</head>
</html>