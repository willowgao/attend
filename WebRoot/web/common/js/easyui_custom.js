var programName = '/xnAttendance';
var jq = jQuery.noConflict();//jQuery将$换成jq，避免冲突

/**
 * 系统样式初化
 * @return
 */

jq(document).ready(function() {
	doSysStyle();
});

//角色类型 普通人员
var ROLETYPE_NOMARL = "1";
//角色类型  初审人员
var ROLETYPE_TRIAL = "2";
// 角色类型 一级审核
var ROLETYPE_FIRST = "3";
// 角色类型 二级审核
var ROLETYPE_SECOND = "4";
// 角色类型 三级审核
var ROLETYPE_THIRD = "5";

/**
 * easyui datagrid单元格实现溢出文本显示省略号的效果。
 * 需要同/web/common/css/label.css 一同引用
 * @param value
 * @param row
 * @param index
 * @return
 */
function datagridcell(value,row,index){  
	if(value==null||value=='null'){
		return "<span title=''></span>";
	}else{
		 return '<span title='+value+'>'+value+'</span>' ;
	}
}  

/**
 * 系统样式初化
 * @return
 */
var doSysStyle = function(){
	if(parent.document.getElementById('sysstyle')!=null){
	    var peo = parent.document.getElementById('sysstyle').value;
	    if(peo!=''&&peo!=null&&peo!='null'){
	    	if(jq("#link_easyui_css")!=null){
	    		jq("#link_easyui_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui.css");
	    		jq("#link_easyui_layout_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/layout.css");
	    	}
	    }
	}
}

/**
 * 日期格式化
 * 
 * @param date
 * @return
 */
function fn_Dateformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}

/**
 * dwr 设置为同步，异步调用获取不到数据
 * 根据KEY获取数据字典中的值
 * @return
 */
function getDictionaryDesc(datatype){
	javascript:dwr.engine.setAsync(false);
	var orgmap = DataDictionaryService.getJsonStrByKey(datatype);
	javascript:dwr.engine.setAsync(true);  
	return jq.parseJSON(orgmap);
}

/**
 * 查询数据字典到下拉列表公共方法
 * @param datatype
 * @return
 */
function getDictionaryForCombox(datatype){
	return programName + '/authority/sysDataDictionary!getDictionary.action?key='+datatype;
} 

/**
 * 根据角色信息查询用户
 * @param roleType
 * @return
 */
function getUsersForCombox(roleType){
	return programName + '/authority/roleManager!getUserForApprove.action?roleType='+roleType;
} 



/**
 * 日期格式化
 * 
 * @param s
 * @return
 */
function fn_DateParser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
//是否有效(0：是，1：否)
function foratterEnable(s){
	var enableMap = getDictionaryDesc('ISENABLE');
	return enableMap[s];
}

/**
 *组织机构
 */
function formatterOrg(s){
	var dataMap = getDictionaryDesc('ORG');
	return dataMap[s];
}
/**
 * 组织类型
 * @param s
 * @return
 */
function formatterOrgType(s){
	var dataMap = getDictionaryDesc('ORGTYPE');
	return dataMap[s];
}

/**
 * 角色类型
 * @param s
 * @return
 */
function formatterRole(s){
	var dataMap = getDictionaryDesc('ROLETYPE');
	return dataMap[s];
}
/**
 * 部门编号
 * @param s
 * @return
 */
function formatterDept(s){
	var dataMap = getDictionaryDesc('DEPT');
	return dataMap[s];
}
/**
 * 核审状态
 * @param s
 * @return
 */
function formatterStatus(s){
	var dataMap = getDictionaryDesc('APPROVER_STATUS');
	return dataMap[s];
}
/**
 * 假期类型
 * @param s
 * @return
 */
function formatterLeave(s){
	var dataMap = getDictionaryDesc('LEAVETYPE');
	return dataMap[s];
}
/**
 * 用户
 * @param s
 * @return
 */
function formatterUser(s){
	var dataMap = getDictionaryDesc('USER');
	return dataMap[s];
}
/**
 * 异常类型
 * @param s
 * @return
 */
function formatterExcep(s){
	var dataMap = getDictionaryDesc('CLOCK_EXCEPTION_TYPE');
	return dataMap[s];
}

/**
 * 日志类型
 * @param s
 * @return
 */
function formatterDiary(s){
	var dataMap = getDictionaryDesc('DIARYTYPE');
	return dataMap[s];
}

/**
 * 工作类型
 * @param s
 * @return
 */
function formatterJob(s){
	var dataMap = getDictionaryDesc('JOBTYPE');
	return dataMap[s];
}
/**
 * 性别类型
 * @param s
 * @return
 */
function formatterSex(s){
	var dataMap = getDictionaryDesc('SEXTYPE');
	return dataMap[s];
}
/**
 * 字符日期、转化为date类型
 * @param strDate yyyy-mm-dd
 * @return
 */
function tranferStr2Date(strDate){
 return	new Date(strDate.replace("-","/"));
}


/**
 * 比较两个日期大小
 * startTime  >  endTime返回 true,反之返回false
 * @param startTime yyyy-mm-dd
 * @param endTime  yyyy-mm-dd
 * @return
 */
function compare2Date(startTime,endTime){
  if(startTime > endTime){
	  return true;
  }
  return false;
}
/**
 * datagrid ���format
 */
(function($) {
	function pagerFilter(data) {
		if ($.isArray(data)) { // is array
			data = {
				total : data.length,
				rows : data
			}
		}
		var dg = $(this);
		var state = dg.data('datagrid');
		var opts = dg.datagrid('options');
		if (!state.allRows) {
			state.allRows = (data.rows);
		}
		var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = $.extend(true, [], state.allRows.slice(start, end));
		return data;
	}

	var loadDataMethod = $.fn.datagrid.methods.loadData;
	$.extend($.fn.datagrid.methods, {
		clientPaging : function(jq) {
			return jq.each(function() {
				var dg = $(this);
				var state = dg.data('datagrid');
				var opts = state.options;
				opts.loadFilter = pagerFilter;
				var onBeforeLoad = opts.onBeforeLoad;
				opts.onBeforeLoad = function(param) {
					state.allRows = null;
					return onBeforeLoad.call(this, param);
				}
				dg.datagrid('getPager').pagination( {
					onSelectPage : function(pageNum, pageSize) {
						opts.pageNumber = pageNum;
						opts.pageSize = pageSize;
						$(this).pagination('refresh', {
							pageNumber : pageNum,
							pageSize : pageSize
						});
						dg.datagrid('loadData', state.allRows);
					}
				});
				$(this).datagrid('loadData', state.data);
				if (opts.url) {
					$(this).datagrid('reload');
				}
			});
		},
		loadData : function(jq, data) {
			jq.each(function() {
				$(this).data('datagrid').allRows = null;
			});
			return loadDataMethod.call($.fn.datagrid.methods, jq, data);
		},
		getAllRows : function(jq) {
			return jq.data('datagrid').allRows;
		}
	})
})(jQuery);

/**
 datagrid删除
 */
var selectIndex = new Array();
function removeit(tableId){
	if(selectIndex.length ==0&&editIndex == undefined){return}
	if(editIndex!= undefined){
		jq(tableId).datagrid('cancelEdit', editIndex)
				.datagrid('deleteRow', editIndex);
	}else{
		for(var i=0;i<selectIndex.length;i++){
			var rowindex = selectIndex[i];
			if(i>0){
				rowindex = rowindex - i;
			}
			jq(tableId).datagrid('cancelEdit', rowindex)
			.datagrid('deleteRow', rowindex);
		}
	}
	editIndex = undefined;
}

/**
 datagrid 切换行之后，取消行编辑状态
**/
function endEditing(){
	if (editIndex == undefined){return true}
	if (jq(tableId).datagrid('validateRow', editIndex)){
		jq(tableId).datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

/**
 * datagrid新增
 * @param tableId
 * @return
 */
function add(tableId){
	if (endEditing()){
		//新增时写入默认值
		jq(tableId).datagrid('appendRow',{});
		editIndex = jq(tableId).datagrid('getRows').length-1;
		jq(tableId).datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
}
/**
 datagrid 列双击之后处理编辑状态
**/
function onClickCell(index, field){
	if (editIndex != index){
		if (endEditing()){
			jq(tableId).datagrid('selectRow', index)
					.datagrid('beginEdit', index);
			var ed = jq(tableId).datagrid('getEditor', {index:index,field:field});
			(jq(ed.target).data('textbox') ? jq(ed.target).textbox('textbox') : jq(ed.target)).focus();
			editIndex = index;
		} else {
			jq(tableId).datagrid('selectRow', editIndex);
		}
	}
}


/**
 * 保存
 * @return
 */
function accept(tableId){
	if (endEditing()){
		jq(tableId).datagrid('acceptChanges');
	}
}
/**
	重重置表格信息
**/

function reject(tableId){
	jq(tableId).datagrid('rejectChanges');
	editIndex = undefined;
}
/**
 * 获取变更信息
 * @return
 */
function getChanges(tableId){
	var rows = jq(tableId).datagrid('getChanges');
	alert(rows.length+' rows are changed!');
}
 

/**
 * datagrid日期格式化
 * @param val
 * @param row
 * @return
 */

function formatterdate(val, row) {
	if (val != null) {
		val = val.replace('T',' ');
		var date = new Date(val);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		if(month <10){
			month = "0" + month;
		}  
		var day = date.getDate();
		if(day < 10){
			day = "0"+day;
		}
		return year + '-' +month+ '-'+ day+" "+val.substring(val.indexOf(" ")+1,val.length);
	}
}



/**
 * datagrid日期格式化
 * @param val
 * @param row
 * @return
 */

function formatterdateYMD(val, row) {
	if (val != null) {
		val = val.replace('T',' ');
		var date = new Date(val);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		if(month <10){
			month = "0" + month;
		}  
		var day = date.getDate();
		if(day < 10){
			day = "0"+day;
		}
		return year + '-' +month+ '-'+ day;
	}
}

/**
 * 日期格式化  formatterYM
 * @param date
 * @return
 */
function formatterYM(date){
	if (!date){return '';}
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	return y + '-' + (m<10?('0'+m):m);
}

/**
 * 
 * @param s
 * @return
 */
function parserYM(s){
	if (!s){return null;}
	var ss = s.split('-');
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	if (!isNaN(y) && !isNaN(m)){
		return new Date(y,m-1,1);
	} else {
		return new Date();
	}
}


Array.prototype.remove = function(s) {     
    for (var i = 0; i < this.length; i++) {     
        if (s == this[i])     
            this.splice(i, 1);     
    }     
}     
    
/**   
 * Map 对象 
 *    
 *    
 * var m = new Map();   
 * m.put('key','value');   
 * ...   
 * var s = "";   
 * m.each(function(key,value,index){   
 *      s += index+":"+ key+"="+value+"/n";   
 * });   
 * alert(s);   
 *    
 * @author dewitt   
 * @date 2008-05-24   
 */    
function Map() {     
    /** 存放键的数组(遍历用到) */    
    this.keys = new Array();     
    /** 存放数据 */    
    this.data = new Object();     
         
    /**   
     * 放入一个键值对   
     * @param {String} key   
     * @param {Object} value   
     */    
    this.put = function(key, value) {     
        if(this.data[key] == null){     
            this.keys.push(key);     
        }     
        this.data[key] = value;     
    };     
         
    /**   
     * 获取某键对应的值   
     * @param {String} key   
     * @return {Object} value   
     */    
    this.get = function(key) {     
        return this.data[key];     
    };     
         
    /**   
     * 删除一个键值对   
     * @param {String} key   
     */    
    this.remove = function(key) {     
        this.keys.remove(key);     
        this.data[key] = null;     
    };     
         
    /**   
     * 遍历Map,执行处理函数   
     *    
     * @param {Function} 回调函数 function(key,value,index){..}   
     */    
    this.each = function(fn){     
        if(typeof fn != 'function'){     
            return;     
        }     
        var len = this.keys.length;     
        for(var i=0;i<len;i++){     
            var k = this.keys[i];     
            fn(k,this.data[k],i);     
        }     
    };     
         
    /**   
     * 获取键值数组(类似Java的entrySet())   
     * @return 键值对象{key,value}的数组   
     */    
    this.entrys = function() {     
        var len = this.keys.length;     
        var entrys = new Array(len);     
        for (var i = 0; i < len; i++) {     
            entrys[i] = {     
                key : this.keys[i],     
                value : this.data[i]     
            };     
        }     
        return entrys;     
    };     
         
    /**   
     * 判断Map是否为空   
     */    
    this.isEmpty = function() {     
        return this.keys.length == 0;     
    };     
         
    /**   
     * 获取键值对数量   
     */    
    this.size = function(){     
        return this.keys.length;     
    };     
         
    /**   
     * 重写toString    
     */    
    this.toString = function(){     
        var s = "{";     
        for(var i=0;i<this.keys.length;i++,s+=','){     
            var k = this.keys[i];     
            s += k+"="+this.data[k];     
        }     
        s+="}";     
        return s;     
    };     
} 

/**
 * 获取datagrid table表格信息</br>
 * 返回带key为insertRow,deleteRow,updateRow json对象
 * @param tableId
 * @return url
 */
function getDataFromDatagrid(tableId){
	 var insertRow = jq(tableId).datagrid('getChanges', "inserted");
	 var deleteRow = jq(tableId).datagrid('getChanges', "deleted");
	 var updateRow = jq(tableId).datagrid('getChanges', "updated"); 
	 var map = new Map();
	 map.put("insertRow", insertRow);
	 map.put("deleteRow", deleteRow);
	 map.put("updateRow", updateRow);
	 var param = JSON.stringify(map);
	return param;
}
