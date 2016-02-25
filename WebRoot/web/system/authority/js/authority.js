var jq = jQuery.noConflict();//jQuery将$换成jq，避免冲突

	jq(document).ready(function() {
		queryTree();
		jq('#checkCode').hide(); 
		//加载下拉表
		jq('#roleType').combobox({    
			url:getDictionaryForCombox('ROLETYPE'),
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
	
	//tree初始化设置
	var menuTreeSetting = {
			check : {
				enable: true 
				//enable : false
			},
			data : {
				key : {
					name : "menuname",
					url:"action",
					checked:"check"
				},
				simpleData : {
					enable : true,
					idKey : "menuid",
					pIdKey : "parentid",
					rootPId : null
				}
			},
			edit:{
				enable:false
			},
			callback : {
				onClick : onMenuClick
			}
	};

	/**
	 *单选填充
	 */
	function onRoleClickRow(index, row) { 
		editIndex = index;
		var roleName = row.rolename;
		var roleCode = row.rolecode;
		jq('#roleName').val(roleName);
		jq('#roleCode').val(roleCode); 
		jq('#roleId').val(row.roleid);
		jq('#roleType').combobox('setValue',row.roletype);
		
		jq.getJSON( programName + '/authority/roleManager!queryMenusByRoleId.action?roleId='+row.roleid, function(datas) {
			//初始化ztree数据
			jq.fn.zTree.init(jq("#menuTree"), menuTreeSetting, datas);
			expandAllMenuTree();
		});
		loadUserData();
	}
	
		
	//查询报表树
	function queryTree() {

		jq.getJSON( programName + '/organization/organizationTree!queryTree.action', function(datas) {
			//初始化ztree数据
			jq.fn.zTree.init(jq("#orgTree"), setting, datas);
			//数据初始化之后展开
			expandAll();
		});
		 

		jq.getJSON( programName + '/menu/menuManager!showTree.action', function(datas) {
			//初始化ztree数据
			jq.fn.zTree.init(jq("#menuTree"), menuTreeSetting, datas);
			//数据初始化之后展开
			expandAllMenuTree();
		});
	}
	
	/**根据orgId查询角色列表信息
	**/
	function getRoleData(orgId,orgType) { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		jq.getJSON( programName + '/authority/roleManager!getRoles.action?orgId=' + orgId+'&orgType='+orgType , function(re_datas) {
			rows = re_datas;
		});
		return rows;
	}
	/**根据roleId查询用户列表信息
	**/
	function getUserData(org,roleId) { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		jq.getJSON( programName + '/authority/roleManager!queryUsersByRoleId.action?roleId=' + roleId+'&org='+org , function(re_datas) {
			rows = re_datas;
		});
		return rows;
	}
	
	//检查并提交form
	function submitForm(){
		var orgId = jq("#orgId").val();
		var deptId = jq("#deptId").val();
		var org = jq("#h_org").val();
		if(orgId==null||orgId==''){
			jq.messager.alert('提示','请选择所属单位!');
			return;
		}
		
		//执行提交
		jq('#addRoleForm').form('submit', {    
		    url: programName + '/authority/roleManager!addRole.action?orgId='+orgId+'&deptId='+deptId+'&org='+org,    
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='success'){	
				   jq.messager.alert('提示','信息更新成功!');
				   //重新加列表信息
					loadRoleData();    
		    		clearForm();
		    		//重新加载用户内存信息
		    	   DataDictionaryService.initDataDictionary();
		    	}
		    } 
		});
	}
	
	
	var editIndex = undefined;
	
	
	
	function removeit(tableId){
		var roleid = jq('#'+tableId).datagrid('getRows')[editIndex]['roleid'];
		jq.messager.confirm('确认','请确认是否删除此条信息？',function(r){    
		    if (r){    
		    	jq.getJSON( programName + '/authority/roleManager!deleteRole.action?roleId=' + roleid , function(re_datas) {
					if(re_datas>0){
						jq.messager.alert('提示','删除成功！');
						//重新加列表信息
						loadRoleData();    
					}else{
						jq.messager.alert('提示','删除失败，请确认角色下是否有用户信息？');
					}
				});
		    }else{  
		    	return;
		    }
		});  
		
		 
	}
	
	//检查角色名是否已经注册
	function checkRoleCode() {
		var userCode = jq("#roleCode").val();
		jq.getJSON( programName + '/user/userManager!checkUserCode.action?roleCode='+roleCode, function(datas) {
			if(datas=='1'){	
			    jq('#checkCode').show();
	    	}else{
			    jq('#checkCode').hide();
	    	}
		});
	}
	//保存角色菜单信息
	function saveMenuForRole(){
		var roleId = jq('#roleId').val();
		var org = jq("#h_org").val();
		if(roleId==null||roleId==''){
			jq.messager.alert('提示','请先选择角色信息!');
			return;
		}
		//获取树信息
	    var menuTree = jq.fn.zTree.getZTreeObj("menuTree");
	    //获取所有被选中节点
	    var nodes = menuTree.getCheckedNodes(true); 
	    var treeIds = "";
	    for (var i=0, l= nodes.length; i<l; i++) {
	        if(nodes[i].menuid){
	        	//节点主键
		        var menuid = nodes[i].menuid; 
		        treeIds = treeIds + menuid + ",";
	        }
	    } 
	    //同步用户菜单标志，1 同步，0不同步
	    var synchronizeType = null;
	    
	    jq.messager.confirm('确认','修改角色所属菜单后，是否同步到所有属于此角色的用户菜单信息？',function(r){    
		    if (r){    
		    	 synchronizeType = '1';
		    }else{  
		    	synchronizeType ='0';
		    }
		    
			 //执行修改操作
		    jq.getJSON( programName + '/authority/roleManager!roleAuthority.action?treeIds='+treeIds+'&roleId='+roleId+'&org='+org+'&synchronizeType='+synchronizeType ,function(datas){
	             if(datas=='0'){
	             	jq.messager.alert('提示','角色权限授权成功!');
	             }else{
	             	jq.messager.alert('提示','角色权限授权失败!');
	             }
		   }); 
		});  
	    
	   
	} 
	
	//展开全部树
	function expandAll() {
		var orgTree = jq.fn.zTree.getZTreeObj("orgTree");
		//默认展开全部节点
		orgTree.expandAll(false);
	}

	//展开全部树
	function expandAllMenuTree() {
		var menuTree = jq.fn.zTree.getZTreeObj("menuTree");
		if(menuTree!=null){
			menuTree.expandAll(true);
		}
	}

	//单击节点
	function onClick(event, treeId, treeNode, clickFlag) {
		jq("#roleId").val("");
		jq("#orgId").val(treeNode.orgid);
		jq("#orgType").val(treeNode.orgType);
		jq("#deptId").val(treeNode.deptid);
		jq("#h_org").val(treeNode.org);
		loadRoleData() ;
	}
	//单击节点
	function onMenuClick(event, treeId, treeNode, clickFlag) {
	}
	//清空form
	function clearForm(){
		jq('#addRoleForm').form('clear');
	}	

	//查询table数据
	function loadRoleData() {
		var orgId = jq('#orgId').val(); 
		var orgType = jq('#orgType').val(); 
		jq('#roleTable').datagrid( {
			data : getRoleData(orgId,orgType)
		}).datagrid('clientPaging');
	}
	

	//查询userTable数据
	function loadUserData() {
		var roleId = jq('#roleId').val(); 
		var org = jq('#h_org').val();
		jq('#userTable').datagrid( {
			data : getUserData(org,roleId)
		}).datagrid('clientPaging');
	}

	
	
	
	