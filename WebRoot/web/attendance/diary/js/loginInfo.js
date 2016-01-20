 //jQuery将$换成jq，避免冲突
	function getData(startTime,endTime) { 
		var rows = null;
		jq.ajaxSettings.async = false; 
		jq.getJSON(programName + '/diary/queryUserLoginInfo!queryLogins.action?startTime=' + startTime + '&endTime=' + endTime, function(re_datas) {
			rows = re_datas;
		});
		return rows;
	}

	jq(function() {
		loadData();
	});

	function loadData() {
		var startTime = jq('#startTime').datebox('getValue');
		var endTime = jq('#endTime').datebox('getValue');
		jq('#dg').datagrid( {
			data : getData(startTime,endTime)
		}).datagrid('clientPaging');
	}
	/**
	 * query 
	 */
	var query = function () {
		var startTime = jq('#startTime').datebox('getValue');
		var endTime = jq('#endTime').datebox('getValue');
	    jq('#dg').datagrid({
	        url: programName + '/diary/queryUserLoginInfo!queryLogins.action?startTime=' + startTime + '&endTime=' + endTime
	    });

	};
	
	
    
	var editIndex = undefined;
	/**
		切换行之后，取消行编辑状态
	**/
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if (jq('#dg').datagrid('validateRow', editIndex)) {
			var ed = jq('#dg').datagrid('getEditor', {
				index : editIndex,
				field : 'loginid'
			});
			var loginid = jq(ed.target).textbox('getText');
			jq('#dg').datagrid('getRows')[editIndex]['loginid'] = loginid;
			jq('#dg').datagrid('updateRow', {index:editIndex,row:{loginid:loginid}});
			jq('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	/**
	列双击之后处理编辑状态
	**/
	function onClickCell(index, field) {
		if (editIndex != index) {
			if (endEditing()) {
				jq('#dg').datagrid('selectRow', index).datagrid('beginEdit', index);
				var ed = jq('#dg').datagrid('getEditor', {
					index : index,
					field : field
				});
				(jq(ed.target).data('textbox') ? jq(ed.target).textbox('textbox') : $(ed.target)).focus();
				editIndex = index;
			} else {
				jq('#dg').datagrid('selectRow', editIndex);
			}
		}
	}
	/**
	 *多选的时候将所有选择的index添加到数组
	 择的
	 */
	function onClickRow(index, row) { 
		//是否存在已经选择的行
		if (selectIndex.toString().indexOf(index) == -1) {
		//不存在增加
			selectIndex.push(index);
		} else {
			for ( var i = 0; i < selectIndex.length; i++) {
				if (index == selectIndex[i]) {
				//存在移除
					selectIndex.splice(i, 1);
				}
			}
		}
	}