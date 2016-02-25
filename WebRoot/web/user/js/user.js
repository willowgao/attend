var jq = jQuery.noConflict();//jQuery将$换成jq，避免冲突

	jq(document).ready(function() {
		queryTree();
		jq('#userCode').change(function(){
			checkUserCode();
		});
		jq('#reUserPwd').change(function(){
			checkPwd();
		});
		jq('#checkCode').hide();
		jq('#checkPwd').hide();
		 

		jq('#usersex').combobox({    
		    url:getDictionaryForCombox('SEXTYPE'),    
		    valueField:'id',    
		    textField:'text'   
		});  
	});

	//tree初始化设置
	var setting = {
		check : {
			//enable: true 
			enable : false
		},
		data : {
			key : {
				name : "orgname"
			},
			simpleData : {
				enable : true,
				idKey : "orgid",
				pIdKey : "parentid",
				rootPId : null
			}
		},
		callback : {
			onClick : onClick
		}
	};

	//展开全部树
	function expandAll() {
		var zTree = jq.fn.zTree.getZTreeObj("orgTree");
		//默认展开全部节点
		zTree.expandAll(false);
	}

	//单击节点
	function onClick(event, treeId, treeNode, clickFlag) {
		var orgId = treeNode.orgid;
		jq("#orgId").val(treeNode.orgid);
		jq("#orgType").val(treeNode.orgType);
		jq("#deptId").val(treeNode.deptid);
		jq("#h_org").val(treeNode.org);
		//初始化表格
		loadData();
		//清空列表
		clearForm();
		jq('#defaultstyle').combobox('setValue','metro-blue'); 
		initRoleMap();
	}
	//清空form
	function clearForm(){
		jq('#addUserForm').form('clear');
	}	
	//检查密码输入是否一致
	function checkPwd(){
		var userPwd = jq("#userPwd").val();
		var reUserPwd = jq("#reUserPwd").val();
		if(reUserPwd!=userPwd){
			 jq('#checkPwd').show();
			return false;
		}else{
			 jq('#checkPwd').hide();
			return true;
		}
	}
	//查询table数据
	function loadData() {
		var orgId = jq('#h_org').val(); 
		var deptId = jq('#deptId').val(); 
		jq('#dg').datagrid( {
			data : getData(orgId,deptId)
		}).datagrid('clientPaging');
	}
	
	
	//查询报表树
	function queryTree() {
		jq.getJSON( programName + '/organization/organizationTree!queryTree.action', function(datas) {
			//初始化ztree数据
				jq.fn.zTree.init(jq("#orgTree"), setting, datas);
				//数据初始化之后展开
				expandAll();
			});

	}
	var isChange = false;
	//检查用户名是否已经注册
	function checkUserCode() {
		var isChange = true;
		var isExists = null;
		var userCode = jq("#userCode").val();
		jq.getJSON( programName + '/user/userManager!checkUserCode.action?userCode='+userCode, function(datas) {
			isExists = datas;
			if(datas=='1'){	
			    jq('#checkCode').show();
	    	}else{
			    jq('#checkCode').hide();
	    	}
		});
		if(isExists=='1'){
		  return true;
		}else{
		  return false;
		}
	}
	
	//初始化角色下拉列表
	function initRoleMap(){
		var orgId = jq("#h_org").val();
		var deptId = jq("#deptId").val();
		var roleId = jq("#roleId").val();
		var userid = jq("#userid").val();
		var qurUrl =  programName + '/user/userManager!getRoles.action?orgId='+orgId;
		if(deptId!=''){
			qurUrl = qurUrl+'&deptId='+deptId;
		}
		if(roleId!=''){
			qurUrl = qurUrl+'&roleId='+roleId;
		}
		jq('#roleMap').combobox({    
		    url:qurUrl,    
		    valueField:'id',    
		    textField:'text'   
		});  
		

		jq('#higherid').combobox({    
		    url: programName + '/authority/roleManager!getUserForHigher.action?deptid='+deptId+'&userid='+userid,    
		    valueField:'id',    
		    textField:'text'
		});  
	} 

	var changeStyle = function(peo){
		if(peo!=null){
			//easyui下拉列表onchange事件，修改主题风格
			jq("#link_easyui_css").attr("href",programName+"/web/common/easyui/themes/"+peo.text+"/easyui.css");
			jq("#link_easyui_layout_css").attr("href",programName+"/web/common/easyui/themes/"+peo.text+"/layout.css");
			parent.document.getElementById('sysstyle').value = peo.text;
			jq(parent.document).contents().find('#link_easyui_css').attr("href",programName+"/web/common/easyui/themes/"+peo.text+"/easyui.css");
			jq(parent.document).contents().find('#link_easyui_layout_css').attr("href",programName+"/web/common/easyui/themes/"+peo.text+"/easyui.css");
		}
	}
	
	//检查并提交form
	function submitForm(){
		var orgId = jq("#h_org").val();
		var deptId = jq("#deptId").val();
		var userId = jq("#userId").val();
		var oldPwd = jq("#oldPwd").val();
		var roleId = jq('#roleMap').combobox('getValue');
		if(orgId==null||orgId==''){
			jq.messager.alert('提示','请选择所属单位!');
			return;
		} 
		//检查用户名是否唯一
		if(isChange){
			if(checkUserCode()){
				return;
			}
		} 
		//检查密码是否一致 
		if(!checkPwd()){
			return;
		}  
		//拼接url
        var queUrl =  programName + '/user/userManager!saveOrUpdate.action?orgId='+orgId;
        if(deptId!=''){
			queUrl = queUrl +'&deptId='+deptId;
		}
		if(roleId!=''){
			queUrl = queUrl +'&roleId='+roleId;
		}
		if(userId!=''){
			queUrl = queUrl +'&userId='+userId;
		} 
		jq('#addUserForm').form('submit', {    
		    url:queUrl,    
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='1'){	
				   jq.messager.alert('提示','信息更新成功!');
				   //重新加列表信息
				   loadData();    
		    	   clearForm();
		    	   //重新加载用户内存信息
		    	   DataDictionaryService.initDataDictionary();
		    	}
		    } 
		});
	}
	
	/**根据orgId查询用户列表信息
	**/
	function getData(orgId,deptId) { 
		var rows = null;
		var url =  programName + '/user/userManager!queryUserListByOrg.action?orgId=' + orgId;
		if(deptId!=''){
			url = url +'&deptId='+deptId
		}
		jq.ajaxSettings.async = false;  
		jq.getJSON(url, function(re_datas) {
			rows = re_datas;
		});
		return rows;
	}
	
	var editIndex = undefined;
	/**
	 *单选填充
	 */
	function onClickRow(index, row) { 
		editIndex = index;
		var userid = row.userid; 
		var org = row.userorg;

		initRoleMap();
		jq.getJSON( programName + '/user/userManager!queryUserListByOrg.action?orgId=' + org+'&userid='+userid , function(re_datas) {
			jq('#userName').val(re_datas.rows[0].username);
			jq('#userCode').val(re_datas.rows[0].usercode);
			jq('#userId').val(re_datas.rows[0].userid);
			jq('#userPwd').val(re_datas.rows[0].userpwd);
			jq('#reUserPwd').val(re_datas.rows[0].userpwd);
			jq('#oldPwd').val(re_datas.rows[0].userpwd);
			jq('#roleId').val(re_datas.rows[0].roleid);
			jq('#roleMap').combobox('setValue',re_datas.rows[0].roleid);
			jq('#higherid').combobox('setValue',re_datas.rows[0].higherid);
			jq('#defaultstyle').combobox('setValue',re_datas.rows[0].defaultstyle);
			jq('#usersex').combobox('setValue',re_datas.rows[0].usersex);
			jq('#deptId').val(re_datas.rows[0].userdeptid); 
		});  


	}
	
	//删除用户
	function removeit(tableId){
		var userid = jq('#'+tableId).datagrid('getRows')[editIndex]['userid'];
		
		jq.messager.confirm('确认','请确认是否删除此条信息？',function(r){    
		    if (r){    
				jq.ajaxSettings.async = false; 
		    	jq.getJSON( programName + '/user/userManager!deleteUser.action?userid=' + userid , function(re_datas) {
					if(re_datas=='0'){
						jq.messager.alert('提示','删除成功！');
						//重新加列表信息
						loadData();
					}else{
						jq.messager.alert('提示','删除失败，请确认角色下是否有用户信息？');
					}
				});
		    }else{  
		    	return;
		    }
		});   
	}
	

	
	