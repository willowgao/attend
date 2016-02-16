 //jQuery将$换成jq，避免冲突
	var jq = jQuery.noConflict();
	function getData(startTime,endTime) { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#clockRecordsForm').serialize();
		jq.ajax( {
			url : programName + '/clock/excepManager!getExcepRecords.action',
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
		jq('#dlg').dialog('close');
		loadData();
	});
	
	//加载数据表
	function loadData() {
		var startTime = jq('#startTime').datebox('getValue');
		var endTime = jq('#endTime').datebox('getValue');
		jq('#dg').datagrid( {
			data : getData(startTime, endTime)
		}).datagrid('clientPaging');
	}

	/**
	 * query 
	 */
	var query = function () { 
		var startTime = jq('#startTime').datebox('getValue');
		var endTime = jq('#endTime').datebox('getValue');
	    jq('#dg').datagrid({
	    	url : programName + '/clock/excepManager!getExcepRecords.action?startTime=' + startTime + '&endTime=' + endTime
	    });

	};
	
	//根据isneed行的颜色
	function setRowColor(index,row){
		if (row.isneed > 0){
			return 'background-color:#CBE8F1;color:#black;';
		}
	}
	/**
	 *  列着色处理
	 * @param value
	 * @param row
	 * @param index
	 * @return
	 */
	function dgCellStyleAmsb(value, row, index) {
		if((value==null||value =='')&&row.isneed=='0'){
			return 'background-color:#F5BB2A;color:#black;';
		}else{
			if(!checkForTimeCD(value,row.amsbtime)){
				return 'background-color:#E1643D;color:#black;';
			}else{
				return 'color:#black;';
			}
		}
    	 
	}
	function dgCellStyleAmxb(value, row, index) {
		if((value==null||value =='')&&row.isneed=='0'){
			return 'background-color:#F5BB2A;color:#black;';
		}else{ 
	    	if(!checkForTimeZT(value,row.amxbtime)){
				return 'background-color:#E1643D;color:#black;';
			}else{
				return 'color:#black;';
			}
		}
    	 
	}
	function dgCellStylePmsb(value, row, index) {
		if((value==null||value =='')&&row.isneed=='0'){
			return 'background-color:#F5BB2A;color:#black;';
		}else{
	    	if(!checkForTimeCD(value,row.pmsbtime)){
				return 'background-color:#E1643D;color:#black;';
			}else{
				return 'color:#black;';
			}
		}
	}
	function dgCellStylePmxb(value, row, index) {
		if((value==null||value =='')&&row.isneed=='0'){
			return 'background-color:#F5BB2A;color:#black;';
		}else{
	    	if(!checkForTimeZT(value,row.pmxbtime)){
				return 'background-color:#E1643D;color:#black;';
			}else{
				return 'color:#black;';
			}
		}
	}
	
	//判断是否异常打卡
	function checkForTimeCD(systime,checkTime){
		if(systime==null){
			return true;
		}
		var a = '01/10/2015 ' + systime;
	    var b = '01/10/2015 ' + checkTime;
	    var d = new Date(a);
	    var e = new Date(b);
	  	if (d > e) {
	  	   return false;
	  	} else {
	   	   return true;
	  	}
	}
	//判断是否异常打卡
	function checkForTimeZT(systime,checkTime){
		if(systime==null){
			return true;
		}
		var a = '01/10/2015 ' + systime;
	    var b = '01/10/2015 ' + checkTime;
	    var d = new Date(a);
	    var e = new Date(b);
	  	if (d > e) {
	  	   return true;
	  	} else {
	   	   return false;
	  	}
	}
	
	
	
	/**
	双击
	**/
	function onClickRow(index, row){
		var userid = row.userid;
		var clockdate = row.clockdate;
		var isneed = row.isneed;
		jq('#userid').attr('value',userid);
		jq('#clockdate').attr('value',clockdate);
		jq('#isneed').attr('value',isneed);
		
	}
	/**
	 * 弹出窗口
	 * @return
	 * Chrome: window.open 
	 * IE:window.showModalDialog
	 * easyui:jq('#dlg').dialog('open')
	 */
	function doExcep(index, row){   
		
			var userid= null;
			var clockdate= null;
			var isneed= null;
			if(row!=null){
				 userid = row.userid;
				 clockdate = row.clockdate;
				 isneed = row.isneed;
			}else{
				 userid =	jq('#userid').val();
				 clockdate = jq('#clockdate').val();
				 isneed = jq('#isneed').val();
			}
			if(userid==''||userid==null){
				jq.messager.alert('提示','请选择一行需要处理的异常记录或双击此记录!');
				return;
			}
		if(isneed == 0){
			jq('#dlgdg').datagrid( {
				data : getData(startTime, endTime)
			}).datagrid('clientPaging');
			
			//打开弹出窗口
			jq('#dlg').dialog('open');
			//将窗口移动到固定的位置
			jq('#dlg').dialog('move',{
			    left: document.documentElement.offsetWidth/2-350,
			    top: document.documentElement.offsetTop,
			    right:"",
			    zIndex:jq.fn.window.defaults.zIndex++
			});
			
			//加载下拉表
			jq('#approver').combobox({    
				url:getUsersForCombox(ROLETYPE_TRIAL),
			    valueField:'id',    
			    textField:'text'   
			});  
		}
	}
	
	//保存数据
	function save(){
	
		var params =  jq('#clockRecordsForm').serialize();
		var comments = jq('#comments').textbox('getValue'); 
		var approver = jq('#approver').combobox('getValue');
		jq.ajax( {
			url : programName + '/clock/excepManager!saveExcep.action?comments='+comments+'&approver='+approver,
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				if(data=='0'){
					jq.messager.alert('提示','保存成功!');
					jq('#dlg').dialog('close');
				}else{
					jq.messager.alert('警告','保存失败!');
				}
			}
		});
	}
 
	