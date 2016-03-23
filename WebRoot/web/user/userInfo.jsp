<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/common.jsp"%>
<%@ include file="../common/ztreeCommon.jsp"%>
<HTML>
	<HEAD>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/user/js/user.js"  charset="utf-8"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
		<html:base target="_self" />
		<META http-equiv="Content-Style-Type" content="text/css">
		<style type="text/css">
.ztree li span.button .add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top; *
	vertical-align: middle
}
</style>
	</HEAD>
	<input type="hidden" id="orgId">
	<input type="hidden" id="userId">
	<input type="hidden" id="h_org">
	<input type="hidden" id="deptId">
	<input type="hidden" id="roleId">
	<body style="background-color: white; height: 100px">
		<div id="treePanel" title="机构信息" class="easyui-panel" style="width: 230px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
			<div style="z-index: -9999;">
				<ul id="orgTree" class="ztree">
				</ul>
			</div>
		</div>
		<div id="userList" title="用户列表信息" class="easyui-panel" style="width: 800px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:231,top:1}">
			<div id="tb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit('dg')">删除</a>
			</div>
			<table id="dg" class="easyui-datagrid" style="width: 100%; height: 100%"
				data-options="onClickRow:onClickRow,
				rownumbers:true,
				toolbar: '#tb',
				ctrlSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'defaultstyle',width:'25%',align:'center',hidden:'true'">
							系统样式
						</th>
						<th data-options="field:'higherid',width:'25%',align:'center',hidden:'true'">
							上级ID
						</th>
						<th data-options="field:'roleid',width:'8%',align:'center',hidden:'true'">
							角色ID
						</th>
						<th data-options="field:'userid',width:'8%',align:'center',hidden:'true'">
							用户ID
						</th>
						<th data-options="field:'userdeptid',width:'15%',align:'center',formatter:formatterDept">
							用户部门
						</th>
						<th data-options="field:'username',width:'10%',align:'center'">
							用户名称
						</th>
						<th data-options="field:'usersex',width:'8%',align:'center',formatter:formatterSex">
							性别
						</th>
						<th data-options="field:'usercode',width:'10%',align:'center'">
							用户编码
						</th>
						<th data-options="field:'userorg',width:'30%',align:'center',formatter:formatterOrg">
							组织机构
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="userInfoPanel" title="用户详细信息" class="easyui-panel" style="width: 300px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1031,top:1}">
			<form id="addUserForm" method="post">
				<div class="label_css">
					<label for="defaultstyle">
						系统样式 :
					</label>
					<input id="defaultstyle" name ="defaultstyle" class="easyui-combobox" style="width: 170px;"
						data-options="onSelect:changeStyle,required:true,valueField:'id',textField:'text',data: [{id: 'default',text:'default'},{id: 'gray',	text: 'gray'},
						{id: 'black',text: 'black'},{id: 'bootstrap',text: 'bootstrap'},{id: 'metro',text: 'metro'},{id: 'metro-blue',text: 'metro-blue',selected:true},
						{id: 'metro-gray',text: 'metro-gray'},{id: 'metro-orange',text: 'metro-orange'},{id: 'metro-green',text: 'metro-green'},
						{id: 'metro-red',text: 'metro-red'},{id: 'ui-cupertino',text: 'ui-cupertino'},{id: 'ui-dark-hive',text: 'ui-dark-hive'},
						{id: 'ui-pepper-grinder',text: 'ui-pepper-grinder'},{id: 'ui-sunny',text: 'ui-sunny'}]">
					</input>
				</div> 
				<div class="label_css">
					<label for="role">
						角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  色 :
					</label>
					<input class="easyui-combobox" type="text" id="roleMap" name="role" data-options="required:true" />
				</div> 
				<div class="label_css">
					<label for="higherid">
						直接上级 :
					</label>
					<input class="easyui-combobox" type="text" id="higherid" name="higherid"/>
				</div> 
				<div class="label_css">
					<label for="name">
						用户姓名 :
					</label>
					<input class="easyui-validatebox" type="text" id="userName" name="userName" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="usersex">
						性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  别 :
					</label>
					<input class="easyui-combobox" type="text" id="usersex" name="usersex" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="usersex">
						参加考核 :
					</label>
					<input class="easyui-combobox" type="text" id="needassess" name="needassess" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="userCode">
						用 户 名 &nbsp;:
					</label>
					<input class="easyui-validatebox" type="text"  id="userCode" name="userCode" data-options="required:true" />
					<div id="checkCode" style="color:red">*用户名已被注册，请重新输入!</div>
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
				<input type="hidden" id="oldPwd" name="oldPwd">
				<div class="label_css">
					<a onclick="submitForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">保存</a>
					<a onclick="clearForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">重置</a>
				</div> 
				
			</form>
		</div>
	</body> 
</HTML>