<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>

		<title>咸宁市人社局平时考核系统</title>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/diary/approve/js/diaryApp.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>

		<form id="diaryDailyForm" method="post"> 
			<div id="commDesc" class="easyui-panel" style="width: 40%; height: 20%; position: absolute; z-index: 8;">
			</div>
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							日志类型：
						</label>
						<input class="easyui-combobox" type="text" id="diarytype"  editable="false"  name="diaryDaily.diarytype" />
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="diaryDaily.starttime" id="starttime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="diaryDaily.endtime" id="endtime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<input type="hidden" name="diaryDaily.diaryid" id="diaryid"></input>
						<input type="hidden" id="userid"></input>
						<input type="hidden" id="nowdiarytype"></input>
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;margin-left: 5px;">查询</a>
					</div>
				</div>
			</div> 

			<!-- 弹出窗口 -->
			<div id="dlg" class="easyui-dialog" title="日志填报申请审核" style="width: 700px; height: 600px; padding: 10px;"
				data-options="
					iconCls: 'icon-search',
					toolbar: '#dlg-toolbar',
					resizable:true,
					modal:true,
					buttons: '#dlg-buttons'
				">
				<div class="easyui-panel" style="width: 100%; margin-top: 2px;">
					<div style="margin: 0px; height: 45%;">
						<label id="t_label" style="margin: 10px;">
							本周总结:
						</label>
						<input name="diaryDaily.content" id="content" class="easyui-textbox"
							data-options="disabled:true,readonly:true,multiline:true,required:true" style="width: 70%; height: 100%;"></input>
					</div>
					<div id="nextCont" style="margin: 0px; height: 25%;">
						<label style="margin: 10px;">
							下期计划:
						</label>
						<input name="diaryDaily.nextcontent" id="nextcontent" class="easyui-textbox"
							data-options="disabled:true,readonly:true,multiline:true,required:true" style="width: 70%; height: 100%;"></input>
					</div>
					<div style="margin: 0px; height: 20%;">
						<label style="margin: 10px;">
							核审意见:
						</label>
						<input name="diaryDaily.comments" id="comments" class="easyui-textbox" data-options="multiline:true" style="width: 70%; height: 100%;"></input>
					</div>
					<div style="margin-top: 5px; height: 5%;">
						<label style="margin: 10px;">
							核审状态:
						</label>
						<input class="easyui-combobox"  editable="false"  type="text" id="status"  editable="false"  name="diaryDaily.status" style="margin-right: 5px;"/>
						<a onclick="save();" href="#" class="easyui-linkbutton c5" style="width: 80px;">保存</a>
					</div>
				</div>

			</div>
			
			<div class="easyui-panel" style="margin-top: 2px; width: 100%; height: 70%">
				<div align="left">
				</div>
				<table id="dg" title="日志申请记录" style="with: 100%; height: 100%"
					data-options="
					ctrlSelect:true,
					onLoadSuccess:onLoadSuccess,
					onDblClickRow:onDblClickRow,
					rownumbers:true,
					striped:true,
					autoRowHeight:false,
					pagination:true,
					fit:true,
					pageList:[5,10,15,20,25,30],
					pageSize:15"
					align="left">
					<thead>
						<tr>
							<th data-options="field:'diarydate',width:'8%',align:'center',formatter:formatterdateYMD">
								申报日期
							</th>
							<th data-options="field:'status',width:'6%',align:'center',formatter:formatterStatus">
								申报状态
							</th>
							<th data-options="field:'diarytype',width:'6%',align:'center',formatter:formatterDiary">
								日志类型
							</th>
							<th data-options="field:'starttime',width:'8%',align:'center',formatter:formatterdateYMD">
								开始日期
							</th>
							<th data-options="field:'endtime',width:'8%',align:'center',formatter:formatterdateYMD">
								终止日期
							</th>
							<th data-options="field:'content',width:'20%',align:'center'">
								日志内容
							</th>
							<th data-options="field:'nextcontent',width:'20%',align:'center'">
								下期计划
							</th>
							<th data-options="field:'comments',width:'15%',align:'center'">
								审核意见
							</th>
							<th data-options="field:'userid',width:'8%',align:'center',formatter:formatterUser">
								申请人
							</th>
							<th data-options="field:'diaryid',width:'0%',align:'center',hidden:'true'">
								请假ID
							</th>
						</tr>
					</thead>
				</table>
		</div>
			
		</form>
		


	</body>
</html>
