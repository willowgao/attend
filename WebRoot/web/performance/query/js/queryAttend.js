 //jQuery将$换成jq，避免冲突
var jq = jQuery.noConflict();
 jq(function() {
		loadData(); 
		//加载下拉表
		jq('#dept').combobox({    
			url:getDeptByOrg(),
		    valueField:'id',    
		    textField:'text'   
		});  

	    drawBar();
	    drawPie();
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
		var params =  jq('#diaryDailyForm').serialize();
		jq.ajax( {
			url : programName + '/query/queryAttend!queryAttend.action',
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
	 * 分页查询
	 * @return
	 */
    var query = function(){
    	var dept = jq('#dept').combobox('getValue');
		var clockdate = jq('#clockdate').datebox('getValue');
	    jq('#dg').datagrid({
	        url: programName + '/query/queryAttend!queryAttend.action?clockdate=' + clockdate + '&dept=' + dept
	    });
	    drawBar();
	    drawPie();
    }
	
    
    function drawBar() {
    	var dept = jq('#dept').combobox('getValue');
        var myChart = echarts.init(document.getElementById('bar'));
        myChart.showLoading({
		    text: '正在努力的读取数据中...',    //loading话术
		})
		var clockdate = jq('#clockdate').datebox('getValue');
        var option =  "";
		jq.ajaxSettings.async = false; 
        jq.getJSON(programName+'/query/queryAttend!queryOrgRanking.action?clockdate=' + clockdate+ '&dept=' + dept , function(datas) {
		//取出json的值
			option =  datas;
		});
		 
        myChart.setOption(option);
        myChart.hideLoading();
        
    }
    
    function drawPie() {
    	var dept = jq('#dept').combobox('getValue');
        var myChart = echarts.init(document.getElementById('pie'));
        myChart.showLoading({
		    text: '正在努力的读取数据中...',    //loading话术
		})
        var option =  "";
        var clockdate = jq('#clockdate').datebox('getValue');
    	jq.ajaxSettings.async = false; 
        jq.getJSON(programName+'/query/queryAttend!queryDeptRanking.action?clockdate=' + clockdate+ '&dept=' + dept, function(datas) {
		//取出json的值
			option =  datas;
		});
		 
        myChart.setOption(option);
        myChart.hideLoading();
        
    }
    
    
    /**
	 *  列着色处理
	 * @param value
	 * @param row
	 * @param index
	 * @return
	 */
	function dgCellStyleAmsb(value, row, index) {
		if((value==null||value =='')){
			return 'background-color:#F5BB2A;color:#black;';
		}
		if(!checkForTimeCD(value,row.amsbtime)){
			return 'background-color:#E1643D;color:#black;';
		}else{
			return 'color:#black;';
		}
    	 
	}
	function dgCellStyleAmxb(value, row, index) {
		if((value==null||value =='')){
			return 'background-color:#F5BB2A;color:#black;';
		} 
    	if(!checkForTimeZT(value,row.amxbtime)||!checkForTimeCD(value,row.pmsbtime)){
			return 'background-color:#E1643D;color:#black;';
		}else{
			return 'color:#black;';
		}
    	 
	}
	function dgCellStylePmsb(value, row, index) {
		if((value==null||value =='')){
			return 'background-color:#F5BB2A;color:#black;';
		}
	 
    	if(!checkForTimeCD(value,row.pmsbtime)){
			return 'background-color:#E1643D;color:#black;';
		}else{
			return 'color:#black;';
		} 
	}
	function dgCellStylePmxb(value, row, index) {
		if((value==null||value =='')){
			return 'background-color:#F5BB2A;color:#black;';
		}
    	if(!checkForTimeZT(value,row.pmxbtime)){
			return 'background-color:#E1643D;color:#black;';
		}else{
			return 'color:#black;';
		}
	}
	
	//判断是否到打卡时间
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
	//判断是否到打卡时间
	function checkForTimeZT(systime,checkTime){
		if(systime==null){
			return true;
		}
		var a = '01/10/2015 ' + systime;
	    var b = '01/10/2015 ' + checkTime;
	    var d = new Date(a);
	    var e = new Date(b);
	  	if (d >= e) {
	  	   return true;
	  	} else {
	   	   return false;
	  	}
	}
	
	
	var exportExcel = function(){
		var rows = jq('#dg').datagrid('getRows');
		var map = new Map();
		map.put("rows", rows);
		var param = JSON.stringify(map);
		
		jq.ajaxSettings.async = false; 
        jq.getJSON(programName+'/query/queryAttend!exportExcel.action?param=' + param, function(datas) {
		//取出json的值
			option =  datas;
		});
        
	}
	
	 
	