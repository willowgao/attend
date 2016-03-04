   jq(function() {
	 	loadData();
	 	loadIndexData(); 
	 	loadCombobox();
	 	changeDept();
	});
   

	var tableId = '#indextb';
	var editIndex = undefined;

    
	//加载数据表
	function loadData() {
		jq('#dg').datagrid( {
			data : getData()
		}).datagrid('clientPaging');
	}
	
	//加载数据表
	function loadIndexData() {
		jq('#indextb').datagrid( {
			data : getIndexData()
		}).datagrid('clientPaging');
	}
	
	
	/**
	 datagrid 切换行之后，取消行编辑状态
	**/
	function endEditing(){
		if (editIndex == undefined){return true}
		if (jq(tableId).datagrid('validateRow', editIndex)){
			jq(tableId).datagrid('endEdit', editIndex);
			setMergeCell();
			setScore();
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 打分判断，设置得分
	 * @return
	 */
	var setScore = function(){
		var score  = 0;
		var rows = jq(tableId).datagrid('getRows');
		for(var i=0;i<rows.length;i++){
			var row = rows[i];
			if(row.indexscore != undefined){
			   if(parseFloat(row.indexscore)>parseFloat(row.indexScore)){
				    jq.messager.alert('提示','得分不能大于标准分!');
				    break;
			   }
			  score = parseFloat(score)+ parseFloat(row.indexscore);
			}
		}
		jq('#score').text(score);
	}
	
	
	var changeDept = function(){
		//easyui下拉列表onchange事件，修改主题风格
		jq('#deptid').combobox({
			onSelect:function(record){	
				loadData();
			}
		});  
		
	} 
	//查询列表数据
	function getIndexData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params =  jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/assess/indexManage!queryIndex.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
			}
		});
		return rows;
	}
	
	
	//查询列表数据 
	function getData() { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		var params = jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/assess/performance!queryUsers.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
			}
		});
		return rows;
	}
	
	
	//加载下拉表
	var loadCombobox= function(){ 
		jq('#deptid').combobox({    
			url:getDeptByOrg(),
		    valueField:'id',    
		    textField:'text'   
		}); 
	}
	
	/**
	 *单选填充
	 */
	function onClickRow(index, row) { 
		var userid= jq('#userid').val(row.userid);
		var params = jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/assess/performance!queryAssess.action?userid='+userid,
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				if(data>1){
					jq.messager.alert('提示','已经存在您对此人员考核信息!');
				}
			}
		});
	}
	
	/** 
	 * 加载事件悬停div 
	 * @param data
	 * @return
	 */
	function onLoadSuccess(data){  
		setMergeCell();
	}
	
	/**
	 * 多行合并
	 */
	var setMergeCell = function(){
		var merges = [{
			index: 0,
			rowspan: 3
		},{
			index: 3,
			rowspan: 2
		},{
			index: 5,
			rowspan: 2
		},{
			index: 7,
			rowspan: 4
		}];
		for(var i=0; i<merges.length; i++){
			jq("#indextb").datagrid('mergeCells',{
				index: merges[i].index,
				field: 'item',
				rowspan: merges[i].rowspan
			});
		}
		
	}
	var setRowStyle = function(value, row, index){
		   return 'height:100px;';
	} 
	
	var checkScore = function(){

		endEditing();
		//获取列表修改的内容
		var url = getDataFromDatagrid(tableId) ;
		jq.ajaxSettings.async = false; 

		var userid = jq('#userid').val();
		jq('#datagrid').val(url);

		var updateRow = jq(tableId).datagrid('getChanges', "updated"); 
		if(updateRow.length!=12){
			jq.messager.alert('提示','请完成所有打分项评分!');
			return;
		}
		
		if(userid==''){
			jq.messager.alert('提示','未选择被考核人员，请先选择!');
			return;
		}
		
		jq.messager.confirm('确认','请您确认打分是否完成？',function(r){    
		    if (r){    
		    	save(); 
		    }else{  
		    	return;
		    }
		});  

		
	}
	
	/**
	 * 保存表单和列表修改的数据
	 * @return
	 */
	var save = function(){
		var rows = null;

		var score = jq('#score').text();
		if(parseFloat(score)>100){
			jq.messager.alert('提示','总分不能超过100!');
			return;
		}
		//获取表单修改的内容
		var params =  jq('#assessForm').serialize();
		jq.ajax( {
			url : programName + '/assess/performance!save.action',
			type : 'post',
			data : params,
			dataType : 'json',
			success : function(data) {
				rows = data;
				if(data=='0'){
					jq.messager.alert('提示','评分成功!');
					loadIndexData();
				}else{
					jq.messager.alert('提示','评分失败!');
				}
			}
		});
	}
	