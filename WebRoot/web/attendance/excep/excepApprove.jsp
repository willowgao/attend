<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>

		<title>咸宁市人社局平时考核系统</title>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/attendance/excep/js/excepApp.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>

		<form id="expForm" >
			<div id="commDesc" class="easyui-panel" style="width: 10%; height: 80%; position: absolute; z-index: 8;">
			</div>
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="clockRecords.startTime" id="startTime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="clockRecords.endTime" id="endTime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<input type="hidden" id="expid"></input>
						<input type="hidden" id="userid"></input>
						<input type="hidden" id="clockdate"></input>
						&nbsp;&nbsp;
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;">查询</a>
					</div>
				</div>
			</div>
		</form>
		<div style="width: 100%; height: 70%">
			<div align="left">
			</div>
			<table id="dg" title="打卡异常信息记录" style="with: 100%; height: 100%"
				data-options="
				onLoadSuccess:onLoadSuccess,
				onDblClickRow:onDblClickRow,
				rownumbers:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'clockdate',width:'10%',align:'center',formatter :formatterdateYMD">
							考勤日期
						</th>
						<th data-options="field:'status',width:'10%',align:'center',formatter:formatterStatus">
							申报状态
						</th>
						<th data-options="field:'exptype',width:'10%',align:'center',formatter:formatterExcep">
							异常类型
						</th>
						<th data-options="field:'userid',width:'10%',align:'center',formatter:formatterUser">
							用户ID
						</th>
						<th data-options="field:'approverid',width:'8%',align:'center',formatter:formatterUser">
							审核人
						</th>
						<th data-options="field:'appdate',width:'10%',align:'center',formatter:formatterdateYMD">
							申报日期
						</th>
						<th data-options="field:'comments',width:'15%',align:'center'">
							异常说明
						</th>
						<th data-options="field:'expid',width:'10%',align:'center',hidden:'true'">
							异常ID
						</th>
						<th data-options="field:'approveid',width:'10%',align:'center',hidden:'true'">
							审核ID
						</th>
					</tr>
				</thead>
			</table>
		</div>

		<!-- 弹出窗口 -->
		<div id="dlg" class="easyui-dialog" title="请假登记申请审核" style="width: 800px; height: 500px; padding: 10px;"
			data-options="
					iconCls: 'icon-search',
					toolbar: '#dlg-toolbar',
					resizable:true,
					modal:true,
					buttons: '#dlg-buttons'
				">
			<table id="dlgdg" title="请假登记申请记录" style="with: 100%; height: 40%"
				data-options="
				rownumbers:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:5"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'clockdate',width:'10%',align:'center',formatter :formatterdateYMD">
							考勤日期
						</th>
						<th data-options="field:'status',width:'10%',align:'center',formatter:formatterStatus">
							申报状态
						</th>
						<th data-options="field:'exptype',width:'10%',align:'center',formatter:formatterExcep">
							异常类型
						</th>
						<th data-options="field:'userid',width:'10%',align:'center',formatter:formatterUser">
							提交人
						</th>
						<th data-options="field:'approverid',width:'8%',align:'center',formatter:formatterUser">
							审核人
						</th>
						<th data-options="field:'appdate',width:'12%',align:'center',formatter:formatterdateYMD">
							申报日期
						</th>
						<th data-options="field:'comments',width:'20%',align:'center'">
							异常说明
						</th>
						<th data-options="field:'expid',width:'10%',align:'center',hidden:'true'">
							请假ID
						</th>
						<th data-options="field:'approveid',width:'10%',align:'center',hidden:'true'">
							审核ID
						</th>
					</tr>
				</thead>
			</table>
			<div style="position: absolute; left: 20px; top: 240px;">
				<div class="label_css">
					<label for="status">
						审批状态 :
					</label>
					<input class="easyui-combobox" type="text" id="status" name="status" />
				</div>
				<div class="label_css">
					<label for="comments">
						审核意见 :
					</label>
					<input name="clockRecords.comments" id="comments" class="easyui-textbox" data-options="multiline:true"
						style="width: 500px; height: 100px;"></input>
				</div>
				<div class="label_css">
					<a onclick="save()" href="#" class="easyui-linkbutton c5" style="width: 80px">传递</a>
				</div>
			</div>
		</div>
	</body>
</html>
