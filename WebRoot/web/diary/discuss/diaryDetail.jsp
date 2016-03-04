<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
		<script type="text/javascript" src="<%=webapp%>/web/diary/discuss/js/diaryDetail.js"></script>
		<title>查询</title>
	</head>
	<body>
		<form id="diaryDailyForm"> 
			<div class="easyui-panel" style="width: 100%;margin-top: 2px; ">
				<div style="height: 48%;">
					<label style="margin: 10px;">
						日志内容:
					</label>
					<input  name="diaryDaily.content" id="content" class="easyui-textbox" data-options="multiline:true,required:true,disabled:true"
						style="width: 70%; height: 100%;" ></input>
				</div>
				<div style="height: 48%;">
					<label style="margin: 10px;">
						下期计划:
					</label>
					<input name="diaryDaily.nextcontent" id="nextcontent" class="easyui-textbox" data-options="multiline:true,required:true,disabled:true"
						style="width: 70%; height: 100%;"  ></input>
				</div> 
			</div> 
		</form>
	</body>
</html>
