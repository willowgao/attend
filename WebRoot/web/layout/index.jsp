<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page language="java" import="com.wgsoft.common.utils.DateUtil" %>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>咸宁市人社局平时考核系统</title> 
		<link rel="shortcut icon" type="image/x-icon" href="<%=webapp%>/web/common/css/images/favicon.ico" />
		<link rel="stylesheet" href="<%=webapp%>/web/common/ztree/metroStyle/metroStyle.css" type="text/css">
		<!--<link rel="stylesheet" href="<%=webapp%>/web/common/zTree3.5/css/awesomeStyle/awesome.css" type="text/css"> -->
		<script type="text/javascript" src="<%=webapp%>/web/common/jquery/jquery-migrate-1.2.1.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/layout/js/index.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/layout/layout.css" type="text/css">
	</head>
	<body> 
	<form id="clockSettingForm">
		<input type="hidden"  name="clockSetting.amsbTime" id="amsbTime" ></input>
		<input type="hidden"  name="clockSetting.amxbTime" id="amxbTime" ></input>
		<input type="hidden"  name="clockSetting.pamsbTime" id="pmsbTime" ></input>
		<input type="hidden"  name="clockSetting.pamxbTime" id="pmxbTime" ></input>
	<form>
	
		<div id="commDesc"  class="easyui-panel" style="width: 10%;height: 3%;position:absolute;z-index:8;border-radius: 8px;"></div>
		<div id="main" class="easyui-layout" style="width: 100%" >
			<div id="north" data-options="region:'north'">
				<div id="logo" style="position: absolute; left: 1px; top: 1px; border-radius: 15px;" />
					<img src="<%=webapp%>/web/common/css/images/logo.png" width="233px;" height="75px;"></img>
				</div>
				<div id="menu_list" style="position: absolute; left: 233px; top: 1px;" />
				</div>
				<div id="style_change" style="position: absolute; left: 1200px; top: 50px;">
				<input type="hidden" id="sysstyle" value="<%=request.getAttribute("sysstyle")%>"/>
					主题风格：
					<input id="cob_cssselector" class="easyui-combobox" style="width: 150px;"
						data-options="valueField:'id',textField:'text',data: [{id: 'default',text:'default'},{id: 'gray',	text: 'gray'},
						{id: 'black',text: 'black'},{id: 'bootstrap',text: 'bootstrap'},{id: 'metro',text: 'metro'},{id: 'metro-blue',text: 'metro-blue'},
						{id: 'metro-gray',text: 'metro-gray'},{id: 'metro-orange',text: 'metro-orange'},{id: 'metro-green',text: 'metro-green'},
						{id: 'metro-red',text: 'metro-red'},{id: 'ui-cupertino',text: 'ui-cupertino'},{id: 'ui-dark-hive',text: 'ui-dark-hive'},
						{id: 'ui-pepper-grinder',text: 'ui-pepper-grinder'},{id: 'ui-sunny',text: 'ui-sunny'}]">
					</input>
				</div>

				<div id="toobars" style="position: absolute; right: 50px; top: 8px;">
					您好！<%=request.getAttribute("username")%>&nbsp;&nbsp;|&nbsp;&nbsp;
					<a onclick="fnLoginOut();" style="width: 80px">退出</a>
				</div>
			</div>
			<input type="hidden" id="statictext" value=">> 您所在菜单：" />
			<!-- <div id="east" data-options="region:'east'" title="east"></div> 
			<div id="south" data-options="region:'south'" title="south"></div>  -->
			<div id="west" data-options="region:'west',split:true" title="系统菜单" style="z-index: -9999;">
				<ul id="orgTree" class="ztree">
				</ul>
			</div>
			<div id="tab_rightmenu" class="easyui-menu" style="width:150px;"> <!--  <div name="tab_menu-tabclose">关闭</div> -->  
				<div name="tab_menu-tabcloseall">关闭全部标签</div>  
				<div name="tab_menu-tabcloseother">关闭其他标签</div>  
				<div class="menu-sep"></div> 
 				<div name="tab_menu-tabcloseright">关闭右侧标签</div>  
 				<div name="tab_menu-tabcloseleft">关闭左侧标签</div> 
			</div>
			<div id="center" data-options="region:'center'" title=">>">
				<div id="mytabs" class="easyui-tabs" data-options="fit : true">
					<div title="个人中心">
						<div class="easyui-draggable" data-options="handle:'#title',onDrag:onDrag"
							style="position: absolute; left: 60; top: 100; width: 1019px; height: 200px; border: 1px solid #ccc;">
							<div id="title" style="padding: 5px;font-weight: bold; ">
								打卡区域
							</div>
							<div id="personInfo" style="position: absolute; left: 300; top:100; color: #404040;font-weight: bold; font-size:20px;">
								<%=request.getAttribute("username")%>,您好！现在是：<%= DateUtil.date2String(new Date(),DateUtil.YNYMDR)%></br> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label style="color: red;font-weight: bold; font-size:30px;" id="timeShow"></lable>
							</div>
							<table id="dg" class="easyui-datagrid">   
							    <thead>   
							        <tr>   
							            <th id="amsbText" data-options="field:'amsbText',width:'25%',align:'center'"></th>   
							            <th id="amxbText" data-options="field:'amxbText',width:'26%',align:'center'"></th>   
							            <th id="pmsbText" data-options="field:'pmsbText',width:'26%',align:'center'"></th>   
							            <th id="pmxbText" data-options="field:'pmxbText',width:'25%',align:'center'"></th>   
							        </tr>   
							    </thead>   
							    <tbody >   
							        <tr id="clockTr">   
							            <td align="center"><a href="javascript:void(0)" id="amsb" onclick="fnClock('1');" style="text-decoration : none;color:red;font-weight:bold; ">打卡</a></td>
							            <td align="center"><a href="javascript:void(0)" id="amxb" onclick="fnClock('2');" style="text-decoration : none;color:red;font-weight:bold;">打卡</td>
							            <td align="center"><a href="javascript:void(0)" id="pmsb" onclick="fnClock('3');" style="text-decoration : none;color:red; font-weight:bold;">打卡</td>
							            <td align="center"><a href="javascript:void(0)" id="pmxb" onclick="fnClock('4');" style="text-decoration : none;color:red;font-weight:bold;">打卡</td>  
							        </tr>     
							    </tbody>   
							</table>  
						</div>
						<div class="easyui-draggable" data-options="handle:'#title',onDrag:onDrag"
							style="position: absolute; left: 60; top: 310; width: 505px; height: 320px;  border: 1px solid #ccc;">
							<div id="title" style="padding: 15px;  color: #404040;font-weight: bold; ">
								 
							</div>
							<div id="topic" class="topic_icon" align="center">
							</div>
							 <table id="waitTable"  style="width: 500px; height: 290px;"
								data-options="
								rownumbers:true,
								autoRowHeight:false,
								fitColumns:true,
								pagination:true,
								pageList:[10,15,20,30],
								pageSize:10"
								align="left">
								<thead>
									<tr> 
										<th data-options="field:'comments',align:'center',formatter :fomatterUrl">
											工作事项
										</th>
										<th data-options="field:'starttime',align:'center',formatter : formatterdateYMD">
											工作开始时间
										</th>
										<th data-options="field:'endtime',align:'center',formatter : formatterdateYMD">
											工作终止时间
										</th>
										<th data-options="field:'settime',align:'center',formatter : formatterdateYMD">
											截止完成时间
										</th> 
										<th data-options="field:'url',hidden:true">
											url
										</th> 
									</tr>
								</thead>
							</table>
							
						</div>
						<div class="easyui-draggable" data-options="handle:'#title',onDrag:onDrag"
							style="position: absolute; left: 575; top: 310; width: 505px; height: 320px; border: 1px solid #ccc;">
							<div id="title" style="padding: 5px;  color: #404040;font-weight: bold; ">
								考勤记录
							</div>
							 <table id="clocktables"  style="width: 500px; height: 290px;"
								data-options="
								rownumbers:true,
								autoRowHeight:false,
								pagination:true,
								pageList:[15,31,60],
								pageSize:31"
								align="left">
								<thead>
									<tr> 
										<th data-options="field:'clockdate',width:'20%',align:'center',formatter : formatterdateYMD">
											考勤日期
										</th>
										<th data-options="field:'amsb',width:'20%',align:'center'">
											上午上班时间
										</th>
										<th data-options="field:'amxb',width:'20%',align:'center'">
											上午下班时间
										</th>
										<th data-options="field:'pmsb',width:'20%',align:'center'">
											下午上班时间
										</th>
										<th data-options="field:'pmxb',width:'18%',align:'center'">
											下午下班时间
										</th>
									</tr>
								</thead>
							</table>
						</div>
						<div id="logoDialog">
							Don't forget the beginner's mind Party must always ! Good lucky to you ! 
						</div>
					</div>
				</div>
				 
			</div>
		</div>
<script type="text/javascript">
	 //jQuery将$换成jq，避免冲突
	var jq = jQuery.noConflict();
	var winWidth = window.innerWidth-30;
	var winHeight = window.innerHeight-30;
	jq('#main').width(winWidth);
	jq('#main').height(winHeight);
	jq('#north').height(parseFloat(winHeight) * 0.10);
	jq('#west').width(parseFloat(winWidth) * 0.15);
 
    
</script>
</body>
</html>
