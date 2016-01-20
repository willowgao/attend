<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/ztreeCommon.jsp"%>
<HTML>
	<HEAD>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/system/menu/js/menu.js" ></script>
		<link rel="stylesheet" href="<%=webapp%>/web/common/css/label.css" type="text/css">
		<html:base target="_self" />
		<META http-equiv="Content-Style-Type" content="text/css">
		<style type="text/css">
.ztree li span.button .add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top; *
	vertical-align: middle
}
</style>
	</HEAD>
	<input type="hidden" id="menuId">
	<input type="hidden" id="parentId">
	<input type="hidden" id="menuaction">
	<body style="background-color: white; height: 100px">
		<div id="treePanel" title="菜单信息" class="easyui-panel" style="width: 230px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
			<div style="z-index: -9999;">
				<ul id="orgTree" class="ztree">
				</ul>
			</div>
		</div>
		</form>
		</div>
		<div id="menuInfoPanel" title="菜单详细信息" class="easyui-panel" style="width: 350px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:231,top:1}">
			<form id="addMenuForm" method="post">
				<div class="label_css">
					<label for="xh">
					序&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号 :
					</label>
					<input class="easyui-validatebox" type="text" id="xh" name="xh" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="menuName">
						菜单名称 :
					</label>
					<input class="easyui-validatebox" type="text" id="menuname" name="menuname" data-options="required:true" />
				</div>
				<div class="label_css">
					<label for="action">
						菜单地址 :
					</label>
					<input class="easyui-validatebox" type="text"  id="action" name="action" data-options="required:false"  style="width:250px"/>
				</div>
				<div class="label_css">
					<label for="isDisAble">
						是否显示 :
					</label>
					<label for="isDisAble">
						是:
					</label>
					<input  type="radio"  id="disable_no" name="isdisable" value="0" />
					<label for="isDisAble">
						否:
					</label>
					<input  type="radio"  id="disable_yes" name="isdisable" value="1" />
				</div>
				<div class="label_css">
					<a onclick="submitForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">保存</a>
					<a onclick="clearForm()" href="#" class="easyui-linkbutton c5" style="width: 80px">重置</a>
				</div>
				<!-- 
				<div>
					<label for="userPwd">
						所属部门:
					</label>
					<input class="easyui-combobox" type="text" name="userPwd" data-options="required:true" />
				</div>
				 -->
			</form>
		</div>
	</body>
	<script type="text/javascript">
	
</script>
</HTML>