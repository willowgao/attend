   jq(function() {
	 	loadData();
	});
    
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
		var params =   jq('#indexForm').serialize();
		jq.ajax( {
			url : programName + '/assess/performance!queryUsers.action',
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
	 *单选填充
	 */
	function onClickRow(index, row) { 
		editIndex = index;
	}
	
	/** 
	 * 加载事件悬停div 
	 * @param data
	 * @return
	 */
	function onLoadSuccess(data){  
		setMergeCell();
	}
	
	/**
	 * 多行合并
	 */
	var setMergeCell = function(){
		var merges = [{
			index: 0,
			rowspan: 3
		},{
			index: 3,
			rowspan: 2
		},{
			index: 5,
			rowspan: 2
		},{
			index: 7,
			rowspan: 4
		}];
		for(var i=0; i<merges.length; i++){
			jq("#indextb").datagrid('mergeCells',{
				index: merges[i].index,
				field: 'item',
				rowspan: merges[i].rowspan
			});
		}
		
	}
	var tableId = '#indextb';
	var editIndex = undefined;

	var setRowStyle = function(value, row, index){
		   return 'height:100px;';
	} 
	