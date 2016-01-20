<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*" %>
<%@ include file="../../common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/attendance/diary/js/loginInfo.js"></script>
		<title>查询</title>
	</head>
	<body>
		<div class="easyui-panel" style="width: 100%">
			<table class="main-font" cellpadding="4">
				<tr>
					<td>
						<label>
							开始时间：
						</label>
						<input id="startTime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
					</td>
					<td>
						<label>
							终止时间：
						</label>
						<input id="endTime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
					</td>
					<td>
						&nbsp;&nbsp;
						<a onclick="query()" href="#" class="easyui-linkbutton c5" style="width: 80px">查询</a>
					</td>
				</tr>
			</table>
		</div>
		<!-- 
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept('dg')">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append('dg')">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit('dg')">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject('dg')">Reject</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges('dg')">GetChanges</a>
		</div>
 -->
		<div style="width: 100%; height: 90%">
			<div align="left">
			</div>
			<table id="dg" title="系统登录记录列表" style="width: 100%; height: 100%"
				data-options="
				onDblClickCell: onClickCell,
				onClickRow:onClickRow,
				rownumbers:true,
				ctrlSelect:true,
				striped:true,
				autoRowHeight:false,
				sortOrder:'desc',
				sortName:'logintime',
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th
							data-options="field:'loginid',width:'20%',align:'center',
						formatter:function(value,row){
							return row.loginid;
						},
						editor:{
							type:'textbox',
							options:{
								valueField:'loginid',
								textField:'username',
								required:true
							}
						}">
							登录流水编码
						</th>
						<th data-options="field:'logincode',width:'8%',align:'center',editor:'textbox'">
							登录编码
						</th>
						<th data-options="field:'username',width:'8%',align:'center',editor:'textbox'">
							用户名称
						</th>
						<th data-options="field:'logintime',width:'12%',align:'center',editor:'textbox',formatter : formatterdate">
							登录时间
						</th>
						<th data-options="field:'loginip',width:'12%',align:'center',editor:'textbox'">
							登录IP
						</th>
						<th data-options="field:'logintmachine',width:'12%',align:'center',editor:'textbox'">
							登录服务器地址
						</th>
					</tr>
				</thead>
			</table>
		</div>
 
	</body>
</html>
