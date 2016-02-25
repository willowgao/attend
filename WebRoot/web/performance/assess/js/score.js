   jq(function() {
	 	loadData();
		jq('#dlg').dialog('close');
	});
 
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/assess/score!queryScore.action?assess.starttime=' + starttime + '&assess.endtime=' + endtime
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
			if(data=='1'){
				jq.messager.alert('提示','更新成功!');
				loadData();
			 }
			}
		});
	} 
	
	
	var exportExcel = function(){
		var rows = jq(tableId).datagrid('getRows');
		jq('#datagrid').val(JSON.stringify(rows));
		jq.ajaxSettings.async = false;  
		var params =  jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/assess/score!exportExcel.action',
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
	

	/**
	 * 双击行，弹窗
	 * @param index
	 * @param row
	 * @return
	 */
	var onDblClickRow = function(index, row){

		//打开弹出窗口
		jq('#dlg').dialog('open');
		jq('#userid').val(row.userid);
		jq('#d_starttime').val(row.starttime);
		jq('#d_endtime').val(row.endtime);
		jq('#dlgdg').datagrid( {
			data : getDetail()
		}).datagrid('clientPaging');
		
		//将窗口移动到固定的位置
		jq('#dlg').dialog('move',{
		    left: document.documentElement.offsetWidth/2-450,
		    top: document.documentElement.offsetTop,
		    right:"",
		    zIndex:jq.fn.window.defaults.zIndex++
		});
	}
	
	/**
	 * 
	 * @return
	 */
	var getDetail = function(){
		var rows = null;
		jq.ajaxSettings.async = false; 
		var userid = jq('#userid').val();  
		var d_starttime = jq('#d_starttime').val();  
		var d_endtime = jq('#d_endtime').val();  
		var params =  jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/assess/score!queryDetail.action?userid='+userid+'&starttime='+d_starttime+'&endtime='+d_endtime,
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
			}
		});
		return rows;
		
	}
	