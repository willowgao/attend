   jq(function() {
	 	loadData();
		jq('#commDesc').hide();
	});
 
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/workDeclare/upload!queryJobs.action?starttime=' + starttime + '&endtime=' + endtime
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
		var params =  jq('#jobAssignmentForm').serialize();
		jq.ajax( {
			url : programName + '/workDeclare/upload!queryJobs.action',
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
		var view = jq("#dg").data().datagrid.dc.view2;
		var bodyCell = view.find("div.datagrid-body td[field]")
		bodyCell.hover(function(){loadDiv(this);},function(){jq('#commDesc').hide();});

	}
	var tableId = '#dg';
	var editIndex = undefined;
	
	/**
	 * 判断完成率是否达到100%
	 * @return
	 */
	var checkProgress = function(){
		var bool = true;
		var rows = jq(tableId).datagrid('getRows');
		for(var i=0;i<rows.length;i++){
			var row = rows[i];
			if(row.progress != undefined){
			   if(parseFloat(row.progress) < 100){
				    jq.messager.alert('提示','工作进度未达到100%,不能上报!');
				    return false;
			   }
			}
		}
		return bool;
	}
	 
	

	/**
	 * 保存获取编辑的行时，需要先完成编辑状态
	 * @return
	 */
	function save(){
		var tableId = '#dg';
		jq(tableId).datagrid('endEdit', editIndex);

		if(!checkProgress()){
			return;
		}
		var url = getDataFromDatagrid(tableId) ;
		jq('#datagrid').val(url);
		var params =  jq('#jobAssignmentForm').serialize();
		jq.ajax( {
			url : programName + '/workDeclare/upload!saveJobs.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
			if(data=='0'){
				jq.messager.alert('提示','更新成功!');
				loadData();
			 }
			}
		});
	}
		   
	/**
	 * 显示悬浮窗口
	 * @param e 列
	 * @return
	 */
	function loadDiv(e){
		jq('#commDesc').html(e.innerText);
		e = window.event;
		xOffset = -23;
		yOffset = 13;
		jq("#commDesc").css("top",(e.pageY - xOffset) + "px").css("left",(e.pageX + yOffset) + "px").css("position","absolute").css("z-index","9999").show();
	}
	 
	