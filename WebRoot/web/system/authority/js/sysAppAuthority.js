 //jQuery将$换成jq，避免冲突
jq(function() {
	loadData();
});

function loadData() { 
	jq(tableId).datagrid( {
		data : getData()
	}).datagrid('clientPaging');
}
	
function getData() { 
	var rows = null;
	jq.ajaxSettings.async = false; 
	jq.getJSON(programName + '/authority/systemAuthority!queryApproves.action', function(re_datas) {
		rows = re_datas;
	});
	return rows;
}
	
/**
 * query 
 */
var query = function () { 
    jq(tableId).datagrid({
        url: programName + '/authority/systemAuthority!queryApproves.action'
    });

};
var tableId = '#dg';
var editIndex = undefined;
 

/**
 * 保存获取编辑的行时，需要先完成编辑状态
 * @return
 */
function save(){ 
	jq(tableId).datagrid('endEdit', editIndex);
	var url = getDataFromDatagrid(tableId) ;
	jq('#datagrid').val(url);

	//获取表单修改的内容
	var params =  jq('#leaveAppSetForm').serialize();
	jq.ajax( {
		url : programName + '/authority/systemAuthority!saveApproves.action',
		type : 'post',
		data : params,
		dataType : 'json',
		success : function(data) {
			rows = data;
			if(data=='0'){
				jq.messager.alert('提示','更新成功!');
				loadData();
			}
		}
	});
	
}
	   