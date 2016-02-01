<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%
	String webapp = request.getContextPath();
	request.setAttribute("webapp", webapp);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	
	<head>	
		<title>咸宁市人社局平时考核系统</title>
		<link rel="stylesheet" href="<%=webapp %>/web/common/css/login.css" type="text/css"></link>
	 	<link rel="shortcut icon" type="image/x-icon" href="<%=webapp%>/web/common/css/images/favicon.ico" />
		<script type="text/javascript" src="<%=webapp %>/web/common/jquery/jquery-1.7.1.min.js"></script>
	</head>
	<body>
		<div id="login" class="login_main">
			<div class="login_head">
				<!-- 
				<div class="login_icon">
				</div>
				 -->
				<div class="login_title">
					用 户 登 录
				</div>
			</div>
			<div class="login_center">
				<div class="login_username">
					<input id="username" name="username"></input>
				</div>
				<div class="login_username_hide">
					<input id="username_hide" name="username_hide"></input>
				</div>
				<div class="login_password">
					<input type="password" id="password" name="password"></input>
				</div>
				<div class="login_password_hide">
					<input type="text" id="password_hide" name="password_hide"></input>
				</div>
				<div id="login_footer" class="login_footer">
					<div class="login_button">
						登 录
					</div>
				</div>
				<div id="login_hide" class="login_hide">
					*请检查用户名密码是否正确!
				</div>
			</div>
		</div>
	</body>


	<script type="text/javascript">
	//jQuery将$换成jq，避免冲突
		var jq = jQuery.noConflict();
	
		jq('#login_hide').hide();
		var winWidth = window.innerWidth;
		var winHeight = window.innerHeight;
		jq('#login').width(winWidth-30);
		jq('#login').height(winHeight-30);
		var username = jq('#username');
		var password = jq('#password');
		var password_hide = jq('#password_hide');
		var username_hide = jq('#username_hide');
		username_hide.val("*用户名");
		password_hide.val("*密码");
				
		username_hide.click(function(){
			username_hide.hide();
			username.focus();
		});
		username.keydown(function(event){
			if(event.keyCode == 13||event.keyCode == 9){
				password_hide.hide();
				password.val('');
				password.focus();
			}
		});
		password_hide.click(function(){
			password_hide.hide();
			password.focus();
		});
		 
		password_hide.focus(function(){
			password_hide.hide();
			password.focus();
		});
		
		password.keydown(function(event){
			if(event.keyCode == 13){
				checkLogin();
			}
		});
		
		jq('#login_footer').click(function(){
			checkLogin();
		});
		 
		function checkLogin(){
			var strName =username.val();
			var strPwd =password.val();
			jq.getJSON('<%=webapp%>/user/userManager!execute.action?username='+strName+'&password='+strPwd, function(datas) {
		 		if(datas!='1'){
		 			jq('#login_hide').show();
		 		}else{
		 			window.location.href='<%=webapp%>/user/userManager!show.action';
		 		}
			});
		}
		
</script>
</html>
