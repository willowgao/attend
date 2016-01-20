<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>咸宁市人社局平时考核系统</title>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/attendance/timeManage/js/timeManage.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form id="clockSettingForm">
			<div class="easyui-panel" title="时间管理信息录入" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							开始时间：
						</label>
						<input name="clockSetting.startTime" id="startTime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止时间：
						</label>
						<input name="clockSetting.endTime" id="endTime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
					</div>
				</div>
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							上班时间：
						</label>
						<input name="clockSetting.amsbTime" id="amsbTime" class="easyui-timespinner"
							data-options="min:'08:00',max:'09:00',showSeconds:true,value:'08:00'"></input>
						<label style="margin: 10px;">
							下班时间：
						</label>
						<input name="clockSetting.amxbTime" id="amxbTime" class="easyui-timespinner"
							data-options="min:'11:30',max:'12:30',showSeconds:true,value:'12:00'"></input>
						<label style="margin: 10px;">
							上班时间：
						</label>
						<input name="clockSetting.pmsbTime" id="pmsbTime" class="easyui-timespinner"
							data-options="min:'13:30',max:'14:30',showSeconds:true,value:'13:30'"></input>
						<label style="margin: 10px;">
							下班时间：
						</label>
						<input name="clockSetting.pmxbTime" id="pmxbTime" class="easyui-timespinner"
							data-options="min:'17:00',max:'18:00',showSeconds:true,value:'17:00'"></input>
						<div class="label_css">
							<a  onclick="save()" href="#" class="easyui-linkbutton c5" style="width: 80px">保存</a>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div style="width: 100%; height: 70%">
			<div align="left">
			</div>
			<table id="dg" title="时间管理信息列表" style="width: 100%; height: 100%"
				data-options="
				rownumbers:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr> 
						<th data-options="field:'startTime',width:'12%',align:'center',formatter : formatterdate">
							开始时间
						</th>
						<th data-options="field:'endTime',width:'12%',align:'center',formatter : formatterdate">
							终止时间
						</th>
						<th data-options="field:'isEnable',width:'6%',align:'center',formatter:foratterEnable">
							是否有效
						</th>
						<th data-options="field:'amsbTime',width:'10%',align:'center'">
							上午上班时间
						</th>
						<th data-options="field:'amxbTime',width:'10%',align:'center'">
							上午下班时间
						</th>
						<th data-options="field:'pmsbTime',width:'10%',align:'center'">
							下午上班时间
						</th>
						<th data-options="field:'pmxbTime',width:'10%',align:'center'">
							下午下班时间
						</th>
					</tr>
				</thead>
			</table>
		</div>
	</body>
</html>
