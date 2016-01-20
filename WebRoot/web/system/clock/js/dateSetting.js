 //jQuery将$换成jq，避免冲突
	var jq = jQuery.noConflict();
	function getData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#dateSetForm').serialize();
		jq.ajax( {
			url : programName + '/sysClock/sysClockSet!query.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
			}
		});
		return rows;
	}

	jq(function() {
		loadData();
	});
	
	//加载数据表
	function loadData() {
		jq('#dg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');
	}

	/**
	 * query 
	 */
	var query = function () { 
		var clockDate = jq('#clockdate').timespinner('getValue');
	    jq('#dg').datagrid({
	    	url : programName + '/sysClock/sysClockSet!query.action?clockDate=' + clockDate
	    });

	};
	
	//根据isneed行的颜色
	function setRowColor(index,row){
		if (row.isneed > 0){
			return 'background-color:#CBE8F1;color:#black;';
		}
	}
	 

	var tableId = '#dg';
	var editIndex = undefined;
	/**
	列双击之后处理编辑状态
	**/
	function onClickCell(index, field){
		if (editIndex != index){
			if (endEditing()){
				jq(tableId).datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				var ed = jq(tableId).datagrid('getEditor', {index:index,field:field});
				(jq(ed.target).data('textbox') ? jq(ed.target).textbox('textbox') : jq(ed.target)).focus();
				editIndex = index;
			} else {
				jq(tableId).datagrid('selectRow', editIndex);
			}
		}
	}

	/**
	切换行之后，取消行编辑状态
	**/
	function endEditing(){
		if (editIndex == undefined){return true}
		if (jq(tableId).datagrid('validateRow', editIndex)){
			var ed = jq(tableId).datagrid('getEditor', {index:editIndex,field:'isneed'});
			var isneed = jq(ed.target).combobox('getValue');
			//jq(tableId).datagrid('getRows')[editIndex]['productname'] = productname;
			jq(tableId).datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	 
	
	
	/**
	 * 保存获取编辑的行时，需要先完成编辑状态
	 * @return
	 */
	function save(){
		jq(tableId).datagrid('endEdit', editIndex);
		var url = getDataFromDatagrid(tableId) ;
		jq.getJSON(programName + '/sysClock/sysClockSet!save.action'+url, function(re_datas) {
			 if(re_datas=='0'){
				jq.messager.alert('提示','更新成功!');
				loadData();
			 }
		}); 
		
	}
 
	