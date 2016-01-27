<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/position/js/position.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form id="positionForm" method="post">
			<div id="commDesc" class="easyui-panel" style="width: 10%; height: 40%; position: absolute; z-index: 8;">
			</div>
			<div class="easyui-panel" title="审核信息" style="width: 100%">

				<div><label style="margin: 10px;">
						审核人员：
					</label>
					<input class="easyui-combobox" type="text" id="approver" name="position.approver" data-options="readonly:true,required:true" />
					<label style="margin: 10px;">
						部门：
					</label>
					<input class="easyui-combobox" type="text" id="deptid" name="position.deptid" data-options="readonly:true,required:true" />
					<label style="margin: 10px;">
						岗位：
					</label>
					<input class="easyui-combobox" type="text" id="roleid" name="position.roleid" data-options="readonly:true,required:true" />
					<label style="margin: 10px;">
						启用日期：
					</label>
					<input name="position.starttime" id="starttime" class="easyui-datebox"
						data-options="formatter:fn_Dateformatter,parser:fn_DateParser,required:true"></input>
						
					<input type="hidden" name="position.datagrid" id="datagrid"></input>
					<a onclick="save();" href="#" class="easyui-linkbutton c5" style="width: 80px; margin: 10px;">传递</a>
				</div>
			</div>
		</form>
		<div id="tb" style="height: auto">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save();">保存</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add('#dg');">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit('#dg');">删除</a>
		</div>
		<div style="width: 100%; height: 80%">
			<div align="left">
			</div>
			<table id="dg" title="岗位信息" style="with: 100%; height: 100%"
				data-options="
				toolbar: '#tb', 
				onDblClickCell:onClickCell,
				rownumbers:true,
				collapsible:true,
				nowrap:false,
				rowStyler:setRowStyle,
				autoRowHeight:false,
				striped:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th
							data-options="field:'statements',width:'30%',align:'center',editor:{
							type:'textbox',
							options:{required:true,multiline:true,height:100,validType:['length[0,250]']}
						}">
							职责要素
						</th>
						<th
							data-options="field:'standards',width:'30%',align:'center',editor:{
							type:'textbox',
							options:{required:true,multiline:true,height:100,validType:['length[0,250]']}
						}">
							考核标准
						</th>
						<th
							data-options="field:'comments',width:'20%',align:'center',editor:{
							type:'textbox',
							options:{required:true,multiline:true,height:100,validType:['length[0,100]']}
						}">
							备注
						</th>
						<th data-options="field:'psid',width:'10%',align:'center',hidden:'true'">
							职责ID
						</th>
						<th data-options="field:'ssid',width:'10%',align:'center',hidden:'true'">
							职责明细ID
						</th>
					</tr>
				</thead>
			</table> 
		</div>
	</body>
</html>
