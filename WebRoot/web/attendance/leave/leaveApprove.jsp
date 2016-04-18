<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>

		<title>咸宁市人社局平时考核系统</title>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/attendance/leave/js/leaveApp.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
	
		<form id="leaveForm">
		<div id="commDesc"  class="easyui-panel" style="width: 10%;height: 80%;position:absolute;z-index:8;">
		</div>
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							假期类型：
						</label>
						<input class="easyui-combobox" type="text"  editable="false"  id="leavetype" name="leaves.leavetype" />
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="leaves.startdate" id="startdate" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="leaves.enddate" id="enddate" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
							<input type="hidden" id="leaveid"></input>
							<input type="hidden"  id="nowstatus"></input>
							<input type="hidden"  id="nowleavetype"></input>
							&nbsp;&nbsp;
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;">查询</a>
					</div> 
				</div>
			</div>
		</form> 
		<div style="width: 100%; height: 70%">
			<div align="left">
			</div>
			<table id="dg" title="请假登记信息记录" style="with: 100%; height: 100%"
				data-options="
				ctrlSelect:true,
				onLoadSuccess:onLoadSuccess,
				onDblClickRow:onDblClickRow,
				rownumbers:true,
				striped:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'userid',width:'8%',align:'center',formatter:formatterUser">
							填报人
						</th>
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
						<th data-options="field:'enddate',width:'10%',align:'center',formatter:formatterdateYMD">
							终止日期
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
						<th data-options="field:'leaveid',width:'10%',align:'center',hidden:'true'">
							请假ID
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
							<th data-options="field:'appdate',width:'12%',align:'center',formatter:formatterdateYMD">
								申报日期
							</th>
							<th data-options="field:'status',width:'12%',align:'center',formatter:formatterStatus">
								申报状态
							</th>
							<th data-options="field:'leavetype',width:'10%',align:'center',formatter:formatterLeave">
								假期类型
							</th>
							<th data-options="field:'startdate',width:'12%',align:'center',formatter:formatterdateYMD">
								开始日期
							</th>
							<th data-options="field:'enddate',width:'12%',align:'center',formatter:formatterdateYMD">
								终止日期
							</th>
							<th data-options="field:'approverid',width:'8%',align:'center',formatter:formatterUser">
								审核人
							</th>
							<th data-options="field:'destination',width:'10%',align:'center'">
								目的地
							</th>
							<th data-options="field:'comments',width:'20%',align:'center',formatter:datagridcell">
								请假原因
							</th>
							<th data-options="field:'userid',width:'10%',align:'center',hidden:'true'">
								用户ID
							</th>
							<th data-options="field:'approveid',width:'10%',align:'center',hidden:'true'">
								审核ID
							</th>
						</tr>
					</thead>
				</table>
				<div style="position: absolute; left: 20px; top: 240px;">
					<div class="label_css">
						<label for="approver">
							下步审批人员 :
						</label>
						<input class="easyui-combobox" type="text" id="approver" name="approver"  editable="false"  />
						<label for="status">
							审批状态 :
						</label>
						<input class="easyui-combobox" type="text" id="status" name="status"   editable="false" />
					</div> 
					<div class="label_css">
						<label for="comments">
							审核意见 :
						</label>
						<input name="clockRecords.comments" id="comments" class="easyui-textbox" data-options="multiline:true,value:'审批同意'"
							style="width: 500px; height: 100px;"></input>
					</div>
					<div class="label_css">
						<a onclick="save()" href="#" class="easyui-linkbutton c5" style="width: 80px">传递</a>
					</div>
				</div>
			</div>
	</body>
</html>
