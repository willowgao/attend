<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/query/js/queryAttends.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
		<title>查询</title>
	</head>
	<body>
		<form id="clockRecordsForm">
			<div class="easyui-panel" title="查询条件" style="width: 100%">
				<div style="margin-top: 10px; margin-bottom: 10px;position: relative">
				
					<div style="margin: 10px;">
						<label >
							人员：
						</label>
						<input class="easyui-combobox" type="text" id="userid" name="clockRecords.userid" data-options="required:true"  />
						<label style="margin: 10px;">
							开始日期：
						</label>
						<input name="clockRecords.startTime" id="startTime" class="easyui-datebox"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							终止日期：
						</label>
						<input name="clockRecords.endTime" id="endTime" class="easyui-datebox" data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						&nbsp;&nbsp;
						<a onclick="query()" href="#" class="easyui-linkbutton c5" style="width: 80px">查询</a>
						<input name="clockRecords.userid" id="userid" type="hidden" />
						<input name="clockRecords.clockdate" id="clockdate" type="hidden" />
						<input name="clockRecords.isneed" id="isneed" type="hidden" />
						<input name="clockRecords.flag" id="flag" type="hidden" />
						
						
					
					</div>
					
					
					<div style="margin: 10px;">
						<div style="margin: 10px;float:left">
							异常打卡
						</div>
						<div style="margin: 10px;background-color: #E1643D;width: 40px;height: 20px;float:left"></div>
						<div  style="margin: 10px;float:left">
							未打卡
						</div> 
						<div style="margin: 10px;background-color: #F5BB2A;width: 40px;height: 20px;float:left"></div>
						<div style="margin: 10px;float:left">
							假期
						</div> 
							<div style="margin: 10px;background-color: #CBE8F1;width: 40px;height: 20px;float:left"></div>
					</div>
				</div>
			</div>
		</form>
			<div class="easyui-panel" style="width: 100%; height: 78%">
				<div align="left">
				</div>
				<table id="dg" title="考勤异常记录" style="width: 100%; height: 100%"
					data-options="
				rownumbers:true,
				autoRowHeight:false,
				pagination:true,
				rowStyler:setRowColor,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
					align="left">
					<thead>
						<tr>
							<th data-options="field:'clockdate',width:'10%',align:'center',formatter :formatterdateYMD">
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
							<th data-options="field:'isneed',width:'10%',align:'center',hidden:'true'">
								是否有效
							</th>
							<th data-options="field:'userid',width:'10%',align:'center',hidden:'true'">
								用户ID
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
							<th data-options="field:'flag',width:'10%',align:'center',hidden:'true'">
								标志
							</th>
						</tr>
					</thead>
				</table>
			</div>
	</body>
</html>
