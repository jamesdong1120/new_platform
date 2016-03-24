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
		<div class="easyui-layout" data-options="fit : true,border : false">
        <div data-options="region:'west',split:true" title="银行交易树" style="width:300px;">
       		<ul id="bankTransTree"></ul>
        </div>
        
        <div data-options="region:'center',split:true" title="交易报文预览" style="padding: 1px;overflow:hidden;">
        	<textarea id="displayArea" cols="82" rows="26" style="overflow:auto;font-size: 12px;color:rgb(51,51,51);resize:none;border:0px;"></textarea>
        </div>
    </div>
	

	
		
	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bedc/bci_xml_file_template_t.js"></script>
</html>