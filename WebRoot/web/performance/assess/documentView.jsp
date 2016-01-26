<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/assess/js/view.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<div class="easyui-panel" style="width: 100%">
			&nbsp;&nbsp;
			<a onclick="query()" href="#" class="easyui-linkbutton c5" style="width: 80px">查看</a>
		</div>
		<!-- 弹出窗口 -->
		<div id="dlg" class="easyui-dialog" title="日志详细信息" style="width: 900px; height: 600px; padding: 10px;"
			data-options="
					iconCls: 'icon-search',
					resizable:true,
					modal:true,
				">
			<iframe align="left" id='diary' scrolling='auto' frameborder='0' style='width: 100%; height: 100%;'></iframe>
		</div>

		<form id="indexForm" method="post">
		</form>


	</body>
</html>
