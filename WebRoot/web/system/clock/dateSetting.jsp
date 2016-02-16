<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/ztreeCommon.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/system/clock/js/dateSetting.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
		<title>查询</title>
	</head>
	<body>
		<form id="dateSetForm">
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<label style="margin: 10px;">
							设置月份：
						</label>
						<input name="clockdate" id="clockdate" class="easyui-timespinner" value="<%=DateUtil.date2String(sysdate,DateUtil.FORMAT_MONTH) %>"
							data-options="onChange:query,formatter:formatterYM,parser:parserYM,selections:[[0,4],[5,7]]"></input>
							<input type="hidden" name="datagrid" id="datagrid"></input>
				</div>
			</div>
		</form>
		<div id="tb" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>
		</div>
		<div style="width: 100%; height: 80%">
			<div align="left">
			</div>
			<table id="dg" title="时间列表" style="with: 100%; height: 100%"
				data-options="
				toolbar: '#tb',
				rownumbers:true,
				onDblClickCell: onClickCell,
				autoRowHeight:false,
				pagination:true,
				rowStyler:setRowColor,
				pageList:[5,10,15,20,25,31],
				pageSize:31"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'clockyear',width:'10%',align:'center'">
							考勤年份
						</th>
						<th data-options="field:'clockdate',width:'10%',align:'center',formatter :formatterdateYMD">
							考勤日期
						</th>
						<th data-options="field:'id',width:'10%',align:'center',hidden:'true'">
							id
						</th>
						<th data-options="field:'isneed',width:'10%',align:'center',formatter :foratterEnable,editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'text',
								method:'get',
								url:'<%=webapp%>/authority/sysDataDictionary!getDictionary.action?key=ISENABLE',
								required:true
							}
						}
						,">
							是否有效
						</th> 
					</tr>
				</thead>
			</table>
		</div>
	</body>
</html>
