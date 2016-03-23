   jq(function() {
	 	loadData();
		jq('#commDesc').hide();
		//加载下拉表
		jq('#approver').combobox({    
			url:getUsersForCombox(),
		    valueField:'id',    
		    textField:'text'   
		});  
	});
 
   /**
    * 
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
	   jq('#jobAssignmentForm').form('submit', {    
		    url:programName + '/workDeclare/jobAssignment!transfer.action?ids='+ids,
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
   
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/workDeclare/jobAssignment!queryJobs.action?starttime=' + starttime + '&endtime=' + endtime
	    });
    }
	
	//加载数据表
	function loadData() {
		var startTime = jq('#starttime').datebox('getValue');
		var endTime = jq('#endtime').datebox('getValue');
		jq('#dg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');
	}
	
	//查询列表数据
	function getData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#jobAssignmentForm').serialize();
		jq.ajax( {
			url : programName + '/workDeclare/jobAssignment!queryJobs.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
			}
		});
		return rows;
	}
	/** 
	 * 加载事件悬停div 
	 * @param data
	 * @return
	 */
	function onLoadSuccess(data){ 
		var view = jq("#dg").data().datagrid.dc.view2;
		var bodyCell = view.find("div.datagrid-body td[field]")
		bodyCell.hover(function(){loadDiv(this);},function(){jq('#commDesc').hide();});

	}
	var tableId = '#dg';
	var editIndex = undefined;

	/**
	 * 保存获取编辑的行时，需要先完成编辑状态
	 * @return
	 */
	function save(){
		var tableId = '#dg';
		jq(tableId).datagrid('endEdit', editIndex);
		var url = getDataFromDatagrid(tableId) ;
		jq('#datagrid').val(url);
		var params =  jq('#jobAssignmentForm').serialize();
		jq.ajax( {
			url : programName + '/workDeclare/jobAssignment!saveJobs.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
			if(data=='0'){
				jq.messager.alert('提示','更新成功!');
				loadData();
			 }
			}
		});
		
		
	}
		   
	/**
	 * 显示悬浮窗口
	 * @param e 列
	 * @return
	 */
	function loadDiv(e){
		jq('#commDesc').html(e.innerText);
		e = window.event;
		xOffset = -23;
		yOffset = 13;
		jq("#commDesc").css("top",(e.pageY - xOffset) + "px").css("left",(e.pageX + yOffset) + "px").css("position","absolute").css("z-index","9999").show();
	}
	 
	