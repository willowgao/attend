<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Date"
import="com.wgsoft.common.utils.DateUtil"%>
<link id="link_easyui_css"  rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/common/easyui/themes/metro-blue/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/common/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/common/easyui/themes/color.css">
<link id="link_easyui_layout_css" rel="stylesheet" href="<%=request.getContextPath() %>/web/common/easyui/themes/metro-blue/layout.css" type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath() %>/web/common/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/web/common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/web/common/easyui/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/web/common/css/main.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/web/common/ztree/js/jquery.ztree.core-3.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/common/ztree/js/jquery.ztree.excheck-3.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/common/ztree/js/jquery.ztree.exedit-3.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/DataDictionaryService.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/web/echarts/echarts.min.js"></script>

<!-- plupload 文件上传 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/web/common/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath() %>/web/common/plupload/js/plupload.full.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/web/common/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/web/common/plupload/js/i18n/zh-CN.js"></script>
<%
	String webapp = request.getContextPath();
	request.setAttribute("webapp", webapp);
%> 
<%
	Date sysdate = new Date();
%>

<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
