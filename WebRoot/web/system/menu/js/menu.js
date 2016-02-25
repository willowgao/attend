var jq = jQuery.noConflict();//jQuery将$换成jq，避免冲突

jq(document).ready(function() {
	queryTree();
});

//tree初始化设置
var setting = {
	check : {
	//enable: true
	enable : false
	},
	data : {
		key : {
			name : "menuname",
			url:"action"
		},
		simpleData : {
			enable : true,
			idKey : "menuid",
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

var type = null;

//单击节点
function onClick(event, treeId, treeNode, clickFlag) {
	var menuid = treeNode.menuid;
	var parentid = treeNode.parentid;
	jq("#menuId").val(menuid);
	jq("#parentId").val(parentid); 
	jq.messager.confirm('确认','请问，是新增菜单吗？',function(r){    
	    if (r){    
	    	type = 'add';
			clearForm();
			if(treeNode.isdisable=='0'){
				jq("#disable_no").attr('checked', 'true');
			}else{
				jq("#disable_yes").attr('checked', 'true');
			}
	    }else{  
	    	type = 'update';
			jq("#menuname").val(treeNode.menuname);
			jq("#action").val(treeNode.action);
			jq("#menuaction").val(treeNode.action);
			if(treeNode.isdisable=='0'){
				jq("#disable_no").attr('checked', 'true');
			}else{
				jq("#disable_yes").attr('checked', 'true');
			}
			jq("#xh").val(treeNode.xh);
	    }
	});  
}

//清除
function clearForm(){
	jq('#addMenuForm').form('clear');
}	



//查询报表树
function queryTree() {

	jq.getJSON( programName + '/menu/menuManager!showTree.action', function(datas) {
		//初始化ztree数据
			jq.fn.zTree.init(jq("#orgTree"), setting, datas);
			//数据初始化之后展开
			expandAll();
		});
}

//检查并提交form
function submitForm(){
	var menuId = jq("#menuId").val();
	var parentId = jq("#parentId").val();
	if(menuId==null||menuId==''){
		jq.messager.alert('提示','请选择需要修改或新增的目录!');
		return;
	}  
	jq('#addMenuForm').form('submit', {    
	    url: programName + '/menu/menuManager!saveMenu.action?menuId='+menuId+'&parentId='+parentId+'&type='+type,    
	    onSubmit: function(){ 
	       return jq(this).form('validate');
	    },
	    success:function(data){    
	    	if(data=='0'){	
			   jq.messager.alert('提示','菜单修改成功!');
			   //重新加列表信息
				queryTree();    
	    		clearForm();
	    	}
	    } 
	});
}