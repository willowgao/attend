<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/ztreeCommon.jsp"%>
<HTML>
	<HEAD>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/system/authority/js/authority.js"  charset="utf-8"></script>
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
	<input type="hidden" id="h_org">
	<input type="hidden" id="deptId">
	<input type="hidden" id="roleId">
	<input type="hidden" id="orgType">
	<body style="background-color: white; height: 100px">
		<div id="treePanel" title="机构与部门信息" class="easyui-panel" style="width: 230px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
			<div style="z-index: -9999;">
				<ul id="orgTree" class="ztree">
				</ul>
			</div>
		</div>
		<div id="userInfoPanel" title="角色新增维护" class="easyui-panel" style="width: 300px; height: 300px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:231,top:1}">
			<form id="addRoleForm" method="post">
				<div class="label_css">
					<label for="name">
						角色名称:
					</label>
					<input class="easyui-validatebox" type="text" id="roleName" name="roleName" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="userCode">
						角色编码:
					</label>
					<input class="easyui-validatebox" type="text"  id="roleCode" name="roleCode" data-options="required:true" />
					<div id="checkCode" style="color:red">*角色编码已被注册，请重新输入!</div>
				</div>
				<div class="label_css">
					<label for="userCode">
						角色类型:
					</label>
					<input class="easyui-combobox" type="text" id="roleType" name="roleType"  data-options="required:true" />
					</div>
				<div class="label_css">
					<a onclick="submitForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">保存</a>
					<a onclick="clearForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">重置</a>
				</div>
				<!-- 
				<div>
					<label for="userPwd">
						所属部门:
					</label>
					<input class="easyui-combobox" type="text" name="userPwd" data-options="required:true" />
				</div>
				 -->
			</form>
		</div>
		<div id="roleList" title="角色列表" class="easyui-panel" style="width: 500px; height: 300px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:531,top:1}">
			<div id="tb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit('roleTable')">删除</a>
			</div>
			<table id="roleTable" class="easyui-datagrid" style="width: 100%; height: 100%"
				data-options="onClickRow:onRoleClickRow,
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
						<th data-options="field:'roleid',width:'8%',align:'center',hidden:'true'">
							id
						</th>
						<th data-options="field:'rolename',width:'20%',align:'center'">
							角色名称
						</th>
						<th data-options="field:'rolecode',width:'20%',align:'center'">
							角色编码
						</th>
						<th data-options="field:'roledeptid',width:'20%',align:'center',formatter:formatterDept">
							部门
						</th>
						<th data-options="field:'roletype',width:'20%',align:'center',formatter:formatterRole">
							角色类型
						</th>
						<th data-options="field:'roleorg',width:'40%',align:'center',formatter:formatterOrg">
							组织机构
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="userList" title="用户列表" class="easyui-panel" style="width: 800px; height: 300px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:231,top:301}">
			<table id="userTable" class="easyui-datagrid" style="width: 100%; height: 100%"
				data-options="rownumbers:true,
				ctrlSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'userid',width:'8%',align:'center',hidden:'true'">
							id
						</th>
						<th data-options="field:'username',width:'20%',align:'center'">
							用户名称
						</th>
						<th data-options="field:'usercode',width:'20%',align:'center'">
							用户编码
						</th>
						<th data-options="field:'userdeptid',width:'20%',align:'center',formatter:formatterDept">
							部门名称
						</th>
						<th data-options="field:'userorg',width:'25%',align:'center',formatter:formatterOrg">
							组织机构
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="menuTreePanel" title="角色菜单信息" class="easyui-panel" style="width: 300px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1031,top:1}">
			<div id="menuTb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="saveMenuForRole()">保存</a>
			</div>
			<div style="z-index: -9999;">
				<ul id="menuTree" class="ztree">
				</ul>
			</div>
		</div>
	</body> 
</HTML>