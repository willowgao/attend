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
						<label >
							部门：
						</label>
						<input class="easyui-combobox" type="text" id="deptid" name="assess.deptid" data-options="required:true"  />
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
						<a onclick="exportExcel();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin-left: 5px;">导出</a>
					</div>
					<input type="hidden" name="assess.datagrid" id="datagrid"></input>
					<input type="hidden"  id="userid"></input>
					<input type="hidden"  id="d_starttime"></input>
					<input type="hidden"  id="d_endtime"></input>
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
				onDblClickRow:onDblClickRow,
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
						<th data-options="field:'higherscore',width:'10%',align:'center'">
							领导打分
						</th>
						<th data-options="field:'peerscore',width:'10%',align:'center'">
							同级打分
						</th>
						<th data-options="field:'reductionscore',width:'10%',align:'center'">
							考勤扣分
						</th>
						<th data-options="field:'finalscore',width:'10%',align:'center'">
							最后得分
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<!-- 弹出窗口 -->
		<div id="dlg" class="easyui-dialog" title="评分明细" style="width: 800px; height: 500px; padding: 10px;"
			data-options="
					iconCls: 'icon-search',
					toolbar: '#dlg-toolbar',
					resizable:true,
					modal:true,
					buttons: '#dlg-buttons'
				">
			<table id="dlgdg" title="评分明细记录" style="witdh: 100%; height: 50%"
				data-options="
				rownumbers:true,
				autoRowHeight:false,
				fitColumns:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:10"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'assesstype',width:'10%',align:'center',formatter:formatterAssess">
							考核类型
						</th>
						<th data-options="field:'assessyear',width:'10%',align:'center'">
							考核年度
						</th>
						<th data-options="field:'deptid',width:'15%',align:'center',formatter:formatterDept">
							部门
						</th>
						<th data-options="field:'userid',width:'10%',align:'center',formatter:formatterUser">
							被考核人
						</th>
						<th data-options="field:'starttime',width:'15%',align:'center',formatter:formatterdateYMD">
							开始日期
						</th>
						<th data-options="field:'endtime',width:'15%',align:'center',formatter:formatterdateYMD">
							终止日期
						</th>
						<th data-options="field:'assesser',width:'10%',align:'center',formatter:formatterUser">
							考核人
						</th>
						<th data-options="field:'finalscore',width:'8%',align:'center'">
							得分
						</th>
					</tr>
				</thead>
			</table> 
			<table id="dlgdg1" title="扣分明细记录" style="witdh: 100%; height: 50%"
				data-options="
				rownumbers:true,
				autoRowHeight:false,
				fitColumns:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:10"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'clockdate',width:'15%',align:'center',formatter:formatterdateYMD">
							考勤时间
						</th>
						<th data-options="field:'userid',width:'10%',align:'center',formatter:formatterUser">
							被考核人
						</th>
						<th data-options="field:'exptype',width:'15%',align:'center',formatter:formatterExcep">
							异常类型
						</th>
						<th data-options="field:'reductionscore',width:'10%',align:'center'">
							考勤扣分
						</th>
					</tr>
				</thead>
			</table> 
		</div>
		
		
	</body>
</html>
