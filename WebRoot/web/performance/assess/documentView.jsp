<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/common.jsp"%>

<%@ include file="../../common/pluploadCommon.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/performance/assess/js/view.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
	</head>
	<body>
		<form  id="docUpload" enctype="multipart/form-data">
			<div class="easyui-panel" style="width: 100%">
				&nbsp;&nbsp;
				<a onclick="query()" href="#" class="easyui-linkbutton c5" style="width: 80px">查看</a>
				<!-- 
				<input class="easyui-filebox" style="width:400px;margin: 5px;" data-options="buttonText: '选择文件...',buttonAlign: 'right',prompt:'选择文件...',buttonIcon:'icon-search'">
				 -->
				
				 
			<a href="#" class="easyui-linkbutton"  style="width: 80px;margin: 5px;" onclick="saveFile()">保存文件</a>
			</div>
			 <div id="uploader" style="width: 35%;height: 30%">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
			<!-- 弹出窗口 -->
			<div id="dlg" class="easyui-dialog" title="日志详细信息" style="width: 900px; height: 600px; padding: 10px;"
				data-options="
						iconCls: 'icon-search',
						resizable:true,
						modal:true,
					">
				<iframe align="left" id='diary' scrolling='auto' frameborder='0' style='width: 100%; height: 100%;'></iframe>
			</div>
 		</form>


	</body>
</html>
