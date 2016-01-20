<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/system/authority/js/sysAppAuthority.js"></script>
		<title>查询</title>
	</head>
	<body>
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add('#dg')">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit('#dg')">删除</a></div>
		<div style="width: 100%; height: 90%">
			<div align="left">
			</div>
			<table id="dg" title="系统审核权限配置列表" style="width: 100%; height: 100%"
				data-options="
				onDblClickCell: onClickCell,
				rownumbers:true,
				ctrlSelect:true,
				toolbar:tb,
				striped:true,
				autoRowHeight:false,
				sortOrder:'desc',
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th
							data-options="field:'leavetype',width:'20%',align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'text',
								method:'get',
								url:'<%=webapp%>/authority/sysDataDictionary!getDictionary.action?key=LEAVETYPE',
								required:true
							}
						}
						,formatter:formatterLeave">
							假期类型
						</th>
						<th data-options="field:'approver',width:'8%',align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'text',
								method:'get',
								url:'<%=webapp%>/authority/sysDataDictionary!getDictionary.action?key=ROLETYPE',
								required:true
							}
						}
						,formatter:formatterRole">
							角色适用类型
						</th>
						<th data-options="field:'leavename',width:'12%',align:'center',editor:'textbox'">
							假期类型描述
						</th> 
						<th data-options="field:'approvername',width:'12%',align:'center',editor:'textbox'">
							适用角色描述
						</th> 
						<th data-options="field:'approvetype',width:'12%',align:'center',editor:'textbox'">
							审核类型
						</th> 
						<th data-options="field:'settingid',width:'12%',hidden:'true'">
							id
						</th> 
					</tr>
				</thead>
			</table>
		</div>
 
	</body>
</html>
