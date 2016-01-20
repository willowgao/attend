<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/diary/discuss/js/diaryDiscuss.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
		<title>查询</title>
	</head>
	<body>
		<form id="diaryDailyForm">
			<div id="commDesc" class="easyui-panel" style="width: 40%; height: 20%; position: absolute; z-index: 8;">
			</div>
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							日志类型：
						</label>
						<input class="easyui-combobox" type="text" id="diarytype" name="diaryDaily.diarytype" />
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="diaryDaily.starttime" id="starttime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="diaryDaily.endtime" id="endtime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						&nbsp;&nbsp;
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px;">查询</a>
						<input name="diaryDaily.content" id="content"   type="hidden"></input>
						<input name="diaryDaily.nextcontent" id="nextcontent"   type="hidden"></input>
						<input name="diaryDaily.diaryid" id="diaryid"   type="hidden"></input>
					</div>
				</div>
			</div>
			<div style="margin-top:2px; width: 100%; height: 70%"> 
				<table id="dg" title="日志信息列表" style="width: 100%; height: 100%"
					data-options="
				rownumbers:true,
				ctrlSelect:true,
				striped:true,
				autoRowHeight:false,
				sortOrder:'diarydate',
				onLoadSuccess:onLoadSuccess,
				onDblClickRow:onDblClickRow,
				sortName:'logintime',
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
					align="left">
					<thead>
						<tr>
							<th data-options="field:'diarydate',width:'8%',align:'center',formatter:formatterdateYMD">
								申报日期
							</th>
							<th data-options="field:'diarytype',width:'5%',align:'center',formatter:formatterDiary">
								日志类型
							</th>
							<th data-options="field:'starttime',width:'7%',align:'center',formatter:formatterdateYMD">
								开始日期
							</th>
							<th data-options="field:'endtime',width:'7%',align:'center',formatter:formatterdateYMD">
								终止日期
							</th>
							<th data-options="field:'content',width:'20%',align:'center'">
								日志内容
							</th>
							<th data-options="field:'nextcontent',width:'20%',align:'center'">
								下期计划
							</th>
							<th data-options="field:'viewcount',width:'5%',align:'center'">
								查阅次数
							</th>
							<th data-options="field:'commentcount',width:'5%',align:'center'">
								评论条数
							</th>
							<th data-options="field:'userid',width:'8%',align:'center',formatter:formatterUser">
								填报人
							</th>
							<th data-options="field:'diaryid',width:'0%',align:'center',hidden:'true'">
								请假ID
							</th>
						</tr>
					</thead>
				</table>
			</div>
		</form>
			<!-- 弹出窗口 -->
			<div id="dlg" class="easyui-dialog" title="日志详细信息" style="width: 900px; height: 600px; padding: 10px;"
				data-options="
					iconCls: 'icon-search',
					resizable:true,
					modal:true,
				">
			 	<iframe align="left" id ='diary' scrolling='auto' frameborder='0'  style='width:70%;height:100%;'></iframe>
				<iframe align="right" id ='discuss' scrolling='auto' frameborder='0'  style='width:30%;height:100%;'></iframe>
			 </div>
		 
	</body>
</html>
