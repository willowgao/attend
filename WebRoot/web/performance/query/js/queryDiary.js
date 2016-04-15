 //jQuery将$换成jq，避免冲突
 	jq(function() {
		loadData(); 
		jq('#dlg').dialog('close');
		jq('#commDesc').hide();
		loadCombobox();
		jq('#fontsize').val(parent.document.getElementById('fontsize').value);
	});
	
	//加载数据表
	function loadData() {
		var startTime = jq('#starttime').datebox('getValue');
		var endTime = jq('#endtime').datebox('getValue');
		jq('#dg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');
	}
	 var loadCombobox = function(){
		//加载下拉表
		jq('#diarytype').combobox({    
			url:getDictionaryForCombox('DIARYTYPE'),
		    valueField:'id',    
		    textField:'text'   
		});  
		
		jq('#userid').combobox({
			url: programName + '/query/queryDiarys!getUsersFormUser.action',
		    valueField:'id',    
		    textField:'text'  
		});
		

		jq('#deptid').combobox({
			url:getDeptByOrg(),
		    valueField:'id',    
		    textField:'text'  
		});
		
	}
	//查询列表数据
	function getData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#diaryDailyForm').serialize();
		jq.ajax( {
			url : programName + '/query/queryDiarys!queryDiarys.action',
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
    	var starttime = jq('#starttime').datebox('getValue');
		var endtime = jq('#endtime').datebox('getValue');
		var diarytype = jq('#diarytype').combobox('getValue');
		var userid = jq('#userid').combobox('getValue');
		var deptid = jq('#deptid').combobox('getValue');
	    jq('#dg').datagrid({
	        url: programName + '/query/queryDiarys!queryDiarys.action?starttime=' + starttime + '&endtime=' + endtime+ '&diarytype=' + diarytype+ '&userid=' + userid+ '&deptid=' + deptid
	    });
    }
	 
	/** 
	 * 加载事件悬停div 
	 * @param data
	 * @return
	 */
	function onLoadSuccess(data){ 
		var view = jq("#dg").data().datagrid.dc.view2;
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
	 * 双击行，弹窗
	 * @param index
	 * @param row
	 * @return
	 */
	function onDblClickRow(index, row){
		//打开弹出窗口
		jq('#dlg').dialog('open');
		jq('#commDesc').hide();
		//将窗口移动到固定的位置
		jq('#dlg').dialog('move',{
		    left: document.documentElement.offsetWidth/2-550,
		    top: document.documentElement.offsetTop/2,
		    right:"",
		    zIndex:jq.fn.window.defaults.zIndex++
		});

		jq('#content').val(row.content);
		jq('#nextcontent').val(row.nextcontent);
		jq('#diaryid').val(row.diaryid);
		var params =  jq('#diaryDailyForm').serialize();

		jq('#diary').attr('src',programName + '/diary/diaryDiscuss!goDetail.action?'+params);
		jq('#discuss').attr('src',programName +  '/diary/diaryDiscuss!goDiscussDetail.action?'+params);
		//
		jq.getJSON(programName + '/diary/diaryDiscuss!addViewCount.action?diaryid='+row.diaryid); 
		
	}
	
	 
	