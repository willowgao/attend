//jQuery将$换成jq，避免冲突
var jq = jQuery.noConflict();
//加载 查询方法
jq(function() {
	loadData();
});

/**
 * 获取数据方法
 * @param startTime
 * @param endTime
 * @return
 */
function getData() { 
	var rows = null;
	jq.ajaxSettings.async = false; 
	jq.getJSON(programName + '/clock/clockManager!querySettings.action', function(re_datas) {
		rows = re_datas;
	}); 
	return rows;
}

/**
 * 查询表单数据填充
 * @return
 */
function loadData() {
	jq('#dg').datagrid( {
		data : getData()
	}).datagrid('clientPaging');
}
	 
/**
 * 保存数据
 * @return
 */
function save() {
	//必须先对提交表单数据数据进行序列化，采用jQuery的serialize()方法  
	var params = jq('#clockSettingForm').serialize();
	if(valid())
	jq.ajax( {
		url : programName + '/clock/clockManager!saveTime.action',
		type : 'post',
		data : params,
		dataType : 'json',
		success : function(data) {
			if(data=='1'){
				jq.messager.alert('提示','保存成功!'); 
				loadData();
			}
		}
	});
}
/**
 * 数据填写校验
 */
function valid(){
	var bool = true;
	//获取表单所有填写的数据 
	var startTime = tranferStr2Date(jq('#startTime').datebox('getValue'));
	var endTime = tranferStr2Date(jq('#endTime').datebox('getValue'));
	if(compare2Date(startTime,endTime)){
		 jq.messager.alert('提示','开始日期大于终止日期!'); 
	     return false;
	}
	//检查记录是否已经存在
	if(checkExists()){
		bool = false;
	}
	return bool;
} 

/**
 * 查检是否已经存在记录
 * @return boolean
 */
function checkExists(){
	var bool = false; 
	jq.ajaxSettings.async = false; 
	var params = jq('#clockSettingForm').serialize();
	jq.ajax( {
		url : programName + '/clock/clockManager!querySettings.action',
		type : 'post',
		data : params,
		dataType : 'json',
		success : function(re_datas) {
			if(re_datas!=null&&re_datas.total>0){
				jq.messager.alert('提示','已经存在此区间的记录,请确认!'); 
				bool = true;
			}
		}
	}); 
	return bool;
}