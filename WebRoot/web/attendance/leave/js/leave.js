 //jQuery将$换成jq，避免冲突
var jq = jQuery.noConflict();
 jq(function() {
		loadData();
		jq('#dlg').dialog('close');
		jq('#commDesc').hide();
		//加载下拉表
		jq('#approver').combobox({    
			url:getUsersForCombox(),
		    valueField:'id',    
		    textField:'text'   
		});  

		//加载下拉表
		jq('#leavetype').combobox({    
			url:getDictionaryForCombox('LEAVETYPE'),
		    valueField:'id',    
		    textField:'text'   
		});  
		
		//easyui下拉列表onchange事件，修改主题风格
		jq('#leavetype').combobox({
			onSelect:function(record){
				var peo = jq('#leavetype').combobox('getValue');
				if(peo=='1'){
					jq('#destination').textbox({ required: true });
				}else{
					jq('#destination').textbox({ required: false });
				}
			}
		}); 
		
	});
 
 	/**
 	 * 页面分页查询
 	 * @return
 	 */
    var query = function(){
    	var startdate = jq('#startdate').datebox('getValue');
		var enddate = jq('#enddate').datebox('getValue');
		var leavetype = jq('#leavetype').combobox('getValue');
		var approver = jq('#approver').combobox('getValue');
	    jq('#dg').datagrid({
	        url:  programName + '/leave/leaveManager!queryLeaves.action?startdate=' + startdate + '&enddate=' + enddate+'&leavetype=' + leavetype+ '&approver=' + approver
	    });
    }
	
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
			url : programName + '/leave/leaveManager!queryLeaves.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
			}
		});
		return rows;
	}
	//删除ID数组定义
	var ids = new Array();
	/**
	 * 删除数据
	 * @return
	 */
	function del(){
		var id = ids.join(',');
		if(id==''){
			jq.messager.alert('提示','此最少选择一条记录!');
			return;
		}
		jq.ajaxSettings.async = false; 
		jq.getJSON(programName + '/leave/leaveManager!delLeave.action?ids=' + id, function(re_datas) {
			if(re_datas!='0'){
				jq.messager.alert('提示','删除成功!');
			}else{
				jq.messager.alert('警告','删除失败!');
			}
		});
		jq('#leaveForm').form('clear');
		query();
	}
	/**
	 * 将未选择的idd删除
	 * @param index
	 * @param row
	 * @return
	 */
	function onUnselect(index, row){
		for(var  i=0;i<ids.length;i++){
			if(ids[i]==row.leaveid){
				//数据存在则移除
				ids.splice(i);
				break;
			}
		} 
	}
	/**
	 * 将选择的id写到数据中去
	 * @param index
	 * @param row
	 * @return
	 */
	function onSelect(index, row){
		var count = 0;
		for(var  i=0;i<ids.length;i++){
			if(ids[i]==row.leaveid){
				count++;
			}
		}
		//如果在数组中没有，则增加到数组中去
		if(count==0){
			ids.push(row.leaveid);
		}
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
	
	//查询列表数据
	function getDetailData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var leaveid = jq('#leaveid').val();
		var params =  jq('#leaveForm').serialize();
		jq.ajax( {
			url : programName + '/leave/leaveManager!queryLeavesApprover.action?leaveid='+leaveid,
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
	 * 双击行，弹窗
	 * @param index
	 * @param row
	 * @return
	 */
	function onDblClickRow(index, row){
		//打开弹出窗口
		jq('#dlg').dialog('open');

		jq('#leaveid').attr('value',row.leaveid);
		
		//将窗口移动到固定的位置
		jq('#dlg').dialog('move',{
		    left: document.documentElement.offsetWidth/2-350,
		    top: document.documentElement.offsetTop,
		    right:"",
		    zIndex:jq.fn.window.defaults.zIndex++
		});
		
		jq('#dlgdg').datagrid( {
			data : getDetailData()
		}).datagrid('clientPaging');
	}
	
	
	/**
	 * 
	 * 保存数据
	 * @return
	 */
	function save(){
		jq('#leaveForm').form('submit', {    
		    url:programName + '/leave/leaveManager!leave.action',
		    onSubmit: function(){  
		       return jq(this).form('validate');
		    },
		    success:function(data){    
		    	if(data=='0'){	
				   jq.messager.alert('提示','信息更新成功!');
				   //重新加列表信息
					jq('#leaveForm').form('clear');
					query();
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
	 
	