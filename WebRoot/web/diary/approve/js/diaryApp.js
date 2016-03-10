 //jQuery将$换成jq，避免冲突
var jq = jQuery.noConflict();
 jq(function() {
		loadData();
		jq('#dlg').dialog('close');
		jq('#commDesc').hide();
		//加载下拉表
		jq('#diarytype').combobox({    
			url:getDictionaryForCombox('DIARYTYPE'),
		    valueField:'id',    
		    textField:'text'   
		});  
		
	});
	
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
		var params =  jq('#diaryDailyForm').serialize();
		jq.ajax( {
			url : programName + '/diary/diaryApprove!queryDiarys.action',
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
	    jq('#dg').datagrid({
	        url: programName + '/diary/diaryApprove!queryDiarys.action?starttime=' + starttime + '&endtime=' + endtime+ '&diarytype=' + diarytype
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
		
		var  diaryid = row.diaryid;
		var  status = row.status;
		var  diarytype = row.diarytype;

		jq('#diaryid').attr('value',diaryid); 
		jq('#userid').attr('value',row.userid); 
		jq('#nowdiarytype').attr('value',diarytype); 
		jq('#content').textbox('setValue',row.content); 
		jq('#nextcontent').textbox('setValue',row.nextcontent); 
		jq('#comments').textbox('setValue',row.comments);
		
		//为日报，不显示下期计划
		if(diarytype=='1'){
			jq('#nextCont').hide();
			jq('#t_label').text('本日总结:');
		}else if(diarytype=='2'){
			jq('#t_label').text('本周总结:');
			jq('#nextCont').show();
		}else if(diarytype=='3'){
			jq('#t_label').text('本月总结:');
			jq('#nextCont').show();
		}else{
			jq('#t_label').text('本季总结:');
			jq('#nextCont').show();
		}
		//将窗口移动到固定的位置
		jq('#dlg').dialog('move',{
		    left: document.documentElement.offsetWidth/2-350,
		    top: document.documentElement.offsetTop,
		    right:"",
		    zIndex:jq.fn.window.defaults.zIndex++
		});
		
		jq('#status').combobox({
			url:programName + '/diary/diaryApprove!initAppStatus.action',
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
		var comments = jq('#comments').textbox('getValue'); 
		var diaryid = jq('#diaryid').val();
		var userid = jq('#userid').attr('value'); 
		var nowdiarytype =  jq('#nowdiarytype').attr('value'); 
		var status = jq('#status').combobox('getValue'); 
		//有下拉列表数据，但是未选择信息的
		if(jq('#status').combobox('getData')!=''&&status==''){
			  jq.messager.alert('警告','请选择审核状态!');
			  return ;
		}
		
		//有下拉列表数据，但是未选择信息的
		if(comments==''){
			  jq.messager.alert('警告','请填写审核意见!');
			  return ;
		}
		jq('#diaryDailyForm').form('submit', {    
		    url:programName + '/diary/diaryApprove!appDiarys.action?diaryid='+diaryid+'&userid='+userid+'&diarytype='+nowdiarytype+'&status='+status+'&comments='+comments,
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='0'){	
				   jq.messager.alert('提示','信息更新成功!');
				   //重新加列表信息
					jq('#diaryDailyForm').form('clear');
					jq('#dlg').dialog('close');
					loadData();
				}else{
					jq.messager.alert('警告','保存失败!');
				}
		    } 
		});
	}
	
	
	 
	