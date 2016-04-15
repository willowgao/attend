 //jQuery将$换成jq，避免冲突
	var jq = jQuery.noConflict();
	function getData(startTime,endTime) { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#clockRecordsForm').serialize();
		jq.ajax( {
			url : programName + '/query/queryAttend!queryAttendsList.action',
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
		//加载下拉表

		jq('#userid').combobox({
			url: programName + '/query/queryAttend!getUsersFormUser.action',
		    valueField:'id',    
		    textField:'text'  
		});
		
		initForm();
		  
	});
	
	/**
	    * 初始化form
	    * @return
	    */
	   var initForm = function(){
		   jq.getJSON(programName + '/query/queryAttend!initForm.action', function(re_datas) {
			   var flag = null;
			   jq.each(re_datas,function(idx,item){  
					 //输出 
					 if(idx=='clockRecordsForm.flag'){
						  flag = jq('#flag').val(item);
					 }
				});
			   jq.each(re_datas,function(idx,item){  
					 //输出 
					 if(parseInt(flag.val()) == parseInt(ROLETYPE_NOMARL) && idx=='clockRecordsForm.userid'){
						 jq('#userid').combobox('setValue',item);
						 jq('#userid').combobox('readonly', true);	
					 }
				});
			   
			}); 
	   }
	   
	   
	
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
		var userid = jq('#userid').combobox('getValue');
	    jq('#dg').datagrid({
	    	url : programName + '/query/queryAttend!queryAttendsList.action?startTime=' + startTime + '&endTime=' + endTime+ '&userid=' + userid
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
	    	if(!checkForTimeZT(value,row.amxbtime)||!checkForTimeCD(value,row.pmsbtime)){
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
	    var d = new Date(new Date(a).getTime()-60*10*1000);
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
	    var d = new Date(new Date(a).getTime()+60*10*1000);
	    var e = new Date(b);
	  	if (d >= e) {
	  	   return true;
	  	} else {
	   	   return false;
	  	}
	}
	
	
	
	 
