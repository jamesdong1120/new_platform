document.onkeydown=keyListener; 
function keyListener(e){ 
e = e ? e : event; 
if(e.keyCode == 13){ 
	login();
	} 
}
$(function() {
		//得到焦点
		$('#password').focus(function() {
			$('#left_hand').animate({
				left : '150',
				top : ' -38'
			}, {
				step : function() {
					if (parseInt($('#left_hand').css('left')) > 140) {
						$('#left_hand').attr('class', 'left_hand');
					}
				}
			}, 2000);
			$('#right_hand').animate({
				right : '-64',
				top : '-38px'
			}, {
				step : function() {
					if (parseInt($('#right_hand').css('right')) > -70) {
						$('#right_hand').attr('class', 'right_hand');
					}
				}
			}, 2000);
		});
		//失去焦点
		$('#password').blur(function() {
			$('#left_hand').attr('class', 'initial_left_hand');
			$('#left_hand').attr('style', 'left:100px;top:-12px;');
			$('#right_hand').attr('class', 'initial_right_hand');
			$('#right_hand').attr('style', 'right:-112px;top:-12px');
		});
	});
	
function login(){
	var username=$('username').val();
	var password=$('password').val();
	var flag=true;
	var errorMsg="";
	if(null==username||username==''){
		flag=false;
		errorMsg+='用户名不能为空<br/>'
	}
	if(null==password||password==''){
		flag=false;
		errorMsg+='密码不能为空<br/>'
	}
	$('#loginForm').submit();
}