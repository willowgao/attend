<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/assess/js/score.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form id="assessForm" method="post">
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="assess.starttime" id="starttime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="assess.endtime" id="endtime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin-left: 5px;">查询</a>
					</div>
					<input type="hidden" name="assess.datagrid" id="datagrid"></input>
				</div>
			</div>
		</form>
		<div id="tb" style="height: auto">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save();">保存</a>
		</div>
		<div style="width: 100%; height: 75%">
			<div align="left">
			</div>
			<table id="dg" title="考核记录" style="with: 100%; height: 100%"
				data-options="
				toolbar: '#tb', 
				rownumbers:true,
				selectOnCheck:true,
				CheckOnSelect:true,
				collapsible:true, 
				autoRowHeight:false,
				striped:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr><th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'assessyear',width:'5%',align:'center'">
							考核年度
						</th>
						<th data-options="field:'deptid',width:'15%',align:'center',formatter:formatterDept">
							部门
						</th>
						<th data-options="field:'userid',width:'6%',align:'center',formatter:formatterUser">
							被考核人
						</th>
						<th data-options="field:'starttime',width:'8%',align:'center',formatter:formatterdateYMD">
							开始日期
						</th>
						<th data-options="field:'endtime',width:'8%',align:'center',formatter:formatterdateYMD">
							终止日期
						</th>
						<th data-options="field:'higherscore',width:'10%',align:'right'">
							领导打分
						</th>
						<th data-options="field:'peerscore',width:'10%',align:'right'">
							同级打分
						</th>
						<th data-options="field:'reductionscore',width:'10%',align:'right'">
							考勤扣分
						</th>
						<th data-options="field:'finalscore',width:'10%',align:'right'">
							最后得分
						</th>
					</tr>
				</thead>
			</table>
		</div>
	</body>
</html>
