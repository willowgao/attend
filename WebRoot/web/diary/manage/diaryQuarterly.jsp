<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/diary/manage/js/diarydaily.js"></script>
		<title>查询</title>
	</head>
	<body>
		<form id="diaryDailyForm">
			<div class="easyui-panel" title="填报信息">
				<div style="margin-top: 2px; margin-bottom: 2px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							填报周期：
						</label>
						<input id="starttime"   name="diaryDaily.starttime"class="easyui-datebox" value="<%=DateUtil.getQuarterFirstMonth(sysdate)%>"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						</td>	
						<label style="margin: 10px;">
							至：
						</label>
						<input id="endtime"   name="diaryDaily.endtime"class="easyui-datebox" value="<%=DateUtil.getQuarterLastDay(sysdate)%>"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						</td>

						<label style="margin: 10px;">
							审批人员：
						</label>
						<input class="easyui-combobox" type="text" id="approver" name="diaryDaily.approverid" data-options="required:true" />
						<input type="hidden" id="diarytype" name="diaryDaily.diarytype"  value="<%=request.getParameter("type") %>"></input>
					</div>
					<div style="margin: 10px;">
							<label style="margin: 10px;">
								审批状态：
							</label>
							<input class="easyui-combobox" type="text" id="status" name="diaryDaily.status" data-options="readonly:true,disabled:true" />
							 
						<label style="margin: 10px;">
							晒业绩：
						</label>
						<input id="onsubmitBtn" class="easyui-switchbutton" checked data-options="onText:'晒',offText:'不晒'"> 
							<a onclick="save()" href="#" class="easyui-linkbutton c5" style="margin-left:10px;width: 80px">保存</a>
							<a onclick="queryHistory()" href="#" class="easyui-linkbutton c5" style="margin-left:10px;width: 80px">查询历史</a>
						
						<input type="hidden" id="onsubmit"  name="diaryDaily.onsubmit"  />
	
						</div>
					</div>
				</div>
			</div>
			<div class="easyui-panel" style="width: 100%;margin-top: 2px; ">
				<div style="margin: 0px; height: 32%;">
					<label style="margin: 10px;">
						季度总结:
					</label>
					<input name="diaryDaily.content" id="content" class="easyui-textbox" data-options="multiline:true,required:true"
						style="width: 70%; height: 100%;"></input>
				</div>
				<div style="margin: 0px; height: 32%;">
					<label style="margin: 10px;">
						下季计划:
					</label>
					<input name="diaryDaily.nextcontent" id="nextcontent" class="easyui-textbox" data-options="multiline:true,required:true"
						style="width: 70%; height: 100%;"></input>
				</div>
				<div style="margin: 0px; height: 15%;">
					<label style="margin: 10px;">
						核审意见:
					</label>
					<input name="diaryDaily.comments" id="comments" class="easyui-textbox" data-options="disable:true,readonly:true,multiline:true"
						style="width: 70%; height: 100%;"></input>
				</div>
			</div> 
			<!-- 历史弹出窗口 -->
			<div id="dlg" class="easyui-dialog" title="历史日志信息查询" style="width: 900px; height: 500px; padding: 10px;"
				data-options="
					iconCls: 'icon-search',
					toolbar: '#dlg-toolbar',
					resizable:true,
					modal:true,
					buttons: '#dlg-buttons',
					onClose:closeDialog
				">
				<div style="margin: 10px;">
						<label style="margin: 10px;">
							填报时间：
						</label>
						<input id="starttime" name="diaryDaily.starttime"class="easyui-datebox" value="<%=DateUtil.getFirstDateOfMonth(DateUtil.date2String(sysdate,DateUtil.FORMAT_MONTH))%>"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"  ></input>
						</td>
						<label style="margin: 10px;">
							至：
						</label>
						<input id="endtime"  name="diaryDaily.endtime"class="easyui-datebox" value="<%=DateUtil.getLastDateOfMonth(DateUtil.date2String(sysdate,DateUtil.FORMAT_MONTH))%>"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser" ></input>
						</td>
						&nbsp;&nbsp;
							<a onclick="query()" href="#" class="easyui-linkbutton c5" style="width: 80px">查询</a>
				</div>
				<table id="dlgdg" title="历史日志信息" style="with: 100%; height: 80%"
					data-options="
				rownumbers:true,
				autoRowHeight:false,
				onLoadSuccess:onLoadSuccess,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:5"
					align="left">
					<thead>
						<tr>
							<th data-options="field:'diarydate',width:'16%',align:'center',formatter:formatterdate">
								填报日期
							</th>
							<th data-options="field:'status',width:'8%',align:'center',formatter:formatterStatus">
								审核状态
							</th>
							<th data-options="field:'content',width:'20%',align:'center'">
								季度总结
							</th>
							<th data-options="field:'nextcontent',width:'20%',align:'center'">
								下季计划
							</th>
							<th data-options="field:'starttime',width:'20%',align:'center',formatter:formatterdateYMD">
								开始时间
							</th>
							<th data-options="field:'endtime',width:'20%',align:'center',formatter:formatterdateYMD">
								终止时间
							</th>
							<th data-options="field:'comments',width:'15%',align:'center'">
								领导审核意见
							</th>
							<th data-options="field:'approverid',width:'10%',align:'center',formatter:formatterUser">
								审核人
							</th> 
							<th data-options="field:'viewcount',width:'10%',align:'center'">
								浏览记录数
							</th> 
							<th data-options="field:'leaveid',width:'10%',align:'center',hidden:'true'">
								日志ID
							</th>
						</tr>
					</thead>
				</table>
			</div>
			
			<!-- 悬浮窗口 -->
			<div id="commDesc"  class="easyui-panel" style="width: 40%;height: 20%;position:absolute;z-index:8;">
		</form>
	</body>
</html>
