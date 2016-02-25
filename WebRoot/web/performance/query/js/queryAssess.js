   jq(function() {
	 	loadData();
	});
 
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/query/queryAssess!queryAssess.action?assess.starttime=' + starttime + '&assess.endtime=' + endtime
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
		var params =  jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/query/queryAssess!queryAssess.action',
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

	 
	var exportExcel = function(){
		var rows = jq(tableId).datagrid('getRows');
		jq('#datagrid').val(JSON.stringify(rows));
		jq.ajaxSettings.async = false;  
		var params =  jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/query/queryAssess!exportExcel.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
			//执行下载操作
			  window.location  =  programName +'/web/files/exportFile'+data;
			}
		});
		return rows;
		
	}
	
	