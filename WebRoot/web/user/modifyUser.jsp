<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/common.jsp"%>
<%@ include file="../common/ztreeCommon.jsp"%>
<HTML>
	<HEAD>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/user/js/modifyUser.js"  charset="utf-8"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</HEAD>
	<input type="hidden" id="orgId">
	<input type="hidden" id="userId">
	<input type="hidden" id="deptId">
	<input type="hidden" id="roleId">
	<body style="background-color: white; height: 100px">
		<div id="treePanel" title="用户信息" class="easyui-panel" style="width: 400px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
			 <form id="addUserForm" method="post">
				<div class="label_css">
					<label for="defaultstyle">
						系统样式 :
					</label>
					<input id="defaultstyle" name="defaultstyle" class="easyui-combobox" style="width: 170px;"
						data-options="onSelect:changeStyle,required:true,valueField:'id',textField:'text',data: [{id: 'default',text:'default'},{id: 'gray',	text: 'gray'},
						{id: 'black',text: 'black'},{id: 'bootstrap',text: 'bootstrap'},{id: 'metro',text: 'metro'},{id: 'metro-blue',text: 'metro-blue',selected:true},
						{id: 'metro-gray',text: 'metro-gray'},{id: 'metro-orange',text: 'metro-orange'},{id: 'metro-green',text: 'metro-green'},
						{id: 'metro-red',text: 'metro-red'},{id: 'ui-cupertino',text: 'ui-cupertino'},{id: 'ui-dark-hive',text: 'ui-dark-hive'},
						{id: 'ui-pepper-grinder',text: 'ui-pepper-grinder'},{id: 'ui-sunny',text: 'ui-sunny'}]">
					</input>
				</div> 
				<div class="label_css">
					<label for="fontsize">
						字体大小 :
					</label>
					<input id="fontsize" name="fontsize" class="easyui-combobox" style="width: 170px;"
						data-options="onSelect:changeFont,required:true,valueField:'id',textField:'text',data: [{id: 'bigger',text:'默认字体',selected:true},{id: 'smaller',	text: '较小字体'}]">
					</input>
				</div> 
				<div class="label_css">
					<label for="name">
						用户姓名 :
					</label>
					<input class="easyui-validatebox" type="text" id="userName" name="userName" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="userPwd">
						用户密码 :
					</label>
					<input class="easyui-validatebox" type="password"  id="userPwd" name="userPwd" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="userPwd">
						确认密码 :
					</label>
					<input class="easyui-validatebox" type="password" id="reUserPwd" name="reUserPwd" data-options="required:true" />
					<div id="checkPwd" style="color:red">*两次输入密码不一致!</div>
				</div>
				<div class="label_css">
					<a onclick="submitForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">保存</a>
					<a onclick="clearForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">重置</a>
				</div> 
			</form>
		</div>
		  
	</body> 
</HTML>