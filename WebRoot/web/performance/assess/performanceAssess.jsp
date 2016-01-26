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
		<div title="用户列表信息" class="easyui-panel" style="width: 300px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
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
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb" style="height: auto">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save();">保存</a>
		</div>
		<div   class="easyui-panel" style="width: 1025px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:302,top:1}">
			
			
			<table id="indextb" class="easyui-datagrid" title="考核明细打分" style="with: 100%; height: 100%"
				data-options="toolbar: '#tb', 
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
							data-options="field:'indexScore',width:'10%',align:'center',editor:{type:'textbox',
							options:{required:true,multiline:true,height:35,validType:['length[0,50]']}
						}">
							分值
						</th>
						<th data-options="field:'indexid',width:'10%',align:'center',hidden:'true'">
							ID
						</th>
					</tr>
				</thead>
			</table>
		</div>

		<form id="indexForm" method="post">
		</form>
		
		
	</body>
</html>
