<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>

		<title>咸宁市人社局平时考核系统</title>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/attendance/leave/js/leave.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
	
		<form id="leaveForm" method="post">
		<div id="commDesc"  class="easyui-panel" style="width: 10%;height: 40%;position:absolute;z-index:8;">
		</div>
			<div class="easyui-panel" title="请假信息录入" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							审批人员：
						</label>
						<input class="easyui-combobox" type="text" id="approver" name="leaves.approverid" data-options="required:true" />
						<label style="margin: 10px;">
							假期类型：
						</label>
						<input class="easyui-combobox" type="text" id="leavetype" name="leaves.leavetype" data-options="required:true" />
						<label style="margin: 10px;">
							&nbsp; 目  的 地：
						</label>
						<input type="hidden" id="leaveid"></input>
						<input class="easyui-textbox" type="text" id="destination" name="leaves.destination" />
					</div>
				</div>
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="leaves.startdate" id="startdate" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser,required:true"></input>
						<label style="margin: 10px;">
							开始时间：
						</label>
						<input name="leaves.starttime" id="starttime" class="easyui-timespinner"
							data-options="min:'08:00',max:'09:00',showSeconds:true,value:'08:00',required:true"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="leaves.enddate" id="enddate" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser,required:true"></input>
						<label style="margin: 10px;">
							终止时间：
						</label>
						<input name="leaves.endtime" id="endtime" class="easyui-timespinner"
							data-options="min:'17:00',max:'18:00',showSeconds:true,value:'17:00',required:true"></input>
					</div>
					
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							请假原因 :
						</label>
						<input name="leaves.comments" id="comments" class="easyui-validatebox" data-options="multiline:true,required:true"
							style="width: 500px; height: 50px;"></input>
					</div>
				</div>
					<div align="center">
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin: 10px;">查询</a>
						<a onclick="save();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin: 10px;">保存</a>
					</div>
				</div>
			</div>
		</form>

		<div id="tb" style="height: auto">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del();">删除</a>
		</div>
		<div style="width: 100%; height: 60%">
			<div align="left">
			</div>
			<table id="dg" title="请假登记信息记录" style="with: 100%; height: 100%"
				data-options="
				toolbar: '#tb',
				ctrlSelect:true,
				onSelect:onSelect,
				onUnselect:onUnselect,
				onLoadSuccess:onLoadSuccess,
				onDblClickRow:onDblClickRow,
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
						<th data-options="field:'appdate',width:'10%',align:'center',formatter:formatterdateYMD">
							申报日期
						</th>
						<th data-options="field:'status',width:'10%',align:'center',formatter:formatterStatus">
							申报状态
						</th>
						<th data-options="field:'leavetype',width:'10%',align:'center',formatter:formatterLeave">
							假期类型
						</th>
						<th data-options="field:'startdate',width:'10%',align:'center',formatter:formatterdateYMD">
							开始日期
						</th>
						<th data-options="field:'starttime',width:'8%',align:'center'">
							开始时间
						</th>
						<th data-options="field:'enddate',width:'10%',align:'center',formatter:formatterdateYMD">
							终止日期
						</th>
						<th data-options="field:'endtime',width:'8%',align:'center'">
							终止时间
						</th>
						<th data-options="field:'approverid',width:'8%',align:'center',formatter:formatterUser">
							审核人
						</th>
						<th data-options="field:'destination',width:'10%',align:'center'">
							目的地
						</th>
						<th data-options="field:'comments',width:'15%',align:'center'">
							请假原因
						</th>
						<th data-options="field:'userid',width:'10%',align:'center',hidden:'true'">
							用户ID
						</th>
						<th data-options="field:'leaveid',width:'10%',align:'center',hidden:'true'">
							请假ID
						</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<!-- 弹出窗口 -->
			<div id="dlg" class="easyui-dialog" title="请假登记申请审核信息" style="width: 800px; height: 500px; padding: 10px;"
				data-options="
					iconCls: 'icon-search',
					toolbar: '#dlg-toolbar',
					resizable:true,
					modal:true,
					buttons: '#dlg-buttons'
				">
				<table id="dlgdg" title="请假审核记录" style="with: 100%; height: 80%"
					data-options="
				rownumbers:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:5"
					align="left">
					<thead>
						<tr>
							<th data-options="field:'appdate',width:'20%',align:'center',formatter:formatterdate">
								审核日期
							</th>
							<th data-options="field:'status',width:'12%',align:'center',formatter:formatterStatus">
								申报状态
							</th>
							<th data-options="field:'leavetype',width:'10%',align:'center',formatter:formatterLeave">
								假期类型
							</th> 
							<th data-options="field:'userid',width:'10%',align:'center',formatter:formatterUser">
								审核人
							</th> 
							<th data-options="field:'comments',width:'20%',align:'center',formatter:datagridcell">
								审核意见
							</th>
							<th data-options="field:'approverid',width:'12%',align:'center',formatter:formatterUser">
								下步审核人
							</th> 
						</tr>
					</thead>
				</table>
			</div>
	</body>
</html>
