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
	
	 

	//展开全部树
	function expandAll() {
		var orgTree = jq.fn.zTree.getZTreeObj("orgTree");
		//默认展开全部节点
		orgTree.expandAll(true);
	}


	//单击节点
	function onClick(event, treeId, treeNode, clickFlag) {
		jq("#roleId").val("");
		jq("#orgId").val(treeNode.orgid);
		jq("#orgType").val(treeNode.orgType);
		jq("#deptId").val(treeNode.deptid);
		jq("#h_org").val(treeNode.org);
		loadOrgData() ;
	} 
	//清空form
	function clearForm(){
		jq('#addOrgForm').form('clear');
	}	

	//查询table数据
	function loadOrgData() {
		var orgId = jq('#orgId').val(); 
		var orgType = jq('#orgType').val(); 
		jq('#orgTable').datagrid( {
			data : getOrgData(orgId,orgType)
		}).datagrid('clientPaging');
	}
	var editIndex = undefined;
	/**
	 *单选填充
	 */
	function onClickRow(index, row) { 
		editIndex = index;
		var deptName = row.deptname;
		var deptId = row.deptid;
		var orgId = row.orgid;
		var orgType = row.orgType;
		jq('#deptName').val(deptName);
		jq("#deptId").val(deptId);
		jq("#orgId").val(orgId);
		jq('#orgType').combobox('setValue', orgType); 
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
	
	/**根据orgId查询机构列表信息
	**/
	function getOrgData(orgId,orgType) { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		jq.getJSON( programName + '/organization/organizationTree!getOrgAndDepts.action?orgId=' + orgId+'&orgType='+orgType , function(re_datas) {
			rows = re_datas;
		});
		return rows;
	}
 
	
	//检查并提交form
	function submitForm(){
		var orgId = jq("#orgId").val();
		var deptId = jq("#deptId").val();
		var org = jq("#h_org").val();
		var orgType = jq('#orgType').combobox('getValue'); 
		if(orgId==null||orgId==''){
			jq.messager.alert('提示','请选择所属单位!');
			return;
		}
		var qurUrl =  programName + '/organization/organizationTree!saveOrUpdateOrganziation.action?orgId='+org;
		if(deptId!=''){
			qurUrl = qurUrl+'&deptId='+deptId;
		}
		if(orgType!=''){
			qurUrl = qurUrl+'&orgType='+orgType;
		}
		
		
		//执行提交
		jq('#addOrgForm').form('submit', {    
		    url:qurUrl,    
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='success'){	
				   jq.messager.alert('提示','信息更新成功!');
				   //重新加列表信息
					loadOrgData();
					//重新加载树 
					queryTree();   
		    		clearForm();
		    		//重新加载组织信息
			    	DataDictionaryService.initDataDictionary();
		    	}
		    } 
		});
	}
	  
	 

	
	