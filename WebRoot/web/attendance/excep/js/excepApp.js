 //jQuery将$换成jq，避免冲突
var jq = jQuery.noConflict();
 jq(function() {
		loadData();

		jq('#dlg').dialog('close');
		
		jq('#commDesc').hide();
		
	});
	
	//加载数据表
	function loadData() {
		var startTime = jq('#startTime').datebox('getValue');
		var endTime = jq('#endTime').datebox('getValue');
		jq('#dg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');
	}
	//查询列表数据
	function getData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#expForm').serialize();
		jq.ajax( {
			url : programName + '/clock/excepManager!getExpApproves.action',
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
	 * query 
	 */
	var query = function () { 
		var startTime = jq('#startTime').datebox('getValue');
		var endTime = jq('#endTime').datebox('getValue');
	    jq('#dg').datagrid({
	        url: programName + '/clock/excepManager!getExpApproves.action?startTime=' + startTime + '&endTime=' + endTime
	    });

	};
	
	var getApprove = function(){
		var rows = null;
		jq.ajaxSettings.async = false; 

		var expid = jq('#expid').val();  
		var params =  jq('#expForm').serialize();
		jq.ajax( {
			url : programName + '/clock/excepManager!getExpApproves.action?expid='+expid,
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
		jq("#commDesc").css("top",(e.pageY - xOffset) + "px").css("left",(e.pageX + yOffset) + "px").css("position","absolute").css("z-index","9999").fadeIn("slow");
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

		
		var  expid = row.expid;

		jq('#expid').attr('value',expid);  
		jq('#userid').attr('value',row.userid);  
		jq('#clockdate').attr('value',row.clockdate);  

		jq('#dlgdg').datagrid( {
			data : getApprove()
		}).datagrid('clientPaging');

		
		//将窗口移动到固定的位置
		jq('#dlg').dialog('move',{
		    left: document.documentElement.offsetWidth/2-350,
		    top: document.documentElement.offsetTop,
		    right:"",
		    zIndex:jq.fn.window.defaults.zIndex++
		});
		
		var reqUrl = programName + '/clock/excepManager!initStatus.action';
		//加载下拉表
		jq('#status').combobox({    
			url:reqUrl,
		    valueField:'id',    
		    textField:'text'   
		});  
	}
	
	
	/**
	 * 
	 * 保存数据
	 * @return
	 */
	function save(){
 
		var expid = jq('#expid').attr('value'); 
		var status = jq('#status').combobox('getValue');  
		var comments =  jq('#comments').textbox('getValue'); 
		var userid = jq('#userid').attr('value'); 
		var clockdate = jq('#clockdate').attr('value'); 
		var params =  jq('#expForm').serialize();
		var reqUrl = programName + '/clock/excepManager!saveExcepApprove.action?status='+status+'&expid='+expid+'&comments='+comments+'&userid='+userid+'&clockdate='+clockdate;
		jq.ajax( {
			url : reqUrl,
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
			if(data=='0'){	
				   jq.messager.alert('提示','信息更新成功!');
				   //重新加列表信息
					jq('#leaveForm').form('clear');
					jq('#dlg').dialog('close');
					loadData();
				}else{
					jq.messager.alert('警告','保存失败!');
				}
		    } 
		});
		 
	}
	
	
	 
	