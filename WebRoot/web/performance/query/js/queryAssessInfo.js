   jq(function() {
		jq('#deptid').combobox({    
			url:getDeptByOrg(),
		    valueField:'id',    
		    textField:'text'   
		}); 
	 	loadData();
	});
 
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
		var deptid = jq('#deptid').combo('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/query/queryAssess!queryAssessInfo.action?assess.starttime=' + starttime + '&assess.endtime=' + endtime+ '&assess.deptid=' + deptid
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
			url : programName + '/query/queryAssess!queryAssessInfo.action',
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
	