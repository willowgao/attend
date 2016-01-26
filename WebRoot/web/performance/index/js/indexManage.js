   jq(function() {
	 	loadData();
		jq('#commDesc').hide();
		
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
		var params =  jq('#indexForm').serialize();
		jq.ajax( {
			url : programName + '/assess/indexManage!queryIndex.action',
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
			jq("#dg").datagrid('mergeCells',{
				index: merges[i].index,
				field: 'item',
				rowspan: merges[i].rowspan
			});
		}
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
		jq.getJSON(programName + '/assess/indexManage!save.action'+url, function(re_datas) {
			 if(re_datas=='0'){
				jq.messager.alert('提示','更新成功!');
				loadData();
			 }
		}); 
	}
		   

   var setRowStyle = function(value, row, index){
	   return 'height:100px;';
   }
    
	