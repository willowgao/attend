<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/ztreeCommon.jsp"%>
<HTML>
	<HEAD>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/system/authority/js/userMenuAuthority.js"  charset="utf-8"></script>
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
	<input type="hidden" id="userId">
	<body style="background-color: white; height: 100px">
		<div id="treePanel" title="机构与部门信息" class="easyui-panel" style="width: 230px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
			<div style="z-index: -9999;">
				<ul id="orgTree" class="ztree">
				</ul>
			</div>
		</div>
		<div id="userList" title="用户列表" class="easyui-panel" style="width: 800px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:231,top:1}">
			<table id="userTable" class="easyui-datagrid" style="width: 100%; height: 100%"
				data-options="onClickRow:onClickRow,
				rownumbers:true,
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
							部门
						</th>
						<th data-options="field:'userorg',width:'30%',align:'center',formatter:formatterOrg">
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