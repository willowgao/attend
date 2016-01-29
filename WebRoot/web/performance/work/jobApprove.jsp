<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>

		<title>咸宁市人社局平时考核系统</title>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/work/js/jobApp.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>

		<form id="jobAssignmentForm">
			<div id="commDesc" class="easyui-panel" style="width: 30%;height:60%; position: absolute; z-index: 8;">
			</div>
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="jobAssignment.starttime" id="starttime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="jobAssignment.endtime" id="endtime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<input type="hidden" id="approveid"></input>
						<input type="hidden" id="jobname"></input>
						<input type="hidden" id="starttime"></input>
						<input type="hidden" id="endtime"></input>
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin-left: 5px;">查询</a>
					</div>
				</div>
			</div>
		
		</form>
		<div class="easyui-panel"  style="width: 100%; height: 75%">
			<div align="left">
			</div>
			<table id="dg" title="工作任务申报记录" style="width: 100%; height: 100%"
				data-options="
				onLoadSuccess:onLoadSuccess,
				onDblClickRow:onDblClickRow,
				rownumbers:true,
				collapsible:true,
				autoRowHeight:false,
				striped:true,
				fit:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'appdate',width:'10%',align:'center',formatter :formatterdateYMD">
							申报日期
						</th>
						<th data-options="field:'status',width:'10%',align:'center',formatter:formatterStatus">
							申报状态
						</th>
						<th data-options="field:'jobname',width:'10%',align:'center'">
							工作单名称
						</th>
						<th data-options="field:'starttime',width:'10%',align:'center',formatter:formatterdateYMD">
							开始日期
						</th>
						<th data-options="field:'endtime',width:'10%',align:'center',formatter:formatterdateYMD">
							终止日期
						</th>
						<th data-options="field:'userid',width:'10%',align:'center',formatter:formatterUser">
							申报人
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
				fitColumns:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:5"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'jobtype',align:'center',formatter:formatterJob">
							工作类型
						</th>
						<th data-options="field:'status',align:'center',formatter:formatterStatus,hidden:'true'">
							申报状态
						</th> 
						<th data-options="field:'comments',align:'center'">
							工作任务
						</th>
						<th data-options="field:'executiver',align:'center',formatter:formatterUser">
							执行人
						</th>
						<th data-options="field:'starttime',align:'center',formatter:formatterdateYMD">
							开始日期
						</th>
						<th data-options="field:'endtime',align:'center',formatter:formatterdateYMD">
							终止日期
						</th>
						<th data-options="field:'plantime',align:'center'">
							计划工时
						</th>
						<th data-options="field:'worktime',align:'center',editor:'textbox',hidden:'true'">
							实际工时
						</th>
						<th data-options="field:'userid',align:'center',formatter:formatterUser,editor:'textbox',hidden:'true'">
							填报人
						</th>
						<th data-options="field:'approver',align:'center',formatter:formatterUser,editor:'textbox',hidden:'true'">
							审核人
						</th>
						<th data-options="field:'appcomments',align:'center',editor:'textbox',hidden:'true'">
							审核意见
						</th>
						<th data-options="field:'appdate',align:'center',formatter:formatterdateYMD,editor:'datebox',hidden:'true'">
							审核时间
						</th>
						<th data-options="field:'jobid',align:'center',hidden:'true'">
							工作ID
						</th>
					</tr>
				</thead>
			</table>
			<div style="position: absolute; left: 20px; top: 240px;">
				<div class="label_css">
					<label for="comments">
						审核意见 :
					</label>
					<input name="jobAssignment.comments" id="comments" class="easyui-textbox" data-options="multiline:true,value:'同意工作安排'"
						style="width: 500px; height: 100px;"></input>
				</div>
				<div class="label_css">
					<label for="status">
						审批状态 :
					</label>
					<input class="easyui-combobox" id="status" name="jobAssignment.status" />
					<a onclick="save()" href="#" class="easyui-linkbutton c5" style="width: 80px">传递</a>
				</div> 
			</div>
		</div>
	</body>
</html>
