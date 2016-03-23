   jq(function() {
	 	loadData();
		jq('#commDesc').hide();
	});
 
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/workDeclare/upload!getJobUploadApp.action?starttime=' + starttime + '&endtime=' + endtime
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
			url : programName + '/workDeclare/upload!getJobUploadApp.action',
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
    * 完成确认
    */
   var save = function(){
	   var rows = jq(tableId).datagrid('getChecked');
	   var ids = '';
	   if(rows==''){
		   jq.messager.alert('警告','请选择需要确定的工单信息!');
		   return ;
	   }
	   for(var i=0;i< rows.length; i++ ){
		   ids = ids+rows[i].jobid+',';
	   }
	   jq('#jobAssignmentForm').form('submit', {    
		    url:programName + '/workDeclare/upload!uploadJobApp.action?ids='+ids,
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(parseInt(data)>=0){	
				   jq.messager.alert('提示','信息更新成功!');
					query();
		    	}
		    } 
		});
   }
   

   /**
    * 回退
    */
   var rollback = function(){
	   var rows = jq(tableId).datagrid('getChecked');
	   var ids = '';
	   if(rows==''){
		   jq.messager.alert('警告','请选择需要回退的工单信息!');
		   return ;
	   }
	   for(var i=0;i< rows.length; i++ ){
		   ids = ids+rows[i].jobid+',';
	   }
	   ids = ids.substring(0, ids.lastIndexOf(','));
	   jq('#jobAssignmentForm').form('submit', {    
		    url:programName + '/workDeclare/upload!rollback.action?ids='+ids,
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(parseInt(data)>=0){	
				   jq.messager.alert('提示','信息更新成功!');
					query();
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
	 
	