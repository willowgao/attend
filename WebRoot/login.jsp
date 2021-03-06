<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	String webapp = request.getContextPath();
	request.setAttribute("webapp", webapp);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>咸宁市人社局平时考核系统</title>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/login.css" type="text/css"></link>
		<link rel="shortcut icon" type="image/x-icon" href="<%=webapp%>/web/common/css/images/favicon.ico" />
		<!-- 新 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="<%=webapp%>/web/common/bootstrap/css/bootstrap.min.css" type="text/css"></link>

		<script type="text/javascript" src="<%=webapp%>/web/common/jquery/jquery-1.12.0.min.js"></script>
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="<%=webapp%>/web/common/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>



			<div id="myCarousel" class="carousel slide"   data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img class="first-slide"  src="<%=webapp%>/web/common/css/images/bann10_1.jpg"  >
						<div class="container">
							<div class="carousel-caption"  style="margin-bottom: 40px;">
								<h1 style="font-size: 55px;">
									咸宁市人力资源和社会保障局
								</h1>
								<p style="font-size: 35px;">
									平时考核信息管理系统
								</p> 
							</div>
						</div>
					</div>
					<div class="item">
						<img  class="second-slide" src="<%=webapp%>/web/common/css/images/banner.jpg" alt="Second slide">
						<div class="container">
							<div class="carousel-caption">
								<h1>
									打造一流和谐机关，铸造一流创业团队，创造一流工作业绩
								</h1>
								<p>
									通过开发运行平时考核信息化管理系统平台，进一步规范平时考核工作，完善平时考核机制，促进工作人员勤政廉政，提高平时考核工作的科学性，提高工作效能。
								</p>
								<p>
									<a class="btn btn-danger btn-lg btn-primary" href="#" role="button">我们的指导思想</a>
								</p>
							</div>
						</div>
					</div>
					<div class="item">
						<img class="third-slide" src="<%=webapp%>/web/common/css/images/Banner-bg.jpg" alt="Third slide">
						<div class="container">
							<div class="carousel-caption">
								<h1>
									突出特点、简便易行、务实管用，实事求是
								</h1>
								<p>
									建立平时考核系统平台，利用信息化手段，对工作人员日常工作的情况进行记录、反馈、评估及互动，实现工作人员平时考核客观、公正、量化、高效，促进人社事业的发展。
								</p>
								<p>
									<a class="btn btn-danger btn-lg btn-primary" href="#" role="button">我们的工作目标</a>
								</p>
							</div>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
					aria-hidden="true"></span> <span class="sr-only">Previous</span> </a>
				<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span> </a>
			</div> 
			<div id="systemName" align="center">
									Xianning Human Resources And Social Security  Usually Assessment Information System</br>Copyright @ 2015-2016</br> MaSoft
									&nbsp;<a href="web/common/browser/Chrome_Setup.exe">Chrome 浏览器下载</a>
								</div>
								
		<div id="login" class="login_main">
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
				<a class="btn btn-danger btn-lg" style="width:100%;">
					登 录
				</a>
			</div>
			<div  id="login_hide" class="login_hide">
				*请检查用户名密码是否正确!
			</div>
		</div>
			<img src="<%=webapp%>/web/common/browser/smalllogo.png" class="img-responsive" style="width:2%;height:4%;position: fixed;left: 305px;top: 570px;z-index: 99999" alt="Responsive image"></img>
			<a class="btn btn-danger btn-lg" href="web/common/browser/Chrome_Setup.exe" style="width:15%;position: fixed;left: 300px;top: 560px">&nbsp;&nbsp;&nbsp;Chrome浏览器下载</a>
			<a class="btn btn-danger btn-lg" href="web/common/files/咸宁市平时考核系统运行实施手册_V1.0.doc" style="width:12%;position: fixed;left: 100px;top: 560px">操作手册下载</a>
			
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
		username_hide.focus();
		 
		username_hide.keydown(function(){
			username_hide.hide();
			username.focus();
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
