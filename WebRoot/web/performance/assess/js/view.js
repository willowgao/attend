   jq(function() {
	 	loadData();
		jq('#dlg').dialog('close');
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
	
	var query = function(){
		jq('#dlg').dialog('open');
		
		jq.ajaxSettings.async = false; 
		jq.getJSON(programName + '/assess/documentView!viewDocument.action', function(re_datas) {
			 if(re_datas!=''){
				jq('#diary').attr('src',programName+'/web/html/'+re_datas);
			 }
		});  
	}
	