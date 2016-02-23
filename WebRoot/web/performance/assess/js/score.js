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
	        url:  programName + '/assess/score!queryScore.action?starttime=' + starttime + '&endtime=' + endtime
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
			url : programName + '/assess/score!queryScore.action',
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
		var tableId = '#dg';
		jq(tableId).datagrid('endEdit', editIndex);

		var rows = jq(tableId).datagrid('getChecked');
		jq('#datagrid').val(JSON.stringify(rows));
		var params =  jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/assess/score!save.action',
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
	