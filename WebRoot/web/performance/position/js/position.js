   jq(function() {
	 	loadData();
		jq('#commDesc').hide();
		initCombobox();
		initForm();
	});
   
   /**
    * 初始化form
    * @return
    */
   var initForm = function(){
	   jq.getJSON(programName + '/position/positionStatement!initForm.action', function(re_datas) {
		   jq.each(re_datas,function(idx,item){  
				 //输出 
				 if(idx=='positionForm.deptid'){
					jq('#deptid').combobox('setValue',item);
				 }else if(idx=='positionForm.roleid'){ 
					 jq('#roleid').combobox('setValue',item);
				 }else if(idx=='positionForm.starttime'){ 
					 jq('#starttime').datebox('setValue',item);
				 }else if(idx=='positionForm.approver'){ 
					 jq('#approver').combobox('setValue',item);
				 }
			});
		}); 
   }
   /**
    * 加载初始化下拉列表
    * @return
    */
   var initCombobox = function(){
		 //加载下拉表
		jq('#approver').combobox({    
			url:getDictionaryForCombox('USER'),
		    valueField:'id',    
		    textField:'text'   
		});  
		
		
		jq('#deptid').combobox({    
			url:getDictionaryForCombox('DEPT'),
		    valueField:'id',    
		    textField:'text'   
		});  
		
		jq('#roleid').combobox({    
			url:getDictionaryForCombox('ROLE'),
		    valueField:'id',    
		    textField:'text'   
		});  
		
   }
   
   var setRowStyle = function(value, row, index){
	   return 'height:100px;';
   }
   
   
    
   /**
    * 传递
    */
   var transfer = function(){
	   var rows = jq(tableId).datagrid('getChecked');
	   var ids = '';
	   if(rows==''){
		   jq.messager.alert('警告','请选择需要传递的工单信息!');
		   return ;
	   }
	   for(var i=0;i< rows.length; i++ ){
		   ids = ids+rows[i].jobid+',';
	   }
	   jq('#positionForm').form('submit', {    
		    url:programName + '/position/positionStatement!transfer.action?ids='+ids,
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='0'){	
				   jq.messager.alert('提示','信息更新成功!');
				   //重新加列表信息
					jq('#positionForm').form('clear');
					query();
		    	}
		    } 
		});
   }
   
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var starttime = jq('#starttime').datebox('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/position/positionStatement!queryPositionStatements.action?starttime=' + starttime 
	    });
    }
	
	//加载数据表
	function loadData() {
		jq('#dg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');
	}
	
	//查询列表数据
	function getData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#positionForm').serialize();
		jq.ajax( {
			url : programName + '/position/positionStatement!queryPositionStatements.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
			}
		});
		return rows;
	}
	 
	var tableId = '#dg';
	var editIndex = undefined;

	/**
	 * 保存获取编辑的行时，需要先完成编辑状态
	 * @return
	 */
	function save(){
		jq(tableId).datagrid('endEdit', editIndex);
		var url = getDataFromDatagrid(tableId) ;
		jq('#positionForm').form('submit', {    
		    url:programName + '/position/positionStatement!savePositionStatement.action'+url,
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='0'){	
				   jq.messager.alert('提示','信息更新成功!');
				   query();
		    	}
		    } 
		});
	}
		   
 
	