 //jQuery将$换成jq，避免冲突
	var jq = jQuery.noConflict();
	jq(function() {
		initDiary();
		loadCombobox();
		jq('#dlg').dialog('close');
		initForm();
		 
		
	});
	/**
	 * 初始日志格式
	 */
	var  initDiary = function(){
		var diarytype = jq('#diarytype').val();
		if(diarytype=='1'){
			jq('#content').textbox('setValue','上午 08：00-09：00  下午 13：00-17：00');
		} 
	}
	/**
	 * 初始化Form
	 * @return
	 */
	var initForm = function(){
		//两种方法都可
		//jq('#diaryDailyForm').form('load',programName + '/diary/diaryDaily!initDiary.action?diarytype='+diarytype);
		jq.ajaxSettings.async = false; 
		var params =  jq('#diaryDailyForm').serialize();
		jq.ajax( {
			url : programName + '/diary/diaryDaily!initDiary.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				jq('#diaryDailyForm').form('load',data);
				jq.each(data,function(idx,item){  
					 //输出 
					 if(idx=='diaryDaily.status'){
						jq('#status').combobox('setValue',item);
					 }  
				});
				
			}
		});
	}
	

	//加载下拉表
	var loadCombobox = function(){
		jq('#approver').combobox({    
			url:getUsersForCombox(ROLETYPE_TRIAL),
		    valueField:'id',    
		    textField:'text'   
		});  
		
		jq('#status').combobox({
			url:getDictionaryForCombox('APPROVER_STATUS'),
		    valueField:'id',    
		    textField:'text'  
		});
	}
	/**
	 * 窗口关闭事件
	 * @return
	 */
	var closeDialog = function(){
		jq('#commDesc').hide();
	}
	/**
	 * 查询历史弹出窗口
	 * @return
	 */
	var queryHistory = function(){
		//打开弹出窗口
		jq('#dlg').dialog('open');

		//将窗口移动到固定的位置
		jq('#dlg').dialog('move',{
		    left: document.documentElement.offsetWidth/2-550,
		    top: document.documentElement.offsetTop,
		    right:"",
		    zIndex:jq.fn.window.defaults.zIndex++
		});
		//填充弹出列表
		jq('#dlgdg').datagrid( {
			data : getDetailData()
		}).datagrid('clientPaging');
	}
	/**
	 * 分页查询
	 * @return
	 */
	var query = function () { 
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
		var diarytype = jq('#diarytype').val();
		jq('#dlgdg').datagrid({
	        url:  programName + '/diary/diaryDaily!queryDiarys.action?starttime=' + starttime + '&endtime=' + endtime+'&diarytype='+diarytype
	    });
	}
	/**
	 * 查询历史数据
	 * @return
	 */
	var getDetailData = function () { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#diaryDailyForm').serialize();
		jq.ajax( {
			url : programName + '/diary/diaryDaily!queryDiarys.action',
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
		var view = jq("#dlgdg").data().datagrid.dc.view2;
		var bodyCell = view.find("div.datagrid-body td[field]");
		bodyCell.hover(function(){loadDiv(this);},function(){jq('#commDesc').hide();});
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
	
	/**
	 * 
	 * 保存数据
	 * @return
	 */
	function save(){
		jq('#diaryDailyForm').form('submit', {    
		    url:programName + '/diary/diaryDaily!save.action?',
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='0'){	
				   jq.messager.alert('提示','日志信息填报成功!');
		    	}else{
		    	   jq.messager.alert('警告','日志信息更新失败，可能已被审核!')
		    	}
		    } 
		});
	}