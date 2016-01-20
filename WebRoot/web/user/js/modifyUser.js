var jq = jQuery.noConflict();//jQuery将$换成jq，避免冲突

	jq(document).ready(function() {	
		
		jq('#checkCode').hide();
		jq('#checkPwd').hide();
		 
		jq('#reUserPwd').change(function(){
			checkPwd();
		}); 
		initForm();
	});
	
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
		
	
	var initForm = function(){
		var queUrl =  programName + '/user/userManager!getUser.action';
		jq.ajaxSettings.async = false; 
		var params =  jq('#addUserForm').serialize();
		jq.ajax({
			url : queUrl,
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				jq('#defaultstyle').combobox('setValue',data.defaultstyle);
				jq('#userName').val(data.username);
				jq('#userPwd').val(data.userpwd);
				jq('#reUserPwd').val(data.userpwd);
				jq('#deptId').val(data.userdeptid);
				jq('#userId').val(data.userid); 
				jq('#orgId').val(data.userorg); 
			}
		});
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
	
 
	//检查并提交form
	function submitForm(){
		var userId = jq("#userId").val();
		if(orgId==null||orgId==''){
			jq.messager.alert('提示','请选择所属单位!');
			return;
		} 
	  
		//检查密码是否一致 
		if(!checkPwd()){
			return;
		}  
		//拼接url
        var queUrl =  programName + '/user/userManager!updateUser.action';
        
		if(userId!=''){
			queUrl = queUrl +'?userId='+userId;
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
		    	}
		    } 
		});
	}
	