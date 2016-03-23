<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/query/js/queryWorks.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form id="jobAssignmentForm" method="post">
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label >
							部门：
						</label>
						<input class="easyui-combobox" type="text" id="deptid" name="job.deptid" data-options="required:true"  />
						<label style="margin: 10px;">
							填报 开始日期：
						</label>
						<input name="job.starttime" id="starttime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="job.endtime" id="endtime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin-left: 5px;">查询</a>
					</div> 
				</div>
			</div>
		</form> 
		<div style="width: 100%; height: 75%">
			<div align="left">
			</div>
			<table id="dg" title="工作任务记录" class="easyui-treegrid" style="width: 100%; height: 100%"
				data-options="
				url:'<%=webapp %>/query/queryWorks!queryWorks.action',
				idField:'id',
				treeField:'jobname', 
				rownumbers:true,
				collapsible:true, 
				animate:true,
				autoRowHeight:false,
				striped:true,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'jobname',width:'12%',align:'center'">
							工作名称
						</th>
						<th data-options="field:'jobtype',width:'8%',align:'center',formatter:formatterJob">
							工作类型
						</th>
						<th data-options="field:'comments',width:'15%',align:'center'">
							工作任务
						</th>
						<th data-options="field:'status',width:'8%',align:'center',formatter:formatterStatus">
							申报状态
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
						<th data-options="field:'worktime',width:'5%',align:'center'">
							实际工时
						</th>
						<th data-options="field:'progress',width:'8%',align:'center'">
							工作进度(%)
						</th>
						<th data-options="field:'filltime',width:'8%',align:'center',formatter:formatterdateYMD">
							填报时间
						</th> 
						<th data-options="field:'userid',width:'6%',align:'center',formatter:formatterUser">
							填报人
						</th> 
						<th data-options="field:'approver',width:'6%',align:'center',formatter:formatterUser">
							审核人
						</th> 
						<th data-options="field:'uploadtime',width:'8%',align:'center',formatter:formatterdateYMD">
							上报日期
						</th>
						<th data-options="field:'confirmtime',width:'8%',align:'center',formatter:formatterdateYMD">
							确认日期
						</th>
						
						<th data-options="field:'id',width:'10%',align:'center',hidden:'true'">
							工作ID
						</th>
						<th data-options="field:'pid',width:'10%',align:'center',hidden:'true'">
							工作ID
						</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</body>
</html>
