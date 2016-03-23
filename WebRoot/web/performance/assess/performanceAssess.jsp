<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/assess/js/assess.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form id="assessForm">
			<div class="easyui-panel" title="部门用户信息" style="width: 300px; height: 82px;"
				data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
				<div style="margin: 15px;">
					<label >
						部门：
					</label>
					<input class="easyui-combobox" type="text" id="deptid" name="assess.deptid" data-options="required:true"  />
				</div>
			</div>
			<div class="easyui-panel" style="width: 300px; height: 520px;"
				data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:84}">
				<table id="dg" class="easyui-datagrid" style="width: 100%; height: 100%"
					data-options="onClickRow:onClickRow,
					rownumbers:true,
					ctrlSelect:true,
					autoRowHeight:false,
					pagination:true,
					pageList:[5,10,15,20,25,30],
					pageSize:25"
					align="left">
					<thead>
						<tr>
							<th data-options="field:'userid',width:'8%',align:'center',hidden:'true'">
								用户ID
							</th>
							<th data-options="field:'userdeptid',width:'40%',align:'center',formatter:formatterDept">
								用户部门
							</th>
							<th data-options="field:'username',width:'40%',align:'center'">
								用户名称
							</th>
							<th data-options="field:'userorg',width:'25%',align:'center',hidden:'true'">
								组织机构
							</th>
							<th data-options="field:'roletype',width:'25%',align:'center',hidden:'true'">
							 	用户角色
							</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="easyui-panel" title="考核评分" style="width: 1025px; height: 80px; margin-top: 2px;"
				data-options="style:{position:'absolute',left:302,top:1}">
				<div style="margin-top: 2px; margin-bottom: 2px;">
					<div style="margin: 10px;">
						<label style="margin: 10px;">
							考核周期：
						</label>
						<input name="assess.starttime" id="starttime" class="easyui-datebox" value="<%=DateUtil.addIntervalOfDate(DateUtil.getFirstDateOfMonth(DateUtil.date2String(sysdate, DateUtil.FORMAT_MONTH)),  0, -1, 0, DateUtil.YMD) %>"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<label style="margin: 10px;">
							至：
						</label>
						<input name="assess.endtime" id="endtime" class="easyui-datebox" value="<%=DateUtil.addIntervalOfDate(DateUtil.getLastDateOfMonth(DateUtil.date2String(sysdate, DateUtil.FORMAT_MONTH)),  0, -1, 0, DateUtil.YMD)%>"
							data-options="formatter:fn_Dateformatter,parser:fn_DateParser"></input>
						<input type="hidden" name="assess.userid" id="userid"></input>
						<input type="hidden" name="assess.roletype" id="roletype"></input>
						<input type="hidden" name="assess.datagrid" id="datagrid"></input>
						<input type="hidden"  id="sysdate" value="<%=DateUtil.getNowDateByFormat(DateUtil.YMD) %>"></input>
						<a href="#" class="easyui-linkbutton c5" style="width: 80px;margin-left: 10px;" id="saveBtn" onclick="checkScore();">保存</a>
						<label style="margin: 10px;">
							得分：
						</label>
						<label id="score" style="color:red;font-size : 20px;font-weight: bold;font: Georgia">
					</div>
				</div>
			</div> 
			<div class="easyui-panel" style="width: 1025px; height: 520px;"
				data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:302,top:84}">
				<table id="indextb" class="easyui-datagrid"  style="with: 100%; height: 100%"
					data-options="
					rownumbers: true,
					onLoadSuccess: onLoadSuccess,
					collapsible:true,
					onDblClickCell:onClickCell,
					rowStyler:setRowStyle,
					nowrap:false,
					autoRowHeight:false,
					striped:true,
					pagination:true,
					showFooter: true,
					pageList:[5,10,15,20,25,30],
					pageSize:15">
					<thead>
						<tr>
							<th data-options="field:'item',width:'10%',align:'center'">
								考核项目
							</th>
							<th data-options="field:'itemDetail',width:'10%',align:'center'">
								评鉴指标
							</th>
							<th data-options="field:'indexContent',width:'50%',align:'left'">
								评鉴标准
							</th>
							<th
								data-options="field:'indexScore',width:'10%',align:'center'">
								满分分值
							</th>
							<th
								data-options="field:'indexscore',width:'10%',align:'center',editor:{type:'textbox',
								options:{required:true,multiline:true,height:35,validType:['length[0,50]']}
							}">
								得分
							</th>
							<th data-options="field:'indexid',width:'10%',align:'center',hidden:'true'">
								ID
							</th>
						</tr>
					</thead>
				</table>
			</div>
		</form>
	</body>
</html>
