<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../common/common.jsp"%>
<%@ include file="../../common/ztreeCommon.jsp"%>
<HTML>
	<HEAD>
		<script type="text/javascript" src="<%=webapp%>/web/common/js/easyui_custom.js"></script>
		<script type="text/javascript" src="<%=webapp%>/web/system/organization/js/organization.js"  charset="utf-8"></script>
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
	<input type="hidden" id="orgId">
	<input type="hidden" id="h_org">
	<input type="hidden" id="roleId">
	<input type="hidden" id="deptId">
	<body style="background-color: white; height: 100px">
		<div id="treePanel" title="机构与部门信息" class="easyui-panel" style="width: 230px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1,top:1}">
			<div style="z-index: -9999;">
				<ul id="orgTree" class="ztree">
				</ul>
			</div>
		</div>
		<div id="orgList" title="组织列表" class="easyui-panel" style="width: 800px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:231,top:1}">
			<table id="orgTable" class="easyui-datagrid" style="width: 100%; height: 100%"
				data-options="onClickRow:onClickRow,
				rownumbers:true,
				ctrlSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageList:[5,10,15,20,25,30],
				pageSize:15"
				align="left">
				<thead>
					<tr>
						<th data-options="field:'deptid',width:'8%',align:'center',hidden:'true'">
							DEPTID
						</th>
						<th data-options="field:'deptname',width:'25%',align:'center'">
							机构名称
						</th> 
						<th data-options="field:'orgType',width:'8%',align:'center',formatter:formatterOrgType">
							组织类型
						</th>
						<th data-options="field:'orgid',width:'30%',align:'center',formatter:formatterOrg">
							组织机构
						</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="orgInfoPanel" title="机构与部门维护" class="easyui-panel" style="width: 300px; height: 600px;"
			data-options="collapsible:true,minimizable:false,maximizable:false,closable:false,style:{position:'absolute',left:1031,top:1}">
			<form id="addOrgForm" method="post">
				<div class="label_css">
					<label for="orgType">
						组织类型 :
					</label>
					<select class="easyui-combobox"  id="orgType" name="orgType" style="width:170px;" >
						 <option value="1">组织</option>   
    					 <option value="2">部门</option> 
					</select> 
				</div>
				<div class="label_css">
					<label for="name">
						机构名称 :
					</label>
					<input class="easyui-validatebox" type="text" id="deptName" name="deptName" data-options="required:true" />
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