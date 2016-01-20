var jq = jQuery.noConflict();//jQuery将$换成jq，避免冲突

	jq(document).ready(function() {
		queryTree();
		jq('#checkCode').hide(); 
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
	};

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
		loadUserData() ;
	} 

	//查询userTable数据
	function loadUserData() {
		var org = jq('#h_org').val();
		var deptId = jq('#deptId').val();
		jq('#userTable').datagrid( {
			data : getUserData(org,deptId)
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
		 
		jq.getJSON( programName + '/menu/menuManager!showTree.action', function(datas) {
			//初始化ztree数据
			jq.fn.zTree.init(jq("#menuTree"), menuTreeSetting, datas);
			//数据初始化之后展开
			expandAllMenuTree();
		});
			

	} 
	/**根据roleId查询用户列表信息
	**/
	function getUserData(org,deptId) { 
		var rows = null;
		var url =  programName + '/user/userManager!queryUserListByOrg.action?orgId=' + org;
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
		var userId = row.userid;
		var userorg = row.userorg;
		var userdeptid = row.userdeptid;
		jq('#userId').val(userId); 
		jq.getJSON( programName + '/authority/userMenuManager!showTreeByUserId.action?userId='+userId, function(datas) {
			//初始化ztree数据
			jq.fn.zTree.init(jq("#menuTree"), menuTreeSetting, datas);
			expandAllMenuTree();
		});
	}
	
	
	//保存角色菜单信息
	function saveMenuForRole(){
		var userId = jq('#userId').val();
		var org = jq("#h_org").val();
		if(userId==null||userId==''){
			jq.messager.alert('提示','请先选择用户信息!');
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
	   	 //执行修改操作
	    jq.getJSON( programName + '/authority/userMenuManager!saveMenuForUser.action?treeIds='+treeIds+'&userId='+userId+'&org='+org ,function(datas){
             if(datas=='0'){
             	jq.messager.alert('提示','用户权限授权成功!');
             }else{
             	jq.messager.alert('提示','用户权限授权失败!');
             }
	   }); 
	} 

	
	