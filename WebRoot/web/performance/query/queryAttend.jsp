<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>

		<title>咸宁市人社局平时考核系统</title>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/query/js/queryAttend.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form id="clockRecordsForm" method="post">
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							部门：
						</label>
						<input class="easyui-combobox" type="text" id="dept" name="clockRecords.dept" />
						<label style="margin: 10px;">
							考勤日期：
						</label>
						<input name="clockRecords.clockdate" id="clockdate" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<a onclick="query();" href="#" class="easyui-linkbutton c5" style="width: 80px; margin-left: 5px;">查询</a>
					</div>
				</div>
			</div>
		</form>
			
			<div class="easyui-panel" style="margin-top: 2px; width: 50%; height: 80%">
				<table id="dg" title="考勤记录" style="width: 100%; height: 100%"
					data-options="
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
							<th data-options="field:'userid',width:'15%',align:'center',formatter :formatterUser">
								用户ID
							</th>
							<th data-options="field:'clockdate',width:'15%',align:'center',formatter :formatterdateYMD">
								考勤日期
							</th>
							<th data-options="field:'amsb',width:'15%',align:'center',styler:dgCellStyleAmsb">
								上午上班时间
							</th>
							<th data-options="field:'amxb',width:'15%',align:'center',styler:dgCellStyleAmxb">
								上午下班时间
							</th>
							<th data-options="field:'pmsb',width:'15%',align:'center',styler:dgCellStylePmsb">
								下午上班时间
							</th>
							<th data-options="field:'pmxb',width:'15%',align:'center',styler:dgCellStylePmxb">
								下午下班时间
							</th>
							<th data-options="field:'amsbtime',width:'10%',align:'center',hidden:'true'">
								标准上午上班时间
							</th>
							<th data-options="field:'amxbtime',width:'10%',align:'center',hidden:'true'">
								标准上午下班时间
							</th>
							<th data-options="field:'pmsbtime',width:'10%',align:'center',hidden:'true'">
								标准下午上班时间
							</th>
							<th data-options="field:'pmxbtime',width:'10%',align:'center',hidden:'true'">
								标准下午下班时间
							</th>
						</tr>
					</thead>
				</table>
			</div>
		<div id="bar" style="position: absolute; left: 670px; top: 81px; width: 49%; height: 40%; border: 1px solid #dddddd; margin: 10px auto;"></div>
		<div id="pie" style="position: absolute; left: 670px; top: 340px; width: 49%; height: 40%; border: 1px solid #dddddd; margin: 10px auto;"></div>
	</body>
</html>
