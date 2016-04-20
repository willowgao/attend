 //jQuery将$换成jq，避免冲突
	var jq = jQuery.noConflict();
	/**
	 * 系统样式初化
	 * @return
	 */
	var initSysStyle = function(){
	    var peo = jq('#sysstyle').val();
	    if(peo!=''&&peo!=null&&peo!='null'){
			jq("#link_easyui_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui.css");
			jq("#link_easyui_layout_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/layout.css");

			jq("iframe").contents().find('#link_easyui_css').attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui.css");
			jq("iframe").contents().find('#link_easyui_layout_css').attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui.css");
	    }
	}
	
	/**
	 * 系统样式初化
	 * @return
	 */
	var changeFont = function (){
		var link_easyui_css = jq("#link_easyui_css").attr("href");
		var link_easyui_css_content = jq(parent.document).contents().find('#link_easyui_css').attr("href");
		var ztree_style = jq("#ztree_style").attr("href");
	    var peo = jq('#fontsize').val();
		if(peo!=null){
			if(peo=='smaller'){
				//easyui下拉列表onchange事件，修改主题风格
				jq("#link_easyui_css").attr("href",link_easyui_css.replace("easyui.css","easyui12.css"));
				jq(parent.document).contents().find('#link_easyui_css').attr("href",link_easyui_css_content.replace("easyui.css","easyui12.css"));
				jq("#ztree_style").attr("href",ztree_style.replace("metroStyle.css","metroStyle12.css"));
				jq(parent.document).contents().find('ztree_style').attr("href",ztree_style.replace("metroStyle.css","metroStyle12.css"));
				
			}else{
				//easyui下拉列表onchange事件，修改主题风格
				jq("#link_easyui_css").attr("href",link_easyui_css.replace("easyui12.css","easyui.css"));
				jq(parent.document).contents().find('#link_easyui_css').attr("href",link_easyui_css_content.replace("easyui12.css","easyui.css"));
			}
		}
	}
	/**
	 固定可移动的窗口
	**/
	function onDrag(e){
		var d = e.data;
		if (d.left < 0){d.left = 0}
		if (d.top < 0){d.top = 0}
		if (d.left + jq(d.target).outerWidth() > jq(d.parent).width()){
			d.left = jq(d.parent).width() - jq(d.target).outerWidth();
		}
		if (d.top + jq(d.target).outerHeight() > jq(d.parent).height()){
			d.top = jq(d.parent).height() - jq(d.target).outerHeight();
		}
	}

	//展开全部树
	function expandAll() {
		var zTree = jq.fn.zTree.getZTreeObj("orgTree");
		//默认展开全部节点
		zTree.expandAll(true);
	}
	//tabs打开新页面，已经存在的，就选择
	function addTab(title, url){    
	    if (jq('#mytabs').tabs('exists', title)){    
	        jq('#mytabs').tabs('select', title);    
	    } else {    
	        var content = "<iframe id ='content' scrolling='auto' frameborder='0'  src='"+url+"' style='width:100%;height:100%;'></iframe>";    
	        jq('#mytabs').tabs('add',{    
	            title:title,    
	            content:content,    
	            closable:true
	        });    
	        
	      /**获取最后一个tabs 在新加的选项卡后面添加"关闭全部"
	        var li = jq(".tabs-wrap ul li:last-child");
	        jq("#close").remove();
	        li.after("<li id='close'><a class='tabs-inner' href='javascript:void()' onClick='javascript:closeAll()'>关闭全部</a></li>");
	      **/
      }
    } 
	
	var changeStyle = function(){
	    var fontsize = jq('#fontsize').val();
		//easyui下拉列表onchange事件，修改主题风格
		jq('#cob_cssselector').combobox({
			onSelect:function(record){
			var peo = jq('#cob_cssselector').combobox('getValue');
			if(fontsize=='bigger'){
				jq("#link_easyui_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui.css");
				jq("#link_easyui_layout_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/layout.css");
				//获取随机数
				var colorNum = Math.round(Math.random() * 8);
				jq("iframe").contents().find('#link_easyui_css').attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui.css");
				jq("iframe").contents().find('#link_easyui_layout_css').attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui.css");
			}else{
				jq("#link_easyui_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui12.css");
				jq("#link_easyui_layout_css").attr("href",programName+"/web/common/easyui/themes/"+peo+"/layout.css");
				//获取随机数
				var colorNum = Math.round(Math.random() * 8);
				jq("iframe").contents().find('#link_easyui_css').attr("href",programName+"/web/common/easyui/themes/"+peo+"/easyui12.css");
				jq("iframe").contents().find('#link_easyui_layout_css').attr("href",programName+"/web/common/easyui/themes/"+peo+"/layout.css");
				
			}
			var classStr = "easyui-linkbutton c"+colorNum+" l-btn l-btn-small l-btn-plain";
			jq("[href='#']").attr("class",classStr);
			
			}
		});  
		
	}
	 
	var fristMenuId = null;
	var initPage = null;
	jq(document).ready(function() {
		
		//查询顶部的模块菜单
		queryMenu();
		//查询左边的菜单树
		queryTree(fristMenuId);
		jq('#commDesc').hide();
		jq('#tab_rightmenu').hide();
		
		jq('#topic').mouseover(onTopic);
		jq('#topic').mouseout(hidDiv);
		jq('#topic').click(queryWait);
		
		//5分钟刷新一次 待办和打卡区域的刷新
		setInterval(refreshLayout,10000*30);

		//10毫秒后初始化页面
		initPage = setInterval(initWait,10);
		
	});
	 
	var waitcount = 0;
	/**
	 * 对首页表格数据进行延时加载
	 * @return
	 */
	var initWait = function(){
		if(waitcount==0){
			//初始化系统设置打卡时间
			queryTimesForNow();
			//初始化打卡记录
			queryClockRecords();
			loadData();
			//加载右键工具拦
			loadTabTools();
			//查询待办
			queryWait();
			
			initSysStyle();
			changeStyle();
			changeFont();
			
			waitcount ++;
		}else{
		  //只执行一次定时任务，第二次进入时关闭任务
		  clearInterval(initPage);
		}
	}
	/**
	 * 定时查询待办任务在打卡的信息
	 * @return
	 */
	var refreshLayout = function(){
		//刷新待办事项
		queryWait();
		//刷新打卡区域
		queryClockRecords();
	}
	
		//tree初始化设置
	var setting = {
		check : {
			//enable: true 
			enable : false
		},
		data : {
			key : {
				name : "menuname",
				url:"action"
			},
			simpleData : {
				enable : true,
				idKey : "menuid",
				pIdKey : "parentid",
				rootPId : null
			}
		},
		edit:{
			enable:false
		},
		callback : {
			onClick : onClick 
		}
	};
	/**
	 设置系统当前时间所属区间的打卡时间
	**/
	function queryTimesForNow(){
		jq.getJSON( programName + '/clock/clockManager!queryTimesForNow.action', function(datas) {
			if(datas.amsbTime!=null){
				 jq('#amsbTime').val(datas.amsbTime);
				 jq('#amxbTime').val(datas.amxbTime);
				 jq('#pmsbTime').val(datas.pmsbTime);
				 jq('#pmxbTime').val(datas.pmxbTime);
				 
			 	//动态设置时间区间
				jq('#dg').datagrid({    
				    columns:[[    
				        {field:'amsbText',title:"上午上班("+datas.amsbTime+")",width:'25%',align:'center'},    
				        {field:'amxbText',title:"上午下班("+datas.amxbTime+")",width:'25%',align:'center'},    
				        {field:'pmsbText',title:"下午上班("+datas.pmsbTime+")",width:'25%',align:'center'},    
				        {field:'pmxbText',title:"下午下班("+datas.pmxbTime+")",width:'25%',align:'center'},    
				    ]]    
				});
			}
			//setValue(existsData);
		});
		
	}
	
	//查询菜单树
	function queryTree(fristMenuId) {
		jq.getJSON(programName+'/menu/menuTree!showTree.action?menuId='+fristMenuId, function(datas) {
			//初始化ztree数据
				jq.fn.zTree.init(jq("#orgTree"), setting, datas);
				//数据初始化之后展开
				expandAll();
		});
	}
	var existsData = null;
	var params = '{';
	//查询打卡记录
	function queryClockRecords(){
		params = '{';
		jq.getJSON(programName+'/clock/clockManager!getClockRecords.action',function(datas){
			existsData = datas;
			if(datas!=null){
				setValue(datas);
			}else{
				jq('#amsb').text('打卡');
				jq('#amxb').text('打卡');
				jq('#pmsb').text('打卡');
				jq('#pmxb').text('打卡');
				params = '{';
			}
		});
	}
	
	function setValue(existsData){
		if(existsData!=null){
			if(existsData.amsb!=null){
				jq('#amsb').text(existsData.amsb);
				jq('#amsb').removeAttr('onClick');
				params += 'amsb:\"'+existsData.amsb+'\",';
			}
			if(existsData.amxb!=null){
				jq('#amxb').text(existsData.amxb);
				jq('#amxb').removeAttr('onClick');
				params += 'amxb:\"'+existsData.amxb+'\",';
			}
			if(existsData.pmsb!=null){
				jq('#pmsb').text(existsData.pmsb);
				jq('#pmsb').removeAttr('onClick');
				params += 'pmsb:\"'+existsData.pmsb+'\",';
			}
			if(existsData.pmxb!=null){
				jq('#pmxb').text(existsData.pmxb);
				jq('#pmxb').removeAttr('onClick');
				params += 'pmxb:\"'+existsData.pmxb+'\",';
			}
		}
	}
	
    
    var checkTime = null;
    //判断是否到打卡时间
	function checkForTime(){
		var a = '01/10/2015 ' + systime;
	    var b = '01/10/2015 ' + checkTime;
	    var d = new Date(a);
	    var e = new Date(b);
	  	if (d > e) {
	  	   return false;
	  	} else {
	   	   return true;
	  	}
	}
	//打卡点击事件
	function fnClock(type){
		
	    var sysamsb = jq('#amsbTime').val();
	    var sysamxb = jq('#amxbTime').val();
	    var syspmsb = jq('#pmsbTime').val();
	    var syspmxb = jq('#pmxbTime').val();
	    
		if(type=='1'){
			params += 'amsb:\"'+systime+'\",'; 
			checkTime = sysamsb;
		}else if(type=='2'){ 
			params += 'amxb:\"'+systime+'\",';
			checkTime = sysamxb;
		}else if(type=='3'){ 
			params += 'pmsb:\"'+systime+'\",';
			checkTime = syspmsb;
		}else if(type=='4'){ 
			params += 'pmxb:\"'+systime+'\",';
			checkTime = syspmxb;
		}
		var isearly = false;
		if(checkForTime()&&(type=='2'||type=='4')){
			jq.messager.confirm('确认','未到打卡时间，是否打卡？',function(r){    
			    if (r){     
			    	params += 'checkTime:\"'+checkTime+'\",';
					params += 'type:\"'+type+'\",';
					
					params = params.substring(0,params.length-1)+'}';
					jq.get(programName+'/clock/clockManager!saveClock.action?params='+params,function(datas){
						queryClockRecords();
					});

					//刷新列表页面
					loadData();
			    }else{
					return;
			    }
			});  
		} else{
			params += 'checkTime:\"'+checkTime+'\",';
			params += 'type:\"'+type+'\",';
			params = params.substring(0,params.length-1)+'}';
			jq.get(programName+'/clock/clockManager!saveClock.action?params='+params,function(datas){
				queryClockRecords();
			});
		}
		//刷新列表页面
		loadData();
	}
	 
	//查询顶部的模块菜单
	function queryMenu() {
		jq.ajaxSettings.async = false; 
		jq.getJSON(programName+'/menu/menuTree!showMenu.action', function(datas) {
			//动态加载表头的模块菜单
			for(var i=0;i<datas.length;i++){
				var menu = datas[i];
				if(i==0){
					fristMenuId = menu.substring(menu.indexOf("_")+1,menu.length);
				}
				var menuId = menu.substring(menu.indexOf("_")+1,menu.length);
				var menuName = menu.substring(0,menu.indexOf("_"));
				jq('#menu_list').append("<a href='#' onclick='queryTree("+menuId+")' class='easyui-linkbutton c5 l-btn l-btn-small l-btn-plain' style='width: 120px; height:70px;border-radius: 15px;' group id ><span class='l-btn-left' style='margin-top:10px;'><span class='l-btn-text'><h3>"+menuName+"</h3></span></span></a>");
			} 
		});
	}
	//退出系统
	function fnLoginOut(){
		window.location.href=programName+'';
	}
	
	//单击节点
	function onClick(event, treeId, treeNode, clickFlag) {
		var action = treeNode.action;
		var sitename = '';
		var parenNode = treeNode.getParentNode();
		while(parenNode!=null){
			sitename  = parenNode.menuname+'->'+sitename;
			parenNode = parenNode.getParentNode();
		}
		sitename += treeNode.menuname;
		var statictext = jq('#statictext').val();
		//这里用$是使用EasyUI中的DIV.panel功能
		jq('#center').panel({title:statictext+sitename});
		//如果没有配置菜单，则不触发点击时间到对应的
		if(action==''||action==null||action=='null'){return;}
		//jq('#content').attr("src",programName+'/'+action);
		addTab(treeNode.menuname,programName+'/'+action);
	}
	
	//系统定时刷新时间
	var systime = null;
	var t = null;
    t = setTimeout(time,1000);//开始执行
    function time()
    {
       clearTimeout(t);//清除定时器
       //从服务端取到系统时间
       jq.get(programName+'/menu/menuManager!getSysDate.action', function(datas) {
			systime =datas;
		}); 
       document.getElementById("timeShow").innerHTML =systime; 
       t = setTimeout(time,1000); //设定定时器，循环执行             
    } 
    
    
    
/**
 * 查询表单数据填充
 * @return
 */
function loadData() {
	jq('#clocktables').datagrid( {
		data : getData()
	}).datagrid('clientPaging');
}
/**
 * 获取数据方法
 * @param startTime
 * @param endTime
 * @return
 */
function getData() { 
	var rows = null;
	jq.getJSON(programName + '/clock/clockManager!queryClocks.action', function(re_datas) {
		rows = re_datas;
	});
	return rows;
}
 
var loadTabTools = function(){
	var tabsId = 'mytabs';//tabs页签Id
	var tab_rightmenuId = 'tab_rightmenu';//tabs右键菜单Id
	
	//绑定tabs的右键菜单
	jq("#"+tabsId).tabs({
		onContextMenu:function(e,title){//这时去掉 tabsId所在的div的这个属性：class="easyui-tabs"，否则会加载2次
		  e.preventDefault();
		  jq('#'+tab_rightmenuId).menu('show',{  
			left: e.pageX,  
			top: e.pageY  
		  }).data("tabTitle",title);
		}
	});
	
	//实例化menu的onClick事件
	jq("#"+tab_rightmenuId).menu({
		onClick:function(item){
		  CloseTab(tabsId,tab_rightmenuId,item.name);
		}
	});
}

/**
	tab关闭事件
	@param	tabId		tab组件Id
	@param	tabMenuId	tab组件右键菜单Id
	@param	type		tab组件右键菜单div中的name属性值
*/
function CloseTab(tabId,tabMenuId,type){
	//tab组件对象
	var tabs = jq('#' + tabId);
	//tab组件右键菜单对象
	var tab_menu = jq('#' + tabMenuId);
	
	//获取当前tab的标题
	var curTabTitle = tab_menu.data('tabTitle');
	
	//关闭当前tab
	if(type === 'tab_menu-tabclose'){
		//通过标题关闭tab
		tabs.tabs("close",curTabTitle);
	}
	
	//关闭全部tab
	else if(type === 'tab_menu-tabcloseall'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getAllTabObj(tabs);
		//循环删除要关闭的tab
		jq.each(closeTabsTitle,function(){
			var title = this;
			tabs.tabs('close',title);
		});
	}
	
	//关闭其他tab
	else if(type === 'tab_menu-tabcloseother'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getAllTabObj(tabs);
		//循环删除要关闭的tab
		jq.each(closeTabsTitle,function(){
			var title = this;
			if(title != curTabTitle){
				tabs.tabs('close',title);
			}
		});
	}
	
	//关闭当前左侧tab
	else if(type === 'tab_menu-tabcloseleft'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getLeftToCurrTabObj(tabs,curTabTitle);
		//循环删除要关闭的tab
		jq.each(closeTabsTitle,function(){
			var title = this;
			tabs.tabs('close',title);
		});
	}
	
	//关闭当前右侧tab
	else if(type === 'tab_menu-tabcloseright'){
		//获取所有关闭的tab对象
		var closeTabsTitle = getRightToCurrTabObj(tabs,curTabTitle);
		//循环删除要关闭的tab
		jq.each(closeTabsTitle,function(){
			var title = this;
			tabs.tabs('close',title);
		});
	}
}

/**
	获取所有关闭的tab对象
	@param	tabs	tab组件
*/
function getAllTabObj(tabs){
	//存放所有tab标题
	var closeTabsTitle = [];
	//所有所有tab对象
	var allTabs = tabs.tabs('tabs');
	jq.each(allTabs,function(){
		var tab = this;
		var opt = tab.panel('options');
		//获取标题
		var title = opt.title;
		//是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡
		var closable = opt.closable;
		if(closable){
			closeTabsTitle.push(title);
		}
	});
	return closeTabsTitle;
}

/**
	获取左侧第一个到当前的tab
	@param	tabs		tab组件
	@param	curTabTitle	到当前的tab
*/
function getLeftToCurrTabObj(tabs,curTabTitle){
	//存放所有tab标题
	var closeTabsTitle = [];
	//所有所有tab对象
	var allTabs = tabs.tabs('tabs');
	for(var i=0;i<allTabs.length;i++){
		var tab = allTabs[i];
		var opt = tab.panel('options');
		//获取标题
		var title = opt.title;
		//是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡
		var closable = opt.closable;
		if(closable){
			//alert('title' + title + '  curTabTitle:' + curTabTitle);
			if(title == curTabTitle){
				return closeTabsTitle;
			}
			closeTabsTitle.push(title);
		}
	}
	return closeTabsTitle;
}

/**
	获取当前到右侧最后一个的tab
	@param	tabs		tab组件
	@param	curTabTitle	到当前的tab
*/
function getRightToCurrTabObj(tabs,curTabTitle){
	//存放所有tab标题
	var closeTabsTitle = [];
	//所有所有tab对象
	var allTabs = tabs.tabs('tabs');
	for(var i=(allTabs.length - 1);i >= 0;i--){
		var tab = allTabs[i];
		var opt = tab.panel('options');
		//获取标题
		var title = opt.title;
		//是否可关闭 ture:会显示一个关闭按钮，点击该按钮将关闭选项卡
		var closable = opt.closable;
		if(closable){
			//alert('title' + title + '  curTabTitle:' + curTabTitle);
			if(title == curTabTitle){
				return closeTabsTitle;
			}
			closeTabsTitle.push(title);
		}
	}
	return closeTabsTitle;
}

var onTopic = function(){ 
	loadDiv(this);
}
var hidDiv = function(){
	jq('#commDesc').hide();
}

/**
 * 显示悬浮窗口
 * @param e 列
 * @return
 */
function loadDiv(e){
	jq('#commDesc').html('更新待办，请点击红色区域');
	e = window.event;
	xOffset = -23;
	yOffset = 13;
	jq("#commDesc").css("top",(e.pageY - xOffset) + "px").css("left",(e.pageX + yOffset) + "px").css("position","absolute").css("z-index","9999").show();
}

/**
 * 查询待办事项
 * @return
 */
var queryWait = function(){
	jq('#waitTable').datagrid( {
		data : getWaitData()
	}).datagrid('clientPaging');
	 
}



 
/**
 * 获取数据方法
 * @param startTime
 * @param endTime
 * @return
 */
function getWaitData() { 
	var rows = null;
	jq.getJSON(programName + '/user/userManager!getWorks.action', function(re_datas) {
		rows = re_datas;
		if(rows!=null){
			jq('#topic').html('<span style="font-size : 22px;font: Georgia">'+rows.total+'</span>条待办事项');
		}
	});
	return rows;
}

/**
 * 格式化，增加超链接
 * @param value
 * @param row
 * @param index
 * @return
 */
var fomatterUrl = function(value,row,index){
	if(row.url==null){
		return value;
	}else{
		return "<a onclick=addTab('"+value+"','"+(programName+row.url)+"') href='#'>"+value+"</a>";
	}
}

 












	 
	 
	 