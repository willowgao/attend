 //jQuery将$换成jq，避免冲突
var jq = jQuery.noConflict();
 jq(function() {
		loadData();

		jq('#dlg').dialog('close');
		
		jq('#commDesc').hide();
		//加载下拉表
		jq('#leavetype').combobox({    
			url:getDictionaryForCombox('LEAVETYPE'),
		    valueField:'id',    
		    textField:'text'   
		});  
		
	});
	
	//加载数据表
	function loadData() {
		var startTime = jq('#startdate').datebox('getValue');
		var endTime = jq('#enddate').datebox('getValue');
		jq('#dg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');
	}
	//查询列表数据
	function getData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#leaveForm').serialize();
		jq.ajax( {
			url : programName + '/leave/leaveManager!queryForApprover.action',
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
    	var startdate = jq('#startdate').datebox('getValue');
		var enddate = jq('#enddate').datebox('getValue');
		var leavetype = jq('#leavetype').combobox('getValue');
	    jq('#dg').datagrid({
	        url: programName + '/leave/leaveManager!queryForApprover.action?startdate=' + startdate + '&enddate=' + enddate+ '&leavetype=' + leavetype
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

		jq('#dlgdg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');

		
		var  leaveid = row.leaveid;
		var  status = row.status;
		var  leavetype = row.leavetype;

		jq('#leaveid').attr('value',leaveid); 
		jq('#nowstatus').attr('value',status); 
		jq('#nowleavetype').attr('value',leavetype); 
		
		//将窗口移动到固定的位置
		jq('#dlg').dialog('move',{
		    left: document.documentElement.offsetWidth/2-350,
		    top: document.documentElement.offsetTop,
		    right:"",
		    zIndex:jq.fn.window.defaults.zIndex++
		});
		var reqUrl = programName + '/leave/leaveManager!initApprover.action?leavetype='+leavetype+'&status='+status+'&userid='+row.userid;
		//加载下拉表 应该为下级审核人员
		jq('#approver').combobox({    
			url:reqUrl,
		    valueField:'id',    
		    textField:'text'   
		});  
		reqUrl = programName + '/leave/leaveManager!initAppStatus.action?leaveid='+leaveid+'&status='+status+'&leavetype='+leavetype;
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
 
		var leaveid = jq('#leaveid').attr('value'); 
		var approverid = jq('#approver').combobox('getValue');
		//有下拉列表数据，但是未选择信息的
		if(jq('#approver').combobox('getData')!=''&&approverid==''){
			  jq.messager.alert('警告','请选择下步审核人员!');
			  return ;
		}
		
		var nowstatus = jq('#status').combobox('getValue'); 
		//有下拉列表数据，但是未选择信息的
		if(jq('#status').combobox('getData')!=''&&nowstatus==''){
			  jq.messager.alert('警告','请选择审核状态!');
			  return ;
		}
		
		var nowleavetype =  jq('#nowleavetype').attr('value'); 
		var comments =  jq('#comments').textbox('getValue'); 
		//有下拉列表数据，但是未选择信息的
		if(comments==''){
			  jq.messager.alert('警告','请填写审核意见!');
			  return ;
		}
		var params =  jq('#expForm').serialize();
		var reqUrl = programName + '/leave/leaveManager!saveApprove.action?approverid='+approverid+'&nowleavetype='+nowleavetype+'&nowstatus='+nowstatus+'&leaveid='+leaveid+'&comments='+comments;
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
	
	
	 
	