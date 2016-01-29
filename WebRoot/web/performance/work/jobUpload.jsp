<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/work/js/jobUpload.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form id="jobAssignmentForm" method="post">
		<div id="commDesc"  class="easyui-panel" style="width: 30%;height:60%;;position:absolute;z-index:8;">
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
						<input name="jobAssignment.endtime" id="endtime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin-left: 5px;">查询</a>
					</div>
					<input type="hidden" name="jobAssignment.datagrid" id="datagrid"></input>
				</div>
			</div>
		</form>
		<div id="tb" style="height: auto">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save();">保存</a>
		</div>
		<div style="width: 100%; height: 75%">
			<div align="left">
			</div>
			<table id="dg" title="工作任务记录" style="with: 100%; height: 100%"
				data-options="
				toolbar: '#tb', 
				onDblClickCell:onClickCell,
				onLoadSuccess:onLoadSuccess,
				rownumbers:true,
				collapsible:true, 
				autoRowHeight:false,
				striped:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'jobtype',width:'8%',align:'center',formatter:formatterJob">
							工作类型
						</th>
						<th data-options="field:'status',width:'10%',align:'center',formatter:formatterStatus,hidden:'true'">
							申报状态
						</th> 
						<th data-options="field:'comments',width:'20%',align:'center'">
							工作任务
						</th>
						<th data-options="field:'executiver',width:'6%',align:'center',formatter:formatterUser">
							执行人
						</th>
						<th data-options="field:'starttime',width:'8%',align:'center',formatter:formatterdateYMD">
							开始日期
						</th>
						<th data-options="field:'endtime',width:'8%',align:'center',formatter:formatterdateYMD">
							终止日期
						</th>
						<th data-options="field:'plantime',width:'5%',align:'center'">
							计划工时
						</th>
						<th data-options="field:'worktime',width:'5%',align:'center',editor:{
							type:'textbox',
							options:{required:true}
						}">
							实际工时
						</th>
						
						<th data-options="field:'progress',width:'8%',align:'center',editor:{
							type:'textbox',
							options:{required:true}
						}">
							工作进度(%)
						</th>
						<th data-options="field:'filltime',width:'6%',align:'center',formatter:formatterdateYMD"">
							填报时间
						</th> 
						<th data-options="field:'userid',width:'6%',align:'center',formatter:formatterUser">
							填报人
						</th> 
						<th data-options="field:'approver',width:'6%',align:'center',formatter:formatterUser,editor:'textbox',hidden:'true'">
							审核人
						</th>
						<th data-options="field:'appcomments',width:'15%',align:'center',editor:'textbox',hidden:'true'">
							审核意见
						</th>
						<th data-options="field:'appdate',width:'8%',align:'center',formatter:formatterdateYMD,editor:'datebox',hidden:'true'">
							审核时间
						</th>
						<th data-options="field:'jobid',width:'10%',align:'center',hidden:'true'">
							工作ID
						</th>
					</tr>
				</thead>
			</table>
		</div>
	</body>
</html>
