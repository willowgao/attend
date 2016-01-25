<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/index/js/indexManage.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<div id="commDesc" class="easyui-panel" style="width: 40%; height: 10%; position: absolute; z-index: 8;">
		</div>
		<div id="tb" style="height: auto">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save();">保存</a>
		</div>
		<table id="dg" class="easyui-datagrid" title="考核指标维护" style="with: 100%; height: 90%"
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
					<th
						data-options="field:'item',width:'10%',align:'center'">
						考核项目
					</th>
					<th
						data-options="field:'itemDetail',width:'10%',align:'center'">
						评鉴指标
					</th>
					<th
						data-options="field:'indexContent',width:'50%',align:'left'">
						评鉴标准
					</th>
					<th
						data-options="field:'indexScore',width:'10%',align:'center',editor:{type:'textbox',
							options:{required:true,multiline:true,height:100,validType:['length[0,50]']}
						}">
						分值
					</th>
					<th data-options="field:'indexid',width:'10%',align:'center',hidden:'true'">
						ID
					</th>
				</tr>
			</thead>
		</table>
		
		<form id="indexForm" method="post">
		</form>
	</body>
</html>
